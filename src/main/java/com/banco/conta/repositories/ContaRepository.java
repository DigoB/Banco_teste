package com.banco.conta.repositories;

import java.util.Optional;

import com.banco.conta.model.Cliente;
import com.banco.conta.model.Conta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByCliente(Cliente Cliente);
    Optional<Conta> findByConta(String conta);
}
