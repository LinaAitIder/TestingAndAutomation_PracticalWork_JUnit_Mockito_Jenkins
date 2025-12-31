package org.example;

public class Main {
    public static void main(String[] args) {
        // Création de livres
        Livre livre1 = new Livre("Harry Potter", "J.K. Rowling", 1997, "123456789");
        Livre livre2 = new Livre("1984", "George Orwell", 1949, "987654321");

        // Création de la bibliothèque
        Bibliothèque bibliothèque = new Bibliothèque();

        // Ajout de livres à la bibliothèque
        bibliothèque.ajouterLivre(livre1);
        bibliothèque.ajouterLivre(livre2);

        // Recherche de livres par titre
        Livre livreRecherche = bibliothèque.rechercherParTitre("Harry Potter");
        if (livreRecherche != null) {
            System.out.println("Livre trouvé : " + livreRecherche.getTitre());
        } else {
            System.out.println("Livre non trouvé.");
        }
    }
}
