/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.clasesDiarioFacil;

import java.util.Date;

/**
 *
 * @author Personal
 */
public interface iAdministrador {
    
    //Mantenimiento de productos
    public String verProductos();
    public void agregarProducto(Producto producto);
    public void agregarCategoria(Categoria categoria);
    public String verListaCategorias();
    
    //Creacion de combos
    public String verListaCombos();
    public void agregarCombo(String combo);
    
    //Pedidos
    public String verListaPedidos();
    public void hacerPedido();
    
    //Promociones
    public void agregarPromocion(String nombre);
    public String verPromociones();
    
    //Mantenimiento de clientes
    public String verListaClientes();
    public void agregarCliente(Cliente cliente);
    public void actualizarCliente(String cliente,String correo);
    
    //Mantenimiento de provedores
    public String verListaProveedores();
    public void agregarProveedor(Proveedor proveedor);

    
    //Ordenes o facturas
    public String verListaFacturas();
    public void buscarFacturaPorFecha(Date fechaFactura);
       
//    System.out.println("1.Mantenimiento de productos.");
//                System.out.println("2.Mantenimiento de proveedores.");
//                System.out.println("3.Creación de paquetes.");
//                System.out.println("4.Pedido.");
//                System.out.println("5.Promoción");
//                System.out.println("6.Mantenimiento de clientes.");
//                System.out.println("7.Ordenes.");
//                System.out.println("8.Salir");
//                System.out.println("------------
    
}
