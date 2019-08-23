/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.ulatina.diarioFacil.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
*
* @author Personal
*/
public abstract class Servicio {
    
    protected static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    protected static String DB_URL = "jdbc:mysql://localhost:3306/patrones"+"?useTimezone=true&serverTimezone=UTC";
    
    protected static String USER = "root";
    protected static String PASS = "123456";
    
    protected static Connection conn =  null;
    
   
    protected void conectar(){
        
        try{
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    protected void desconectar(){
        
        try{
            if (!conn.isClosed()) {
            conn.close();
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
