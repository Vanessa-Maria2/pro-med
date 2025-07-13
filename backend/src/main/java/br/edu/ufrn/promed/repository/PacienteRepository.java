package br.edu.ufrn.promed.repository;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.model.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PacienteRepository {

    private final DatabaseConnection databaseConnection;

    private final PessoaRepository pessoaRepository;

    private final AlergiaRepository alergiaRepository;

    private final DoencaRepository doencaRepository;

    public PacienteRepository(DatabaseConnection databaseConnection, PessoaRepository pessoaRepository, AlergiaRepository alergiaRepository, DoencaRepository doencaRepository) {
        this.databaseConnection = databaseConnection;
        this.pessoaRepository = pessoaRepository;
        this.alergiaRepository = alergiaRepository;
        this.doencaRepository = doencaRepository;
    }

    public void cadastrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (historico_familia_doencas, altura, peso, tipo_sanguineo, rh_sanguineo, cpf) VALUES (?, ?, ?, ?, ?, ?)";

        pessoaRepository.cadastro(paciente.getPessoa());

        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, paciente.getHistoricoFamiliaDoencas());
            ps.setString(2, String.valueOf(paciente.getAltura()));
            ps.setString(3, String.valueOf(paciente.getPeso()));
            ps.setString(4, String.valueOf(paciente.getTipoSanguineo()));
            ps.setString(5, String.valueOf(paciente.getRhSanguineo()));
            ps.setString(6, paciente.getPessoa().getCpf());
            ps.executeUpdate();
            databaseConnection.closeConnection();

            if(!paciente.getAlergias().isEmpty())
                alergiaRepository.alergia_has_paciente(paciente);

            if(!paciente.getDoencas().isEmpty())
                doencaRepository.paciente_has_doenca(paciente);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar paciente");
        }
    }
}
