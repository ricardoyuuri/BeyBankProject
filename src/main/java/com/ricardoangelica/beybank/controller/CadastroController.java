package com.ricardoangelica.beybank.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ricardoangelica.beybank.model.Cadastro;
import com.ricardoangelica.beybank.repository.CadastroRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cadastro")
@Slf4j
public class CadastroController {

    @Autowired
    CadastroRepository cadastroRepository;

    @GetMapping("/")
    public String getProjectInfo() {
        String projectName = "BeyBank";
        String teamMembers = "Angélica Ferreira de Matos Melo, Ricardo Yuri G Domingues"; 

        return "Projeto: " + projectName + "\nIntegrantes da equipe: " + teamMembers;
    }

    @GetMapping
    public List<Cadastro> index(){
        return cadastroRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cadastro create(@RequestBody @Valid Cadastro cadastro){
        log.info("Cadastrando conta: {}", cadastro);
        return cadastroRepository.save(cadastro);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cadastro> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);

        return cadastroRepository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cadastro> get(@PathVariable String cpf){
        log.info("Buscar por id: {}", cpf);

        return cadastroRepository
            .findByCpf(cpf)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id){
        log.info("Apagando agendamento {}", id);

        verificarSeExisteCadastro(id);

        cadastroRepository.deleteById(id);
    }

    private void verificarSeExisteCadastro(Long id) {
        cadastroRepository
          .findById(id)
          .orElseThrow(
              () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada" )
        );
    
}
}