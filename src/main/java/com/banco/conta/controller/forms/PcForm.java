package com.banco.conta.controller.forms;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.banco.conta.model.PrimeiroCadastro;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class PcForm {
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank
    private String sobrenome;
    @NotBlank @NotNull @Email
    private String email;
    @NotBlank @NotNull @Size(max = 11)
    private String cnh;
    @NotNull @JsonFormat(pattern = "dd-MM-yyyy", shape = Shape.STRING)
    private LocalDate nascimento;

    // public PcForm(String nome, String sobrenome, String email, String cnh, LocalDate nascimento){

    //     this.nome = nome;
    //     this.sobrenome = sobrenome;
    //     this.email = email;
    //     this.cnh = cnh;
    //     this.nascimento = nascimento;
    // }


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

    public PrimeiroCadastro cadastro() {
        return new PrimeiroCadastro(this.nome, this.sobrenome, this.email, this.cnh, this.nascimento);
    }

    
}
