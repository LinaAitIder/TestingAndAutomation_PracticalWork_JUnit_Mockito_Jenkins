package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Livre {
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private String isbn;

    public Livre(String titre, String auteur, int anneePublication, String isbn) {
        // Validation de l'année avant de créer la date
        if (anneePublication < 1000 || anneePublication > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("L'année de publication doit être comprise entre 1000 et l'année actuelle");
        }
        
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être vide");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
        }
        
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = LocalDate.of(anneePublication, 1, 1); // Par défaut au 1er janvier
        this.isbn = isbn;
    }
    
    public Livre(String titre, String auteur, LocalDate datePublication, String isbn) {
        if (datePublication == null) {
            throw new IllegalArgumentException("La date de publication ne peut pas être nulle");
        }
        
        if (isDateNotReasonable(datePublication)) {
            throw new IllegalArgumentException("La date de publication doit être comprise entre l'an 1000 et la date actuelle");
        }
        
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être vide");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
        }
        
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.isbn = isbn;
    }

    public Livre() {
        this.titre = "";
        this.auteur = "";
        this.datePublication = LocalDate.now();
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
        return datePublication.getYear();
    }
    
    public LocalDate getDatePublication() {
        return datePublication;
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
        if (anneePublication < 1000 || anneePublication > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("L'année de publication doit être comprise entre 1000 et l'année actuelle");
        }
        this.datePublication = LocalDate.of(anneePublication, 1, 1); // Par défaut au 1er janvier
    }
    
    public void setDatePublication(LocalDate datePublication) {
        if (datePublication == null) {
            throw new IllegalArgumentException("La date de publication ne peut pas être nulle");
        }
        if (isDateNotReasonable(datePublication)) {
            throw new IllegalArgumentException("La date de publication doit être comprise entre l'an 1000 et la date actuelle");
        }
        this.datePublication = datePublication;
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
        
        if (!datePublication.equals(livre.datePublication)) return false;
        if (!titre.equals(livre.titre)) return false;
        if (!auteur.equals(livre.auteur)) return false;
        return isbn.equals(livre.isbn);
    }
    
    @Override
    public int hashCode() {
        int result = titre.hashCode();
        result = 31 * result + auteur.hashCode();
        result = 31 * result + datePublication.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }
    
    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", datePublication=" + datePublication +
                ", isbn='" + isbn + '\'' +
                '}';
    }
    
    private boolean isDateNotReasonable(LocalDate date) {
        if (date == null) {
            return true;
        }
        int year = date.getYear();
        int currentYear = LocalDate.now().getYear();
        return year < 1000 || year > currentYear;
    }
    
    public boolean isAncien() {
        // Vérifie si le livre est ancien (publié avant 1900)
        return datePublication.getYear() < 1900;
    }

}

class Bibliotheque {
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
    
    public boolean estVide() {
        return livres.isEmpty();
    }
    
    public int getNombreLivres() {
        return livres.size();
    }
}