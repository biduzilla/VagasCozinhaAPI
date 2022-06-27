package com.example.vagascozinhaapi.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_qualificacao")
public class Qualificacao {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "qualificacao")
    private String qualificacao;

    @ManyToOne(targetEntity = Curriculum.class)
    @JoinColumn(name = "qualificacao_curriculum_id")
    private Curriculum curriculum;
}
