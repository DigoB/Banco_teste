package com.banco.conta.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.banco.conta.controller.forms.PcForm;
import com.banco.conta.controller.forms.ScForm;
import com.banco.conta.model.Cliente;
import com.banco.conta.model.PrimeiroCadastro;
import com.banco.conta.model.SegundoCadastro;
import com.banco.conta.repositories.ClienteRepository;
import com.banco.conta.repositories.PcRepository;
import com.banco.conta.repositories.ScRepository;
import com.banco.conta.services.EmailDuplicado;
import com.banco.conta.services.NovaConta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    // Responsável por injetar informações externas na Classe atual
    @Autowired
    private PcRepository pcRepository;
    @Autowired
    private ScRepository scRepository;
    @Autowired
    private EmailDuplicado emailDuplicado;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private NovaConta novaConta;
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailDuplicado);
    }

    @PostMapping("/pc")
    public ResponseEntity<?> primeiroCadastro(@RequestBody @Valid PcForm form, UriComponentsBuilder uri) {

        PrimeiroCadastro pc = new PrimeiroCadastro(form.getNome(), form.getSobrenome(), form.getEmail(), form.getCnh(),
                form.getNascimento());

        // Se comunica com o banco de dados, salva as informações e gera o id
        pcRepository.save(pc);

        URI path = uri.path("/cadastro/sc/{id}").buildAndExpand(pc.getId()).toUri();
        return ResponseEntity.created(path).build();

    }

    @PostMapping("/sc/{id}")
    public ResponseEntity<?> segundoCadastro(@PathVariable @Valid Long id, @RequestBody @Valid ScForm form,
            UriComponentsBuilder uri) {

        // Criando o segundo cadastro e trazendo as informações do primeiro enquanto
        // verifica se as mesmas estão válidas
        Optional<PrimeiroCadastro> pc = pcRepository.findById(id);
        if (!pc.isPresent()) {
            throw new IllegalStateException("Cadastro não encontrado");
        }
        SegundoCadastro sc = new SegundoCadastro(form.getCep(), form.getRua(), form.getBairro(), form.getComplemento(),
                form.getCidade(), form.getEstado(), pc.get());

        scRepository.save(sc);

        URI path = uri.path("/cadastro/tc/{id}").buildAndExpand(sc.getId()).toUri();
        return ResponseEntity.created(path).build();

    }

    @PostMapping("/tc/{id}")
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

    @GetMapping("/qc/{id}")
    public ResponseEntity<Cliente> envioDeDados(@PathVariable Long id, UriComponentsBuilder uBuilder) {
        SegundoCadastro sc = scRepository.findById(id).orElseThrow(() -> new IllegalStateException("Cliente não encontrado"));

        Cliente cliente = new Cliente(sc);
    
        clienteRepository.save(cliente);

        URI path = uBuilder.path("/cadastro/qc/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(path).body(cliente);
    }

    @GetMapping("/qc/{id}/{aceite}")
    public ResponseEntity<?> aceiteDeCadastro(@PathVariable Long id, @PathVariable String aceite) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        
        // Precisamos criar a conta do cliente, para caso seja aceito
        novaConta.criarConta(aceite, cliente.orElseThrow(() -> new IllegalStateException("Cliente nâo encontrado")));
        return ResponseEntity.ok().build();
    }
}
