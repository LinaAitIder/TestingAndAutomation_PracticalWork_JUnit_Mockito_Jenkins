import org.example.CurrencyConverter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Tests Unitaires
class CurrencyConverterTest {
    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CurrencyConverter();
    }

    // Test Montant Nul et vérification que résultat est 0 après conversion
    @Test
    @DisplayName("Test avec montant nul - doit retourner 0")
    public void testMontantNull(){
        double result = CurrencyConverter.convertCurrency(0,"MAD", "EUR");
        assertEquals(0.0,result,0.001, "Conversion de 0 doit retourner 0");
    }

    // Test Montant est négatif, Vérification que l'application lève une exception IllegalArgumentException
    @Test
    @DisplayName("Test avec montant négatif - doit lever IllegalArgumentException")
    public void testMontantNgtv(){
        assertThrows(IllegalArgumentException.class, ()->{
            converter.convertCurrency(-100, "MAD", "EUR");
        }, "Montant négatif doit lever une exception");
    }

    // Verification que la conversion est correct avec taux de conversion actuel 1MAD=0.09 EUR
    @Test
    @DisplayName("Test conversion MAD vers EUR avec taux fixe")
    public void testMadToEurConversion_ShouldBeCorrect(){
        // Test 1 : 100 MAD = 9 EUR
        double result = CurrencyConverter.convertCurrency(100, "MAD", "EUR");
        assertEquals(9,result,0.001, "Conversion de 100 doit retourber 9 EUR");
        // Test 2 : 1MAD = 0.09 EUR
        double result2 = CurrencyConverter.convertCurrency(1, "MAD", "EUR");
        assertEquals(0.09,result2,0.001, "Conversion de 1 MAD doit retourber 0.09 EUR");
        // Test 3: 0.5 MAD = 0.045 EUR
        double result3 = converter.convertCurrency(0.5, "MAD", "EUR");
        assertEquals(0.045, result3, 0.001, "0.5 MAD doit être 0.045 EUR");
    }

    // Verification que la conversion inverse (EUR -> MAD) avec un taux fxe
    @Test
    @DisplayName("Test conversion EUR vers MAD avec taux fixe")
    public void testEurToMadConversion(){
        // Test 1 : 10EUR = 111.111 MAD
        double result = CurrencyConverter.convertCurrency(10, "EUR", "MAD");
        assertEquals(111.1,result,0.001,"10 Euros doit retourner 111.111 MAD");
    }

    // Test avec des valeurs Aléatoires
    @Test
    @DisplayName("Test avec valeurs Aléatoires")
    public void testValeursAleartoires(){
        double[] testAmounts = {10.0, 20.0, 50.0, 100.0};
        for (double amount : testAmounts) {
            double expected = amount * 11.11;
            double result = CurrencyConverter.convertCurrency(amount, "EUR", "MAD");
            assertEquals(expected,result,0.001, String.format("%.2f EUR doit être %.2f MAD", amount, expected));
        }
        for (double amount : testAmounts) {
            double expected2 = amount * 0.09;
            double result2 = CurrencyConverter.convertCurrency(amount, "MAD", "EUR");
            assertEquals(expected2,result2,0.001, String.format("%.2f MAD doit être %.2f EUR", amount, expected2));
        }

    }
}