/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Carrito {
    
    private List<Producto> productosEnCarrito = new ArrayList<>();
    private double precioTotalCarrito = 0;

    public Carrito() {
        
    }

    public List<Producto> getProductosEnCarrito() {
        return productosEnCarrito;
    }

    public void setProductosEnCarrito(Producto productosEnCarrito) {
        this.productosEnCarrito.add(productosEnCarrito);
    }

    public double getPrecioTotalCarrito() {
        for (Producto p : this.getProductosEnCarrito()) {
            this.precioTotalCarrito += (p.getPrecioProducto()*p.getCantidad());
        }
        return precioTotalCarrito;
    }

    public void setPrecioTotalCarrito(double precioTotalCarrito) {
        this.precioTotalCarrito = precioTotalCarrito;
    }
    
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
    
        sb.append("Carrito\n-------------------------------------------------------------------------------\n");
        
        for (Producto p : this.getProductosEnCarrito()) {
            sb.append(p.getNombreProducto() + ". Vence: " + p.getFechaVencimiento() + " valor por unidad: " + p.getPrecioProducto() + ". Cantidad: " + p.getCantidad() + ". Precio: " + p.getPrecioProducto()*p.getCantidad() +". \n");
        }
        
        sb.append("-------------------------------------------------------------------------------\n");
        sb.append("Precio Total: \n");
        
        sb.append(this.getPrecioTotalCarrito());
    
        return sb.toString();
    }
    
}
