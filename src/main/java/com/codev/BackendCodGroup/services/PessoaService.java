package com.codev.BackendCodGroup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.BackendCodGroup.models.Pessoa;
import com.codev.BackendCodGroup.services.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> updatePessoa(Long id, Pessoa pessoaDetails) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setNome(pessoaDetails.getNome());
            pessoa.setDatanascimento(pessoaDetails.getDatanascimento());
            pessoa.setCpf(pessoaDetails.getCpf());
            pessoa.setFuncionario(pessoaDetails.getFuncionario());
            pessoa.setGerente(pessoaDetails.getGerente());
            return pessoaRepository.save(pessoa);
        });
    }

    public boolean deletePessoa(Long id) {
        if(pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
}
