package br.edu.ufrn.promed.dto.response;

import java.util.Date;

public class HorarioAtendimentoResponseDto {

    private int id;

    private String status;

    private String horario;

    private Date data;

    private int numCrmMedico;

    private String cpfMedico;

    private String ufCrmMedico;

    private int idRecorrencia;

    private int idGrupoRecorrencia;

    private String nomeMedico;

    public String getSobrenomeMedico() {
        return sobrenomeMedico;
    }

    public void setSobrenomeMedico(String sobrenomeMedico) {
        this.sobrenomeMedico = sobrenomeMedico;
    }

    private String sobrenomeMedico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

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

    public int getIdRecorrencia() {
        return idRecorrencia;
    }

    public void setIdRecorrencia(int idRecorrencia) {
        this.idRecorrencia = idRecorrencia;
    }

    public int getIdGrupoRecorrencia() {
        return idGrupoRecorrencia;
    }

    public void setIdGrupoRecorrencia(int idGrupoRecorrencia) {
        this.idGrupoRecorrencia = idGrupoRecorrencia;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }
}
