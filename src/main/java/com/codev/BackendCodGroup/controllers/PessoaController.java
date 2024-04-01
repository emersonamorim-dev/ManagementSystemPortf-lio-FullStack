package com.codev.BackendCodGroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codev.BackendCodGroup.models.Pessoa;
import com.codev.BackendCodGroup.services.PessoaService;
import java.util.List;

@RestController
@RequestMapping("/api")  
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoa")
    public List<Pessoa> getAllPessoas() {
        return pessoaService.getAllPessoas();
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        return pessoaService.getPessoaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/pessoa")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = pessoaService.savePessoa(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

   @PutMapping("/pessoa/{id}")
   public ResponseEntity<String> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetails) {
       return pessoaService.updatePessoa(id, pessoaDetails)
               .map(pessoaAtualizada -> ResponseEntity.ok("Pessoa atualizada com sucesso."))
               .orElseGet(() -> ResponseEntity.notFound().build());
   }

   @DeleteMapping("/pessoa/{id}")
   public ResponseEntity<String> deletePessoa(@PathVariable Long id) {
       if (pessoaService.deletePessoa(id)) {
           return ResponseEntity.ok("Pessoa apagada com sucesso.");
       } else {
           return ResponseEntity.notFound().build();
       }
   }


}
