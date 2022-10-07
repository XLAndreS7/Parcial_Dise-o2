/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Persona;
import modelo.Producto;
import modelo.Venta;

/**
 *
 * @author acer
 */
public class ControladorPrincipal {
  Conexion conexion;

    public ControladorPrincipal() {
        conexion = new Conexion();
    }
 
    public boolean guardar(String cedula,String nombre, 
  String apellido, String correoElectronico,String contrasena){
       
        Persona persona = new Persona(cedula, nombre, apellido, correoElectronico, contrasena);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("insert into Persona(cedula,nombre,apellido,correoElectronico,contrasena) "
                + "values('" + persona.getCedula() + "','" +
                persona.getNombre() + "','" +
                persona.getApellido() + "','" +
                persona.getCorreoElectronico()+ "'," +
                persona.getContrasena()+ ")");
            conexion.desconectar();         
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }

    public List<String> buscar(String cedula) {
        
        List<String> temp = new ArrayList<String>();
        
        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select cedula,nombre,apellido,correoElectronico,"
                            + "contrasena from Persona where "
                            + "cedula='" + cedula + "'"));        

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("cedula"));
                temp.add(conexion.getResultadoDB().getString("nombre"));
                temp.add(conexion.getResultadoDB().getString("apellido"));
                temp.add(conexion.getResultadoDB().getString("correoElectronico"));
                temp.add(conexion.getResultadoDB().getString("contrasena")+"");
            }
            conexion.desconectar();                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorPrincipal.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();
        }
        return temp;
    }

    public boolean modificar(String cedula,String nombre, 
    String apellido, String correoElectronico,String contrasena){
       
        Persona persona = new Persona(cedula, nombre, apellido, correoElectronico, contrasena);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("update Persona set nombre='" + persona.getNombre() + 
                "',apellido='" + persona.getApellido() + "'," +
                "correoElectronico='" + persona.getCorreoElectronico()+ "'," +        
                "contrasena=" + persona.getContrasena()+  
                 " where cedula='" + persona.getCedula()+"'");
            conexion.desconectar();          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }

    public boolean eliminar(String cedula) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute
        ("delete from Persona where cedula='" + cedula+"'");
            conexion.desconectar();         
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }
    public boolean guardarProducto(String codigoProducto,String nombreProducto, 
  double precio, int cantidad, String categoria){
       
        Producto producto = new Producto(codigoProducto, nombreProducto, precio, cantidad, categoria);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("insert into Producto(codigoProducto,nombreProducto,precio,cantidad,categoria) "
                + "values('" + producto.getCodigoProducto()+ "','" +
                producto.getNombreProducto()+ "','" +
                producto.getPrecio()+ "','" +
                producto.getCantidad()+ "'," +
                producto.getCategoria()+ ")");
            conexion.desconectar();         
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }

    public List<String> buscarProducto(String codigoProducto) {
        
        List<String> temp = new ArrayList<String>();
        
        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigoProducto,nombreProducto,precio,cantidad,"
                            + "categoria from Producto where "
                            + "codigoProducto='" + codigoProducto + "'"));        

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("codigoProducto"));
                temp.add(conexion.getResultadoDB().getString("nombreProducto"));
                temp.add(conexion.getResultadoDB().getString("precio"));
                temp.add(conexion.getResultadoDB().getString("cantidad"));
                temp.add(conexion.getResultadoDB().getString("categoria")+"");
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorPrincipal.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }

    public boolean modificarProducto(String codigoProducto,String nombreProducto, 
  double precio, int cantidad, String categoria){
       
        Producto producto = new Producto(codigoProducto, nombreProducto, precio, cantidad, categoria);

        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("update Producto set nombreProducto='" + producto.getNombreProducto()+ 
                "',precio='" + producto.getPrecio()+ "'," +
                "cantidad='" + producto.getCantidad()+  "',"+     
                "categoria=" + producto.getCategoria() 
                + " where codigoProducto='" + producto.getCodigoProducto()+"'");
            conexion.desconectar();          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }

    public boolean eliminarProducto(String codigoProducto) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute
        ("delete from Producto where codigoProducto='" + codigoProducto+"'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }  
    public List<String> validarCorreo(String correoElectronico) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select correoElectronico from Persona where "
                            + "correoElectronico='" + correoElectronico + "'"));

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("correoElectronico") + "");
            }
            conexion.desconectar();//se desconecta de la base de datos
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;

    }

    public List<String> validarContraseña(String contrasena) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select contrasena from Persona where "
                            + "contrasena='" + contrasena + "'"));

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("contrasena") + "");
            }
            conexion.desconectar();//se desconecta de la base de datos
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPrincipal.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;

    }
    
       public DefaultTableModel listar() {
        DefaultTableModel temporal;
        String nombreColumnas[] = {"Codigo", "Nombre", "precio", "cantidad","categoria"};
        temporal = new DefaultTableModel(
                new Object[][]{}, nombreColumnas);
        conexion.conectar();
        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigoProducto,nombreProducto,precio,cantidad,"
                            + "categoria from Producto order by codigoProducto"));//consulta        
            while (conexion.getResultadoDB().next()) {
                temporal.addRow(new Object[]{
                    conexion.getResultadoDB().getString("codigoProducto"),
                    conexion.getResultadoDB().getString("nombreProducto"),
                    conexion.getResultadoDB().getDouble("precio"),
                    conexion.getResultadoDB().getInt("cantidad"),
                    conexion.getResultadoDB().getString("categoria")});
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {            
            Logger.getLogger(ControladorPrincipal.class.getName()).
                    log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }

        return temporal;

    }
        public boolean guardarVenta(String codigoVenta, String fechaVenta, int unidadesVendidas, int total, Producto producto){
       
        Venta venta = new Venta(codigoVenta, fechaVenta, unidadesVendidas, total, producto);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute
        ("insert into Venta(codigoVenta,fechaVenta,unidadesVendidas,total,producto) "
                + "values('" + venta.getCodigoVenta()+ "','" +
                venta.getFechaVenta()+ "','" +
                venta.getUnidadesVendidas()+ "','" +
                venta.getTotal()+ "'," +
                venta.getProducto()+ ")");
            conexion.desconectar();         
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();          
            return false;
        }
    }
}
