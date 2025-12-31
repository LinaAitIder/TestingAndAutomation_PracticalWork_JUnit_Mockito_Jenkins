# Exercice 1 de TP1 JUnit

## Les Tests Réalisés

### Tests pour la classe `CurrencyConverter`
- **Test avec montant nul** : Vérification que 0 MAD donne 0 EUR et vice versa
- **Test avec montant négatif** : Vérification qu'une exception est levée
- **Test de conversion correcte** : Validation des calculs MAD → EUR et EUR → MAD
- **Tests avec valeurs aléatoires** : Vérification avec divers montants

### Tests pour la classe `ExchangeRate`
- **Test des getters** : Vérification des taux de conversion retournés
- **Test des setters avec valeurs invalides** : Exception pour valeurs négatives ou nulles
- **Test de mise à jour des taux** : Validation des mises à jour correctes

## Réponses aux Questions

### Question 3-1 : Importance des tests des valeurs limites

Il est important de tester les valeurs limites dans une application de conversion de devises pour :

- **Robustesse du système** : Garantir que l'application ne plante pas avec des entrées inattendues
- **Intégrité financière** : Éviter les conversions erronées qui pourraient causer des pertes financières
- **Expérience utilisateur** : Fournir des messages d'erreur clairs aux utilisateurs
- **Prévention des bugs** : Détecter les erreurs de logique
- **Sécurité** : Prévenir les erreurs de calcul exploitables
- **Conformité** : Respecter les normes de qualité logicielle

### Question 3-2 : Tests pour détecter erreurs de performance et compatibilité

Pour détecter les erreurs de performance et compatibilité :

**Tests de performance :**
- Mesure du temps d'exécution pour un grand nombre de conversions
- Vérification avec des tests simultanés pour détecter des problèmes de concurrence
- Vérification de la consommation mémoire lors de conversions massives

**Tests de compatibilité :**
- Tests avec différentes versions de Java (8, 11, 17)
- Vérification du fonctionnement sur différents OS (Windows, Linux, macOS)
- Tests avec différentes locales et formats de nombres


## Intégration avec Maven

### Avantages de l'utilisation de Maven

Les avantages de l'utilisation de Maven pour la gestion des dépendances et l'automatisation des tests sont :

1. **Gestion automatique des dépendances** :
    - Maven télécharge automatiquement JUnit et autres librairies
    - Il permet de gérer les conflits de versions
    - Simplifie la réplication du projet sur différentes machines

2. **Automatisation des tests** :
    - Exécution automatique des tests à chaque build
    - Génération de rapports détaillés
    - Intégration facile avec CI/CD (Jenkins, GitLab CI)

3. **Standardisation du projet** :
    - Structure de projet uniforme
    - Configuration centralisée dans `pom.xml`
    - Facilite la collaboration en équipe

4. **Gestion du cycle de vie** :
    - Commandes standardisées : `mvn test`, `mvn compile`, `mvn package`
    - Phases de build bien définies
    - Extensible via des plugins

