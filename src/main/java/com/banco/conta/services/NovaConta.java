package com.banco.conta.services;

import com.banco.conta.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class NovaConta {
    @Autowired 
    private JavaMailSender mailSender;
    public void envioDeEmail(String aceite, Cliente cliente) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(cliente.getEmail());
        if (aceite == "Não aceito") {
            message.setSubject("Sobre sua abertura de conta!");
            message.setText("Pelo amor de Deus, abra a sua conta conosco!");
            mailSender.send(message);
        }

    }
}
