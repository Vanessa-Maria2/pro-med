package br.edu.ufrn.promed.model;

import java.util.List;

public class MedicoRequestDto {

    private int numCrm;

    private int ufCrm;

    private Pessoa pessoa;

    private List<Formacao> formacoes;

    private List<Especialidade> especialidades;

    public int getNumCrm() {
        return numCrm;
    }

    public void setNumCrm(int numCrm) {
        this.numCrm = numCrm;
    }

    public int getUfCrm() {
        return ufCrm;
    }

    public void setUfCrm(int ufCrm) {
        this.ufCrm = ufCrm;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}
