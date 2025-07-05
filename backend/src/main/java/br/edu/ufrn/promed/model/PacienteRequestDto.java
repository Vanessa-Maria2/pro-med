package br.edu.ufrn.promed.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PacienteRequestDto {

    private String historicoFamiliaDoencas;

    @NotNull(message="O campo altura é obrigatório")
    private float altura;

    @NotNull(message="O campo peso é obrigatório")
    private float peso;

    @NotNull(message="O campo tipo sanguíneo é obrigatório")
    private char tipoSanguineo;

    @NotNull(message="O campo rh sanguíneo é obrigatório")
    private char rhSanguineo;

    private PessoaDto pessoaDto;

    private List<Alergia> alergias;

    private List<Doenca> doencas;

    public String getHistoricoFamiliaDoencas() {
        return historicoFamiliaDoencas;
    }

    public void setHistoricoFamiliaDoencas(String historicoFamiliaDoencas) {
        this.historicoFamiliaDoencas = historicoFamiliaDoencas;
    }

    @NotNull(message = "O campo altura é obrigatório")
    public float getAltura() {
        return altura;
    }

    public void setAltura(@NotNull(message = "O campo altura é obrigatório") float altura) {
        this.altura = altura;
    }

    @NotNull(message = "O campo peso é obrigatório")
    public float getPeso() {
        return peso;
    }

    public void setPeso(@NotNull(message = "O campo peso é obrigatório") float peso) {
        this.peso = peso;
    }

    @NotNull(message = "O campo tipo sanguíneo é obrigatório")
    public char getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(@NotNull(message = "O campo tipo sanguíneo é obrigatório") char tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    @NotNull(message = "O campo rh sanguíneo é obrigatório")
    public char getRhSanguineo() {
        return rhSanguineo;
    }

    public void setRhSanguineo(@NotNull(message = "O campo rh sanguíneo é obrigatório") char rhSanguineo) {
        this.rhSanguineo = rhSanguineo;
    }

    public PessoaDto getPessoaDto() {
        return pessoaDto;
    }

    public void setPessoaDto(PessoaDto pessoaDto) {
        this.pessoaDto = pessoaDto;
    }

    public List<Alergia> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<Alergia> alergias) {
        this.alergias = alergias;
    }

    public List<Doenca> getDoencas() {
        return doencas;
    }

    public void setDoencas(List<Doenca> doencas) {
        this.doencas = doencas;
    }

}
