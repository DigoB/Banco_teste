package com.banco.conta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



@Entity
public class SegundoCadastro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @NotNull @Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;
    @NotBlank @NotNull
    private String rua;
    @NotBlank @NotNull
    private String bairro;
    @NotBlank @NotNull
    private String complemento;
    @NotBlank @NotNull
    private String cidade;
    @NotBlank @NotNull
    private String estado;
    @NotNull @OneToOne
    private PrimeiroCadastro primeiroCadastro;

    @Deprecated
    public SegundoCadastro(){}

    public SegundoCadastro(String cep, String rua, String bairro, String complemento, String cidade, String estado, PrimeiroCadastro primeiroCadastro){
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.primeiroCadastro = primeiroCadastro;
    }

    public PrimeiroCadastro getPrimeiroCadastro() {
        return this.primeiroCadastro;
    }

    public void setPrimeiroCadastro(PrimeiroCadastro primeiroCadastro) {
        this.primeiroCadastro = primeiroCadastro;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
