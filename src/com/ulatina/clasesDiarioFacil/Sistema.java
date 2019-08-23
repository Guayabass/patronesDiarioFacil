/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

import com.ulatina.diarioFacil.DAO.ServicioAdministrador;
import com.ulatina.diarioFacil.DAO.ServicioCliente;
import com.ulatina.diarioFacil.DAO.ServicioFactura;
import com.ulatina.diarioFacil.DAO.ServicioProductoComprado;
import com.ulatina.diarioFacil.DAO.ServicioProveedor;
import com.ulatina.diarioFacil.DAO.Servicio_Categoria;
import com.ulatina.diarioFacil.DAO.Servicio_Producto;
import com.ulatina.diarioFacil.patronFabrica.CrearCombo;
import com.ulatina.diarioFacil.patronFabrica.Fabrica;
import com.ulatina.diarioFacil.patronFabrica.ICombo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Personal
 */
public class Sistema implements iSistema{
    
    private Scanner lector = new Scanner(System.in);
    private ServicioCliente servicioCliente;
    private ServicioAdministrador servicioAdmin;
    private ServicioProveedor servicioProveedor;
    private Servicio_Producto sp;
    private Servicio_Categoria sc;
    private List<ICombo> listaCombos = new ArrayList<>();
    private Cliente clienteIngresado = new Cliente();

    public List<ICombo> getListaCombos() {
        return listaCombos;
    }

    public void setListaCombos(List<ICombo> listaCombos) {
        this.listaCombos = listaCombos;
    }
    
    @Override
    public void registrar(Cliente usuario) {
        
        this.servicioCliente = new ServicioCliente();
        
        servicioCliente.insert(usuario);
        
        System.out.println("Guardado con exito.");
        
    }
    
    @Override
    public void validarUsuario(iSistema sistema){
        
        this.servicioCliente = new ServicioCliente();
        this.servicioAdmin = new ServicioAdministrador();
        
        do{
        
        System.out.println("Bienvenido a Diario Facil.");
        System.out.println("-----------------------------------------------");
        System.out.println("Digite su nombre de usuario.");
        String nombreUsuario = lector.next();
        System.out.println("Digite su contrasenna.");
        String contrasenna = lector.next();
        
        
        for (Object obj : servicioCliente.selectAll()) {
            
            if (((Usuario)obj).getNombreUsuario().equalsIgnoreCase(nombreUsuario) && ((Usuario)obj).getContrasenna().equalsIgnoreCase(contrasenna) && ((Usuario)obj).getIdUsuario() == ((Cliente)obj).getUsuario()) {
                
                    
                Cliente clienteValidado = ((Cliente)obj);
               this.clienteIngresado = ((Cliente)obj);
                MenuCliente menuCliente = new MenuCliente(sistema);
                clienteValidado.setComportamientoUsuario(menuCliente);
                
                System.out.println("Bienvenido " + clienteValidado.getNombreCliente());
               
                
                clienteValidado.mostrarMenu();
                
            }
           
        }
        
        for (Object obj2 : servicioAdmin.selectAll()) {
                
                    if (((Usuario)obj2).getNombreUsuario().equalsIgnoreCase(nombreUsuario) && ((Usuario)obj2).getContrasenna().equalsIgnoreCase(contrasenna) && ((Usuario)obj2).getIdUsuario() == ((Administrador)obj2).getUsuario()) {
                
                    
                    Administrador adminValidado = ((Administrador)obj2);
                    MenuAdmin menuAdmin = new MenuAdmin(sistema);
                    adminValidado.setComportamientoUsuario(menuAdmin);
                
                    System.out.println("Bienvenido " + adminValidado.getNombreAdmin());
                       
                    adminValidado.mostrarMenu();
                
            } 
        }
       
        }while(true);
    }
    
    
    @Override
    public String mostrarProductos(){
        this.sp = new Servicio_Producto();
        this.sc = new Servicio_Categoria();
        
        StringBuffer sb = new StringBuffer();
        
        sb.append("----------------------------------------------------");
        sb.append("\n");
        
        for (Object obj : sc.selectAll()) {
            
                    sb.append(((Categoria)obj).getNombreCategoria());
                    sb.append("\n");
                    
                    for (Object o : sp.selectAll()) {
                        
                        if (((Categoria)obj).getIdCategoria() == ((Producto)o).getCategoria().getIdCategoria()) {
                            
                            sb.append(((Producto)o));
                            sb.append("\n");
                            
                        }
                }
                 sb.append("----------------------------------------------------\n");
                }
        sb.append("----------------------------------------------------\n");
        
        
        return sb.toString();
    }
    
    @Override
    public List<Cliente> mostrarListaClientes(){
        this.servicioCliente = new ServicioCliente();
        
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        
        for (Object obj : this.servicioCliente.selectAll()) {
            listaClientes.add(((Cliente)obj));
        }
        return listaClientes;
    }
    
    @Override
    public void actualizarCliente(String cliente,String correo){
        
        this.servicioCliente = new ServicioCliente();//Instancio el servicio cliente
        
        Cliente c = new Cliente(); // Creo un objecto de tipo Cliete
        
        for (Object obj : this.servicioCliente.selectAll()) {
            
            if (((Cliente)obj).getNombreUsuario().equalsIgnoreCase(cliente)) {
               
                c = (Cliente)obj; // Recorro la lista de cliente verificando si el nombre de usuario es igual al que le paso y se lo asigno a la variable
                c.setCorreo(correo);
                
            }
            
        }
       
        this.servicioCliente.update(c);
        
    }
    
    @Override
    public void guardarProveedor(Proveedor proveedor){
        
        this.servicioProveedor = new ServicioProveedor();
        
        this.servicioProveedor.insert(proveedor);
        
    }
    
    @Override
    public String mostrarListaProveedores(){
        
        StringBuffer sb = new StringBuffer();
        this.servicioProveedor = new ServicioProveedor();
        
        
        for (Object obj : this.servicioProveedor.selectAll()) {
            
            sb.append((Proveedor)obj);
            sb.append("\n");
            
        }
        
        return sb.toString();
        
    }
    
    @Override
    public void nuevoProducto(Producto producto){
    
        this.sp = new Servicio_Producto();
        
        this.sp.insert(producto);
        
    }
    
    @Override
    public void nuevaCategoria(Categoria categoria){
        
        this.sc = new Servicio_Categoria();
        
        sc.insert(categoria);
        
    }
    
    @Override
    public String verCategorias(){
        
        StringBuffer sb = new StringBuffer();
        this.sc = new Servicio_Categoria();
        
        
        for (Object obj : this.sc.selectAll()) {
            sb.append(((Categoria)obj));
            sb.append("\n");
        }
        
        return sb.toString();
        
    }

    @Override
    public void nuevoCombo(String combo) {
        Fabrica fabrica = new CrearCombo(combo);
        listaCombos.add(fabrica.getCombo());
    }

    @Override
    public String verCombos() {
        
        StringBuffer sb = new StringBuffer();
        
        for (ICombo combo : this.getListaCombos()) {
            sb.append(combo);
            sb.append("\n");
        }
        return sb.toString();   
    }
    
    @Override
    public void agregarProductoCarrito(String nombreProducto,int cantidad){
           
        Producto p = new Producto();
        
        this.sp = new Servicio_Producto();
        
        for (Object obj : sp.selectAll()) {
            
            if (((Producto)obj).getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                
                p = (Producto)obj;
                
            }
            
        }
        
        this.clienteIngresado.getCarrito().setProductosEnCarrito(p);
        
        sp.updateStock(p, cantidad);
        
        p.setCantidad(cantidad);
        
        for(Object obj: sp.selectAll()){
            if (((Producto)obj).getCantidad()<=5){
            ((Producto)obj).notificarObservadores();
        }
        
        if(cantidad>=5){
            this.clienteIngresado.setTipoCliente("VIP");
        }
        
         
    }
    }
    
    @Override
    public Carrito verCarrito(){
        
        return this.clienteIngresado.getCarrito();
    }

    @Override
    public void insertarOrden() {
        Orden o=new Orden();
        o.setProductos(this.clienteIngresado.getCarrito().getProductosEnCarrito());
    }

    @Override
    public Cliente agarrarClienteIngresado() {
       return this.clienteIngresado; 
    }
    
    @Override
    public void realizarCompra(){
        
        ServicioFactura sf = new ServicioFactura();
        ServicioProductoComprado sfc = new ServicioProductoComprado(); 
        
        Orden orden = new Orden();
        
        Factura factura = new Factura(this.clienteIngresado,new Date());
        sf.insert(factura);
        
        for (Object o : sf.selectAll()) {
            factura = ((Factura)o);
        }
        
        orden.setFact(factura);
        
        for (Producto p : this.clienteIngresado.getCarrito().getProductosEnCarrito()) {
            orden.agregarProducto(p);
            sfc.productoEnOrden(factura.getNumeroOrden(),p.getCodigoProducto(),p.getCantidad());
        }
        
        this.clienteIngresado.getCarrito().setPrecioTotalCarrito(0);
        this.clienteIngresado.getCarrito().getProductosEnCarrito().clear();
        
        System.out.println("Compra realizada");
        System.out.println(orden);
    
    }
    
    @Override
    public String verOrdenes(){
        StringBuffer sb = new StringBuffer();
        ServicioProductoComprado sfc = new ServicioProductoComprado();
         
        List<Object> o = sfc.selectAll();
       
        for (Object obj : o ) {
            sb.append(((Orden)obj));
            sb.append("\n");
        }
        
        
        return sb.toString();
    
    }
    @Override
    public void cambiarContra(){
        String contra = JOptionPane.showInputDialog("Ingrese su nueva contraseña:");
        this.clienteIngresado.setContrasenna(contra);
        servicioCliente.updateContra(this.clienteIngresado);
    }    

    @Override
    public void agregarComboCarrito(String combo) {
        
        ICombo co;
        
        for (ICombo c : this.getListaCombos()) {
            if (combo.equalsIgnoreCase(c.getClass().getSimpleName())) {
                
                for (Producto p : c.getlistaDeProductos()) {
            
                this.clienteIngresado.getCarrito().setProductosEnCarrito(p);
                co = c;
                
                }
                
            }
        }
         
        System.out.println("Su combo "+combo.getClass().getSimpleName()+" se agrego al carrito.");
        
    }

    @Override
    public String verHistorial() {
        StringBuffer sb = new StringBuffer();
        ServicioProductoComprado sfc = new ServicioProductoComprado();
        
        List<Orden> retorno = sfc.historialDeCliente(this.clienteIngresado);
        
        for (Orden o : retorno) {
            sb.append(o +"\n");
        }
        return sb.toString();
    }
    
    
    
}

