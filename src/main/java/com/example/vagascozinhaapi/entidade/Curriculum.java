package com.example.vagascozinhaapi.entidade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "emailcontatocv")
    private String emailContatoCV;

    @Column(name = "sobre")
    private String sobre;

    @Column(name = "experiencias")
    @ElementCollection
    private List<String> experiencias;

    @Column(name = "qualificacoes")
    @ElementCollection
    private List<String> qualificacoes;

}