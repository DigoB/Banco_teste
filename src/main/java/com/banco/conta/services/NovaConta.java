package com.banco.conta.services;

import com.banco.conta.model.Cliente;
import com.banco.conta.model.Conta;
import com.banco.conta.repositories.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NovaConta {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ContaRepository contaRepository;

    public void criarConta(String aceite, Cliente cliente) {
        if (aceite.equals("NaoAceito")) {
            emailNaoAceite(cliente);

        } else if (aceite.equals("Aceito")) {
            Conta conta = new Conta(cliente);
            contaRepository.save(conta);

            emailAceite(conta);
        } else {
            throw new IllegalArgumentException("Ocorreu um erro inesperado");
        }

    }

    private void emailNaoAceite(Cliente cliente) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(cliente.getEmail());
        message.setSubject("Sobre sua abertura de conta!");
        message.setText("Pelo amor de Deus, abra a sua conta conosco!");
        mailSender.send(message);

        
    }
    private void emailAceite(Conta conta) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(conta.getCliente().getEmail());

        message.setSubject("Sobre sua abertura de conta!");
        message.setText("Sua conta foi criada com sucesso!\n" + 
        "Seus dados são: \n" + 
        "Agencia: " + conta.getAgencia() + "\n" + 
        "Conta: " + conta.getConta() + "-" + conta.getDigito() + "\n\n" + 
        "Seja bem vindo!");

        mailSender.send(message);
    }
}
