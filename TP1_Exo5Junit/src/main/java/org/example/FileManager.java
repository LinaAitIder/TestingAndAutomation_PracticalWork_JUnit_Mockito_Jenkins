package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    
    /**
     * Lit le contenu d'un fichier et le retourne sous forme de chaîne
     * @param filePath Le chemin du fichier à lire
     * @return Le contenu du fichier sous forme de chaîne
     * @throws IOException si le fichier n'existe pas ou ne peut pas être lu
     */
    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                if (reader.ready()) { // Vérifie s'il y a plus de lignes à lire
                    content.append("\n");
                }
            }
        }
        
        return content.toString();
    }
}