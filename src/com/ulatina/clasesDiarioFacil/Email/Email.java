/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil.Email;

import com.ulatina.clasesDiarioFacil.Pedido;
import com.ulatina.clasesDiarioFacil.Producto;
import com.ulatina.diarioFacil.DAO.ServicioPedido;
import com.ulatina.diarioFacil.DAO.Servicio_Producto;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Personal
 */
public class Email {
    
        
    public void pedidoAutomatico(String correo, String nombre, double precio, int cantidad){
        Servicio_Producto sp=new Servicio_Producto();
        Producto p = new Producto();
        
        for(Object o:sp.selectAll()){
            if (((Producto)o).getNombreProducto().equalsIgnoreCase(nombre)){
                p = ((Producto)o);
            }
            
        }
        
        
        Pedido pedido = new Pedido(p);
        
        ServicioPedido sPedido = new ServicioPedido();
        sPedido.insert(pedido);
        

        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "parapropruebas@gmail.com");
        properties.put("mail.smtp.auth","true");
        
        Session s = Session.getDefaultInstance(properties,null);
        
        MimeMessage m = new MimeMessage(s);
       
        try{
        
        m.setFrom(new InternetAddress("parapropruebas@gmail.com"));
        
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(correo) );
        m.setSubject("Pedido de productos.");
          
        String mensaje = pedido.getProductoPedido().getNombreProducto() + " por un precio de " + pedido.getProductoPedido().getPrecioProducto() + " por unidad, cantidad de " + pedido.getProductoPedido().getCantidad() + " unidades. agradecemos su servicios. Atentamente Alejandro Murillo Gutierrez. Dueño de DiarioFacil.";
        m.setText(mensaje);
        
        Transport transport = s.getTransport("smtp");
        transport.connect("parapropruebas@gmail.com", "123456pruebas");
        transport.sendMessage(m, m.getAllRecipients());
        transport.close();
        
        
        }catch(MessagingException me){
            me.printStackTrace();
        }
    
    }
    
    public void hacerPedido(){
        
        String nombreProducto = JOptionPane.showInputDialog("Nombre del producto");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad"));
        Producto p = new Producto(nombreProducto,precio,cantidad);
        
        Servicio_Producto sp = new Servicio_Producto();
        
        for (Object o : sp.selectAll()) {
            if (((Producto)o).getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                p = (Producto)o;
            }
        }
        p.setCantidad(cantidad);
        
        Pedido pedido = new Pedido(p);
        
        ServicioPedido sPedido = new ServicioPedido();
        sPedido.insert(pedido);
        

        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "parapropruebas@gmail.com");
        properties.put("mail.smtp.auth","true");
        
        Session s = Session.getDefaultInstance(properties,null);
        
        MimeMessage m = new MimeMessage(s);
       
        try{
        
        m.setFrom(new InternetAddress("parapropruebas@gmail.com"));
        
        String des = JOptionPane.showInputDialog("Escriba el correo receptor.");
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(des) );
        m.setSubject("Pedido de productos.");
          
        String mens = JOptionPane.showInputDialog("Mensaje.");
        String mensaje = mens + " " + pedido.getProductoPedido().getNombreProducto() + " por un precio de " + pedido.getProductoPedido().getPrecioProducto() + " por unidad, cantidad de " + pedido.getProductoPedido().getCantidad() + " unidades. agradecemos su servicios. Atentamente Anthony Flores Boza. Administador DiarioFacil.";
        m.setText(mensaje);
        
        Transport transport = s.getTransport("smtp");
        transport.connect("parapropruebas@gmail.com", "123456pruebas");
        transport.sendMessage(m, m.getAllRecipients());
        transport.close();
        
        
        }catch(MessagingException me){
            me.printStackTrace();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("Enviado con éxito.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        }
}
