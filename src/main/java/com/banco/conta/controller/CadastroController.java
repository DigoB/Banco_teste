package com.banco.conta.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.banco.conta.controller.forms.PcForm;
import com.banco.conta.controller.forms.ScForm;
import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.model.SegundoCadastro;
import com.banco.conta.repositories.PcRepository;
import com.banco.conta.repositories.ScRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired 
    private ScRepository scRepository;

    @PostMapping("/pc")
    public ResponseEntity <PrimeiroCadastro> primeiroCadastro(@RequestBody @Valid PcForm form, UriComponentsBuilder uri){

        PrimeiroCadastro pc = new PrimeiroCadastro(form.getNome(), form.getSobrenome(), form.getEmail(), form.getCnh(), form.getNascimento());

        // Se comunica com o banco de dados, salva as informações e gera o id
        pcRepository.save(pc);

        URI path = uri.path("/cadastro/sc/{id}").buildAndExpand(pc.getId()).toUri();
        return ResponseEntity.created(path).body(pc);

    }
    @PostMapping("/sc/{id}")
    public ResponseEntity <SegundoCadastro> segundoCadastro(@PathVariable @Valid Long id, @RequestBody @Valid ScForm form, 
                                                            UriComponentsBuilder uri){
        
        // Criando o segundo cadastro e trazendo as informações do primeiro enquanto verifica se as mesmas estão válidas
        Optional <PrimeiroCadastro> pc = pcRepository.findById(id);
        if (!pc.isPresent()){
            throw new IllegalStateException("Cadastro não encontrado");
        }
        SegundoCadastro sc = new SegundoCadastro(form.getCep(), form.getRua(), form.getBairro(), form.getComplemento(), form.getCidade(), 
                    form.getEstado(), pc.get());


        scRepository.save(sc);

        URI path = uri.path("/cadastro/tc/{id}").buildAndExpand(sc.getId()).toUri();
        return ResponseEntity.created(path).body(sc);

    }

}
