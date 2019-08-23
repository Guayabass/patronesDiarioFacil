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
public interface iUsuario {
    
    public String verListaProductos();
    public String verCombos();
    public Carrito verCarritoCompras();
    public void cambiarContrasenna(Cliente cliente);
    public void agregarAlCarrito(String nombreProducto,int cantidad);
    
}
