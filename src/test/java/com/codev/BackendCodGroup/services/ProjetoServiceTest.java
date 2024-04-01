package com.codev.BackendCodGroup.services;

import com.codev.BackendCodGroup.models.Projeto;
import com.codev.BackendCodGroup.services.repository.ProjetoRepository;
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
public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    private Projeto projeto;

    @BeforeEach
    void setUp() {
        projeto = new Projeto();
        projeto.setId(1L);
        // Configurar outros campos necessários do Projeto aqui
    }

    @Test
    void findAll_ShouldReturnAllProjetos() {
        when(projetoRepository.findAll()).thenReturn(Arrays.asList(projeto));
        List<Projeto> result = projetoService.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(projetoRepository).findAll();
    }

    @Test
    void findById_WhenProjetoExists_ShouldReturnProjeto() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        Optional<Projeto> result = projetoService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(projeto, result.get());
        verify(projetoRepository).findById(1L);
    }

    @Test
    void save_ShouldSaveAndReturnProjeto() {
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);
        Projeto savedProjeto = projetoService.save(new Projeto());
        assertNotNull(savedProjeto);
        verify(projetoRepository).save(any(Projeto.class));
    }

    @Test
    void updateProjeto_WhenProjetoExists_ShouldUpdateAndReturnProjeto() {
        Projeto updatedDetails = new Projeto();
        updatedDetails.setNome("Projeto Atualizado");
        // Atualize outros campos conforme necessário

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        Optional<Projeto> result = projetoService.updateProjeto(1L, updatedDetails);

        assertTrue(result.isPresent());
        assertEquals("Projeto Atualizado", result.get().getNome());
        // Verifique a atualização de outros campos conforme necessário
        verify(projetoRepository).findById(1L);
        verify(projetoRepository).save(projeto);
    }

    @Test
    void deleteProjeto_WhenProjetoExists_ShouldReturnTrue() {
        when(projetoRepository.existsById(1L)).thenReturn(true);
        boolean isDeleted = projetoService.deleteProjeto(1L);
        assertTrue(isDeleted);
        verify(projetoRepository).deleteById(1L);
    }

    @Test
    void deleteProjeto_WhenProjetoDoesNotExist_ShouldReturnFalse() {
        when(projetoRepository.existsById(1L)).thenReturn(false);
        boolean isDeleted = projetoService.deleteProjeto(1L);
        assertFalse(isDeleted);
        verify(projetoRepository, never()).deleteById(1L);
    }
}
