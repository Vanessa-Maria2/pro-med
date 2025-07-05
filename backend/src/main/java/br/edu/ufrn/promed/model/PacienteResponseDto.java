package br.edu.ufrn.promed.model;

import java.util.List;

public class PacienteResponseDto {

    private String historicoFamiliaDoencas;

    private float altura;

    private float peso;

    private char tipoSanguineo;

    private char rhSanguineo;

    private Pessoa pessoa;

    private List<Alergia> alergias;

    private List<Doenca> doencas;

    public String getHistoricoFamiliaDoencas() {
        return historicoFamiliaDoencas;
    }

    public void setHistoricoFamiliaDoencas(String historicoFamiliaDoencas) {
        this.historicoFamiliaDoencas = historicoFamiliaDoencas;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public char getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(char tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public char getRhSanguineo() {
        return rhSanguineo;
    }

    public void setRhSanguineo(char rhSanguineo) {
        this.rhSanguineo = rhSanguineo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
