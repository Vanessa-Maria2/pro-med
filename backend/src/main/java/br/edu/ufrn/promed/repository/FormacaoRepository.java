package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Formacao;
import br.edu.ufrn.promed.model.Medico;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class FormacaoRepository {

    private final DatabaseConnection databaseConnection;

    public FormacaoRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void cadastro(Medico medico) {
        String sql = "INSERT INTO formacao (id, descricao, Medico_num_crm, Medico_cpf, Medico_ue_crm) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Formacao formacao : medico.getFormacoes()) {
                ps.setInt(1, formacao.getId());
                ps.setString(2, formacao.getDescricao());
                ps.setString(3, String.valueOf(medico.getNumCrm()));
                ps.setString(4, String.valueOf(medico.getPessoa().getCpf()));
                ps.setString(5, String.valueOf(medico.getUfCrm()));
                ps.executeUpdate();
            }
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar formação");
        }
    }
}
