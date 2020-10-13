package com.banco.conta.services;

import com.banco.conta.security.ClienteSecurity;

import org.springframework.security.core.context.SecurityContextHolder;

public class ContaService {
    public static ClienteSecurity usuarioAutenticado(){
        try{
            return (ClienteSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch(Exception e) {
            return null;
        }
    }
}
