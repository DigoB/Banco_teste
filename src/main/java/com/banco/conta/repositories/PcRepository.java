package com.banco.conta.repositories;

import java.util.Optional;

import com.banco.conta.model.PrimeiroCadastro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PcRepository extends JpaRepository <PrimeiroCadastro, Long> {
    Optional <PrimeiroCadastro> findByEmail(String email);
}
