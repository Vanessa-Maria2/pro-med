package br.edu.ufrn.promed.dto.response;

public class DoencaResponseDto {

    private String cid;

    private String descricao;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
