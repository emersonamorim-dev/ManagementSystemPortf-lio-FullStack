package com.codev.BackendCodGroup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codev.BackendCodGroup.models.Membro;
import com.codev.BackendCodGroup.services.repository.MembroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public List<Membro> findAll() {
        return membroRepository.findAll();
    }

    public Optional<Membro> findById(Long id) {
        return membroRepository.findById(id);
    }

    public Membro save(Membro membro) {
        return membroRepository.save(membro);
    }

    public Optional<Membro> updateMembro(Long id, Membro membroDetails) {
        return membroRepository.findById(id).map(membro -> {
            membro.setIdprojeto(membroDetails.getIdprojeto());
            membro.setIdpessoa(membroDetails.getIdpessoa());
            return membroRepository.save(membro);
        });
    }

    public boolean deleteMembro(Long id) {
        if(membroRepository.existsById(id)) {
            membroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
