package com.cafegross.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloTolvaCafe implements ITolvaRepositorio {
    // Almacena transitoriamente los registros mapeados
    private ArrayList<TolvaCafe> cacheTolvas;

    public ModeloTolvaCafe() {
        this.cacheTolvas = new ArrayList<>();
        // Inicialización que mapea los datos de la base MySQL
        cacheTolvas.add(new TolvaEstandar(101, "Colombia", 1500.0, 450.0, 90));
        cacheTolvas.add(new TolvaHermetica(102, "Etiopía", 800.0, 210.0, 1.05));
        cacheTolvas.add(new TolvaEstandar(103, "Bolivia", 1200.0, 0.0, 60));
    }

    @Override
    public ArrayList<TolvaCafe> consultarTolvasDisponibles() throws SQLException {
        // En entorno de producción ejecuta el PreparedStatement y mapea el ResultSet
        return new ArrayList<>(this.cacheTolvas);
    }

    @Override
    public boolean insertarRegistroCarga(int numeroTolva, double kilos, String operador) throws SQLException, TolvaExcepcion {
        TolvaCafe encontrada = null;
        for (TolvaCafe t : cacheTolvas) {
            if (t.getNumeroTolva() == numeroTolva) {
                encontrada = t;
                break;
            }
        }

        if (encontrada == null) {
            throw new TolvaExcepcion("La tolva #" + numeroTolva + " no existe en el registro.");
        }

        // Valida reglas de negocio del dominio
        encontrada.ingresarCarga(kilos);

        // Simula la persistencia del INSERT en tabla registro_carga
        return true;
    }

    @Override
    public void simularFalloConexion() throws SQLException {
        throw new SQLException("Error de enlace JDBC: Communications link failure to MySQL server at localhost:3306.");
    }
}