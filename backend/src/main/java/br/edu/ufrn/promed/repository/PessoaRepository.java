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
    private final TelefoneRepository telefoneRepository;
    private final GerenteRepository gerenteRepository;
    private final RecepcionistaRepository recepcionistaRepository;

    public PessoaRepository(DatabaseConnection databaseConnection, PasswordUtil passwordUtil, TelefoneRepository telefoneRepository, GerenteRepository gerenteRepository, RecepcionistaRepository recepcionistaRepository) {
        this.databaseConnection = databaseConnection;
        this.passwordUtil = passwordUtil;
        this.telefoneRepository = telefoneRepository;
        this.gerenteRepository = gerenteRepository;
        this.recepcionistaRepository = recepcionistaRepository;
    }

    public void cadastro(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (cpf, nome, sobrenome, email, endereco, data_nascimento, senha, ativo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (existePessoa(pessoa)) {
            throw new RuntimeException("Já existe pessoa cadastrada com este email ou cpf");
        }
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
            ps.setString(9, pessoa.getTipo());
            ps.executeUpdate();

            databaseConnection.closeConnection();

            this.telefoneRepository.cadastroTelefone(pessoa);

            if (pessoa.getTipo().equals("recepcionista"))
                this.recepcionistaRepository.cadastro(pessoa);

            if(pessoa.getTipo().equals("gerente"))
                this.gerenteRepository.cadastro(pessoa);

        } catch (SQLException e) {
            throw new RuntimeException("Erro cadastrar pessoa");
        }
    }

    public Optional<Pessoa> findByEmail(String email) {
        String sql = "SELECT cpf, nome, sobrenome, email, endereco, data_nascimento, senha, tipo FROM pessoa WHERE email = ?";
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
                pessoa.setTipo(rs.getString("tipo"));
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(pessoa);
    }

    public void ativar(String email) {
        String sql = "UPDATE pessoa SET ativo = ? WHERE email = ?";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "1");
            ps.setString(2, email);
            ps.executeUpdate();
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void inativar(String cpf) {
        String sql = "UPDATE pessoa SET ativo = ? WHERE cpf = ?";

        try (
                Connection connection = databaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            connection.setAutoCommit(true);
            ps.setBoolean(1, false);
            ps.setString(2, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existePessoa(Pessoa pessoa) {
        String sql = "SELECT COUNT(*) FROM pessoa WHERE email = ?  OR cpf = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoa.getEmail());
            ps.setString(2, pessoa.getCpf());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void alterarSenha(String email, String novoHash) {
        String sql = "UPDATE pessoa SET senha = ? WHERE email = ?";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, novoHash);
            ps.setString(2, email);

            int linhasAfetadas = ps.executeUpdate();
            if(linhasAfetadas == 0) {
                throw new SQLException("Erro ao alterar senha, usuário com email " + email + " não encontrado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar senha", e);
        }
    }
}
