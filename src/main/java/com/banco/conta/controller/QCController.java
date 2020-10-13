package com.banco.conta.controller;

import java.net.URI;

import com.banco.conta.model.Cliente;
import com.banco.conta.model.SegundoCadastro;
import com.banco.conta.repositories.ClienteRepository;
import com.banco.conta.repositories.ScRepository;
import com.banco.conta.services.NovaConta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class QCController {
    
    @Autowired
    private ScRepository scRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private NovaConta novaConta;

    @PostMapping("/cadastro/qc/{id}")
    public ResponseEntity<Cliente> envioDeDados(@PathVariable Long id, UriComponentsBuilder uBuilder) {
        SegundoCadastro sc = scRepository.findById(id).orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

        Cliente cliente = new Cliente(sc);
    
        clienteRepository.save(cliente);

        URI path = uBuilder.path("/cadastro/qc/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(path).body(cliente);
    }

    @PostMapping("/cadastro/qc/{id}/{aceite}")
    public ResponseEntity<?> aceiteDeCadastro(@PathVariable Long id, @PathVariable String aceite) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalStateException("Cliente nâo encontrado"));
        
        // Precisamos criar a conta do cliente, para caso seja aceito
        novaConta.criarConta(aceite, cliente);
        return ResponseEntity.ok().build();
    }
}
