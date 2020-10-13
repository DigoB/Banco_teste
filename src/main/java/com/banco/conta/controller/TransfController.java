package com.banco.conta.controller;

import java.net.URI;

import javax.validation.Valid;

import com.banco.conta.controller.forms.TransferenciaForm;
import com.banco.conta.model.Conta;
import com.banco.conta.model.Transferencias;
import com.banco.conta.repositories.ClienteRepository;
import com.banco.conta.repositories.ContaRepository;
import com.banco.conta.repositories.TransfRepository;
import com.banco.conta.services.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController 
@RequestMapping("/transf")
public class TransfController {
    @Autowired
    private TransfRepository transfRepository;

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Transferencias> criarTransferencia(@RequestBody @Valid TransferenciaForm form, UriComponentsBuilder uriBuilder){
        Conta contaDestino = contaRepository.findByConta(form.getContaDestino()).orElseThrow(
            () -> new IllegalStateException("Conta destino não existe"));
        Conta contaOrigem = contaRepository.findByCliente(
            clienteRepository.findByEmail(ContaService.usuarioAutenticado().getUsername()).orElseThrow(
                () -> new IllegalStateException("Usuário não encontrado"))).orElseThrow(
                    () -> new IllegalStateException("Usuário não possui conta"));

        if (contaOrigem.getSaldo() < form.getValor()) {
            throw new IllegalArgumentException("Conta de origem não possui o valor para transferência");
        }

        Transferencias transf = form.transferencias(contaDestino, contaOrigem);

        contaOrigem.setSaldo(form.getValor());
        contaRepository.save(contaOrigem);
        transfRepository.save(transf);

        URI uri = uriBuilder.path("/transf/{id}").buildAndExpand(transf.getId()).toUri();
        return ResponseEntity.created(uri).body(transf);
    }
}
