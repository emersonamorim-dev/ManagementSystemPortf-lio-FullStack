package com.codev.BackendCodGroup.controllers;

import com.codev.BackendCodGroup.models.Pessoa;
import com.codev.BackendCodGroup.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @Test
    void getAllPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa());
        pessoas.add(new Pessoa());
        when(pessoaService.getAllPessoas()).thenReturn(pessoas);

        List<Pessoa> result = pessoaController.getAllPessoas();

        assertEquals(2, result.size());
        assertEquals("Emerson", result.get(0).getNome());
        assertEquals("Amorim", result.get(1).getNome());
    }

    @SuppressWarnings("null")
    @Test
    void getPessoaById() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.getPessoaById(1L)).thenReturn(Optional.of(pessoa));

        ResponseEntity<Pessoa> result = pessoaController.getPessoaById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Emerson", result.getBody().getNome());
    }

    @Test
    void getPessoaById_NotFound() {
        when(pessoaService.getPessoaById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Pessoa> result = pessoaController.getPessoaById(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }


    @Test
    void createPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.savePessoa(pessoa)).thenReturn(pessoa);
    
        ResponseEntity<Pessoa> result = pessoaController.createPessoa(pessoa);
    
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    
        // Check if the body is present before accessing its value
        assertNotNull(result.getBody());
    
        // Print the actual and expected names
        String expectedName = "Emerson";
        @SuppressWarnings("null")
        String actualName = result.getBody().getNome();
        System.out.println("Expected Name: " + expectedName);
        System.out.println("Actual Name: " + actualName);
    
        // Check if the actual name matches the expected name
        assertEquals(expectedName, actualName);
    }
    
    
    @Test
    void updatePessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.updatePessoa(1L, pessoa)).thenReturn(Optional.of(pessoa));

        ResponseEntity<String> result = pessoaController.updatePessoa(1L, pessoa);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Pessoa atualizada com sucesso.", result.getBody());
    }

    @Test
    void updatePessoa_NotFound() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.updatePessoa(1L, pessoa)).thenReturn(Optional.empty());

        ResponseEntity<String> result = pessoaController.updatePessoa(1L, pessoa);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deletePessoa() {
        when(pessoaService.deletePessoa(1L)).thenReturn(true);

        ResponseEntity<String> result = pessoaController.deletePessoa(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Pessoa apagada com sucesso.", result.getBody());
    }

    @Test
    void deletePessoa_NotFound() {
        when(pessoaService.deletePessoa(1L)).thenReturn(false);

        ResponseEntity<String> result = pessoaController.deletePessoa(1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
