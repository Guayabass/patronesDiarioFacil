/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil.patronObservador;

/**
 *
 * @author Personal
 */
public interface Sujeto {
    
    public void registrarObservador(Observador o);
    public void retirarObservador(Observador o);
    public void notificarObservadores();
    
}
