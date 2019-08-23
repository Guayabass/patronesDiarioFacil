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
public class Cliente extends Usuario{
    
    private int cedulaCliente = 0;
    private String nombreCliente = "";
    private String correo = "";
    private String tipoCliente = "";
    private int usuario = 0 ;
    private Carrito carrito = new Carrito();
    
    public Cliente(){
        
        this.comportamientoUsuario = new MenuCliente();//Como es cliente se instancia la variable de tipo iMenu como MenuCliente por medio del super 
        
    }
   
    
    public Cliente(String nombreUsuario,String contrasenna,int cedulaCliente,String nombreCliente,String correoCliente){
    
        super(nombreUsuario,contrasenna);
        this.comportamientoUsuario = new MenuCliente();//Aqui sucede lo mismo pero con el constructor sobrecargado.
        this.cedulaCliente = cedulaCliente;
        Cliente.admin = true;
        this.nombreCliente = nombreCliente;
        this.correo = correoCliente;
        this.tipoCliente = "Básico";
        
    }
    
    public Cliente(int idUsuario,String nombreUsuario,String contrasenna,int cedulaCliente,String nombreCliente,String correoCliente,int usuario){
    
        super(idUsuario,nombreUsuario,contrasenna);
        this.comportamientoUsuario = new MenuCliente();//Aqui sucede lo mismo pero con el constructor sobrecargado.
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.correo = correoCliente;
        this.usuario = usuario;
        this.tipoCliente = "Básico";
        
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
    @Override
    public String toString(){
    
        return this.getCedulaCliente() + " " + this.getNombreCliente() + " " + this.getCorreo()+ " " + this.getTipoCliente();
        
    }
    
}
