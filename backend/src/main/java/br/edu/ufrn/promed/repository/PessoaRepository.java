package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Pessoa;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PessoaRepository {

    private final DatabaseConnection databaseConnection;

    public PessoaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    public void cadastro(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (cpf, nome, sobrenome, email, endereco, data_nascimento, senha, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getSobrenome());
            ps.setString(4, pessoa.getEmail());
            ps.setString(5, pessoa.getEndereco());
            java.util.Date data = pessoa.getDataNascimento();
            Date dataSql = new java.sql.Date(data.getTime());
            ps.setDate(6, dataSql);
            ps.setString(7, pessoa.getSenha());
            ps.setString(8, "1");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
