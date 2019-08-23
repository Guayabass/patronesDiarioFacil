/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.patronFabrica;

import com.ulatina.clasesDiarioFacil.Producto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Navidad implements ICombo {
    

    @Override
    public List<Producto> getlistaDeProductos() {
       List<Producto> listaProducto = new ArrayList<Producto>();;
       Producto p = new Producto("Rompope",(new Date()),6000,5);
          listaProducto.add(p);
          return listaProducto;
    }

    @Override
    public double getPrecio() {
        double precio = 0;
        for(Producto p:this.getlistaDeProductos()){
            precio = p.getPrecioProducto()*p.getCantidad();
            precio = (precio-(precio*0.35));
    }
    return precio;
    }
    
   public String toString(){
       
        StringBuffer sb = new StringBuffer();
        sb.append("Combo Navideño\n");
         for(Producto p: this.getlistaDeProductos()){
             
            sb.append(p.getNombreProducto());
            
            sb.append("\n");
        }
         sb.append("Cantidad: ");
         sb.append("5\n");
         sb.append("Precio: ");
         sb.append(this.getPrecio());
         return sb.toString();
    }
        
}
