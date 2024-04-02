package com.codev.BackendCodGroup.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.codev.BackendCodGroup.models.Projeto;
import com.codev.BackendCodGroup.services.ProjetoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Projeto projeto;

   @BeforeEach
   void setUp() {
       projeto = new Projeto();
       // Add more properties as needed
   }

   @SuppressWarnings("null")
@Test
   public void getAllProjetos_ShouldReturnAllProjetos() throws Exception {
       when(projetoService.findAll()).thenReturn(Arrays.asList(projeto));
   
       mockMvc.perform(get("/api/projeto"))
               .andExpect(status().isOk())
               .andExpect(content().contentType((MediaType) MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].id").value(projeto.getId()));
   
       verify(projetoService, times(1)).findAll();
   }

    @SuppressWarnings("null")
    @Test
    public void getProjetoById_WhenProjetoExists_ShouldReturnProjeto() throws Exception {
        when(projetoService.findById(1L)).thenReturn(Optional.of(projeto));

        mockMvc.perform(get("/api/projeto/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(projeto.getId()));

        verify(projetoService, times(1)).findById(1L);
    }

    @Test
    public void getProjetoById_WhenProjetoDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(projetoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/projeto/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(projetoService, times(1)).findById(1L);
    }

    @SuppressWarnings("null")
    @Test
    public void createProjeto_ShouldCreateProjetoAndReturnCreated() throws Exception {
        when(projetoService.save(any(Projeto.class))).thenReturn(projeto);

        mockMvc.perform(post("/api/projeto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projeto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(projeto.getId()));

        verify(projetoService, times(1)).save(any(Projeto.class));
    }

}
