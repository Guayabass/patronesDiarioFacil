/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Orden {
    
    private Factura fact = new Factura();
    private List<Producto> productos = new ArrayList<>();
    private double total;
    private double pagaCon;
    private double cambio;
    private String piePagina;
    
    public Orden(){
    
    }
    
    
    
    public Orden(Factura fact,Producto producto){
        
       this.fact = fact; 
       this.agregarProducto(producto);
  
         
    }

    public Factura getFact() {
        return fact;
    }

    public void setFact(Factura fact) {
        this.fact = fact;
    }
    
    
    
    public Orden(Factura fact,double pagaCon){
        
       
        this.fact = fact;
        this.pagaCon = pagaCon;
        
        
    }
    
   public List<Producto> getProductos(){
       return productos;
   }
    
    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }
     
    public double getPagaCon(){
        this.pagaCon = 10000;
        return this.pagaCon;
    }
    
    public void setPagaCon(double pagaCon){
        this.pagaCon = pagaCon;
    }
    
    public double getCambio(){
        
        this.cambio = this.getPagaCon() - this.getTotal();
        
        return this.cambio;
    }
    
    public String getPiePagina(){

        this.piePagina = "Gracias por su compra.";
        
        return this.piePagina;
    }
    
    public void setPiePagina(String piePagina){
        this.piePagina = piePagina;
    }

    public double getTotal() {
       double total = 0;
       ClienteVIPHandler vip = new ClienteVIPHandler();
       ClienteNormalHandler cNormal = new ClienteNormalHandler();
       vip.setSuccessor(cNormal);
       
        for (Producto p : this.getProductos()) {
            
            total += p.getCantidad()*p.getPrecioProducto();
            this.total = total*vip.handleRequest(((Cliente)this.getFact().getCliente()));
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
   
        
      public String toString(){
      
        StringBuffer sb = new StringBuffer();
      
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YY");
      
      
      sb.append("\n*******************************************************************************************\n");
      sb.append("No.Orden: " + this.getFact().getNumeroOrden());
      sb.append("\n");
      sb.append("Cedula: " + ((Cliente)this.getFact().getCliente()).getCedulaCliente());
      sb.append("\n");
      sb.append("Nombre: " + ((Cliente)this.getFact().getCliente()).getNombreCliente());
      sb.append("\nFecha:\t" + formato.format(this.getFact().getFecha()));
      
      sb.append("\n*******************************************************************************************\n");
      for (Producto p : this.getProductos()) {
              sb.append(p + "\n");
      }
      
      
      System.out.println(fact.getFecha());
      sb.append("\n*******************************************************************************************\n");
      sb.append("\n");
      sb.append("Total: " + this.getTotal());
      sb.append("\n");
      sb.append("\n*******************************************************************************************\n");
      sb.append(this.getPiePagina());
            
      return sb.toString();
      }
}
