package com.codev.BackendCodGroup.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.codev.BackendCodGroup.models.Membro;
import com.codev.BackendCodGroup.services.MembroService;

@SpringBootTest
class MembroControllerTest {

    @Autowired
    private MembroController membroController;

    @MockBean
    private MembroService membroService;

    @Test
    void getAllMembros_ShouldReturnStatus200_WhenMemberExists() {
        // Arrange
        List<Membro> expectedMembers = List.of(
        new Membro(1L, "Emerson", "Amorim", "emerson_tecno@hotmail.com"),
        new Membro(2L, "Emerson", "Dev", "emerson_tecno@hotmail.com"));
        when(membroService.findAll()).thenReturn(expectedMembers);

        // Act
        ResponseEntity<List<Membro>> response = ResponseEntity.ok(expectedMembers);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedMembers, response.getBody());

    }

    @Test
    void getMembroById_ShouldReturnStatus200_WhenMemberExists() {
        // Arrange
        Long id = 1L;
        Membro expectedMember = new Membro(id, "Emerson", "Dev", "emerson_tecno@hotmail.com");
        when(membroService.findById(id)).thenReturn(Optional.of(expectedMember));

        // Act
        ResponseEntity<Membro> response = membroController.getMembroById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedMember, response.getBody());
    }

    @Test
    void createMembro_ShouldReturnStatus201_WhenMemberCreated() {
        // Arrange
        Membro newMember = new Membro("Emerson", "Amorim", "emerson_tecno@hotmail.com");
        when(membroService.save(newMember)).thenReturn(newMember);

        // Act
        ResponseEntity<Membro> response = membroController.createMembro(newMember);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newMember, response.getBody());
    }

    @Test
    void updateMembro_ShouldReturnStatus200_WhenMemberUpdated() {
        // Arrange
        Long id = 1L;
        Membro updatedMember = new Membro(id, "Emerson", "Amorim", "emerson_tecno@hotmail.com");
        when(membroService.updateMembro(id, updatedMember)).thenReturn(Optional.of(updatedMember));

        // Act
        ResponseEntity<?> response = membroController.updateMembro(id, updatedMember);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Membro atualizado com sucesso.", response.getBody());
    }

    @Test
    void deleteMembro_ShouldReturnStatus200_WhenMemberDeleted() {
        // Arrange
        Long id = 1L;
        when(membroService.deleteMembro(id)).thenReturn(true);

        // Act
        ResponseEntity<String> response = membroController.deleteMembro(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Membro apagado com sucesso.", response.getBody());
    }
}