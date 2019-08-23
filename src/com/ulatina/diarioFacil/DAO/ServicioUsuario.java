/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import com.ulatina.clasesDiarioFacil.Administrador;
import com.ulatina.clasesDiarioFacil.Cliente;
import com.ulatina.clasesDiarioFacil.Usuario;
import static com.ulatina.diarioFacil.DAO.Servicio.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laboratorio
 */
public class ServicioUsuario extends Servicio implements InterfaceDAO {

    @Override
    public List<Object> selectAll() {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> uList = new ArrayList<>();
        try {
            //STEP 3: Execute a querey
            super.conectar();
        
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT us.idUsuario,us.nombreUsuario,us.contrasenna,ad.usuarioAdministrador,cl.usuarioCliente FROM usuario us,administrador ad,cliente cl where us.idUsuario=ad.usuarioAdministrador or us.idUsuario=cl.usuarioCliente";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idUsuario = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombreUsuario");
                String contrasenna = rs.getString("contrasenna");
                int usuarioC = rs.getInt("usuarioCliente");
                int usuarioA = rs.getInt("usuarioAdministrador");
                
                if (usuarioC == idUsuario) {
                    
                   Usuario cliente = new Cliente();
                   cliente.setIdUsuario(idUsuario);
                   cliente.setNombreUsuario(nombreUsuario);
                   cliente.setContrasenna(contrasenna);
                   uList.add(cliente);
                    
                }else if(usuarioA == idUsuario){
                    
                    Usuario admin = new Administrador();
                    admin.setIdUsuario(idUsuario);
                    admin.setNombreUsuario(nombreUsuario);
                    admin.setContrasenna(contrasenna);
                    uList.add(admin);
                 
                }
                
                //Display values
                //   System.out.println("ID: "+id+", Nombre: " +nombre);
               
               
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
        return uList;        
    }
    
    public List<Object> seleccionarAdministrador(){
    ResultSet rs = null;
        Statement stmt = null;
        List<Object> uList = new ArrayList<>();
        try {
            //STEP 3: Execute a querey
            super.conectar();
        
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT us.idUsuario,us.nombreUsuario,us.contrasenna,ad.usuarioAdministrador FROM usuario us,administrador ad where us.idUsuario=ad.usuarioAdministrador";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idUsuario = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombreUsuario");
                String contrasenna = rs.getString("contrasenna");
                int usuarioA = rs.getInt("usuarioAdministrador");
                
                if (usuarioA == idUsuario) {
                    
                   Usuario administrador = new Administrador();
                   administrador.setIdUsuario(idUsuario);
                   administrador.setNombreUsuario(nombreUsuario);
                   administrador.setContrasenna(contrasenna);
                   uList.add(administrador);
                    
                }
   

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
        return uList; 
  
    }

    
    @Override
    public void insert(Object obj) {
        Statement stmt = null;
        try {
            //STEP 3: Execute a querey
            
            super.conectar();
            
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into usuario (nombreUsuario, contrasenna) values ('" + (((Usuario)obj).getNombreUsuario()) + "','" + (((Usuario)obj).getContrasenna()) + "')");
              
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ha pasado un error.");
        } finally {
            try {
                stmt.close();
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }        
    }
    
    
    public List<Object> seleccionarCliente(){
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> uList = new ArrayList<>();
        try {
            //STEP 3: Execute a querey
            super.conectar();
        
            stmt = conn.createStatement();
            String sql;
            sql = "select us.idUsuario,us.nombreUsuario,us.contrasenna,cl.usuarioCliente from usuario us,cliente cl where us.idUsuario=cl.usuarioCliente";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idUsuario = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombreUsuario");
                String contrasenna = rs.getString("contrasenna");
                int usuarioC = rs.getInt("usuarioCliente");
                
                if (usuarioC == idUsuario) {
                    
                   Usuario cliente = new Cliente();
                   cliente.setIdUsuario(idUsuario);
                   cliente.setNombreUsuario(nombreUsuario);
                   cliente.setContrasenna(contrasenna);
                   uList.add(cliente);
                    
                }
   

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
        return uList; 
  
    }
    
    public int seleccionarIdCliente(Object obj){
        ResultSet rs = null;
        Statement stmt = null;
        int idCliente = 0;
        try {
            //STEP 3: Execute a querey
            super.conectar();
        
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idUsuario FROM usuario where nombreUsuario='"+((Cliente)obj).getNombreUsuario()+"' and contrasenna='"+((Cliente)obj).getContrasenna()+"'";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Recibe el id del cliente que le pasamos por parametro
                idCliente = rs.getInt("idUsuario");
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
        return idCliente;        
  
    }
    
    public int buscarIdAdmin(Object o){
        ResultSet rs = null;
        Statement stmt = null;
        int idAdmin = 0;
        try {
            //STEP 3: Execute a querey
            super.conectar();
        
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idUsuario FROM usuario where nombreUsuario='"+((Administrador)o).getNombreUsuario()+"' and contrasenna='"+((Administrador)o).getContrasenna()+"'";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            if (rs.next()) {
                //Recibe el id del cliente que le pasamos por parametro
                idAdmin = rs.getInt("idUsuario");
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
        return idAdmin;        
  
    }
    
    

   
    @Override
    public void update(Object obj) {
        Statement stmt = null;
        try {
            //STEP 3: Execute a querey
            super.conectar();
            System.out.println("Conectado a la base, creando un statement...");
            stmt = conn.createStatement();
            stmt.executeUpdate("update usuario set nombreUsuario = ('" + (((Usuario)obj).getNombreUsuario()) +"'), contrasenna = ('" + (((Usuario)obj).getContrasenna()) +"')");
              System.out.println("Usuario actualizado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ha pasado un error.");
        } finally {
            try {
                stmt.close();
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }         
    }

    @Override
    public void delete(Object obj) {
        Statement stmt = null;
        try {
            //STEP 3: Execute a querey
            super.conectar();
            System.out.println("Conectado a la base, creando un statement...");
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from usuario where idUsuario = ('" + (((Usuario)obj).getIdUsuario()) + "')");
            System.out.println("El usuario con el id " + (((Usuario)obj).getIdUsuario()) + " ha sido removido de la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ha pasado un error.");
        } finally {
            try {
                stmt.close();
                super.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
