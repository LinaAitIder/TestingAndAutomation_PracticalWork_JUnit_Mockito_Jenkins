import org.example.ExchangeRate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test les taux retournés
// Tester si les taux sont négatifs et nuls
//
class ExchangeRateTest{
    // Test de getters
    // Test avec val négatives ou nulles
    // Test de setters = effectue correctement les mises à jour et rejette les valeurs invalides
    private ExchangeRate exchangeRate;
    @BeforeEach
    void setUp(){
        exchangeRate = new ExchangeRate();
    }

    @Test
    public void testgetEurToMadRate()
    {
        // En utilisant le principe AAA
        // ARRANGE
        double expectedRate = 10.71;
        //ACT
        exchangeRate.setEurToMadRate(expectedRate);
        double actualRate = exchangeRate.getEurToMadRate();
        //ASSERT
        assertEquals(expectedRate, actualRate, "Getter doit retourner la valeur 10.71");
    }

    @Test
    public void testgetMadToEurRate()
    {
        exchangeRate.setMadToEurRate(0.093);
        assertEquals(0.093, exchangeRate.getMadToEurRate());
    }

    @Test
    public void testsetEurToMadWithNegValues()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            exchangeRate.setEurToMadRate(-0.093);
        });
    }

    @Test
    public void testsetEurToMadWithNullValues()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            exchangeRate.setEurToMadRate(0);
        });
    }

    @Test
    public void testsetMadToEurWithNegValues()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            exchangeRate.setMadToEurRate(-10.71);
        });
    }

    @Test
    public void testsetMadToEurWithNullValues()
    {
        assertThrows(IllegalArgumentException.class, ()->{
            exchangeRate.setMadToEurRate(0);
        });
    }
}