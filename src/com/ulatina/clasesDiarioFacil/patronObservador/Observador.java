/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil.patronObservador;

import com.ulatina.clasesDiarioFacil.Categoria;
import java.util.Date;

/**
 *
 * @author Personal
 */
public interface Observador {
    
    public void actualizar(String nombre, double precio, int cantidad);
    
}
