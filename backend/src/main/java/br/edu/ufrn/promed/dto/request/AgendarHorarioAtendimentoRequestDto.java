package br.edu.ufrn.promed.dto.request;

public class AgendarHorarioAtendimentoRequestDto {

    private String cpf;

    private int horarioAtendimentoId;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getHorarioAtendimentoId() {
        return horarioAtendimentoId;
    }

    public void setHorarioAtendimentoId(int horarioAtendimentoId) {
        this.horarioAtendimentoId = horarioAtendimentoId;
    }
}
