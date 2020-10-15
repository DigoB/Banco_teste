package com.banco.conta.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 4)
    private String agencia;
    @NotNull
    @Size(max = 8)
    private String conta;
    @NotNull
    @Size(max = 1)
    private String digito;
    @NotNull
    private Integer codBanco;
    @NotNull
    @OneToOne
    private Cliente cliente;
    @NotNull
    private Double saldo;

    private String senha;

    @Deprecated
    public Conta() {
    }

    public Conta(Cliente cliente) {
        Random random = new Random();
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        this.agencia = String.valueOf(random.nextInt((9999 - 1000) + 1) + 1000);
        this.conta = String.valueOf(random.nextInt((99999999 - 10000000) + 1) + 10000000);
        this.digito = String.valueOf(random.nextInt(10));
        this.codBanco = 500;
        this.cliente = cliente;
        this.saldo = 200d;
        this.senha = encoder.encode("123456");
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getDigito() {
        return this.digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public Integer getCodBanco() {
        return this.codBanco;
    }

    public void setCodBanco(Integer codBanco) {
        this.codBanco = codBanco;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo -= saldo;
    }

    public void validarTransferencia(Double saldo) {
        this.saldo += saldo;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
