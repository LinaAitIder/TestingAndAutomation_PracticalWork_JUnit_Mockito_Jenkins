package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class LivreTest {
    
    @Test
    public void testLivreUsesLocalDateForPublicationDate() {
        Livre livre = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        
        assertNotNull(livre.getDatePublication());
        assertEquals(1949, livre.getDatePublication().getYear());
        assertEquals(1949, livre.getAnneePublication());
    }
    
    @Test
    public void testDateValidationInRange() {
        assertDoesNotThrow(() -> {
            new Livre("Livre Moderne", "Auteur", 2000, "123456789");
        });
        
        assertDoesNotThrow(() -> {
            new Livre("Livre Ancien", "Auteur", 1500, "123456790");
        });
        
        assertDoesNotThrow(() -> {
            new Livre("Livre Actuel", "Auteur", LocalDate.now().getYear(), "123456791");
        });
    }
    
    @Test
    public void testPublicationDateValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Livre Trop Ancien", "Auteur", 999, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Livre Futur", "Auteur", LocalDate.now().getYear() + 1, "123456789");
        });
        
        assertDoesNotThrow(() -> {
            new Livre("Livre Valide", "Auteur", 1500, "123456789");
        });
    }
    
    @Test
    public void testCompatibilityWithOldTests() {
        Livre livre = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        
        assertEquals("1984", livre.getTitre());
        assertEquals("George Orwell", livre.getAuteur());
        assertEquals(1949, livre.getAnneePublication());
        assertEquals("978-0-452-28423-4", livre.getIsbn());
        
        assertEquals(1949, livre.getDatePublication().getYear());
        assertEquals(LocalDate.of(1949, 1, 1), livre.getDatePublication());
    }
    
    @Test
    public void testPerformanceWithNewDateRepresentation() {
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10000; i++) {
            Livre livre = new Livre("Livre " + i, "Auteur " + i, 1900 + (i % 120), "ISBN" + i);
            assertNotNull(livre);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        assertTrue(duration < 5000, "La création de 10000 livres avec LocalDate devrait prendre moins de 5 secondes");
    }
    
    @Test
    public void testExceptionHandlingInMethods() {
        Livre livre = new Livre("Test", "Auteur", 2000, "123456789");
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setAnneePublication(999);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setAnneePublication(LocalDate.now().getYear() + 1);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setDatePublication(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setDatePublication(LocalDate.of(500, 1, 1));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setDatePublication(LocalDate.of(LocalDate.now().getYear() + 1, 1, 1));
        });
    }
    
    @Test
    public void testExceptionsThrownInAppropriateSituations() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", 500, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", LocalDate.now().getYear() + 5, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", (LocalDate) null, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", LocalDate.of(500, 1, 1), "123456789");
        });
    }
    
    @Test
    public void testComplexEdgeCases() {
        assertDoesNotThrow(() -> {
            new Livre("Livre An Mil", "Auteur", 1000, "123456789");
        });
        
        assertDoesNotThrow(() -> {
            new Livre("Livre Actuel", "Auteur", LocalDate.now().getYear(), "123456789");
        });
        
        assertDoesNotThrow(() -> {
            new Livre("Livre An Mil", "Auteur", LocalDate.of(1000, 1, 1), "123456789");
        });
        
        assertDoesNotThrow(() -> {
            new Livre("Livre Actuel", "Auteur", LocalDate.of(LocalDate.now().getYear(), 12, 31), "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Livre Presque An Mil", "Auteur", 999, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Livre Presque Futur", "Auteur", LocalDate.now().getYear() + 1, "123456789");
        });
    }
    
    @Test
    public void testIsAncienMethod() {
        Livre livreAncien = new Livre("Bible", "Auteurs multiples", 1455, "000000001");
        assertTrue(livreAncien.isAncien());
        
        Livre livreModerne = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        assertFalse(livreModerne.isAncien());
    }
    
    @Test
    public void testEqualityWithLocalDate() {
        Livre livre1 = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        Livre livre2 = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        
        assertEquals(livre1, livre2);
        assertEquals(livre1.hashCode(), livre2.hashCode());
    }
}