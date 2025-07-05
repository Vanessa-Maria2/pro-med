package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Pessoa;
import br.edu.ufrn.promed.util.PasswordUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class PessoaRepository {

    private final DatabaseConnection databaseConnection;
    private final PasswordUtil passwordUtil;

    public PessoaRepository(DatabaseConnection databaseConnection, PasswordUtil passwordUtil) {
        this.databaseConnection = databaseConnection;
        this.passwordUtil = passwordUtil;
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
            ps.setString(7, passwordUtil.hashPassword(pessoa.getSenha()));
            ps.setString(8, "1");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Pessoa> findByEmail(String email) {
        String sql = "SELECT cpf, nome, sobrenome, email, endereco, data_nascimento, senha FROM pessoa WHERE email = ?";
        Pessoa pessoa = null;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setSobrenome(rs.getString("sobrenome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));
                pessoa.setSenha(rs.getString("senha"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(pessoa);
    }
}
