package br.edu.ufrn.promed.dto.response;

import br.edu.ufrn.promed.model.Especialidade;
import br.edu.ufrn.promed.model.Formacao;
import br.edu.ufrn.promed.model.Pessoa;

import java.util.List;

public class MedicoResponseDto {

    private String numCrm;

    private String ufCrm;

    private PessoaResponseDto pessoaResponseDto;

    private List<Formacao> formacoes;

    private List<Especialidade> especialidades;

    public String getNumCrm() {
        return numCrm;
    }

    public void setNumCrm(String numCrm) {
        this.numCrm = numCrm;
    }

    public String getUfCrm() { return ufCrm; }

    public void setUfCrm(String ufCrm) { this.ufCrm = ufCrm; }

    public PessoaResponseDto getPessoaResponseDto() {
        return pessoaResponseDto;
    }

    public void setPessoaResponseDto(PessoaResponseDto pessoaResponseDto) {
        this.pessoaResponseDto = pessoaResponseDto;
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
