package br.edu.ufrn.promed.dto.response;

import java.sql.Time;
import java.util.Date;

public class ConsultaResponseDto {

    private int id;

    private String resumo;

    private Time horaInicio;

    private Time horaFim;

    private int horarioAtendimentoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public int getHorarioAtendimentoId() {
        return horarioAtendimentoId;
    }

    public void setHorarioAtendimentoId(int horarioAtendimentoId) {
        this.horarioAtendimentoId = horarioAtendimentoId;
    }
}
