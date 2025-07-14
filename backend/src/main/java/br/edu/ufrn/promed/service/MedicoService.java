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

    public void cancelarAgendaRecorrente(int grupoRecorrenciaId) {
        int linhasAfetadas = horarioAtendimentoRepository.cancelarHorarioPorGrupoRecorrenciaAtendimento(grupoRecorrenciaId);

        if  (linhasAfetadas == 0) {
            throw new NoSuchElementException("Horário não existe ou já cadastrado ");
        }
    }

    public void cancelarHorarioPorId(int horarioId) {
        int linhasAfetadas = horarioAtendimentoRepository.cancelarHorarioPorId(horarioId);

        if (linhasAfetadas == 0) {
            throw new IllegalStateException("Não foi possível cancelar. O horário pode não existir, não pertencer a este médico ou já está ocupado/cancelado.");
        }
    }

}
