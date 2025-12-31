// Classe qui realise les conversion selon les taux dfn
package org.example;

public class CurrencyConverter {

    public static double convertCurrency(double currency, String From, String to){
        if (currency < 0){
            throw new IllegalArgumentException("La valeur de montant a convertir ne doit pas etre ngtv");
        }
        double rate;
        double converted = 0;
        if(From.equals("MAD") && to.equals("EUR")){
            System.out.println("MAD -> EUR");
            rate = ExchangeRate.getMadToEurRate();
            System.out.println("rate "+rate);
            System.out.println("currency "+currency);
            converted = (double) (currency * rate);
        }
        if (From.equals("EUR") && to.equals("MAD")) {
            System.out.println("EUR -> MAD");
            rate = ExchangeRate.getEurToMadRate();
            System.out.println("rate "+rate);
            System.out.println("currency "+currency);
            converted = (double) (currency * rate);
        }
        return converted;
    }
}