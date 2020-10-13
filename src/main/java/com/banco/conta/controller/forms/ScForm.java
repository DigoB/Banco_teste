package com.banco.conta.controller.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.model.SegundoCadastro;

public class ScForm {
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

    public SegundoCadastro cadastro(PrimeiroCadastro pc) {
        return new SegundoCadastro(this.cep, this.rua, this.bairro, this.complemento, this.cidade, this.estado, pc);
    }
}
