/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.GrafoDAO;
import Modelo.Grafo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edgar
 */
public class GrafoController extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String action=request.getParameter("action");
        try{
            switch (action){
                case "readAll":
                    readAll(request, response);
                    break;
                case "DFS":
                    DFS(request, response);
                    break;
                case "hamiltonian":
                    hamiltonian(request, response);
                    break;
                case "graficar":
                    graficar(request, response);
                    break;
            }
        }catch (SQLException es){
            es.getStackTrace();
        }
    }
    
    private void readAll(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException {
        GrafoDAO list = new GrafoDAO();
        List<Grafo> grafos = list.getAllGrafos();
        if (grafos != null){
            request.setAttribute("grafos", grafos);
            request.setAttribute("action", "readAll");
            request.setAttribute("initMsg", "Grafos guardados en la DB");
        }
        else{
            request.setAttribute("grafos", null);
            request.setAttribute("action", "readAll");
            request.setAttribute("initMsg", "No hay registros");
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("../Vista/main.jsp");
        dispatcher.forward(request, response);
    }
    
    private void DFS(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException {
        Grafo g = read(request);
        if (g.getName() != null){
            request.setAttribute("grafo", g);
            request.setAttribute("action", "DFS");
            request.setAttribute("initMsg", "DFS del Grafo seleccionado");
        }
        else{
            request.setAttribute("grafo", null);
            request.setAttribute("action", "DFS");
            request.setAttribute("initMsg", "No hay registro");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("../Vista/main.jsp");
        dispatcher.forward(request, response);
    }
    
    private void hamiltonian(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException {
        Grafo g = read(request);
        String num = request.getParameter("vertice");
        if (g.getName() != null && num != ""){
            request.setAttribute("grafo", g);
            request.setAttribute("action", "hamiltonian");
            request.setAttribute("initMsg", "Ciclo Hamiltoniano del Grafo seleccionado");
            try {
                int n = Integer.parseInt(num);
                String ham = g.hamiltonian(g.getMatrix(), n);
                request.setAttribute("hamCycl", ham);
            } catch (NumberFormatException nfe){
              nfe.printStackTrace();
              request.setAttribute("hamCycl", "No se introdujo un entero en el vértice");
            }
            
        }
        else{
            request.setAttribute("grafo", null);
            request.setAttribute("action", "hamiltonian");
            request.setAttribute("initMsg", "No hay registro");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("../Vista/main.jsp");
        dispatcher.forward(request, response);
    }
    
    private void graficar(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException {
        Grafo g = read(request);
        if (g.getName() != null){
            request.setAttribute("grafo", g);
            request.setAttribute("action", "graficar");
            request.setAttribute("initMsg", "Imagen del Grafo seleccionado en ventana emergente");
        }
        else{
            request.setAttribute("grafo", null);
            request.setAttribute("action", "graficar");
            request.setAttribute("initMsg", "No hay registro");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("../Vista/main.jsp");
        dispatcher.forward(request, response);
    }
    
    private Grafo read(HttpServletRequest request) throws ServletException,IOException,SQLException {
        String nombre = request.getParameter("grafo");
        GrafoDAO dao = new GrafoDAO();
        Grafo g = dao.read(nombre);
        return g;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Realiza el proceso del menú";
    }// </editor-fold>

}
