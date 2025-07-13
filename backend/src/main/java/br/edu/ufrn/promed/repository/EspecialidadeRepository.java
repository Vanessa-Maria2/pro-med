package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Especialidade;
import br.edu.ufrn.promed.model.Medico;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EspecialidadeRepository {

    private final DatabaseConnection databaseConnection;

    public EspecialidadeRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastrarEspecialidade(Medico medico) {
        String sql = "INSERT INTO medico_has_especialidade (Medico_num_crm, Medico_cpf, Medico_uf_crm, Especialidade_id) VALUES (?, ?, ?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Especialidade especialidade : medico.getEspecialidades()) {
                ps.setString(1, String.valueOf(medico.getNumCrm()));
                ps.setString(2, String.valueOf(medico.getPessoa().getCpf()));
                ps.setString(3, String.valueOf(medico.getUfCrm()));
                ps.setString(4, String.valueOf(especialidade.getId()));
                ps.executeUpdate();
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Especialidade> listarTodasEspecialidades() {
        List<Especialidade> especialidades = new ArrayList<>();
        String sql = "select id, descricao from especialidade";

        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Especialidade especialidade = new Especialidade();

                especialidade.setId(rs.getInt("id"));
                especialidade.setDescricao(rs.getString("descricao"));
                especialidades.add(especialidade);
            }
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return especialidades;
    }
}
