package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Especialidade;
import br.edu.ufrn.promed.model.Medico;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class EspecialidadeRepository {

    private final DatabaseConnection databaseConnection;

    public EspecialidadeRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastrarEspecialidade(Medico medico) {
        String sql = "INSERT INTO medico_has_especialidade (Medico_num_crm, Medico_cpf, Medico_uf_crm, Especialidade_id) VALUES (?, ?, ?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Especialidade especialidade : medico.getEspecialidades()) {
                ps.setString(1, String.valueOf(medico.getNumCrm()));
                ps.setString(2, String.valueOf(medico.getPessoa().getCpf()));
                ps.setString(3, String.valueOf(medico.getUfCrm()));
                ps.setString(4, String.valueOf(especialidade.getId()));
                ps.executeUpdate();
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
