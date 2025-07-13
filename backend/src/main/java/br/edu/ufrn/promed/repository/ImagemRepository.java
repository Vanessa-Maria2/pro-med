package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.model.Exame;
import br.edu.ufrn.promed.model.Imagem;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class ImagemRepository {

    public void cadastro(Exame exame, Connection conexao) {
        String sql = "INSERT INTO imagens (id, path, Exame_id) VALUES (?,?,?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            for(Imagem imagem: exame.getImagens()) {
                ps.setInt(1, imagem.getId());
                ps.setString(2, imagem.getPath());
                ps.setInt(3, exame.getId());
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
