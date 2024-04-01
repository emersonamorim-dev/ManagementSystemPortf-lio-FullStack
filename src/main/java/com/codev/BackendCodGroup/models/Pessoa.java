package com.codev.BackendCodGroup.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set; 
import java.util.HashSet;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date datanascimento;
    private String cpf;
    private Boolean funcionario;
    private Boolean gerente;

    @ManyToMany(mappedBy = "pessoas")
    private Set<Projeto> projetos = new HashSet<>();

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
    public Date getDatanascimento() {
        return datanascimento;
    }
    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Boolean getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }
    public Boolean getGerente() {
        return gerente;
    }
    public void setGerente(Boolean gerente) {
        this.gerente = gerente;
    }


    public boolean isFuncionario() {
        return funcionario != null ? funcionario : false; 
    }
    
    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }
    
    public boolean isGerente() {
        return gerente != null ? gerente : false; 
    }
    
    public void setGerente(boolean gerente) {
        this.gerente = gerente;
    }
    


}
