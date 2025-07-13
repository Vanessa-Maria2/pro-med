package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.GrupoRecorrencia;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GrupoRecorrenciaRepository {

    private final DatabaseConnection databaseConnection;

    public GrupoRecorrenciaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public GrupoRecorrencia cadastro(Connection connection, GrupoRecorrencia grupoRecorrencia) {
        String sql = "INSERT INTO gruporecorrencia (id, descricao) VALUES (?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(grupoRecorrencia.getId()));
            ps.setString(2, grupoRecorrencia.getDescricao());
            ps.executeUpdate();
            return buscarPorId(connection, grupoRecorrencia.getDescricao());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GrupoRecorrencia buscarPorId(Connection connection, String descricao) {
        String sql = "SELECT * FROM gruporecorrencia WHERE descricao = ?";
        GrupoRecorrencia grupoRecorrencia = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, descricao);
            ResultSet rs = ps.executeQuery();
            grupoRecorrencia = new GrupoRecorrencia();
            while (rs.next()) {
                grupoRecorrencia.setId(rs.getInt("id"));
                grupoRecorrencia.setDescricao(rs.getString("descricao"));
            }
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grupoRecorrencia;
    }
}
