package com.cafegross.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ITolvaRepositorio {
    ArrayList<TolvaCafe> consultarTolvasDisponibles() throws SQLException;
    boolean insertarRegistroCarga(int numeroTolva, double kilos, String operador) throws SQLException, TolvaExcepcion;
    void simularFalloConexion() throws SQLException;
}