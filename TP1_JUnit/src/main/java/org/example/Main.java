package org.example;

// classe permettant l'interaction avec l'utilisateur via la console

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println("Entrez votre montant a convertir :");
       Scanner scanner = new Scanner(System.in);
       double montant = scanner.nextDouble();
       // Menu de choix de conversion
       int choix;
       System.out.println("Type de conversion :");
       System.out.println("1- MAD->EUR");
       System.out.println("2- EUR->MAD");
       System.out.println("Autres - Quitter");
       System.out.println("Choisissez votre type de conversion :");
       choix = scanner.nextInt();
       double converted ;
       switch(choix){
        case(1): 
            converted = CurrencyConverter.convertCurrency(montant,"MAD","EUR");
            System.out.println("Result conversion :"+ converted);
            break;
        case(2):
            converted = CurrencyConverter.convertCurrency(montant,"EUR","MAD");
            System.out.println("Result conversion :"+ converted);
            break;
        default:
            break;
       }
       
    }
}