package br.edu.ufrn.promed.model;

import java.util.Date;

public class Receita {

    private int id;

    private Date dataAtual;

    private Date dataValidade;

    private String prescricao;

    private int consultaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPreescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public int getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }
}
