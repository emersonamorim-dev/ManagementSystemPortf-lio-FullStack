package com.codev.BackendCodGroup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codev.BackendCodGroup.dto.MembroDTO;
import com.codev.BackendCodGroup.models.Membro;

import com.codev.BackendCodGroup.services.MembroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")    
public class MembroController {

    @Autowired
    private MembroService membroService;

    // Buscar todos os membros
    @GetMapping("/membro")
    public List<Membro> getAllMembros() {
        return membroService.findAll();
    }

    // Busca membro por ID
    @GetMapping("/membro/{id}")
    public ResponseEntity<Membro> getMembroById(@PathVariable Long id) {
        Optional<Membro> membro = membroService.findById(id);
        if (membro.isPresent()) {
            return ResponseEntity.ok(membro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Criar novo membro
    @PostMapping("/membro")
     public ResponseEntity<Membro> createMembro(@RequestBody Membro membro) {
         Membro savedMembro = membroService.save(membro);
         return new ResponseEntity<>(savedMembro, HttpStatus.CREATED);
     }

     // via web service
    @PostMapping("/membro/WebService")
     public ResponseEntity<Membro> addMembro(@RequestBody MembroDTO membroDTO) {
         Membro novoMembro = new Membro();
         novoMembro.setNome(membroDTO.getNome());
         novoMembro.setAtribuicao(membroDTO.getAtribuicao());
         Membro salvo = membroService.save(novoMembro);
         return new ResponseEntity<>(salvo, HttpStatus.CREATED);
     }



    // Atualizar membro
    @PutMapping("/membro/{id}")
    public ResponseEntity<?> updateMembro(@PathVariable Long id, @RequestBody Membro membroDetails) {
        return membroService.updateMembro(id, membroDetails)
                .map(membroAtualizado -> ResponseEntity.ok("Membro atualizado com sucesso."))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/membro/{id}")
    public ResponseEntity<String> deleteMembro(@PathVariable Long id) {
        if (membroService.deleteMembro(id)) {
            return ResponseEntity.ok("Membro apagado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
