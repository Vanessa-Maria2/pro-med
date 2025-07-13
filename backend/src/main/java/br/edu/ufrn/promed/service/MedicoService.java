package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.mapper.MedicoMapper;
import br.edu.ufrn.promed.dto.request.MedicoRequestDto;
import br.edu.ufrn.promed.dto.response.MedicoResponseDto;
import br.edu.ufrn.promed.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
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
}
