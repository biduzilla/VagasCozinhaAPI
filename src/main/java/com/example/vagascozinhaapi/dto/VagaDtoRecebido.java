package com.example.vagascozinhaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VagaDtoRecebido {
    private Integer userId;
    private String cargo;
    private String descricao;
    private String local;
    List<String> requisitos;
    private double remuneracao;
}