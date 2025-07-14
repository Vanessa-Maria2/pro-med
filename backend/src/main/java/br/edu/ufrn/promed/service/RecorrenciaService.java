package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.dto.request.RecorrenciaRequestDto;
import br.edu.ufrn.promed.enums.DiaSemana;
import br.edu.ufrn.promed.model.Recorrencia;
import br.edu.ufrn.promed.repository.GrupoRecorrenciaRepository;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import br.edu.ufrn.promed.repository.RecorrenciaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecorrenciaService {

    private final RecorrenciaRepository recorrenciaRepository;
    private final HorarioAtendimentoRepository horarioAtendimentoRepository;
    private final DatabaseConnection databaseConnection;
    private final GrupoRecorrenciaRepository grupoRecorrenciaRepository;

    public RecorrenciaService(RecorrenciaRepository recorrenciaRepository, HorarioAtendimentoRepository horarioAtendimentoRepository, DatabaseConnection databaseConnection, GrupoRecorrenciaRepository grupoRecorrenciaRepository) {
        this.recorrenciaRepository = recorrenciaRepository;
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
        this.databaseConnection = databaseConnection;
        this.grupoRecorrenciaRepository = grupoRecorrenciaRepository;
    }

    public RecorrenciaRequestDto cadastrar(RecorrenciaRequestDto recorrenciaRequestDto) throws SQLException {
        this.recorrenciaRepository.cadastro(recorrenciaRequestDto);
        return recorrenciaRequestDto;
    }
    
    public List<LocalDate> gerarDatas(Recorrencia recorrencia) {
        List<LocalDate> data = new ArrayList<>();
        data = this.recorrenciaRepository.gerarDatas(recorrencia.getData_inicio().toLocalDate(), recorrencia.getData_fim().toLocalDate(), recorrencia.getDiaSemanas());
        return data;
    }
    public void atualizarRecorrencia(int recorrenciaId, String medicoCpf, RecorrenciaRequestDto dto) {
        Connection connection = null;

        try {
            connection = databaseConnection.getConnection();
            connection.setAutoCommit(false); // Inicia a transação


            if (horarioAtendimentoRepository.existeHorarioOcupadoNaRecorrencia(connection, recorrenciaId)) {
                throw new IllegalStateException("Não é possível alterar uma recorrência que já possui horários agendados.");
            }

            horarioAtendimentoRepository.cancelarHorariosDoMedicoPorRecorrencia(connection, recorrenciaId, medicoCpf);

            Recorrencia recorrenciaParaAtualizar = dto.getRecorrencias().get(0);
            recorrenciaParaAtualizar.setId(recorrenciaId);
            recorrenciaRepository.atualizar(connection, recorrenciaParaAtualizar);
            grupoRecorrenciaRepository.atualizar(connection, dto.getGrupoRecorrencia());

            List<LocalDate> datasParaGerar = gerarDatas(
                    new java.sql.Date(recorrenciaParaAtualizar.getData_inicio().getTime()).toLocalDate(),
                    new java.sql.Date(recorrenciaParaAtualizar.getData_fim().getTime()).toLocalDate(),
                    recorrenciaParaAtualizar.getDiaSemanas()
            );

            for (LocalDate data : datasParaGerar) {
                horarioAtendimentoRepository.inserirRecorrencia(
                        connection,
                        "DISPONIVEL",
                        recorrenciaParaAtualizar.getHora_inicio(),
                        data,
                        dto.getNumCrmMedico(),
                        dto.getCpfMedico(),
                        dto.getUfCrmMedico(),
                        recorrenciaParaAtualizar.getId(),
                        dto.getGrupoRecorrencia().getId()
                );
            }

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try { connection.rollback(); } catch (SQLException ignore) {}
            }
            throw new RuntimeException("Falha ao atualizar a recorrência. A operação foi revertida.", e);

        } finally {
            if (connection != null) {
                try { connection.close(); } catch (SQLException ignore) {}
            }
        }
    }
    

    private List<LocalDate> gerarDatas(LocalDate dataInicio, LocalDate dataFim, List<DiaSemana> diaSemanas) {
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
}
