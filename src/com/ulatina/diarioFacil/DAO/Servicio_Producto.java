/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import com.ulatina.clasesDiarioFacil.Categoria;
import com.ulatina.clasesDiarioFacil.Producto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Personal
 */
public class Servicio_Producto extends Servicio implements InterfaceDAO {

    @Override
    public List<Object> selectAll() {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        Categoria categoria = new Categoria();
        Servicio_Categoria s = new Servicio_Categoria();
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM producto";
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int id = rs.getInt("idProducto");
               String nom = rs.getString("nombreProducto");
               Date fecha = rs.getDate("fechaVencimiento");
               double valor = rs.getDouble("valor");
               int cantidad = rs.getInt("cantidad");
               int cat = rs.getInt("categoria");
               
              
               for (Object o : s.selectAll()) {
                   if (cat == ((Categoria)o).getIdCategoria()) {
                       categoria = ((Categoria)o);
                   }
               }
                
               listaRetorno.add(new Producto(id,nom,fecha,valor,cantidad,categoria));
             
           }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                
                rs.close();
                stmt.close();
                this.desconectar();
                
            }catch(Exception e){
                
                e.printStackTrace();
            
            }
        }
        
        return listaRetorno;
    }

    @Override
    public void insert(Object obj) {
        Statement stmt = null;
        DateFormat formato = new SimpleDateFormat("YY-MM-dd");
        Categoria categoria = new Categoria();
        
        
        String nombreCategoria = JOptionPane.showInputDialog("Nombre de la categoria.");
        
        Servicio_Categoria s = new Servicio_Categoria();
        
        for (Object o : s.selectAll()) {
            if (((Categoria)o).getNombreCategoria().equalsIgnoreCase(nombreCategoria)) {
                categoria = ((Categoria)o);
            }
        }
        
        
        try{
            
            
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO producto(nombreProducto,fechaVencimiento,valor,cantidad,categoria) VALUES('"+((Producto)obj).getNombreProducto()+"','"+formato.format(((Producto)obj).getFechaVencimiento())+"',"+((Producto)obj).getPrecioProducto()+","+((Producto)obj).getCantidad()+","+categoria.getIdCategoria()+")";
            
            int insert = stmt.executeUpdate(sql);
        
        }catch(Exception e){
            e.printStackTrace(); 
        }finally{
        
            try{
            
            stmt.close();
            this.desconectar();
                
        }catch(Exception e){
            e.printStackTrace();
        
        }
      
        }
    }
    
    public void updateStock(Producto producto,int resta){
        
        Statement stmt = null;
        
        try {
            //STEP 3: Execute a querey
            super.conectar();
      
            stmt = conn.createStatement();
            
            stmt.executeUpdate("update producto set cantidad='" + (producto.getCantidad()-resta) +"' WHERE idProducto='"+producto.getCodigoProducto()+"'");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ha pasado un error.");
        } finally {
            try {
                stmt.close();
                super.desconectar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    
    }
    
    @Override
    public void update(Object obj) {
        Statement stmt = null;      
        
        try{
            
            
            this.conectar();
            
            stmt = conn.createStatement();
            stmt.executeUpdate("update producto set valor = '" + (((Producto)obj).getPrecioProducto()) +"' WHERE idProducto = '" + (((Producto)obj).getCodigoProducto()) +"'");
            
           
        
        }catch(Exception e){
            e.printStackTrace(); 
        }finally{
        
            try{
            
            stmt.close();
            this.desconectar();
                
        }catch(Exception e){
            e.printStackTrace();
        
        }
      
        }        
    }

    @Override
    public void delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

