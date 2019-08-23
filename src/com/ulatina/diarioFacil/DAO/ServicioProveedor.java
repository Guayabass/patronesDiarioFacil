/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import com.ulatina.clasesDiarioFacil.Proveedor;
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
public class ServicioProveedor extends Servicio implements InterfaceDAO{

    @Override
    public List<Object> selectAll() {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> pList = new ArrayList<>();
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM proveedor";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idProveedor = rs.getInt("idProveedor");
                String nombreProveedor = rs.getString("nombreProveedor");
                String correoProveedor = rs.getString("correoProveedor");
                String telProveedor = rs.getString("telProveedor");
                //Display values
                Proveedor p = new Proveedor(idProveedor,nombreProveedor,correoProveedor,telProveedor);
                pList.add(p);
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
            String sql = "INSERT INTO proveedor (nombreProveedor,correoProveedor,telProveedor) VALUES('"+((Proveedor)obj).getNombreProveedor()+"','"+((Proveedor)obj).getCorreoProveedor()+"','"+((Proveedor)obj).getTelProveedor()+"')";
         
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
