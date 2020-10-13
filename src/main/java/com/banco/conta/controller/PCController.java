package com.banco.conta.controller;

import java.net.URI;

import javax.validation.Valid;

import com.banco.conta.controller.forms.PcForm;
import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.repositories.PcRepository;
import com.banco.conta.services.EmailDuplicado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PCController {
    
    @Autowired
    private PcRepository pcRepository;
    @Autowired
    private EmailDuplicado emailDuplicado;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailDuplicado);
    }

    @PostMapping("/cadastro/pc")
    public ResponseEntity<?> primeiroCadastro(@RequestBody @Valid PcForm form, UriComponentsBuilder uri) {

        PrimeiroCadastro pc = form.cadastro();

        // Se comunica com o banco de dados, salva as informações e gera o id
        pcRepository.save(pc);

        URI path = uri.path("/cadastro/sc/{id}").buildAndExpand(pc.getId()).toUri();
        return ResponseEntity.created(path).build();

    }
}
