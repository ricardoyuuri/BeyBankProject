package com.ricardoangelica.beybank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardoangelica.beybank.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    Optional<Cadastro> findByCpf(String cpf);
    
}
