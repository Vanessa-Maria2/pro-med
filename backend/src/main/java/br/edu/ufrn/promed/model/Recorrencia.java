package br.edu.ufrn.promed.model;

import br.edu.ufrn.promed.enums.DiaSemana;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Recorrencia {

    private int id;

    private Time hora_inicio;

    private Time hora_fim;

    private Date data_inicio;

    private Date data_fim;

    private List<DiaSemana> diaSemanas;

    private int idGrupoRecorrencia;

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Time hora_fim) {
        this.hora_fim = hora_fim;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DiaSemana> getDiaSemanas() {
        return diaSemanas;
    }

    public void setDiaSemanas(List<DiaSemana> diaSemanas) {
        this.diaSemanas = diaSemanas;
    }

    public int getIdGrupoRecorrencia() {
        return idGrupoRecorrencia;
    }

    public void setIdGrupoRecorrencia(int idGrupoRecorrencia) {
        this.idGrupoRecorrencia = idGrupoRecorrencia;
    }
}
