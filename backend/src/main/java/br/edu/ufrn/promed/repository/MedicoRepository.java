package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Medico;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class MedicoRepository {

    private final DatabaseConnection databaseConnection;

    private final PessoaRepository pessoaRepository;

    private final FormacaoRepository formacaoRepository;

    private final EspecialidadeRepository especialidadeRepository;

    public MedicoRepository(DatabaseConnection databaseConnection, PessoaRepository pessoaRepository, FormacaoRepository formacaoRepository, EspecialidadeRepository especialidadeRepository) {
        this.databaseConnection = databaseConnection;
        this.pessoaRepository = pessoaRepository;
        this.formacaoRepository = formacaoRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public void cadastro(Medico medico) {
        String sql = "INSERT INTO medico (num_crm, uf_crm, cpf) VALUES (?, ?, ?)";

        pessoaRepository.cadastro(medico.getPessoa());

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(medico.getNumCrm()));
            ps.setString(2, String.valueOf(medico.getUfCrm()));
            ps.setString(3, medico.getPessoa().getCpf());
            ps.executeUpdate();
            databaseConnection.closeConnection();

            if(!medico.getFormacoes().isEmpty())
                formacaoRepository.cadastro(medico);

            if(!medico.getEspecialidades().isEmpty())
                especialidadeRepository.cadastrarEspecialidade(medico);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
