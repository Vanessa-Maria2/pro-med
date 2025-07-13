package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.model.Telefone;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TelefoneRepository {

    private final DatabaseConnection databaseConnection;

    public TelefoneRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastroTelefone(Pessoa pessoa) {
        String sql = "INSERT INTO telefone (telefone, Pessoa_cpf) VALUES (?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            for (Telefone telefone : pessoa.getTelefones()) {
                 ps.setString(1, telefone.getDescricao());
                 ps.setString(2, pessoa.getCpf());
                 ps.execute();
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
