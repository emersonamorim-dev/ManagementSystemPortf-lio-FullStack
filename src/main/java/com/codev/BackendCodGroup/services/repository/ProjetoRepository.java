package com.codev.BackendCodGroup.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codev.BackendCodGroup.models.Projeto;


public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
}
