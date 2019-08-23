/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import com.ulatina.clasesDiarioFacil.Cliente;
import com.ulatina.clasesDiarioFacil.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ServicioCliente extends Servicio implements InterfaceDAO {

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
            sql = "SELECT * FROM usuario,cliente where idUsuario=usuarioCliente";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idUsuario = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombreUsuario");
                String contrasenna = rs.getString("contrasenna");
                int cedulaCliente = rs.getInt("cedulaCliente");
                String nombreCliente = rs.getString("nombreCliente");
                String correoCliente = rs.getString("correoCliente");
                int usuario = rs.getInt("usuarioCliente");
                //Display values
                //   System.out.println("ID: "+id+", Nombre: " +nombre);
                Cliente c = new Cliente(idUsuario,nombreUsuario, contrasenna, cedulaCliente, nombreCliente, correoCliente,usuario);
                uList.add(c);

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
    public void insert(Object obj) { //primero metemos el id de serviciousuario en cliente y despues verificamos que sean el mismo y despues el insert.
        ServicioUsuario u = new ServicioUsuario();
        int idDelUsuario = 0;
        Statement stmt = null;

        u.insert(obj);

        idDelUsuario = u.seleccionarIdCliente(obj);
        
        try {
            super.conectar();
          
            stmt = conn.createStatement();

//            String nombre;
//            String correo;
//            String usuario;
//            String contra;
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Digite la cedula para insertar en la base de datos:");
//            id = sc.nextInt();
//            System.out.println("Ingrese el nombre para insertar en la base de datos:");
//            nombre = sc.next();
//            System.out.println("Ingrese el correo para insertar en la base de datos:");
//            correo = sc.next();
//            System.out.println("Ingrese el nombre de usuario para insertar en la base de datos:");
//            usuario = sc.next();
//            System.out.println("Ingrese la contraseña para insertar en la base de datos:");
//            contra = sc.next();
            stmt.executeUpdate("insert into cliente (cedulaCliente, nombreCliente, correoCliente, usuarioCliente) values ('" + (((Cliente) obj).getCedulaCliente()) + "','" + (((Cliente) obj).getNombreCliente()) + "','" + (((Cliente) obj).getCorreo()) + "','" + idDelUsuario + "')");
//            System.out.println("Los valores han sido ingresados en la base de datos.");

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
    public void update(Object obj) {
        Statement stmt = null;
        try {
            //STEP 3: Execute a querey
            super.conectar();
      
            stmt = conn.createStatement();
            stmt.executeUpdate("update cliente set correoCliente='" + (((Cliente)obj).getCorreo()) +"' WHERE cedulaCliente='"+((Cliente)obj).getCedulaCliente()+"'");
            System.out.println("Cliente actualizado exitosamente.");
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
    public void updateContra(Object obj){
        Statement stmt = null;
        try {
            //STEP 3: Execute a querey
            super.conectar();
      
            stmt = conn.createStatement();
            stmt.executeUpdate("update usuario set contrasenna='" + (((Cliente)obj).getContrasenna()) +"' WHERE idUsuario='"+((Usuario)obj).getIdUsuario()+"'");
            System.out.println("Contraseña actualizada correctamente.");
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
            stmt.executeUpdate("delete from cliente where usuario = ('" + (((Cliente)obj).getUsuario()) + "')");
            System.out.println("El cliente con el id " + (((Cliente)obj).getUsuario()) + " ha sido removido de la base de datos.");
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
