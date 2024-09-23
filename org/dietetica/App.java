package org.dietetica;

import controller.Inicio;
import view.Menu;

public class App {

    public static void main(String[] args) {

        Menu menu = new Menu();
        realizarPruebas();

        menu.mostrarMenuPrincipal();
    }

    private static void realizarPruebas() {
        Inicio inicio = new Inicio();

        System.out.println("Realizando carga masiva de datos...");
        inicio.cargaMasiva();

        System.out.println("Ejecutando pruebas de CRUD...");
        inicio.ejecutarPruebas();
    }
}
