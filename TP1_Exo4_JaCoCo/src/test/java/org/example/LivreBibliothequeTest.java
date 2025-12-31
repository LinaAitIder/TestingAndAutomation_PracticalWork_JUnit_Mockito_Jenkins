package org.example;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LivreBibliothequeTest {
    
    private Livre livre;
    private Bibliotheque bibliotheque;
    
    @BeforeEach
    void setUp() {
        livre = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        bibliotheque = new Bibliotheque();
    }
    
    @Test
    void testLivreConstructor() {
        assertEquals("1984", livre.getTitre());
        assertEquals("George Orwell", livre.getAuteur());
        assertEquals(1949, livre.getAnneePublication());
        assertEquals("978-0-452-28423-4", livre.getIsbn());
        assertEquals(LocalDate.of(1949, 1, 1), livre.getDatePublication());
    }
    
    @Test
    void testLivreGettersAndSetters() {
        livre.setTitre("Animal Farm");
        assertEquals("Animal Farm", livre.getTitre());
        
        livre.setAuteur("George Orwell");
        assertEquals("George Orwell", livre.getAuteur());
        
        livre.setAnneePublication(1945);
        assertEquals(1945, livre.getAnneePublication());
        
        livre.setIsbn("978-0-451-00263-0");
        assertEquals("978-0-451-00263-0", livre.getIsbn());
    }
    
    @Test
    void testLivreDatePublication() {
        livre.setDatePublication(LocalDate.of(1950, 5, 15));
        assertEquals(1950, livre.getAnneePublication());
        assertEquals(LocalDate.of(1950, 5, 15), livre.getDatePublication());
    }
    
    @Test
    void testLivreIsAncien() {
        assertFalse(livre.isAncien());
        Livre livreModerne = new Livre("Livre Moderne", "Auteur", 2020, "123456789");
        assertFalse(livreModerne.isAncien());
    }
    
    @Test
    void testLivreEqualsAndHashCode() {
        Livre livre1 = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        Livre livre2 = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        
        assertEquals(livre1, livre2);
        assertEquals(livre1.hashCode(), livre2.hashCode());
        
        Livre livre3 = new Livre("1985", "George Orwell", 1949, "978-0-452-28423-4");
        assertNotEquals(livre1, livre3);
    }
    
    @Test
    void testLivreValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", 999, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", LocalDate.now().getYear() + 1, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("", "Auteur", 2000, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "", 2000, "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", 2000, "");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setTitre("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setAuteur("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setIsbn("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setAnneePublication(999);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setDatePublication(null);
        });
    }
    
    @Test
    void testLivreToString() {
        String expected = "Livre{titre='1984', auteur='George Orwell', datePublication=1949-01-01, isbn='978-0-452-28423-4'}";
        assertTrue(livre.toString().contains("1984"));
        assertTrue(livre.toString().contains("George Orwell"));
    }
    
    @Test
    void testBibliothequeAddAndRemove() {
        assertTrue(bibliotheque.estVide());
        assertEquals(0, bibliotheque.getNombreLivres());
        
        bibliotheque.ajouterLivre(livre);
        assertFalse(bibliotheque.estVide());
        assertEquals(1, bibliotheque.getNombreLivres());
        
        bibliotheque.retirerLivre(livre);
        assertTrue(bibliotheque.estVide());
        assertEquals(0, bibliotheque.getNombreLivres());
    }
    
    @Test
    void testBibliothequeSearchByTitle() {
        bibliotheque.ajouterLivre(livre);
        
        Livre found = bibliotheque.rechercherParTitre("1984");
        assertNotNull(found);
        assertEquals("1984", found.getTitre());
        
        Livre notFound = bibliotheque.rechercherParTitre("Non Existant");
        assertNull(notFound);
    }
    
    @Test
    void testBibliothequeSearchByAuthor() {
        bibliotheque.ajouterLivre(livre);
        
        Livre found = bibliotheque.rechercherParAuteur("George Orwell");
        assertNotNull(found);
        assertEquals("George Orwell", found.getAuteur());
        
        Livre notFound = bibliotheque.rechercherParAuteur("Autre Auteur");
        assertNull(notFound);
    }
    
    @Test
    void testBibliothequeSearchByISBN() {
        bibliotheque.ajouterLivre(livre);
        
        Livre found = bibliotheque.rechercherParISBN("978-0-452-28423-4");
        assertNotNull(found);
        assertEquals("978-0-452-28423-4", found.getIsbn());
        
        Livre notFound = bibliotheque.rechercherParISBN("000-0-000-00000-0");
        assertNull(notFound);
    }
    
    @Test
    void testBibliothequeGetLivres() {
        bibliotheque.ajouterLivre(livre);
        
        List<Livre> livres = bibliotheque.getLivres();
        assertEquals(1, livres.size());
        assertEquals(livre, livres.get(0));
    }
    
    @Test
    void testLivreWithLocalDateConstructor() {
        LocalDate date = LocalDate.of(2020, 12, 25);
        Livre livreAvecDate = new Livre("Test Livre", "Auteur Test", date, "987654321");
        
        assertEquals("Test Livre", livreAvecDate.getTitre());
        assertEquals("Auteur Test", livreAvecDate.getAuteur());
        assertEquals(2020, livreAvecDate.getAnneePublication());
        assertEquals(date, livreAvecDate.getDatePublication());
    }
    
    @Test
    void testLivreValidationWithLocalDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", LocalDate.of(500, 1, 1), "123456789");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", LocalDate.of(LocalDate.now().getYear() + 1, 1, 1), "123456789");
        });
    }
}