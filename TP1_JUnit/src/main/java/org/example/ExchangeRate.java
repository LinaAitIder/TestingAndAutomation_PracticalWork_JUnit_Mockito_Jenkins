// Classe qui gère les taux de conversion entre MAD et EUR
package org.example;

public class ExchangeRate {
    public static double MadToEurRate = (double) 0.09;
    public static double EurToMadRate = (double) 11.11;

    // Setters 
    public void setMadToEurRate(double value){
        if(value<=0){
            throw new IllegalArgumentException("La valeur de taux doit être positive, Vous avez saisi :"+value);
        }
        this.MadToEurRate = value;
    }

    public void setEurToMadRate(double value){
        if(value<=0){
            throw new IllegalArgumentException("La valeur de taux doit être positive, Vous avez saisi :"+value);
        }
        this.EurToMadRate = value;
    }

    // Getters
    public static double getMadToEurRate(){
        return MadToEurRate;
    }
    public static double getEurToMadRate(){
        return EurToMadRate;
    }

}