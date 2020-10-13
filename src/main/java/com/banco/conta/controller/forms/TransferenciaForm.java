package com.banco.conta.controller.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.banco.conta.model.Conta;
import com.banco.conta.model.Transferencias;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class TransferenciaForm {
    @NotNull @NotBlank
    private Double valor;
    @NotNull @NotBlank
    private String cpf;
    @NotNull @NotBlank
    private String codTransferencia;
    @NotNull @NotBlank
    private String contaDestino;
    @NotNull @NotBlank
    private String agenciaDestino;


    public TransferenciaForm(Double valor, String cpf, String bancoOrigem, String contaOrigem, String agenciaOrigem, String codTransferencia, String contaDestino, String agenciaDestino) {
        this.valor = valor;
        this.cpf = cpf;
        this.codTransferencia = codTransferencia;
        this.contaDestino = contaDestino;
        this.agenciaDestino = agenciaDestino;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCodTransferencia() {
        return this.codTransferencia;
    }

    public void setCodTransferencia(String codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public String getContaDestino() {
        return this.contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getAgenciaDestino() {
        return this.agenciaDestino;
    }

    public void setAgenciaDestino(String agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }
    
    @Bean
    @Scope("singleton")
    public Transferencias transferencias(Conta contaDestino, Conta contaOrigem){
        return new Transferencias(this.valor, this.cpf, "500", contaOrigem.getAgencia().toString(), 
                contaOrigem.getConta().toString(), this.codTransferencia, contaDestino);
    } 

}
