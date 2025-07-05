package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Pessoa;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class GerenteRepository {

    private final DatabaseConnection databaseConnection;

    public GerenteRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastro(Pessoa pessoa) {
        String sql = "INSERT INTO gerente (cpf) VALUES (?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getCpf());
            ps.execute();
            databaseConnection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
