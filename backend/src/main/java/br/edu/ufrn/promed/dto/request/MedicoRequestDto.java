package br.edu.ufrn.promed.dto.request;

import br.edu.ufrn.promed.model.Especialidade;
import br.edu.ufrn.promed.model.Formacao;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MedicoRequestDto {

    @NotNull(message = "O campo número CRM é obrigatório")
    private int numCrm;

    @NotNull(message = "O campo uf CRM é obrigatório")
    private int ufCrm;

    private PessoaRequestDto pessoaRequestDto;

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

    public PessoaRequestDto getPessoaRequestDto() {
        return pessoaRequestDto;
    }

    public void setPessoaDto(PessoaRequestDto pessoaRequestDto) {
        this.pessoaRequestDto = pessoaRequestDto;
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
