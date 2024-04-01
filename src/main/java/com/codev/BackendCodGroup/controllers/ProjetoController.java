package com.codev.BackendCodGroup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codev.BackendCodGroup.dto.AssociacaoDTO;
import com.codev.BackendCodGroup.models.Projeto;
import com.codev.BackendCodGroup.services.ProjetoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")   
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    // Busca todos
    @GetMapping("/projeto")
    public List<Projeto> getAllProjetos() {
        return projetoService.findAll();
    }

    // Busca projetos por ID
    @GetMapping("/projeto/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.findById(id);
        if (projeto.isPresent()) {
            return ResponseEntity.ok(projeto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria
    @PostMapping("/projeto")
     public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
         Projeto savedProjeto = projetoService.save(projeto);
         return new ResponseEntity<>(savedProjeto, HttpStatus.CREATED);
         
     }
    // Associa
     @PostMapping("/projeto/pessoa-projetos")
     public ResponseEntity<?> associarPessoaProjeto(@RequestBody AssociacaoDTO dto) {
         projetoService.associarPessoaProjeto(dto.getPessoaId(), dto.getProjetoId());
         return ResponseEntity.ok().build();
     }
 

    // Atualiza
   @PutMapping("/projeto/{id}")
   public ResponseEntity<String> updateProjeto(@PathVariable Long id, @RequestBody Projeto projetoDetails) {
       return projetoService.updateProjeto(id, projetoDetails)
               .map(proejtoAtualizada -> ResponseEntity.ok("Projeto atualizado com sucesso."))
               .orElseGet(() -> ResponseEntity.notFound().build());
   }
   // Deleta
   @DeleteMapping("/projeto/{id}")
   public ResponseEntity<String> deleteProjeto(@PathVariable Long id) {
       if (projetoService.deleteProjeto(id)) {
           return ResponseEntity.ok("Projeto apagado com sucesso.");
       } else {
           return ResponseEntity.notFound().build();
       }
   }
}
