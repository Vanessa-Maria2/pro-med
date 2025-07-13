package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.enums.DiaSemana;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class DiaSemanaRepository {


    public void cadastro(int idRecorrencia, int idGrupoRecorrencia, List<DiaSemana> diaSemanas, Connection connection) {
        String sql = "INSERT INTO recorrencia_has_diasemana (Recorrencia_id, Recorrencia_GrupoRecorrencia_id, DiaSemana_id) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            for(DiaSemana diaSemana : diaSemanas) {
                ps.setString(1, String.valueOf(idRecorrencia));
                ps.setString(2, String.valueOf(idGrupoRecorrencia));
                ps.setString(3, String.valueOf(diaSemana.getId()));
                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
