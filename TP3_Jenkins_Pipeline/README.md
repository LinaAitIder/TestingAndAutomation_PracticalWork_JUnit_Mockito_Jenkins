# Library Management System - TP3 Jenkins Pipeline

## Testing if Everything is Working

### 1. Running Unit Tests
To test if the unit tests are working correctly, run:
```bash
mvn test
```

This will execute all 8 unit tests in the [LivreServiceTest](file:///c:/Users/hp/Desktop/TP_Design-Patterns/TP3_Jenkins_Pipeline/src/test/java/org/example/library/LivreServiceTest.java) class:
- `getAllLivres_ShouldReturnAllLivres()`
- `getLivreById_WithExistingId_ShouldReturnLivre()`
- `getLivreById_WithNonExistingId_ShouldReturnEmpty()`
- `addLivre_ShouldSaveAndReturnLivre()`
- `updateLivre_WithExistingId_ShouldUpdateAndReturnLivre()`
- `updateLivre_WithNonExistingId_ShouldReturnNull()`
- `emprunterLivre_WithAvailableBook_ShouldSetDisponibleToFalse()`
- `retournerLivre_WithBorrowedBook_ShouldSetDisponibleToTrue()`

### 2. Testing Code Coverage with JaCoCo
To run tests and generate code coverage reports:
```bash
mvn test jacoco:report
```

This will:
- Execute all tests
- Generate JaCoCo coverage reports in `target/site/jacoco/`
- Show coverage metrics for all classes

### 3. Building the Application
To test if the full build works:
```bash
mvn clean compile package -DskipTests
```

This will:
- Clean the target directory
- Compile all source code
- Package the application as a JAR file
- Skip tests to speed up the build process

### 4. Running the Full Pipeline Process
To simulate the complete Jenkins pipeline:

1. **Checkout/Initialization**: The project is ready to be checked out from version control
2. **Compile**: `mvn compile` - compiles the Java source code
3. **Test**: `mvn test` - runs all unit tests
4. **Package**: `mvn package` - creates the executable JAR file
5. **Coverage Report**: `mvn jacoco:report` - generates code coverage reports

### 5. Running the Application
To test if the Spring Boot application starts correctly:
```bash
mvn spring-boot:run
```

Or run the packaged JAR:
```bash
java -jar target/library-management-system-1.0.0.jar
```

## Jenkins Pipeline Verification

The [Jenkinsfile](file:///c:/Users/hp/Desktop/TP_Design-Patterns/TP3_Jenkins_Pipeline/Jenkinsfile) contains the following stages that can be tested:

1. **Checkout** - Verifies Git integration
2. **Compile** - Tests Maven compilation
3. **Run Unit Tests** - Executes unit tests and publishes results
4. **Static Code Analysis** - Runs SonarQube analysis
5. **Package** - Creates the deployable artifact
6. **Deploy to Dev** - Deploys to development environment

## Expected Results

When everything is working correctly:
- ✅ All 8 unit tests pass
- ✅ Code coverage reports are generated successfully
- ✅ Maven build completes without errors
- ✅ Spring Boot application starts without issues
- ✅ JAR file is created in the target directory
- ✅ JaCoCo reports show coverage metrics

## Pipeline Testing Checklist

- [x] Unit tests execute successfully (8/8 tests passing)
- [x] Code coverage is generated via JaCoCo
- [x] Maven build completes successfully
- [x] Spring Boot application compiles and runs
- [x] JAR packaging works correctly
- [x] All pipeline stages can be executed manually

The system is ready for integration with a Jenkins server using the provided Jenkinsfile.