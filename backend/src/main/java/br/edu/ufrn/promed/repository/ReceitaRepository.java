package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.model.Consulta;
import br.edu.ufrn.promed.model.Receita;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class ReceitaRepository {

    public void cadastro(Consulta consulta, Connection connection) {
        String sql = "INSERT INTO receita (data_atual, data_validade, prescricao, Consulta_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for(Receita receita : consulta.getReceitas()) {
                java.sql.Date sqlDataAtual = new java.sql.Date(receita.getDataAtual().getTime());
                ps.setDate(1, sqlDataAtual);
                java.sql.Date sqlDataValidade = new java.sql.Date(receita.getDataValidade().getTime());
                ps.setDate(2, sqlDataValidade);
                ps.setString(3, receita.getPrescricao());
                ps.setInt(4, consulta.getId());
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
