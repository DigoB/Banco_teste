package com.banco.conta.services;

import com.banco.conta.model.Cliente;
import com.banco.conta.model.Conta;
import com.banco.conta.repositories.ClienteRepository;
import com.banco.conta.repositories.ContaRepository;
import com.banco.conta.security.ClienteSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));
        Conta conta = contaRepository.findByCliente(cliente).orElseThrow(() -> new IllegalStateException("Cliente não tem conta"));
        return new ClienteSecurity(conta.getId(), cliente.getEmail(), conta.getSenha(), cliente.getPerfis());
    }
       
}
