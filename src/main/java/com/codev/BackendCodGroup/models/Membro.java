package com.codev.BackendCodGroup.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membro")
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String atribuicao; 
    private Long idprojeto; 
    private Long idpessoa; 

    // Construtor sem argumentos exigido pela JPA
    public Membro() {
    }

    // Construtor completo
    public Membro(Long id, String nome, String atribuicao, Long idprojeto, Long idpessoa) {
        this.id = id;
        this.nome = nome;
        this.atribuicao = atribuicao;
        this.idprojeto = idprojeto;
        this.idpessoa = idpessoa;
    }

    // Construtor usado nos testes com id, nome, atribuicao, e email
    public Membro(Long id, String nome, String atribuicao, String email) {
        this.id = id;
        this.nome = nome;
        this.atribuicao = atribuicao;

    }

    // Construtor sem o ID para criação de novos membros onde o ID é gerado automaticamente
    public Membro(String nome, String atribuicao, Long idprojeto, Long idpessoa) {
        this.nome = nome;
        this.atribuicao = atribuicao;
        this.idprojeto = idprojeto;
        this.idpessoa = idpessoa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(String atribuicao) {
        this.atribuicao = atribuicao;
    }

    public Long getIdprojeto() {
        return idprojeto;
    }

    public void setIdprojeto(Long idprojeto) {
        this.idprojeto = idprojeto;
    }

    public Long getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Long idpessoa) {
        this.idpessoa = idpessoa;
    }
}
