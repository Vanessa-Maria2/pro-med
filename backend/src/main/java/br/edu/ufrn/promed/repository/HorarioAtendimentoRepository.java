package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.dto.response.HorarioAtendimentoResponseDto;
import br.edu.ufrn.promed.enums.StatusHorarioAtendimento;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HorarioAtendimentoRepository {

    private DatabaseConnection databaseConnection;

    public HorarioAtendimentoRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<HorarioAtendimentoResponseDto> buscarTodas() {
        List<HorarioAtendimentoResponseDto> horarioAtendimentos = new ArrayList<>();

        String sql = "SELECT nome, sobrenome, status, horario, data_disponivel, id, Medico_cpf, Medico_num_crm, Medico_uf_crm FROM promed.horario_atendimento as ha \n" +
                "join medico as m on ha.Medico_cpf = m.cpf\n" +
                "join pessoa as p on m.cpf = p.cpf\n" +
                "where status = ?";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(StatusHorarioAtendimento.DISPONIVEL));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HorarioAtendimentoResponseDto horarioAtendimento = new HorarioAtendimentoResponseDto();
                horarioAtendimento.setId(rs.getInt("id"));
                horarioAtendimento.setData(rs.getDate("data_disponivel"));
                horarioAtendimento.setHorario(rs.getString("horario"));
                horarioAtendimento.setStatus(rs.getString("status"));
                horarioAtendimento.setCpfMedico(rs.getString("Medico_cpf"));
                horarioAtendimento.setNumCrmMedico(rs.getInt("Medico_num_crm"));
                horarioAtendimento.setUfCrmMedico(rs.getString("Medico_uf_crm"));
                horarioAtendimento.setNomeMedico(rs.getString("nome"));
                horarioAtendimento.setSobrenomeMedico(rs.getString("sobrenome"));
                horarioAtendimentos.add(horarioAtendimento);
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar horários de atendimentos disponíveis.");
        }
        return horarioAtendimentos;
    }

    public List<HorarioAtendimentoResponseDto> buscarPorMedico(String Medico_cpf) {
        List<HorarioAtendimentoResponseDto> horarioAtendimentos = new ArrayList<>();

        String sql = "SELECT h.id, h.status, h.horario, h.data_disponivel, h.Medico_num_crm, h.Medico_cpf," +
                " h.Medico_uf_crm, h.Recorrencia_id, h.Recorrencia_GrupoRecorrencia_id, p.nome, p.sobrenome " +
                "FROM horario_atendimento h " +
                "JOIN medico m ON h.medico_cpf = m.cpf " +
                "JOIN pessoa p ON m.cpf = p.cpf " +
                "WHERE m.cpf = ?";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, Medico_cpf);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HorarioAtendimentoResponseDto horarioAtendimento = new HorarioAtendimentoResponseDto();

                horarioAtendimento.setId(rs.getInt("id"));
                horarioAtendimento.setStatus(rs.getString("status"));
                horarioAtendimento.setHorario(rs.getString("horario"));
                horarioAtendimento.setData(rs.getDate("data_disponivel"));
                horarioAtendimento.setNumCrmMedico(rs.getInt("Medico_num_crm"));
                horarioAtendimento.setCpfMedico(rs.getString("Medico_cpf"));
                horarioAtendimento.setUfCrmMedico(rs.getString("Medico_uf_crm"));
                horarioAtendimento.setIdRecorrencia(rs.getInt("Recorrencia_id"));
                horarioAtendimento.setIdGrupoRecorrencia(rs.getInt("Recorrencia_GrupoRecorrencia_id"));
                horarioAtendimento.setNomeMedico(rs.getString("nome"));
                horarioAtendimento.setSobrenomeMedico(rs.getString("sobrenome"));
                horarioAtendimentos.add(horarioAtendimento);
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar horários atendimentos por médico");
        }

        return horarioAtendimentos;
    }


    public boolean isHorarioAtendimentoDisponivel(int horarioAtendimentoId) {
        String sql = "SELECT COUNT(*) FROM horario_atendimento WHERE id = ? AND status = ?";
      
       try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, horarioAtendimentoId);
            ps.setString(2, StatusHorarioAtendimento.DISPONIVEL.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void ocuparHorario(int horarioAtendimentoId, Connection connection) {
        String sql = "UPDATE horario_atendimento SET status = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, StatusHorarioAtendimento.AGENDADO.toString());
            ps.setInt(2, horarioAtendimentoId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
