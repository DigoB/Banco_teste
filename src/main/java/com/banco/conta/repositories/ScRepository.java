package com.banco.conta.repositories;

import com.banco.conta.model.SegundoCadastro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScRepository extends JpaRepository <SegundoCadastro, Long> {

}
