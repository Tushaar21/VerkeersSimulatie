package sr.unasat.application;

import sr.unasat.service.serviceimpl.SimImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimImpl sim = new SimImpl();

        System.out.println("Welkom bij de verkeerssimulatie, gemaakt door Binesrie Tushaar voor het vak Datastructuren en Algoritmen.");
        System.out.println("Kies nummer 1 voor forward simulation, 2 voor reverse simulation: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        if(input == 1){
            sim.simulation();
        }else{
            sim.simulationReverse();
        }
    }
}