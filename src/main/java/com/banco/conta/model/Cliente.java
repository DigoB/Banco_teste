package com.banco.conta.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity 
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String sobrenome;
    @NotNull @Email
    private String email;
    @NotNull @Size(max = 11)
    private String cnh;
    @NotNull @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private LocalDate nascimento;
    @NotNull @Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;
    @NotNull
    private String rua;
    @NotNull
    private String bairro;
    @NotNull
    private String complemento;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
     
    @Deprecated
    public Cliente(){

    }

    public Cliente(SegundoCadastro cliente) {
        this.nome = cliente.getPrimeiroCadastro().getNome();
        this.sobrenome = cliente.getPrimeiroCadastro().getSobrenome();
        this.email = cliente.getPrimeiroCadastro().getEmail();
        this.cnh = cliente.getPrimeiroCadastro().getCnh();
        this.nascimento = cliente.getPrimeiroCadastro().getNascimento();
        this.cep = cliente.getCep();
        this.rua = cliente.getRua();
        this.bairro = cliente.getBairro();
        this.complemento = cliente.getComplemento();
        this.cidade = cliente.getCidade();
        this.estado = cliente.getEstado();
    }   


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return this.cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
 
}
