/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

/**
 *
 * @author Laboratorio
 */
public abstract class PromocionDecorador implements Promocion{
    private Promocion promocion;

    public PromocionDecorador(Promocion promocion) {
        this.promocion = promocion;
    }
    protected Promocion getPromocion(){
        return this.promocion;
    }    
}
