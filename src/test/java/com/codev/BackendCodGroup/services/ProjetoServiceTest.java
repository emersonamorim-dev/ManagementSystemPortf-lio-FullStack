package com.codev.BackendCodGroup.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.codev.BackendCodGroup.models.Pessoa;
import com.codev.BackendCodGroup.models.Projeto;
import com.codev.BackendCodGroup.services.repository.PessoaRepository;
import com.codev.BackendCodGroup.services.repository.ProjetoRepository;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(new Projeto());
        projetos.add(new Projeto());
        when(projetoRepository.findAll()).thenReturn(projetos);

        List<Projeto> result = projetoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(id);
        when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));

        Optional<Projeto> result = projetoService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testSave() {
        Projeto projeto = new Projeto();
        when(projetoRepository.save(projeto)).thenReturn(projeto);

        Projeto result = projetoService.save(projeto);

        assertNotNull(result);
    }

    @Test
    public void testUpdateProjeto() {
        Long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(id);
        Projeto projetoDetails = new Projeto();
        projetoDetails.setNome("Novo Nome");
        when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));
        when(projetoRepository.save(projeto)).thenReturn(projeto);

        Optional<Projeto> result = projetoService.updateProjeto(id, projetoDetails);

        assertTrue(result.isPresent());
        assertEquals("Novo Nome", result.get().getNome());
    }

    @Test
    public void testDeleteProjeto() {
        Long id = 1L;
        when(projetoRepository.existsById(id)).thenReturn(true);

        boolean result = projetoService.deleteProjeto(id);

        assertTrue(result);
        verify(projetoRepository).deleteById(id);
    }

    @Test
    public void testDeleteProjeto_NotFound() {
        Long id = 1L;
        when(projetoRepository.existsById(id)).thenReturn(false);

        boolean result = projetoService.deleteProjeto(id);

        assertFalse(result);
    }

    @Test
    public void testAssociarPessoaProjeto() {
        Long pessoaId = 1L;
        Long projetoId = 1L;
        Pessoa pessoa = new Pessoa();
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));
        when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));

        projetoService.associarPessoaProjeto(pessoaId, projetoId);

        assertTrue(projeto.getPessoas().contains(pessoa));
        verify(projetoRepository).save(projeto);
    }
}
