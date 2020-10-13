package com.banco.conta.controller;

import com.banco.conta.model.Conta;
import com.banco.conta.model.Transferencias;
import com.banco.conta.repositories.ContaRepository;
import com.banco.conta.repositories.TransfRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {
    
    @Autowired
    private TransfRepository transfRepository;
    @Autowired
    private ContaRepository contaRepository;

    @PutMapping("/transf/{id}")
    public ResponseEntity<?> verificarTransferencias(@PathVariable Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new IllegalStateException("Conta não encontrada"));
        Transferencias transf = transfRepository.findByContaDestino(conta).orElseThrow(
            () -> new IllegalStateException("Não há transferencias para a conta desejada"));

        conta.validarTransferencia(transf.getValor());
        contaRepository.save(conta);

        return ResponseEntity.ok().build();
    }
}
