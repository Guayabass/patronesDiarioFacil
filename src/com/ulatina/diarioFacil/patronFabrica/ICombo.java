/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.patronFabrica;

import com.ulatina.clasesDiarioFacil.Producto;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ICombo {
    
    public List<Producto> getlistaDeProductos();
    public double getPrecio();
      
}


