package com.codev.BackendCodGroup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.BackendCodGroup.models.Pessoa;
import com.codev.BackendCodGroup.models.Projeto;
import com.codev.BackendCodGroup.services.repository.PessoaRepository;
import com.codev.BackendCodGroup.services.repository.ProjetoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    public ProjetoService(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
}

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Optional<Projeto> updateProjeto(Long id, Projeto projetoDetails) {
        return projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoDetails.getNome());
            projeto.setDataInicio(projetoDetails.getDataInicio());
            projeto.setDataPrevisaoFim(projetoDetails.getDataPrevisaoFim());
            projeto.setDataFim(projetoDetails.getDataFim());
            projeto.setDescricao(projetoDetails.getDescricao());
            projeto.setStatus(projetoDetails.getStatus());
            projeto.setOrcamento(projetoDetails.getOrcamento());
            projeto.setRisco(projetoDetails.getRisco());
            return projetoRepository.save(projeto);
        });
    }
    

    public boolean deleteProjeto(Long id) {
        if(projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void associarPessoaProjeto(Long pessoaId, Long projetoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado."));

        projeto.getPessoas().add(pessoa);
        projetoRepository.save(projeto);
    }

    
}

