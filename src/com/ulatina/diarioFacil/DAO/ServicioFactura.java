/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import com.ulatina.clasesDiarioFacil.Categoria;
import com.ulatina.clasesDiarioFacil.Cliente;
import com.ulatina.clasesDiarioFacil.Factura;
import com.ulatina.clasesDiarioFacil.Orden;
import static com.ulatina.diarioFacil.DAO.Servicio.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Personal
 */
public class ServicioFactura extends Servicio implements InterfaceDAO {

    @Override
    public List<Object> selectAll() {
        ResultSet rs = null;
        Statement stmt = null;
        ServicioCliente sc = new ServicioCliente();
        List<Object> oList = new ArrayList<>();
        Cliente c = new Cliente();
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Factura";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            
            while (rs.next()) {
                //Retrieve by column name
                int numeroFactura = rs.getInt("numeroFactura");
                int cliente = rs.getInt("cliente");
                Date fechaFactura = rs.getDate("fecha");
                
                for (Object obj : sc.selectAll()) {
                    
                    if (((Cliente)obj).getCedulaCliente() == cliente) {
                        c = ((Cliente)obj);
                    }
                    
                }
                
                Factura o = new Factura(numeroFactura,c,fechaFactura);
                oList.add(o);

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
        return oList;

    }

    @Override
    public void insert(Object obj) {
        Statement stmt = null;
        SimpleDateFormat formato = new SimpleDateFormat("yy/MM/dd");
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "insert into Factura(cliente,fecha) VALUES('"+((Cliente)((Factura)obj).getCliente()).getCedulaCliente()+"','"+formato.format(((Factura)obj).getFecha())+"')";
            
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
        Statement stmt = null;
        
        try {
            //STEP 3: Execute a querey
            super.conectar();
      
            stmt = conn.createStatement();
            
            //stmt.executeUpdate("update factura set totalPagar='" + ((Factura)obj).getTotalAPagar() +"' WHERE numeroFactura='"+((Orden)obj).getNumeroOrden()+"'");
            
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
    public void delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
