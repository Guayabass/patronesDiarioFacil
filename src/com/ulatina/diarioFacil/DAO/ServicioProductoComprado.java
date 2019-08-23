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
import com.ulatina.clasesDiarioFacil.Producto;
import static com.ulatina.diarioFacil.DAO.Servicio.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Personal
 */
public class ServicioProductoComprado extends Servicio implements InterfaceDAO {

@Override
    public List<Object> selectAll() {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> oList = new ArrayList<>();
        ServicioFactura sf = new ServicioFactura();
        Factura f = new Factura();
        
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT numeroFactura FROM Factura";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int numeroFactura = rs.getInt("numeroFactura");
                
                for (Object fac : sf.selectAll()) {
                    if (numeroFactura == ((Factura)fac).getNumeroOrden()) {
                        f = ((Factura)fac);
                    }
                }
                
                List<Producto> productos = this.ordenesProductos(numeroFactura);
                
                Orden o = new Orden ();
                o.setFact(f);
                
                for (Producto pr : productos) {
                    for (Producto pr2 : this.seleccionarCantidad(pr.getCodigoProducto(), numeroFactura)) {
                     
                        if (pr.getCodigoProducto() == pr2.getCodigoProducto()) {
                            pr.setCantidad(pr2.getCantidad());
                            o.agregarProducto(pr);
                        }
                        
                    }
                    
                }
                
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
    
    public List<Producto> seleccionarCantidad(int codigoProducto,int factura){
    
        ResultSet rs = null;
        Statement stmt = null;
        List<Producto> oList = new ArrayList<>();
        
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT fc.productoComprado,fc.cantidad FROM facturaclienteproducto fc,factura f WHERE fc.productoComprado='"+codigoProducto+"' AND f.numeroFactura='"+factura+"' AND fc.factura='"+factura+"'";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int codProducto = rs.getInt("productoComprado");
                int cantidad = rs.getInt("cantidad");
                
                
                Producto p = new Producto();
                p.setCodigoProducto(codProducto);
                p.setCantidad(cantidad);
                
                oList.add(p);
          
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
    
    public List<Producto> ordenesProductos(int numeroFactura){
       
        ResultSet rs = null;
        Statement stmt = null;
        List<Producto> oList = new ArrayList<>();
        Servicio_Categoria sc = new Servicio_Categoria();
        Categoria ca = new Categoria();
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM producto WHERE idProducto IN (SELECT pr.idProducto FROM facturaclienteproducto fc, producto pr WHERE fc.productoComprado = pr.idProducto AND fc.factura='"+numeroFactura+"')";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                
                int idProducto = rs.getInt("idProducto");
                String nombreProducto = rs.getString("nombreProducto");
                Date fecha = rs.getDate("fechaVencimiento");
                double valor = rs.getDouble("valor");
                int cantidad = rs.getInt("cantidad");
                int categoria = rs.getInt("categoria");
              
                for (Object obj : sc.selectAll()) {
                    if (categoria == ((Categoria)obj).getIdCategoria()) {
                        ca = (Categoria)obj;
                    }
                }
                
                Producto p = new Producto(idProducto,nombreProducto,fecha,valor,cantidad,ca);
                oList.add(p);
                        
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
    
    public void productoEnOrden(int factura,int productoComprado,int cantidad){
    
        Statement stmt = null;
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            
            
                String sql = "insert into facturaclienteproducto(factura,productoComprado,cantidad) VALUES('"+factura+"','"+productoComprado+"','"+cantidad+"')";
            
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
    public void insert(Object obj) {
        Statement stmt = null;
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            
            for (Producto p : ((Orden)obj).getProductos()) {
                
                String sql = "insert into facturaclienteproducto(factura,productoComprado) VALUES('"+((Orden)obj).getFact().getNumeroOrden()+"','"+p.getCodigoProducto()+"')";
            
                int i = stmt.executeUpdate(sql);
            
            }
    
            
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
    
   
    
    public List<Orden> historialDeCliente(Cliente cliente) {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Orden> oList = new ArrayList<>();
        ServicioFactura sf = new ServicioFactura();
        Factura f = new Factura();
        
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT DISTINCT numeroFactura FROM Factura fa,cliente cl WHERE fa.cliente='"+cliente.getCedulaCliente()+"'";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int numeroFactura = rs.getInt("numeroFactura");
                
                for (Object fac : sf.selectAll()) {
                    if (numeroFactura == ((Factura)fac).getNumeroOrden()) {
                        f = ((Factura)fac);
                    }
                }
                
                List<Producto> productos = this.historialCliente(cliente);
                
                Orden o = new Orden ();
                o.setFact(f);
                
                for (Producto pr : productos) {
                    for (Producto pr2 : this.seleccionarCantidad(pr.getCodigoProducto(), numeroFactura)) {
                     
                        if (pr.getCodigoProducto() == pr2.getCodigoProducto()) {
                            pr.setCantidad(pr2.getCantidad());
                            o.agregarProducto(pr);
                        }
                        
                    }
                    
                }
                
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
    
    public List<Producto> historialCliente(Cliente cliente){
    
        ResultSet rs = null;
        Statement stmt = null;
        List<Producto> oList = new ArrayList<>();
        Servicio_Categoria sc = new Servicio_Categoria();
        Categoria ca = new Categoria();
        try {
            //STEP 3: Execute a querey
            super.conectar();
           
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT DISTINCT pr.idProducto,pr.nombreProducto,pr.fechaVencimiento,pr.valor,pr.cantidad,pr.categoria FROM producto pr,facturaclienteproducto fc,cliente cl WHERE pr.idProducto=fc.productoComprado AND cl.cedulaCliente='"+cliente.getCedulaCliente()+"'";
            rs = stmt.executeQuery(sql);
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                
                int idProducto = rs.getInt("idProducto");
                String nombreProducto = rs.getString("nombreProducto");
                Date fecha = rs.getDate("fechaVencimiento");
                double valor = rs.getDouble("valor");
                int cantidad = rs.getInt("cantidad");
                int categoria = rs.getInt("categoria");
              
                for (Object obj : sc.selectAll()) {
                    if (categoria == ((Categoria)obj).getIdCategoria()) {
                        ca = (Categoria)obj;
                    }
                }
                
                Producto p = new Producto(idProducto,nombreProducto,fecha,valor,cantidad,ca);
                oList.add(p);
                        
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
    
  
   
        
        
    }

  
    

