/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

/**
 *
 * @author Personal
 */
public abstract class Usuario {
    
    protected int idUsuario;
    protected String nombreUsuario = "";
    protected String contrasenna = "";
    protected static boolean admin = true;
    protected iMenu comportamientoUsuario; // Creo una variable de tipo iMenu(Interface).

    public Usuario() {
        
    }
    
    public Usuario(int idUsuario,String nombreUsuario,String contrasenna) {
        
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
        
    }
    
    public Usuario(String nombreUsuario,String contrasenna) {
        
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
        
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
    
    //Hago un setter para asignar que tipo de interface voy a instanciar.
    public void setComportamientoUsuario(iMenu comportamientoUsuario){
        this.comportamientoUsuario = comportamientoUsuario; 
    }

    public iMenu getComportamientoUsuario() {
        return comportamientoUsuario;
    }
    
    
    
    //Este metodo llama a desplegarMenu(Metodo de la interface) de la variable comportamientoUsuario segun como se instancie. 
    public void mostrarMenu(){
        this.comportamientoUsuario.desplegarMenu();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        Usuario.admin = admin;
    }
    
    
    
    public String toString(){
    
        return this.getNombreUsuario() + " " + this.getContrasenna();
        
    }
    
}
