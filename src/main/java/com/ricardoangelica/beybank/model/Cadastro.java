package com.ricardoangelica.beybank.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.ricardoangelica.beybank.validation.TipoMovimentacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Cadastro {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero_conta;

    @NotBlank(message = "{cadastro.nome_titular.notblank}")
    private String nome_titular;

    @NotBlank(message = "{cadastro.agencia_conta.notblank}")
    private String agencia_conta;

    @NotNull(message = "{cadastro.data_abertura.notnull}")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Past(message = "{cadastro.data_abertura.past}") 
    private LocalDate data_abertura;

    @NotBlank(message = "{cadastro.saldo_inicial.notblank}")
    @Positive(message = "{cadastro.saldo_inicial.positive}")
    private String saldo_inicial;

    @NotBlank(message = "{cadastro.status_conta.notblank}")
    private String status_conta;

    @NotBlank(message = "{cadastro.tipo_conta.notblank}")
    @TipoMovimentacao
    private String tipo_conta;

    @NotBlank(message = "{cadastro.cpf.notblank}")
    @CPF(message = "{cadastro.cpf.cpf}")
    private String cpf;
}

//  //@NotBlank(message = "{cadastro.numero_conta.notblank}")