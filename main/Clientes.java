package main;


public class Clientes {

    //Constructor de la clase
    public Clientes(String nombre, String apellidos, String dni) {

    }
    public Clientes() {
    	
    }
    
    
    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    //Variables
    private String nombre;
    private String apellidos;
    private String dni;

}
