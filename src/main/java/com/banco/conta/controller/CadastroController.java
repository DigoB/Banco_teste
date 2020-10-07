package com.banco.conta.controller;

import java.net.URI;

import javax.validation.Valid;

import com.banco.conta.controller.forms.PcForm;
import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.repositories.PcRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    // Responsável por injetar informações externas na Classe atual
    @Autowired
    private PcRepository pcRepository;

    @PostMapping("/pc")
    public ResponseEntity <PrimeiroCadastro> primeiroCadastro(@RequestBody @Valid PcForm form, UriComponentsBuilder uri){

        PrimeiroCadastro pc = new PrimeiroCadastro(form.getNome(), form.getSobrenome(), form.getEmail(), form.getCnh(), form.getNascimento());

        // Se comunica com o banco de dados, salva as informações e gera o id
        pcRepository.save(pc);

        URI path = uri.path("/cadastro/sc/{id}").buildAndExpand(pc.getId()).toUri();
        return ResponseEntity.created(path).body(pc);
    }
}
