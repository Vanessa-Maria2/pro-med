package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.dto.response.MedicoResponseDto;
import br.edu.ufrn.promed.mapper.MedicoMapper;
import br.edu.ufrn.promed.model.Medico;
import br.edu.ufrn.promed.model.Pessoa;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@Repository
public class MedicoRepository {

    private final DatabaseConnection databaseConnection;

    private final PessoaRepository pessoaRepository;

    private final FormacaoRepository formacaoRepository;

    private final EspecialidadeRepository especialidadeRepository;

    private final MedicoMapper medicoMapper;

    public MedicoRepository(DatabaseConnection databaseConnection, PessoaRepository pessoaRepository, FormacaoRepository formacaoRepository, EspecialidadeRepository especialidadeRepository, MedicoMapper medicoMapper) {
        this.databaseConnection = databaseConnection;
        this.pessoaRepository = pessoaRepository;
        this.formacaoRepository = formacaoRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.medicoMapper = medicoMapper;
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

            if (!medico.getFormacoes().isEmpty())
                formacaoRepository.cadastro(medico);

            if (!medico.getEspecialidades().isEmpty())
                especialidadeRepository.cadastrarEspecialidade(medico);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MedicoResponseDto> buscarTodos() {
        List<MedicoResponseDto> medicosDtoList = new ArrayList<>();
        String sql = "SELECT m.num_crm, m.uf_crm, p.cpf, p.nome, " +
                "p.sobrenome, p.email, p.endereco, p.data_nascimento " +
                "FROM medico m JOIN pessoa p ON m.cpf = p.cpf";

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setSobrenome(rs.getString("sobrenome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setDataNascimento(rs.getDate("data_nascimento"));

                Medico medico = new Medico();
                medico.setNumCrm(rs.getInt("num_crm"));
                medico.setUfCrm(rs.getString("uf_crm"));
                medico.setPessoa(pessoa);

                MedicoResponseDto dto = medicoMapper.toMedicoResponseDto(medico);
                medicosDtoList.add(dto);
            }
            databaseConnection.closeConnection();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return medicosDtoList;
    }
}
