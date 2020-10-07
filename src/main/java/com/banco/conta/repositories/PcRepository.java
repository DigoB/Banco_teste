package com.banco.conta.repositories;

import com.banco.conta.model.PrimeiroCadastro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PcRepository extends JpaRepository <PrimeiroCadastro, Long> {
    
}
