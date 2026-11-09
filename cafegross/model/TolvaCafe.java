package com.cafegross.model;

public abstract class TolvaCafe {
    protected int numeroTolva;
    protected String origen;
    protected double pesoMaximo;
    protected double kilosActuales;
    protected boolean estadoOperativo;

    public TolvaCafe(int numeroTolva, String origen, double pesoMaximo, double kilosActuales) {
        this.numeroTolva = numeroTolva;
        this.origen = origen;
        this.pesoMaximo = pesoMaximo;
        this.kilosActuales = kilosActuales;
        this.estadoOperativo = true;
    }

    public abstract void ingresarCarga(double kilos) throws TolvaExcepcion;

    public int getNumeroTolva() { return numeroTolva; }
    public String getOrigen() { return origen; }
    public double getPesoMaximo() { return pesoMaximo; }
    public double getKilosActuales() { return kilosActuales; }
    public boolean isEstadoOperativo() { return estadoOperativo; }

    public double getPorcentajeOcupacion() {
        if (pesoMaximo == 0) return 0.0;
        return (kilosActuales / pesoMaximo) * 100.0;
    }
}