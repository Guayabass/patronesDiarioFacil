/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

import com.ulatina.diarioFacil.DAO.ServicioCliente;

/**
 *
 * @author Laboratorio
 */
public class PromocionFeature extends PromocionDecorador{
    
    public PromocionFeature(Promocion promocion) {
        super(promocion);
    }

    @Override
    public double descuento2() {
        double promo=0;
        promo=getPromocion().descuento2()-0.10;
       return promo;
    }

    @Override
    public double descuento() {
        double promo=0;
        promo=getPromocion().descuento2();
       return promo;
    } 
}
