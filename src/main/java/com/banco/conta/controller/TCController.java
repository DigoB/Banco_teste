package com.banco.conta.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.repositories.PcRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TCController {
    
    @Autowired
    private PcRepository pcRepository;

    @PostMapping("/cadastro/tc/{id}")
    public ResponseEntity<?> uploadCpf(@PathVariable Long id, @RequestParam @Valid MultipartFile fotoFile,
            UriComponentsBuilder uriBuilder, HttpServletRequest request) {

        PrimeiroCadastro pcObj = pcRepository.findById(id).orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

        Path dir = Paths.get("src\\main\\java\\com\\banco\\conta\\resources");
        String s = dir.toAbsolutePath().toString();

        try {
            File foto = new File(s + "/" + pcObj.getNome() + id + "." + fotoFile.getOriginalFilename().split("\\.")[1]);
            fotoFile.transferTo(foto);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
        URI path = uriBuilder.path("/cadastro/qc/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(path).build();
    }

}
