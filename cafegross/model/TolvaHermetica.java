package com.cafegross.model;

public class TolvaHermetica extends TolvaCafe {
    private double presionAtmosferica;

    public TolvaHermetica(int numeroTolva, String origen, double pesoMaximo, double kilosActuales, double presionAtmosferica) {
        super(numeroTolva, origen, pesoMaximo, kilosActuales);
        this.presionAtmosferica = presionAtmosferica;
    }

    @Override
    public void ingresarCarga(double kilos) throws TolvaExcepcion {
        if (!this.estadoOperativo) {
            throw new TolvaExcepcion("La tolva hermética #" + numeroTolva + " está fuera de servicio.");
        }
        if (presionAtmosferica < 0.8 || presionAtmosferica > 1.2) {
            throw new TolvaExcepcion("Presión barométrica inestable (" + presionAtmosferica + " bar).");
        }
        if (kilos < 10.0 || kilos > 70.0) {
            throw new TolvaExcepcion("Saco especial de " + kilos + " kg fuera de norma (10 a 70 kg).");
        }
        if ((this.kilosActuales + kilos) > this.pesoMaximo) {
            throw new TolvaExcepcion("Sobrecarga: excede el límite hermético de " + this.pesoMaximo + " kg.");
        }
        this.kilosActuales += kilos;
    }
}