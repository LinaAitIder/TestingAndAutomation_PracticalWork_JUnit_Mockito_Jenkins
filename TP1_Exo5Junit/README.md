# TP1_Exo5Junit - Gestion des Fichiers avec JUnit

## Partie 1 : Implémentation de la Classe FileManager

La classe FileManager implémente une méthode readFile qui permet de lire le contenu d'un fichier texte. Elle répond aux exigences suivantes :
1. Prend en paramètre le chemin du fichier à lire
2. Retourne le contenu du fichier sous forme de chaîne de caractères
3. Lève une exception IOException si le fichier n'existe pas

## Partie 2 : Tests Unitaires avec JUnit

J'ai implémenté la classe FileManagerTest avec les tests suivants :
- Lecture d'un fichier existant : vérifie que le contenu est correctement lu
- Gestion d'exception pour un fichier inexistant : vérifie qu'une IOException est levée
- Lecture d'un fichier vide : test supplémentaire pour couvrir un cas limite
- Lecture d'un fichier avec caractères spéciaux : test pour vérifier la gestion des accents

## Réponses aux Questions

### 1. Pourquoi est-il important de gérer les exceptions dans une application qui interagit avec des fichiers ?

La gestion des exceptions est cruciale car :
- Les fichiers peuvent ne pas exister au moment de la lecture
- Des problèmes de permissions peuvent survenir
- Des erreurs de lecture peuvent arriver pendant le traitement
- Une application robuste doit anticiper ces erreurs pour ne pas planter

### 2. Différence entre exception vérifiée et non vérifiée ? Pourquoi IOException est vérifiée ?

- **Exception vérifiée** : Doit être déclarée avec `throws` ou gérée avec `try-catch`. Le compilateur impose cette gestion (ex: `IOException`).
- **Exception non vérifiée** : Hérite de `RuntimeException`, pas besoin d'être déclarée (ex: `NullPointerException`).

`IOException` est vérifiée car elle étend `Exception` et non `RuntimeException`. Cela oblige les développeurs à gérer les erreurs de lecture/écriture, ce qui est essentiel pour la fiabilité.

### 3. Avantages de l'intégration des tests avec Maven ?

- Automatisation des tests à chaque build
- Gestion automatique des dépendances de test
- Standardisation de la structure des projets
- Facilité d'intégration avec les outils CI/CD

### 4. Méthode pour tester les fichiers volumineux ou corrompus ?

Pour les fichiers volumineux :
- Créer des fichiers de test de différentes tailles
- Utiliser des tests avec `@Timeout` pour vérifier les performances
- Tester la gestion de la mémoire

Pour les fichiers corrompus :
- Créer des fichiers avec des encodages incorrects
- Tester des fichiers avec des caractères invalides
- Simuler des erreurs pendant la lecture