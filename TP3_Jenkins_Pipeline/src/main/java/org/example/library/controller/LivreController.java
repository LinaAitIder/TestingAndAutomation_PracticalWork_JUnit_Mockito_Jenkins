package org.example.library.controller;

import org.example.library.model.Livre;
import org.example.library.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    
    @Autowired
    private LivreService livreService;
    
    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return ResponseEntity.ok(livres);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        Optional<Livre> livre = livreService.getLivreById(id);
        return livre.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        Livre savedLivre = livreService.addLivre(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLivre);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre livreDetails) {
        Livre updatedLivre = livreService.updateLivre(id, livreDetails);
        if (updatedLivre != null) {
            return ResponseEntity.ok(updatedLivre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}/emprunter")
    public ResponseEntity<Void> emprunterLivre(@PathVariable Long id) {
        livreService.emprunterLivre(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/retourner")
    public ResponseEntity<Void> retournerLivre(@PathVariable Long id) {
        livreService.retournerLivre(id);
        return ResponseEntity.ok().build();
    }
}