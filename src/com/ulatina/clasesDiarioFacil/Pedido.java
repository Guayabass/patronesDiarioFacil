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
public class Pedido {
    private int codigoPedido;
    private Producto productoPedido = new Producto();

    
    public Pedido(){
    
    }
    
    public Pedido(int codigoPedido,Producto producto){
        this.codigoPedido = codigoPedido;
        this.productoPedido = producto;
    }
    
    public Pedido(Producto producto){
        this.productoPedido = producto;
    }
    
    public int getCodigoPedido(){
        return codigoPedido;
    }
    
    public void setCodigoPedido(int codigoPedido){
        this.codigoPedido = codigoPedido;
    }

    public Producto getProductoPedido() {
        return productoPedido;
    }

    public void setProductoPedido(Producto productoPedido) {
        this.productoPedido = productoPedido;
    }
    
    
    
    public String toString(){
    StringBuffer sb = new StringBuffer();
    
    sb.append("Pedido: " + this.getCodigoPedido());
    
        sb.append(this.getProductoPedido());
    
    sb.append("--------------------------------------------------------------------------------------------------------------------\n");
      
    return sb.toString();
    }
}
