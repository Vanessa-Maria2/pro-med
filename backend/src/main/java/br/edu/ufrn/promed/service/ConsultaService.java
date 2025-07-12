package br.edu.ufrn.promed.service;

import br.edu.ufrn.promed.dto.request.ConsultaRequestDto;
import br.edu.ufrn.promed.dto.response.ConsultaResponseDto;
import br.edu.ufrn.promed.mapper.ConsultaMapper;
import br.edu.ufrn.promed.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final ConsultaMapper consultaMapper;

    public ConsultaService(ConsultaRepository consultaRepository, ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
    }

    public ConsultaResponseDto cadastrarConsulta(ConsultaRequestDto consultaRequestDto) throws SQLException {
        var consulta = consultaMapper.toConsulta(consultaRequestDto);
        this.consultaRepository.cadastrar(consulta);
        return consultaMapper.toConsultaResponseDto(consulta);
    }
}
