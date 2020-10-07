package com.banco.conta.services;

import java.util.Optional;

import com.banco.conta.controller.forms.PcForm;
import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.repositories.PcRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailDuplicado implements Validator {

    @Autowired
    private PcRepository pcRepository;

    @Override
    public boolean supports(Class<?> arg0) {
        
        return PcForm.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object form, Errors errors) {
        if (errors.hasErrors()) return;

        PcForm cadastro = (PcForm) form;

        Optional <PrimeiroCadastro> validar = pcRepository.findByEmail(cadastro.getEmail());

        if (validar.isPresent()){
            errors.rejectValue("Email", null, "Email já cadastrado");
        }
    }
    
}
