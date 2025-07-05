package br.edu.ufrn.promed.model;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;

public class PessoaDto {

    @NotBlank(message="O campo cpf é obrigatório")
    @CPF(message = "O campo CPF é inválido")
    private String cpf;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo sobrenome é obrigatório")
    private String sobrenome;

    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "O campo email é inválido")
    private String email;

    @NotBlank(message = "O campo endereço é obrigatório")
    private String endereco;

    @NotNull(message = "O campo data de nascimento é obrigatório")
    private Date dataNascimento;

    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 6, max = 10, message = "O tamanho excedeu, escreva uma senha de 6 a 10 dígitos")
    private String senha;

    private List<Telefone> telefones;

    private String tipo;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
