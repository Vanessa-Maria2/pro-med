package br.edu.ufrn.promed.dto.request;

import br.edu.ufrn.promed.model.GrupoRecorrencia;
import br.edu.ufrn.promed.model.Recorrencia;

import java.util.List;

public class RecorrenciaRequestDto {

    private int numCrmMedico;

    private String cpfMedico;

    private String ufCrmMedico;

    private List<Recorrencia> recorrencias;

    private GrupoRecorrencia  grupoRecorrencia;

    public int getNumCrmMedico() {
        return numCrmMedico;
    }

    public void setNumCrmMedico(int numCrmMedico) {
        this.numCrmMedico = numCrmMedico;
    }

    public String getCpfMedico() {
        return cpfMedico;
    }

    public void setCpfMedico(String cpfMedico) {
        this.cpfMedico = cpfMedico;
    }

    public String getUfCrmMedico() {
        return ufCrmMedico;
    }

    public void setUfCrmMedico(String ufCrmMedico) {
        this.ufCrmMedico = ufCrmMedico;
    }

    public List<Recorrencia> getRecorrencias() {
        return recorrencias;
    }

    public void setRecorrencias(List<Recorrencia> recorrencias) {
        this.recorrencias = recorrencias;
    }

    public GrupoRecorrencia getGrupoRecorrencia() {
        return grupoRecorrencia;
    }

    public void setGrupoRecorrencia(GrupoRecorrencia grupoRecorrencia) {
        this.grupoRecorrencia = grupoRecorrencia;
    }
}
