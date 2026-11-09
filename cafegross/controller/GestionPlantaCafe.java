package com.cafegross.controller;

import com.cafegross.model.ITolvaRepositorio;
import com.cafegross.model.ModeloTolvaCafe;
import com.cafegross.view.VistaTolvaCafe;

public class GestionPlantaCafe {
    public static void main(String[] args) {
        ITolvaRepositorio modelo = new ModeloTolvaCafe();
        VistaTolvaCafe vista = new VistaTolvaCafe();
        ControladorTolvaCafe controlador = new ControladorTolvaCafe(modelo, vista);

        controlador.iniciar();
    }
}
