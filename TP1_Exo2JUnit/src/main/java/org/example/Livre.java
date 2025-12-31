package org.example;
import java.util.ArrayList;
import java.util.List;

class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private String isbn;

    public Livre(String titre, String auteur, int anneePublication, String isbn) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être vide");
        }
        if (anneePublication <= 0) {
            throw new IllegalArgumentException("L'année de publication doit être positive");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
        }
        
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
    }

    public Livre() {
        this.titre = "";
        this.auteur = "";
        this.anneePublication = 0;
        this.isbn = "";
    }

    // Getters et Setters

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        this.titre = titre;
    }
    public void setAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être vide");
        }
        this.auteur = auteur;
    }
    public void setAnneePublication(int anneePublication) {
        if (anneePublication <= 0) {
            throw new IllegalArgumentException("L'année de publication doit être positive");
        }
        this.anneePublication = anneePublication;
    }
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
        }
        this.isbn = isbn;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Livre livre = (Livre) obj;
        
        if (anneePublication != livre.anneePublication) return false;
        if (!titre.equals(livre.titre)) return false;
        if (!auteur.equals(livre.auteur)) return false;
        return isbn.equals(livre.isbn);
    }
    
    @Override
    public int hashCode() {
        int result = titre.hashCode();
        result = 31 * result + auteur.hashCode();
        result = 31 * result + anneePublication;
        result = 31 * result + isbn.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneePublication=" + anneePublication +
                ", isbn='" + isbn + '\'' +
                '}';
    }

}

class Bibliothèque {
    private List<Livre> livres = new ArrayList<>();

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public void retirerLivre(Livre livre) {
        livres.remove(livre);
    }

    public Livre rechercherParTitre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().equals(titre)) {
                return livre;
            }
        }
        return null;
    }

    public Livre rechercherParAuteur(String auteur) {
        for (Livre livre : livres) {
            if (livre.getAuteur().equals(auteur)) {
                return livre;
            }
        }
        return null;
    }

    public Livre rechercherParISBN(String isbn) {
        for (Livre livre : livres) {
            if (livre.getIsbn().equals(isbn)) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> getLivres() {
        return livres;
    }
}

