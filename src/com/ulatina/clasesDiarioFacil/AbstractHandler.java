/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

/**
 *
 * @author Kainthel
 */
public abstract class AbstractHandler {
    
    protected AbstractHandler successor;
    
    public void setSuccessor (AbstractHandler successor){
        this.successor = successor;
    }
    public abstract double handleRequest(Cliente request);
    
}
