package com.cafegross.controller;

import com.cafegross.model.ITolvaRepositorio;
import com.cafegross.model.TolvaCafe;
import com.cafegross.model.TolvaExcepcion;
import com.cafegross.view.VistaTolvaCafe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ControladorTolvaCafe {
    private ITolvaRepositorio modelo;
    private VistaTolvaCafe vista;
    // Arreglo estático complementario para orígenes permitidos
    private String[] origenesValidos = {"Colombia", "Etiopía", "Bolivia"};

    public ControladorTolvaCafe(ITolvaRepositorio modelo, VistaTolvaCafe vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean activo = true;

        while (activo) {
            try {
                int opcion = vista.mostrarMenu();

                switch (opcion) {
                    case 1:
                        ArrayList<TolvaCafe> lista = modelo.consultarTolvasDisponibles();
                        vista.mostrarTolvas(lista);
                        break;

                    case 2:
                        int nro = vista.pedirNumeroTolva();
                        double kilos = vista.pedirKilos();
                        String operador = vista.pedirOperador();

                        boolean exito = modelo.insertarRegistroCarga(nro, kilos, operador);
                        if (exito) {
                            vista.mostrarMensaje("Pesaje persistido exitosamente en tabla 'registro_carga'.");
                        }
                        break;

                    case 3:
                        vista.mostrarMensaje("Comprobando enlace de red con el motor MySQL...");
                        modelo.simularFalloConexion();
                        break;

                    case 4:
                        vista.mostrarMensaje("Cerrando sesión en Café Gross. Prototipo finalizado.");
                        activo = false;
                        break;

                    default:
                        vista.mostrarError("Opción no válida. Ingrese un valor de 1 a 4.");
                }

            } catch (InputMismatchException e) {
                vista.mostrarError("Entrada inválida. Debe ingresar valores numéricos.");
                vista.limpiarBuffer();
            } catch (TolvaExcepcion e) {
                vista.mostrarError("Regla de negocio infringida: " + e.getMessage());
            } catch (SQLException e) {
                vista.mostrarError("Excepción SQL interceptada: [" + e.getErrorCode() + "] " + e.getMessage());
            } finally {
                // Cláusula de liberación segura
            }
        }
    }
}