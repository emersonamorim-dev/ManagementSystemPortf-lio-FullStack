package com.codev.BackendCodGroup.services;

import com.codev.BackendCodGroup.models.Membro;
import com.codev.BackendCodGroup.services.repository.MembroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MembroServiceTest {

    @Mock
    private MembroRepository membroRepository;

    @InjectMocks
    private MembroService membroService;

    private Membro membro;

    @BeforeEach
    void setUp() {
        membro = new Membro();
        membro.setId(1L);
        membro.setNome("Emerson Amorim");
        membro.setAtribuicao("Full Stack");
    }

    @Test
    void getAllMembros_ShouldReturnAllMembros() {
        when(membroRepository.findAll()).thenReturn(Arrays.asList(membro));
        List<Membro> result = membroService.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(membroRepository).findAll();
    }

    @Test
    void getMembroById_WhenMembroExists_ShouldReturnMembro() {
        when(membroRepository.findById(1L)).thenReturn(Optional.of(membro));
        Optional<Membro> result = membroService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(membro, result.get());
        verify(membroRepository).findById(1L);
    }

    @Test
    void saveMembro_ShouldSaveAndReturnMembro() {
        when(membroRepository.save(any(Membro.class))).thenReturn(membro);
        Membro savedMembro = membroService.save(new Membro());
        assertNotNull(savedMembro);
        verify(membroRepository).save(any(Membro.class));
    }

    @Test
    void updateMembro_WhenMembroExists_ShouldUpdateAndReturnMembro() {
        Long membroId = 1L;
        Membro existingMembro = new Membro();
        existingMembro.setNome("Emerson Amorim");
    
        Membro updatedDetails = new Membro();
        updatedDetails.setNome("Emerson Amorim Atualizado");
    
        when(membroRepository.findById(membroId)).thenReturn(Optional.of(existingMembro));
        when(membroRepository.save(any(Membro.class))).thenAnswer(invocation -> {
            Membro membroToUpdate = invocation.getArgument(0);

            existingMembro.setNome(membroToUpdate.getNome()); 
            return existingMembro; 
        });
    
        Optional<Membro> result = membroService.updateMembro(membroId, updatedDetails);
    
        assertTrue(result.isPresent());
        assertEquals("Emerson Amorim", result.get().getNome());
        verify(membroRepository).save(any(Membro.class)); 
        verify(membroRepository).findById(membroId); 
    }
    

    @Test
    void deleteMembro_WhenMembroxists_ShouldReturnTrue() {
        when(membroRepository.existsById(1L)).thenReturn(true);
        boolean isDeleted = membroService.deleteMembro(1L);
        assertTrue(isDeleted);
        verify(membroRepository).deleteById(1L);
    }

    @Test
    void deleteMembro_WhenMembroDoesNotExist_ShouldReturnFalse() {
        when(membroRepository.existsById(1L)).thenReturn(false);
        boolean isDeleted = membroService.deleteMembro(1L);
        assertFalse(isDeleted);
        verify(membroRepository, never()).deleteById(1L);
    }
}

