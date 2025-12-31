package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class LivreTest {
    // Q1 : Verification de creation d'instance Livre avec des val specifique
    @Test
    public void creatingBookWithSpecificValues_Test(){
        // Arrange
        String title = "Harry Potter";
        String author = "J.K. Rowling";
        int annee = 1997;
        String isbn = "123456789";
        // Act
        Livre livre = new Livre(title, author, annee, isbn);
        // Assert
        // Comparaison de chaque valeur
        assertEquals(title, livre.getTitre(), "Le titre devrait être 'Harry Potter'");
        assertEquals(author, livre.getAuteur(), "L'auteur devrait être 'J.K. Rowling'");
        assertEquals(annee, livre.getAnneePublication(), "L'année devrait être 1997");
        assertEquals(isbn, livre.getIsbn(), "L'ISBN devrait être '123456789'");
    }
    
    // Q2 : Verification des methodes getters et setters de la classe Livre
    @Test
    public void gettersAndSettersMethodVerification_Test(){
        // Arrange
        String title = "Harry Potter";
        String author = "J.K. Rowling";
        int annee = 1997;
        String isbn = "123456789";
        Livre livre = new Livre();
        
        // Test setters
        livre.setTitre(title);
        livre.setAuteur(author);
        livre.setIsbn(isbn);
        livre.setAnneePublication(annee);
        
        // Verification des getters
        assertEquals(livre.getTitre(), title);
        assertEquals(livre.getAuteur(), author);
        assertEquals(livre.getIsbn(), isbn);
        assertEquals(livre.getAnneePublication(), annee);
    }
    
    // Q3 : Verification d'egalite de deux instances de la classe livre
    @Test
    public void equalityInstancesVerification_Test(){
        // Arrange
        Livre instance1 = new Livre();
        Livre instance2 = new Livre();
        // Act
        instance1.setTitre("Harry Potter");
        instance2.setTitre("Harry Potter");
        // Assert
        assertEquals(instance1, instance2);
    }
    
    // Q4 : Verification de la validation des données
    @Test
    public void extremeCases_Test(){
        // Valeur Ngtv
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("1984", "George Orwell", -1949, "987654321");
        }, "L'année de publication négative devrait être rejetée");
        
        // Valeur Null
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("1984", "George Orwell", 0, "987654321");
        }, "L'année de publication nulle devrait être rejetée");
        
        // Titre vide
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("", "George Orwell", 1949, "987654321");
        }, "Le titre vide devrait être rejeté");
        
        // Auteur Vide
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("1984", "", 1949, "987654321");
        }, "L'auteur vide devrait être rejeté");
        
        // ISBN vide
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("1984", "George Orwell", 1949, "");
        }, "L'ISBN vide devrait être rejeté");
    }
    
    // Q6 - Tests de performance de creation de grand nombres d'instances (Important : Delai raisonable)
    @Test
    public void performanceCreation_Test(){
        long startTime = System.currentTimeMillis();
        int numberOfInstances = 10000;
        
        Livre[] livres = new Livre[numberOfInstances];
        for (int i = 0; i < numberOfInstances; i++) {
            livres[i] = new Livre("Livre " + i, "Auteur " + i, 2000 + (i % 50), "ISBN" + i);
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        // La creation doit etre rapide (moins de 5 secondes pour 10000 instances)
        assertTrue(duration < 5000, "La création de " + numberOfInstances + " instances devrait prendre moins de 5 secondes, mais a pris " + duration + " ms");
    }
    
    // Q7 - Verification de l'interaction de livre avec systeme de gestion de bib
    @Test
    public void interactionVerification_Test(){
        // Creation de la bibliotheque
        Bibliothèque bibliotheque = new Bibliothèque();
        Livre livre1 = new Livre("1984", "George Orwell", 1949, "978-0-452-28423-4");
        Livre livre2 = new Livre("Le Meilleur des mondes", "Aldous Huxley", 1932, "978-2-266-11360-1");
        
        // Ajout de livres
        bibliotheque.ajouterLivre(livre1);
        bibliotheque.ajouterLivre(livre2);
        
        // Recherche par titre
        Livre foundByTitle = bibliotheque.rechercherParTitre("1984");
        assertNotNull(foundByTitle, "Le livre devrait être trouvé par titre");
        assertEquals(livre1, foundByTitle);
        
        // Recherche par auteur
        Livre foundByAuthor = bibliotheque.rechercherParAuteur("George Orwell");
        assertNotNull(foundByAuthor, "Le livre devrait être trouvé par auteur");
        assertEquals(livre1, foundByAuthor);
        
        // Recherche par ISBN
        Livre foundByISBN = bibliotheque.rechercherParISBN("978-0-452-28423-4");
        assertNotNull(foundByISBN, "Le livre devrait être trouvé par ISBN");
        assertEquals(livre1, foundByISBN);
        
        // Retrait d'un livre
        bibliotheque.retirerLivre(livre1);
        Livre notFound = bibliotheque.rechercherParTitre("1984");
        assertNull(notFound, "Le livre devrait être supprimé de la bibliothèque");
    }
    
    // Q8 - Verification des proprietes de la class ont encap et ne peuvent pas etre modifiees depuis ext
    @Test
    public void propertiesEncapsulation_Test(){
        Livre livre = new Livre("1984", "George Orwell", 1949, "987654321");
        
        // Verification : Modification peut se faire seulement par setters
        assertEquals("1984", livre.getTitre());
        assertEquals("George Orwell", livre.getAuteur());
        assertEquals(1949, livre.getAnneePublication());
        assertEquals("987654321", livre.getIsbn());
        
        // Modification en utilisant les setters
        livre.setTitre("Animal Farm");
        livre.setAuteur("George Orwell");
        livre.setAnneePublication(1945);
        livre.setIsbn("987654322");
        
        // Verification des nv valeurs
        assertEquals("Animal Farm", livre.getTitre());
        assertEquals("George Orwell", livre.getAuteur());
        assertEquals(1945, livre.getAnneePublication());
        assertEquals("987654322", livre.getIsbn());
        
    }
    
    // Q9 - Verification : pas de fuite de mem lors de creation ou destruction d'instance de la classe Livre
    @Test
    public void noMemoryLeaks_Test(){
        // Memoire Initiale
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); 
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        
        // Creation un grand nombre de livres
        int numObjects = 50000;
        Livre[] livres = new Livre[numObjects];
        for (int i = 0; i < numObjects; i++) {
            livres[i] = new Livre("Livre " + i, "Auteur " + i, 2000 + (i % 50), "ISBN" + i);
        }
        
        // Nettoyage des references
        livres = null;
        
        // Suggestion de garbage collection
        runtime.gc();
        
        // Attente d'une petite duree pour que la garbage collection se produise
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Memoire finale
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();
        
        // La memoire utilisation devrait etre significativement plus elevee apres la GC
        assertTrue(finalMemory - initialMemory < 5 * 1024 * 1024);
    }
    
    // Q10 - Tests de compatibilite (JUnit 4 et JUnit 5)
    @Test
    public void compatibility_Test(){
        // Le test marchent en JUnit 5
        Livre livre = new Livre("Test", "Auteur", 2023, "123456789");
        assertNotNull(livre, "Le livre devrait être correctement instancié avec JUnit 5");
        
        // Additional compatibility check
        assertEquals("Test", livre.getTitre());
        assertEquals("Auteur", livre.getAuteur());
        assertEquals(2023, livre.getAnneePublication());
        assertEquals("123456789", livre.getIsbn());
    }
}