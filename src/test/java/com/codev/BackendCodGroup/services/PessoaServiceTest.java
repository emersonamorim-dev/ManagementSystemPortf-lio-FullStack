package com.codev.BackendCodGroup.services;

import com.codev.BackendCodGroup.models.Pessoa;
import com.codev.BackendCodGroup.services.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Emerson Amorim");
        pessoa.setCpf("12345678900");
  
    }

    @Test
    void getAllPessoas_ShouldReturnAllPessoas() {
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa));
        List<Pessoa> result = pessoaService.getAllPessoas();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(pessoaRepository).findAll();
    }

    @Test
    void getPessoaById_WhenPessoaExists_ShouldReturnPessoa() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        Optional<Pessoa> result = pessoaService.getPessoaById(1L);
        assertTrue(result.isPresent());
        assertEquals(pessoa, result.get());
        verify(pessoaRepository).findById(1L);
    }

    @Test
    void savePessoa_ShouldSaveAndReturnPessoa() {
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        Pessoa savedPessoa = pessoaService.savePessoa(new Pessoa());
        assertNotNull(savedPessoa);
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void updatePessoa_WhenPessoaExists_ShouldUpdateAndReturnPessoa() {
        Pessoa updatedDetails = new Pessoa();
        updatedDetails.setNome("Emerson Amorim Atualizado");
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Optional<Pessoa> result = pessoaService.updatePessoa(1L, updatedDetails);

        assertTrue(result.isPresent());
        assertEquals("Emerson Amorim Atualizado", result.get().getNome());
        verify(pessoaRepository).findById(1L);
        verify(pessoaRepository).save(pessoa);
    }

    @Test
    void deletePessoa_WhenPessoaExists_ShouldReturnTrue() {
        when(pessoaRepository.existsById(1L)).thenReturn(true);
        boolean isDeleted = pessoaService.deletePessoa(1L);
        assertTrue(isDeleted);
        verify(pessoaRepository).deleteById(1L);
    }

    @Test
    void deletePessoa_WhenPessoaDoesNotExist_ShouldReturnFalse() {
        when(pessoaRepository.existsById(1L)).thenReturn(false);
        boolean isDeleted = pessoaService.deletePessoa(1L);
        assertFalse(isDeleted);
        verify(pessoaRepository, never()).deleteById(1L);
    }
}
