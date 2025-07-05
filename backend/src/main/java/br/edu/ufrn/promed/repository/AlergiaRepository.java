package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Alergia;
import br.edu.ufrn.promed.model.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class AlergiaRepository {

    private final DatabaseConnection databaseConnection;

    public AlergiaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastrarAlergia(Alergia alergia) {
        String sql = "INSERT INTO alergia (descricao) VALUES (?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, alergia.getDescricao());
            ps.executeUpdate();
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alergia_has_paciente(Paciente paciente) {
        String sql = "INSERT INTO alergia_has_paciente (Alergia_id, Paciente_Pessoa_cpf) VALUES (?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            for (Alergia alergia : paciente.getAlergias()) {
                ps.setString(1, String.valueOf(alergia.getId()));
                ps.setString(2, paciente.getPessoa().getCpf());
                ps.executeUpdate();
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}