# TP1_Exo4_JaCoCo - Intégration de la Couverture de Code avec JaCoCo

## I. Configuration de JaCoCo

### 1. Comment intégrer JaCoCo dans votre projet Maven/Gradle pour mesurer la couverture de code ?

Pour intégrer JaCoCo dans un projet Maven, on ajoute le plugin dans la section `<plugins>` du fichier pom.xml. Le plugin s'occupe d'instrumenter le code pendant les tests pour mesurer la couverture.

### 2. Quels plugins ou dépendances JaCoCo avez-vous ajoutés dans votre fichier de configuration ?

J'ai ajouté le plugin JaCoCo sans dépendances spécifiques. Voici la configuration dans le pom.xml :

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>${jacoco.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## II. Exécution des Tests avec JaCoCo

### 3. Comment exécutez-vous vos tests unitaires JUnit en utilisant JaCoCo pour collecter des données de couverture ?

Quand on fait `mvn test`, JaCoCo est automatiquement activé grâce à l'agent préparé. L'agent suit quelles parties du code sont exécutées pendant les tests.

### 4. Quelles options ou paramètres utilisez-vous pour spécifier la tâche de collecte de couverture dans Maven/Gradle ?

L'agent JaCoCo est configuré automatiquement avec l'exécution `prepare-agent`. Les données sont enregistrées dans le fichier target/jacoco.exec.

## III. Génération des Rapports

### 5. Comment générez-vous les rapports de couverture de code à partir des données collectées par JaCoCo ?

Les rapports sont générés automatiquement après les tests grâce à l'exécution `report`. On peut aussi les générer manuellement avec `mvn jacoco:report`.

### 6. Quel format de rapport utilisez-vous (par exemple, HTML, XML) et comment pouvez-vous le personnaliser ?

JaCoCo génère plusieurs formats :
- HTML: dans target/site/jacoco/
- XML: dans target/site/jacoco/jacoco.xml
- CSV: dans target/site/jacoco/jacoco.csv

## IV. Analyse des Rapports JaCoCo

### 7. Quelles informations spécifiques sur la couverture de code pouvez-vous extraire des rapports générés par JaCoCo ?

Les rapports montrent :
- Couverture des lignes de code
- Couverture des branches (conditions)
- Couverture des méthodes
- Couverture des classes

### 8. Comment identifiez-vous les parties du code qui ne sont pas couvertes par les tests unitaires ?

Dans le rapport HTML, les lignes sont colorées :
- Vert : exécutée
- Jaune : partiellement couverte
- Rouge : non exécutée

## V. Objectifs de Couverture de Code

### 9. Quels sont les objectifs de couverture de code que vous avez définis pour votre projet ? (par exemple, 80% d'instructions couvertes)

Aucun objectif spécifique n'a été défini. Le plugin génère juste les rapports sans seuils.

### 10. Comment ajusteriez-vous ces objectifs en fonction des besoins du projet ?

On pourrait ajouter une exécution `check` avec des seuils :

```xml
<execution>
    <id>check</id>
    <goals>
        <goal>check</goal>
    </goals>
    <configuration>
        <rules>
            <rule>
                <element>BUNDLE</element>
                <limits>
                    <limit>
                        <counter>COMPLEXITY</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.8</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>
</execution>
```

## VI. Scénarios de Tests pour Améliorer la Couverture

### 11. Proposez des scénarios de tests spécifiques que vous pourriez ajouter pour améliorer la couverture de code.

Des tests pour toutes les conditions, cas limites, et méthodes comme toString(), equals(), hashCode().

### 12. Comment JaCoCo peut vous aider à identifier les parties du code qui nécessitent des tests supplémentaires ?

JaCoCo montre les lignes non couvertes en rouge dans le rapport HTML.

## VII. Exemples de Rapports JaCoCo

### 13. Présentez un exemple de rapport JaCoCo et expliquez comment interpréter les différentes métriques fournies.

Le rapport est dans target/site/jacoco/index.html. Les métriques principales sont :
- Instructions: % de lignes exécutées
- Branches: % de branches de conditions exécutées
- Lines: % de lignes de code exécutées
- Methods: % de méthodes exécutées

### 14. Quelles sont les sections clés d'un rapport JaCoCo et que signifient-elles ?

- Package: Vue par paquetage
- Source file: Détail ligne par ligne
- Classes: Vue par classe
- Methods: Couverture par méthode

## VIII. Intégration avec un Système d'Intégration Continue

### 15. Comment intégreriez-vous JaCoCo dans un système d'intégration continue (par exemple, Jenkins) pour assurer une surveillance continue de la couverture de code ?

Dans un pipeline Jenkins, on pourrait :
- Exécuter mvn test pour collecter les données
- Exécuter mvn jacoco:report pour générer les rapports
- Définir des seuils avec mvn jacoco:check

## IX. Réflexion sur la Couverture de Code

### 16. Comment la couverture de code vous aide-t-elle à garantir la qualité de votre code ?

Elle permet d'identifier les parties non testées et d'améliorer la suite de tests.

### 17. Quels types de bugs ou de lacunes dans le code pensez-vous pouvoir identifier en examinant les rapports JaCoCo ?

- Code mort (non exécuté)
- Conditions non testées
- Chemins d'exécution manquants

## X. Récapitulatif et Améliorations

### 18. Proposez des idées d'amélioration ou de raffinement de l'utilisation de JaCoCo dans le contexte de votre gestion de bibliothèque.

Améliorations possibles :
- Définir des seuils minimums de couverture
- Intégrer JaCoCo à SonarQube
- Automatiser la vérification dans le pipeline CI/CD

## Exécution du projet

Pour exécuter les tests avec JaCoCo :
```
mvn clean test
```

Pour générer le rapport de couverture :
```
mvn jacoco:report
```

Les rapports sont dans target/site/jacoco/index.html.