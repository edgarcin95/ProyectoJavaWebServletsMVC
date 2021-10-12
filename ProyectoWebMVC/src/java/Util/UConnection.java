/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgar
 */
public class UConnection {
    private static Connection conn=null;
    
    public static Connection getConnection() throws IOException, ClassNotFoundException {
        try {
            if( conn == null ) {
                // define un visor por el uso de la conexion
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook()); 
                // obtiene la informacion desde un archivo de propiedades
                String path = "C:\\Users\\edgar\\Documents\\UNAM\\1er semestre\\Programación Avanzada\\Netbeans\\ProyectoWebMVC\\src\\db.properties";
                Properties pros = new Properties();
                File file = new File(path);
                FileInputStream f = new FileInputStream(file);
                pros.load(f);
                // asigna la informacion a las variables
                String driver=pros.getProperty("driver");
                String url=pros.getProperty("url");
                String pwd=pros.getProperty("pwd");
                String usr=pros.getProperty("usr");
                // carga la clase mysqlconnector almacenada en library
                Class.forName(driver);
                // establece la conexion con la DB
                conn = DriverManager.getConnection(url,usr,pwd);
                conn.setAutoCommit(false);
            }
            return conn;
        }
        catch(FileNotFoundException ef){
            System.out.println("No encontramos el archivo que buscas \n" + ef);
            throw new RuntimeException("Error en la direccion",ef);
        }
        catch(SQLException ex) {
            throw new RuntimeException("Error al crear la conexion",ex);
        }
        catch (ClassNotFoundException ec) {
                System.out.println("No se tiene el controlador " + ec);
            throw new RuntimeException("No se tiene el controlador",ec);
                }
    } // fin de getConnection
    
    // clase interna para vigilar por el cierre de la conexion
    static class MiShDwnHook extends Thread {
        
        @Override
        public void run() { // JVM invocara a este metodo para cerrar la conexion
            try {
                Connection conn = UConnection.getConnection();
                conn.close();
            }
            catch( SQLException ex ) {
                throw new RuntimeException(ex);
            } 
            catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(UConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // fin de run
    }
}
