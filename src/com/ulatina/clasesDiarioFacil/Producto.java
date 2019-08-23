/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

import com.ulatina.clasesDiarioFacil.patronObservador.Observador;
import com.ulatina.clasesDiarioFacil.patronObservador.Sujeto;
import com.ulatina.diarioFacil.DAO.Servicio_Producto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Personal
 */
public class Producto implements Sujeto{
    
    private int codigoProducto;
    private String nombreProducto;
    private Date fechaVencimiento;
    private double precioProducto;
    private int cantidad;
    private Categoria categoria;
    private List<Proveedor> provedores = new ArrayList<>();
   
    public Producto(){
      
    }
    
    public Producto(String nombreProducto,double precio,int cantidad){
        this.nombreProducto = nombreProducto;
        this.precioProducto = precio;
        this.cantidad = cantidad;
    }
    
    public Producto(String nombreProducto,Date fechaVencimiento,double precioProducto,int cantidad,Categoria categoria){
    
        this.nombreProducto = nombreProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.categoria = categoria;
        
    }

    public Producto(String nombreProducto,Date fechaVencimiento,double precioProducto,int cantidad){
    
       
        this.nombreProducto = nombreProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
      
        
    }
    
    public Producto(int codigoProducto, String nombreProducto, Date fechaVencimiento, double precioProducto, int cantidad, Categoria categoria) {

        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.fechaVencimiento = fechaVencimiento;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.registrarObservador(new Proveedor());
        
    }
    
    

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Proveedor> getProvedores() {
        return provedores;
    }

    public void setProvedores(List<Proveedor> provedores) {
        this.provedores = provedores;
    }   
    
    @Override
    public String toString(){
     return "Codigo: " + this.getCodigoProducto() + ". Producto: " + this.getNombreProducto() + ". Fecha de vencimiento: " + this.getFechaVencimiento() + ". Valor: " + this.getPrecioProducto() + ". Unidades: " + this.getCantidad();
    }

   
    @Override
    public void registrarObservador(Observador o) {
        this.provedores.add((Proveedor)o);
    }

    @Override
    public void retirarObservador(Observador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notificarObservadores() {
        this.provedores.forEach(provedor -> provedor.actualizar(this.getNombreProducto(), this.getPrecioProducto(), 15));
    }

}
