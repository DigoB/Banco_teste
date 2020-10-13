package com.banco.conta.repositories;

import java.util.Optional;

import com.banco.conta.model.Conta;
import com.banco.conta.model.Transferencias;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransfRepository extends JpaRepository<Transferencias, Long>{
    Optional<Transferencias> findByContaDestino(Conta conta);
}
