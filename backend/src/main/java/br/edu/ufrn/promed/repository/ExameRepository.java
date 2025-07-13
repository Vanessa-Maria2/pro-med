package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.model.Consulta;
import br.edu.ufrn.promed.model.Exame;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExameRepository {

    private final ImagemRepository imagemRepository;

    public ExameRepository(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    public void cadastro(Consulta consulta, Connection connection) {
        String sql = "INSERT INTO exame (descricao, tipo, laudo, Consulta_id) VALUES (?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Exame exame : consulta.getExames()) {
                ps.setString(1, exame.getDescricao());
                ps.setString(2, exame.getTipo());
                ps.setString(3, exame.getLaudo());
                ps.setInt(4, consulta.getId());
                ps.execute();

                var exameSalvo = this.buscar(exame, consulta.getId(), connection);
                exame.setId(exameSalvo.getId());

                this.imagemRepository.cadastro(exame, connection);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Exame buscar(Exame exame, int consultaId, Connection connection) {
        String sql = "SELECT * FROM exame WHERE descricao = ? AND tipo = ? AND laudo = ? AND Consulta_id = ?";

        Exame retorno = new Exame();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, exame.getDescricao());
            ps.setString(2, exame.getTipo());
            ps.setString(3, exame.getLaudo());
            ps.setInt(4, consultaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                retorno.setId(rs.getInt("id"));
                retorno.setDescricao(rs.getString("descricao"));
                retorno.setTipo(rs.getString("tipo"));
                retorno.setLaudo(rs.getString("laudo"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }
}
