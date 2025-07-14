package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.config.DatabaseConnection;
import br.edu.ufrn.promed.mapper.MedicoMapper;
import br.edu.ufrn.promed.dto.request.MedicoRequestDto;
import br.edu.ufrn.promed.dto.response.MedicoResponseDto;
import br.edu.ufrn.promed.repository.GrupoRecorrenciaRepository;
import br.edu.ufrn.promed.repository.HorarioAtendimentoRepository;
import br.edu.ufrn.promed.repository.MedicoRepository;
import br.edu.ufrn.promed.repository.RecorrenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;
    private final HorarioAtendimentoRepository horarioAtendimentoRepository;
    private final RecorrenciaRepository recorrenciaRepository;
    private final GrupoRecorrenciaRepository grupoRecorrenciaRepository;
    private final DatabaseConnection databaseConnection;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper, HorarioAtendimentoRepository horarioAtendimentoRepository, RecorrenciaRepository recorrenciaRepository, GrupoRecorrenciaRepository grupoRecorrenciaRepository, DatabaseConnection databaseConnection) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
        this.recorrenciaRepository = recorrenciaRepository;
        this.grupoRecorrenciaRepository = grupoRecorrenciaRepository;
        this.databaseConnection = databaseConnection;
    }

    public MedicoResponseDto cadastro(MedicoRequestDto medicoRequestDto) {
        var medico = medicoMapper.toMedico(medicoRequestDto);
        medicoRepository.cadastro(medico);
        return medicoMapper.toMedicoResponseDto(medico);
    }

    public List<MedicoResponseDto> buscarTodos() {
        List<MedicoResponseDto> medicosDtoList = medicoRepository.buscarTodos();
        return medicosDtoList;
    }

    public void cancelarHorarioAtendimento(int medicoCrm, int horarioAtendimentoId) {
        int linhasAfetadas = horarioAtendimentoRepository.cancelarHorarioAtendimento(horarioAtendimentoId, medicoCrm);

        if  (linhasAfetadas == 0) {
            throw new NoSuchElementException("Horário não existe ou já cadastrado ");
        }

    }

    public void cancelarHorarioRecorrencia(int horarioId, int recorrenciaId) {
        int linhasAfetadas = horarioAtendimentoRepository.cancelarHorarioPorRecorrencia(horarioId, recorrenciaId);

        if (linhasAfetadas == 0) {
            throw new IllegalStateException("Não foi possível cancelar. O horário pode não existir, não pertencer a este médico ou já está ocupado/cancelado.");
        }
    }

    public void cancelarTodaAgendaRecorrente(int medicoCrm) {
        Connection connection = null;

        try {
            connection = databaseConnection.getConnection();
            connection.setAutoCommit(false); // 1. INICIA A TRANSAÇÃO

            int gruposAfetados = grupoRecorrenciaRepository.cancelarTodosOsGruposPorMedico(connection, medicoCrm);

            if (gruposAfetados == 0) {
                throw new IllegalStateException("Nenhum grupo de recorrência ativo encontrado para este médico.");
            }

            horarioAtendimentoRepository.cancelarTodosHorariosFuturosDoMedico(connection, medicoCrm);

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {  }
            }
            throw new RuntimeException("Falha ao cancelar a agenda recorrente. A operação foi revertida.", e);

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException ignore) { }
            }
        }
    }


}
