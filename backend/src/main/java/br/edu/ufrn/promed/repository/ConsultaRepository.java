package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Consulta;
import br.edu.ufrn.promed.service.HorarioAtendimentoService;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ConsultaRepository {

    private final DatabaseConnection databaseConnection;

    private final HorarioAtendimentoService horarioAtendimentoService;

    private final ReceitaRepository receitaRepository;

    private final ExameRepository exameRepository;

    public ConsultaRepository(DatabaseConnection databaseConnection, HorarioAtendimentoService horarioAtendimentoService, ReceitaRepository receitaRepository, ExameRepository exameRepository) {
        this.databaseConnection = databaseConnection;
        this.horarioAtendimentoService = horarioAtendimentoService;
        this.receitaRepository = receitaRepository;
        this.exameRepository = exameRepository;
    }

    public void cadastrar(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (resumo, hora_inicio, hora_fim, Horario_atendimento_id) VALUES (?,?,?,?)";

        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, consulta.getResumo());
            ps.setString(2, consulta.getHoraInicio().toString());
            ps.setString(3, consulta.getHoraFim().toString());
            ps.setInt(4, consulta.getHorarioAtendimentoId());
            ps.execute();

            var consultaBuscada = this.buscar(consulta, connection);
            consulta.setId(consultaBuscada.getId());

            if(!consulta.getExames().isEmpty()) {
                exameRepository.cadastro(consulta, connection);
            }

            if(!consulta.getReceitas().isEmpty()) {
                receitaRepository.cadastro(consulta, connection);
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.commit();
            databaseConnection.closeConnection();
        }
    }

    public Consulta buscar(Consulta consulta, Connection connection) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE resumo = ? AND hora_inicio = ? AND hora_fim = ? AND Horario_atendimento_id = ?";

        Consulta resultado = new Consulta();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, consulta.getResumo());
            ps.setString(2, consulta.getHoraInicio().toString());
            ps.setString(3, consulta.getHoraFim().toString());
            ps.setInt(4, consulta.getHorarioAtendimentoId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultado.setResumo(rs.getString("resumo"));
                resultado.setId(rs.getInt("id"));
                resultado.setHoraInicio(rs.getTime("hora_inicio"));
                resultado.setHoraFim(rs.getTime("hora_fim"));
                resultado.setHorarioAtendimentoId(rs.getInt("Horario_atendimento_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
