package com.cafegross.view;

import com.cafegross.model.TolvaCafe;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaTolvaCafe {
    private Scanner scanner;

    public VistaTolvaCafe() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=======================================================");
        System.out.println("   SISTEMA CAFÉ GROSS - ARQUITECTURA MVC & PERSISTENCIA");
        System.out.println("=======================================================");
        System.out.println("1. Consultar inventario de Tolvas (SELECT desde MySQL)");
        System.out.println("2. Registrar pesaje e ingreso de saco (INSERT en MySQL)");
        System.out.println("3. Testear tolerancia a fallos (SQLException de conexión)");
        System.out.println("4. Salir");
        System.out.println("=======================================================");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public int pedirNumeroTolva() {
        System.out.print("Ingrese el identificador de Tolva: ");
        return scanner.nextInt();
    }

    public double pedirKilos() {
        System.out.print("Ingrese peso del saco (10 a 70 kg): ");
        return scanner.nextDouble();
    }

    public String pedirOperador() {
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Nombre del operador de pesaje: ");
        return scanner.nextLine();
    }

    public void mostrarTolvas(ArrayList<TolvaCafe> tolvas) {
        System.out.println("\n--- REGISTROS RECUPERADOS DESDE PERSISTENCIA (ArrayList) ---");
        System.out.printf("%-10s %-12s %-12s %-12s %-10s%n", "Tolva N°", "Origen", "Stock (kg)", "Capacidad", "Ocupación");
        System.out.println("------------------------------------------------------------");
        for (TolvaCafe t : tolvas) {
            System.out.printf("%-10d %-12s %-12.2f %-12.2f %-9.2f%%%n",
                    t.getNumeroTolva(), t.getOrigen(), t.getKilosActuales(), t.getPesoMaximo(), t.getPorcentajeOcupacion());
        }
        System.out.println("------------------------------------------------------------");
    }

    public void mostrarMensaje(String msg) {
        System.out.println(">> INFO: " + msg);
    }

    public void mostrarError(String error) {
        System.out.println(">> ERROR CONTROLADO: " + error);
    }

    public void limpiarBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}