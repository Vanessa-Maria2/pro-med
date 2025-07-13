package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.dto.response.DoencaResponseDto;
import br.edu.ufrn.promed.model.Doenca;
import br.edu.ufrn.promed.model.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoencaRepository {

    private final DatabaseConnection databaseConnection;

    public DoencaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastrarDoenca(Doenca doenca) {
        String sql = "INSERT INTO doenca (cid, descricao) VALUES (?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(doenca.getCid()));
            ps.setString(2, doenca.getDescricao());
            ps.executeUpdate();
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void paciente_has_doenca(Paciente paciente) {
        String sql = "INSERT INTO paciente_has_doenca (Paciente_Pessoa_cpf, Doenca_cid) VALUES (?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            for (Doenca doenca : paciente.getDoencas()) {
                ps.setString(1, paciente.getPessoa().getCpf());
                ps.setString(2,  String.valueOf(doenca.getCid()));
                ps.executeUpdate();
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DoencaResponseDto> buscarTodos() {
        String sql = "SELECT * FROM doenca";

        List<DoencaResponseDto> doencas = new ArrayList<>();

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                DoencaResponseDto doenca = new DoencaResponseDto();
                doenca.setCid(rs.getString("cid"));
                doenca.setDescricao(rs.getString("descricao"));
                doencas.add(doenca);
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doencas;
    }

}
