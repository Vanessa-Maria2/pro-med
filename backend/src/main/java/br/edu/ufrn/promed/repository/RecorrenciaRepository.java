package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.dto.request.RecorrenciaRequestDto;
import br.edu.ufrn.promed.enums.DiaSemana;
import br.edu.ufrn.promed.model.Recorrencia;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RecorrenciaRepository {

    private final DatabaseConnection databaseConnection;

    private final GrupoRecorrenciaRepository grupoRecorrenciaRepository;

    private final DiaSemanaRepository diaSemanaRepository;

    public RecorrenciaRepository(DatabaseConnection databaseConnection, GrupoRecorrenciaRepository grupoRecorrenciaRepository, DiaSemanaRepository diaSemanaRepository) {
        this.databaseConnection = databaseConnection;
        this.grupoRecorrenciaRepository = grupoRecorrenciaRepository;
        this.diaSemanaRepository = diaSemanaRepository;
    }

    public void cadastro(RecorrenciaRequestDto recorrenciaRequestDto) throws SQLException {
        String sql = "INSERT INTO Recorrencia (id, hora_inicio, hora_fim, inicio_dia, dia_fim, GrupoRecorrencia_id) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;

        try {
            connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);
            var grupoRecorrencia  = grupoRecorrenciaRepository.cadastro(connection, recorrenciaRequestDto.getGrupoRecorrencia());
            recorrenciaRequestDto.setGrupoRecorrencia(grupoRecorrencia);

            PreparedStatement ps = connection.prepareStatement(sql);

            for(Recorrencia recorrencia: recorrenciaRequestDto.getRecorrencias()) {
                ps.setInt(1, recorrencia.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String horaInicio = sdf.format(recorrencia.getHora_inicio());
                String horaFim = sdf.format(recorrencia.getHora_fim());
                ps.setString(2, horaInicio);
                ps.setString(3, horaFim);
                Date dataInicio = recorrencia.getData_inicio();
                Date datai = new java.sql.Date(dataInicio.getTime());
                ps.setDate(4, datai);
                Date dataFim = recorrencia.getData_fim();
                Date dataf = new java.sql.Date(dataFim.getTime());
                ps.setDate(5, dataf);
                ps.setString(6, String.valueOf(grupoRecorrencia.getId()));
                ps.executeUpdate();

                var recorrenciaSalva = buscar(connection, recorrencia, grupoRecorrencia.getId());
                recorrencia.setId(recorrenciaSalva.getId());

                diaSemanaRepository.cadastro(recorrenciaSalva.getId(), grupoRecorrencia.getId(), recorrencia.getDiaSemanas(), connection);
                geraEventoRecorrente(connection, recorrencia, recorrenciaRequestDto);
            }
        } catch (Exception e) {
            connection.rollback();
            throw new IllegalArgumentException("Erro ao cadastrar recorrência para horário de atendimento");
        } finally {
            connection.commit();
            databaseConnection.closeConnection();
        }
    }

    public void geraEventoRecorrente(Connection connection, Recorrencia recorrencia, RecorrenciaRequestDto recorrenciaRequestDto) throws SQLException {
        String sql = "INSERT INTO horario_atendimento (status, horario, data_disponivel, Paciente_cpf, Medico_num_crm, Medico_cpf, Medico_uf_crm, Recorrencia_id, Recorrencia_GrupoRecorrencia_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        List<LocalDate> datas = gerarDatas(recorrencia.getData_inicio().toLocalDate(), recorrencia.getData_fim().toLocalDate(), recorrencia.getDiaSemanas());

        for(LocalDate data: datas) {
            boolean existe = isHorarioOcupado(connection, recorrencia, recorrenciaRequestDto, data);
            if(existe) {
                throw new IllegalArgumentException("Horário de atendimento não está disponível") ;
            }
            preparedStatement.setString(1, "DISPONIVEL");
            Time horaInicioDate = recorrencia.getHora_inicio();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String horaStr = sdf.format(horaInicioDate);
            preparedStatement.setString(2, horaStr);
            preparedStatement.setString(3, data.toString());
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, String.valueOf(recorrenciaRequestDto.getNumCrmMedico()));
            preparedStatement.setString(6, recorrenciaRequestDto.getCpfMedico());
            preparedStatement.setString(7, recorrenciaRequestDto.getUfCrmMedico());
            preparedStatement.setString(8, String.valueOf(recorrencia.getId()));
            preparedStatement.setString(9, String.valueOf(recorrenciaRequestDto.getGrupoRecorrencia().getId()));
            preparedStatement.executeUpdate();
        }
    }

    public Recorrencia buscar(Connection connection, Recorrencia recorrencia, int idGrupoRecorrencia) {
        String sql = "SELECT * FROM Recorrencia WHERE hora_inicio = ? AND hora_fim = ? AND inicio_dia = ? AND dia_fim = ? AND GrupoRecorrencia_id = ?";

        Recorrencia recorrenciaBuscado = null;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            Time horaInicioDate = recorrencia.getHora_inicio();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String horaInicio = sdf.format(horaInicioDate);
            Time horaFimDate = recorrencia.getHora_fim();
            String horaFim = sdf.format(horaFimDate);
            ps.setString(1, horaInicio);
            ps.setString(2, horaFim);
            Date dataInicio = recorrencia.getData_inicio();
            Date dataFim = recorrencia.getData_fim();
            ps.setDate(3, dataInicio);
            Date dataFimDate = recorrencia.getData_fim();
            ps.setDate(4, dataFim);
            ps.setString(5, String.valueOf(idGrupoRecorrencia));

            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                recorrenciaBuscado = new Recorrencia();
                recorrenciaBuscado.setId(resultSet.getInt("id"));
                recorrenciaBuscado.setData_inicio(resultSet.getDate("inicio_dia"));
                recorrenciaBuscado.setData_fim(resultSet.getDate("dia_fim"));
                recorrenciaBuscado.setHora_inicio(resultSet.getTime("hora_inicio"));
                recorrenciaBuscado.setHora_fim(resultSet.getTime("hora_fim"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recorrenciaBuscado;
    }

    public List<LocalDate> gerarDatas(LocalDate dataInicio, LocalDate dataFim, List<DiaSemana> diaSemanas) {
        List<LocalDate> datas = new ArrayList<>();
        LocalDate dataAtual = dataInicio;

        Set<DayOfWeek> diasPermitidos = diaSemanas.stream()
                .map(DiaSemana::getDiaJava)
                .collect(Collectors.toSet());

        while (!dataAtual.isAfter(dataFim)) {
            if (diasPermitidos.contains(dataAtual.getDayOfWeek())) {
                datas.add(dataAtual);
            }
            dataAtual = dataAtual.plusDays(1);
        }

        return datas;
    }

    public boolean isHorarioOcupado(Connection connection, Recorrencia recorrencia, RecorrenciaRequestDto recorrenciaRequestDto, LocalDate data) {
        String sql = "SELECT * FROM horario_atendimento WHERE horario = ? AND data_disponivel = ? AND Medico_num_crm = ? AND Medico_cpf = ? AND Medico_uf_crm = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, recorrencia.getHora_inicio().toString());
            ps.setString(2, data.toString());
            ps.setString(3, String.valueOf(recorrenciaRequestDto.getNumCrmMedico()));
            ps.setString(4, recorrenciaRequestDto.getCpfMedico());
            ps.setString(5, recorrenciaRequestDto.getUfCrmMedico());
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
