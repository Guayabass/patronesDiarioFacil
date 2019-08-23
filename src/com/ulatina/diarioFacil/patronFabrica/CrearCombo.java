/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.patronFabrica;

/**
 *
 * @author Usuario
 */
public class CrearCombo implements Fabrica {

    private ICombo combo;
    
    public CrearCombo(String nombreCombo){
        
        if(nombreCombo.equalsIgnoreCase("Navidad")){
            combo = new Navidad();
        }else if(nombreCombo.equalsIgnoreCase("Halloween")){
            combo = new Halloween();
        }else if (nombreCombo.equalsIgnoreCase("Valentin")){
            combo = new Valentin();
    }
        
    }
    
    @Override
    
    public ICombo getCombo() {
        return this.combo;
    }

   
    
}
