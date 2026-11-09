package com.cafegross.model;

public class TolvaEstandar extends TolvaCafe {
    private int tiempoAlmacenamientoMax;

    public TolvaEstandar(int numeroTolva, String origen, double pesoMaximo, double kilosActuales, int tiempoAlmacenamientoMax) {
        super(numeroTolva, origen, pesoMaximo, kilosActuales);
        this.tiempoAlmacenamientoMax = tiempoAlmacenamientoMax;
    }

    @Override
    public void ingresarCarga(double kilos) throws TolvaExcepcion {
        if (!this.estadoOperativo) {
            throw new TolvaExcepcion("La tolva #" + numeroTolva + " está fuera de servicio.");
        }
        if (kilos < 10.0 || kilos > 70.0) {
            throw new TolvaExcepcion("Saco de " + kilos + " kg fuera de rango reglamentario (10 a 70 kg).");
        }
        if ((this.kilosActuales + kilos) > this.pesoMaximo) {
            throw new TolvaExcepcion("Sobrecarga: supera la capacidad estructural de " + this.pesoMaximo + " kg.");
        }
        this.kilosActuales += kilos;
    }
}