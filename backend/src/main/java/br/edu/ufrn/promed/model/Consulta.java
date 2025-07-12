package br.edu.ufrn.promed.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Consulta {

    private int id;

    private String resumo;

    private Time horaInicio;

    private Time horaFim;

    private int horarioAtendimentoId;

    private List<Exame> exames;

    private List<Receita> receitas;

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

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

}
