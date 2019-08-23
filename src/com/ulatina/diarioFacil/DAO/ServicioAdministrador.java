/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.diarioFacil.DAO;

import com.ulatina.clasesDiarioFacil.Administrador;
import static com.ulatina.diarioFacil.DAO.Servicio.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class ServicioAdministrador extends Servicio implements InterfaceDAO {
    @Override
    public List<Object> selectAll() {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> aList = new ArrayList<>();
    
        
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM usuario,administrador where idUsuario=usuarioAdministrador";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idAdministrador = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombreUsuario");
                String contrasenna = rs.getString("contrasenna");
                int cedulaAdministrador = rs.getInt("cedulaAdministrador");
                String nombreAdministrador = rs.getString("nombreAdministrador");
                String correoAdministrador = rs.getString("correoAdministrador");
                int usuarioAdministrador = rs.getInt("usuarioAdministrador");
                //Display values
                //   System.out.println("ID: "+id+", Nombre: " +nombre);
                Administrador a = new Administrador(idAdministrador,nombreUsuario,contrasenna,cedulaAdministrador, nombreAdministrador,correoAdministrador,usuarioAdministrador);
                aList.add(a);

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
        return aList;

    }

    @Override
    public void insert(Object obj) { //primero metemos el id de serviciousuario en cliente y despues verificamos que sean el mismo y despues el insert.
        ServicioUsuario u = new ServicioUsuario();

        Statement stmt = null;

        u.insert(obj);

        int idDelUsuario = 0;

        idDelUsuario = u.buscarIdAdmin(obj);
       
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

              stmt.executeUpdate("insert into administrador (cedulaAdministrador, nombreAdministrador, correoAdministrador, usuarioAdministrador) values (" + (((Administrador) obj).getCedulaAdmin()) + ",'" + (((Administrador) obj).getNombreAdmin()) + "','" + (((Administrador) obj).getCorreoAdmin()) + "','" + idDelUsuario + "')");

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
            stmt.executeUpdate("update administrador set nombreAdmin = ('" + (((Administrador)obj).getNombreAdmin()) +"'), cedulaAdmin = ('" + (((Administrador)obj).getCedulaAdmin()) +"'), correoAdmin = ('" + (((Administrador)obj).getCorreoAdmin()) +"')");
              
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
            
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from administrador where usuario = ('" + (((Administrador)obj).getUsuario()) + "')");
            System.out.println("El admin con el id " + (((Administrador)obj).getUsuario()) + " ha sido removido de la base de datos.");
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
    
