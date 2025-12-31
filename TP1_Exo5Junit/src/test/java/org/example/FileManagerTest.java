package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileManagerTest {
    
    private FileManager fileManager;
    private File testFile;
    
    @BeforeEach
    public void setUp() throws IOException {
        fileManager = new FileManager();
        testFile = File.createTempFile("test", ".txt");

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Ligne 1\nLigne 2\nLigne 3");
        }
    }
    
    @AfterEach
    public void tearDown() {
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    public void testLectureFichierExistant() throws IOException {
        String content = fileManager.readFile(testFile.getAbsolutePath());
        assertEquals("Ligne 1\nLigne 2\nLigne 3", content);
    }
    
    @Test
    public void testLectureFichierInexistant() {
        String nonExistentFilePath = "fichier_inexistant.txt";
        assertThrows(IOException.class, () -> {
            fileManager.readFile(nonExistentFilePath);
        });
    }
    
    @Test
    public void testLectureFichierVide() throws IOException {
        File emptyFile = File.createTempFile("empty", ".txt");
        try {
            String content = fileManager.readFile(emptyFile.getAbsolutePath());
            assertEquals("", content);
        } finally {
            emptyFile.delete();
        }
    }
    
    @Test
    public void testLectureFichierCaracteresSpeciaux() throws IOException {
        File specialFile = File.createTempFile("special", ".txt");
        try (FileWriter writer = new FileWriter(specialFile)) {
            writer.write("Test avec caractères spéciaux: éàçùêîô");
        }
        try {
            String content = fileManager.readFile(specialFile.getAbsolutePath());
            assertEquals("Test avec caractères spéciaux: éàçùêîô", content);
        } finally {
            specialFile.delete();
        }
    }
}