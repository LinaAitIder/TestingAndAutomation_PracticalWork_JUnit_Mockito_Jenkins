package org.example.library.service;

import org.example.library.model.Livre;
import org.example.library.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    
    @Autowired
    private LivreRepository livreRepository;
    
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
    
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }
    
    public Livre addLivre(Livre livre) {
        return livreRepository.save(livre);
    }
    
    public Livre updateLivre(Long id, Livre livreDetails) {
        Optional<Livre> livreOptional = livreRepository.findById(id);
        if (livreOptional.isPresent()) {
            Livre livre = livreOptional.get();
            livre.setTitre(livreDetails.getTitre());
            livre.setAuteur(livreDetails.getAuteur());
            livre.setIsbn(livreDetails.getIsbn());
            livre.setDisponible(livreDetails.getDisponible());
            return livreRepository.save(livre);
        }
        return null;
    }
    
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
    
    public void emprunterLivre(Long id) {
        Optional<Livre> livreOptional = livreRepository.findById(id);
        if (livreOptional.isPresent()) {
            Livre livre = livreOptional.get();
            if (livre.getDisponible()) {
                livre.setDisponible(false);
                livreRepository.save(livre);
            }
        }
    }
    
    public void retournerLivre(Long id) {
        Optional<Livre> livreOptional = livreRepository.findById(id);
        if (livreOptional.isPresent()) {
            Livre livre = livreOptional.get();
            if (!livre.getDisponible()) {
                livre.setDisponible(true);
                livreRepository.save(livre);
            }
        }
    }
}