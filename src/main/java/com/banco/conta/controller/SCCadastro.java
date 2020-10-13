package com.banco.conta.controller;

import java.net.URI;

import javax.validation.Valid;

import com.banco.conta.controller.forms.ScForm;
import com.banco.conta.model.SegundoCadastro;
import com.banco.conta.repositories.PcRepository;
import com.banco.conta.repositories.ScRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SCCadastro {
    
    @Autowired
    private ScRepository scRepository;
    @Autowired
    private PcRepository pcRepository;

    @PostMapping("/cadastro/sc/{id}")
    public ResponseEntity<?> segundoCadastro(@PathVariable @Valid Long id, @RequestBody @Valid ScForm form,
            UriComponentsBuilder uri) {

        // Criando o segundo cadastro e trazendo as informações do primeiro enquanto
        // verifica se as mesmas estão válidas
        SegundoCadastro sc = form.cadastro(pcRepository.findById(id).orElseThrow(
            () -> new IllegalStateException("Cadastro não encontrado")));

        scRepository.save(sc);

        URI path = uri.path("/cadastro/tc/{id}").buildAndExpand(sc.getId()).toUri();
        return ResponseEntity.created(path).build();

    }
}
