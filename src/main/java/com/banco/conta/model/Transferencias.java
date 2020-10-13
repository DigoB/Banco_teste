package com.banco.conta.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class Transferencias {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double valor;
    @NotNull
    private LocalDateTime data;
    @NotNull
    private String cpf;
    @NotNull
    private String bancoOrigem;
    @NotNull
    private String contaOrigem;
    @NotNull
    private String agenciaOrigem;
    @NotNull
    private String codTransferencia;
    @NotNull @ManyToOne
    private Conta contaDestino;
    @Deprecated
    public Transferencias() {}

    public Transferencias(Double valor, String cpf, String bancoOrigem, String agenciaOrigem,
    String contaOrigem, String codTransferencia, Conta contaDestino) {
        this.valor = valor;
        this.data = LocalDateTime.now();
        this.cpf = cpf;
        this.bancoOrigem = bancoOrigem;
        this.contaOrigem = contaOrigem;
        this.agenciaOrigem = agenciaOrigem;
        this.codTransferencia = codTransferencia;
        this.contaDestino = contaDestino;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBancoOrigem() {
        return this.bancoOrigem;
    }

    public void setBancoOrigem(String bancoOrigem) {
        this.bancoOrigem = bancoOrigem;
    }

    public String getContaOrigem() {
        return this.contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getAgenciaOrigem() {
        return this.agenciaOrigem;
    }

    public void setAgenciaOrigem(String agenciaOrigem) {
        this.agenciaOrigem = agenciaOrigem;
    }

    public String getCodTransferencia() {
        return this.codTransferencia;
    }

    public void setCodTransferencia(String codTransferencia) {
        this.codTransferencia = codTransferencia;
    }


    public Conta getContaDestino() {
        return this.contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }


    
}
