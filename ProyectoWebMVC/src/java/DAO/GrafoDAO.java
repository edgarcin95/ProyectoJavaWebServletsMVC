/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Grafo;
import Util.UConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgar
 */
public class GrafoDAO {
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;
    
    public List getAllGrafos() {
        List ret = new ArrayList<>();
        try {
            con = UConnection.getConnection();
            String sql = "SELECT * FROM grafos";
            pstm = con.prepareStatement(sql);
            // instruccion para solicitar la consulta a la DB
            rs = pstm.executeQuery(); // realiza la petición 
            String aristas = null;
            while( rs.next() ) {
                Grafo g = new Grafo(rs.getString("nombre"),
                        Integer.parseInt(rs.getString("numvertices")),
                        Integer.parseInt(rs.getString("numaristas")));
                aristas = rs.getString("aristas");
                String[] arSplit = aristas.split("/");
                for (String arSplit1 : arSplit) {
                    String[] v = arSplit1.split(",");
                    g.addEdge(Integer.parseInt(v[0]),Integer.parseInt(v[1]));
                }
                ret.add(g);
            }
        }
        catch(IOException | ClassNotFoundException ex){
            System.out.println("\n No se pudo establecer la conexion"+ ex);
        }
        catch (SQLException ex){
            System.out.println("\n La tabla no tiene registros");
            Logger.getLogger(GrafoDAO.class.getCanonicalName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Grafo read(String n) {
        try {
            con = UConnection.getConnection();
//            System.out.println(con.toString());
            String sql = "SELECT * FROM grafos WHERE nombre = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, n);
            // instruccion para solicitar la consulta a la DB
            rs = pstm.executeQuery(); // realiza la petición 
            String nombre = null;
            int numvertices = 0;
            int numaristas = 0;
            String aristas = null;
            while( rs.next() ) {
                nombre = rs.getString("nombre");
                numvertices = Integer.parseInt(rs.getString("numvertices"));
                numaristas = Integer.parseInt(rs.getString("numaristas"));
                aristas = rs.getString("aristas");
            }
            Grafo go = new Grafo(nombre,numvertices,numaristas);
            if (nombre != null){
                String[] arSplit = aristas.split("/");
                for (String arSplit1 : arSplit) {
                    String[] v = arSplit1.split(",");
                    go.addEdge(Integer.parseInt(v[0]),Integer.parseInt(v[1]));
                }
            }
            return go;
        }
        catch(IOException | ClassNotFoundException ex){
            System.out.println("\n No se pudo establecer la conexion" + ex);
            return null;
        }
        catch (SQLException ex){
            System.out.println("\n No existe el registro " + ex.getMessage());
            return null;
        }
    }
}
