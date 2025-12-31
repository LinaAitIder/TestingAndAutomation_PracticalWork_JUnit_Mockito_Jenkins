package org.example.library;

import org.example.library.model.Livre;
import org.example.library.repository.LivreRepository;
import org.example.library.service.LivreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivreServiceTest {

    @Mock
    private LivreRepository livreRepository;

    @InjectMocks
    private LivreService livreService;

    private Livre livre;

    @BeforeEach
    void setUp() {
        livre = new Livre("Test Book", "Test Author", "123456789");
        livre.setId(1L);
    }

    @Test
    void getAllLivres_ShouldReturnAllLivres() {
        // Given
        List<Livre> livres = Arrays.asList(livre);
        when(livreRepository.findAll()).thenReturn(livres);

        // When
        List<Livre> result = livreService.getAllLivres();

        // Then
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitre());
        verify(livreRepository, times(1)).findAll();
    }

    @Test
    void getLivreById_WithExistingId_ShouldReturnLivre() {
        // Given
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));

        // When
        Optional<Livre> result = livreService.getLivreById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Test Book", result.get().getTitre());
        verify(livreRepository, times(1)).findById(1L);
    }

    @Test
    void getLivreById_WithNonExistingId_ShouldReturnEmpty() {
        // Given
        when(livreRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Optional<Livre> result = livreService.getLivreById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(livreRepository, times(1)).findById(1L);
    }

    @Test
    void addLivre_ShouldSaveAndReturnLivre() {
        // Given
        when(livreRepository.save(any(Livre.class))).thenReturn(livre);

        // When
        Livre result = livreService.addLivre(livre);

        // Then
        assertNotNull(result);
        assertEquals("Test Book", result.getTitre());
        verify(livreRepository, times(1)).save(any(Livre.class));
    }

    @Test
    void updateLivre_WithExistingId_ShouldUpdateAndReturnLivre() {
        // Given
        Livre updatedLivre = new Livre("Updated Book", "Updated Author", "987654321");
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));
        when(livreRepository.save(any(Livre.class))).thenReturn(updatedLivre);

        // When
        Livre result = livreService.updateLivre(1L, updatedLivre);

        // Then
        assertNotNull(result);
        assertEquals("Updated Book", result.getTitre());
        verify(livreRepository, times(1)).findById(1L);
        verify(livreRepository, times(1)).save(any(Livre.class));
    }

    @Test
    void updateLivre_WithNonExistingId_ShouldReturnNull() {
        // Given
        when(livreRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Livre result = livreService.updateLivre(1L, livre);

        // Then
        assertNull(result);
        verify(livreRepository, times(1)).findById(1L);
        verify(livreRepository, never()).save(any(Livre.class));
    }

    @Test
    void emprunterLivre_WithAvailableBook_ShouldSetDisponibleToFalse() {
        // Given
        livre.setDisponible(true);
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));
        when(livreRepository.save(any(Livre.class))).thenReturn(livre);

        // When
        livreService.emprunterLivre(1L);

        // Then
        verify(livreRepository, times(1)).findById(1L);
        verify(livreRepository, times(1)).save(any(Livre.class));
        assertFalse(livre.getDisponible());
    }

    @Test
    void retournerLivre_WithBorrowedBook_ShouldSetDisponibleToTrue() {
        // Given
        livre.setDisponible(false);
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));
        when(livreRepository.save(any(Livre.class))).thenReturn(livre);

        // When
        livreService.retournerLivre(1L);

        // Then
        verify(livreRepository, times(1)).findById(1L);
        verify(livreRepository, times(1)).save(any(Livre.class));
        assertTrue(livre.getDisponible());
    }
}