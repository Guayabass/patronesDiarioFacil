/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;


import com.ulatina.clasesDiarioFacil.Pedido;
import com.ulatina.clasesDiarioFacil.Producto;
import static com.ulatina.diarioFacil.DAO.Servicio.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class ServicioPedido extends Servicio implements InterfaceDAO{

    @Override
    public List<Object> selectAll() {
        
        ResultSet rs = null;
        Statement stmt = null;
        Servicio_Producto sp = new Servicio_Producto();
        Producto p = new Producto();
        List<Object> pList = new ArrayList<>();
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM pedido pe,producto pr WHERE pr.idProducto=pe.producto";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idPedido = rs.getInt("idPedido");
                int producto = rs.getInt("producto");
                
                for (Object obj : sp.selectAll()) {
                    if (((Producto)obj).getCodigoProducto() == producto) {
                        p = ((Producto)obj);
                    }
                }
               
                pList.add(new Pedido(idPedido,p));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return pList;
    }

    @Override
    public void insert(Object obj) {
        Statement stmt = null;
      
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO pedido(producto) VALUES("+((Pedido)obj).getProductoPedido().getCodigoProducto()+")";
            
            int i = stmt.executeUpdate(sql);
              
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
    public void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
