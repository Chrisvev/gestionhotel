package main;

public class Habitaciones {
    private int numPersonas;
    private String categoria; 
    private String tipo;
    private String estado; 

    public Habitaciones(int numPersonas, String categoria, String tipo) {
        this.numPersonas = numPersonas;
        this.categoria = categoria;
        this.tipo = tipo;
        this.estado = "Libre"; 
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}