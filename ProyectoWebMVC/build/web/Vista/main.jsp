<%-- 
    Document   : main
    Created on : 23/01/2021, 03:47:48 PM
    Author     : edgar
--%>

<%@page import="java.util.Arrays"%>
<%@page import="Modelo.Grafo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyecto Algoritmos</title>
    </head>
    <body>
        <!--<h1>Proyecto de Programación avanzada</h1>
        Universidad Nacional Autónoma de México<br>
        Maestría en Ciencia e Ingeniería de la Computación<br>
        Programación avanzada<br>
        Profesor: Dr. Paul Erick Mendez Monroy<br>
        Alumno: Edgar Rigoberto Santos Martín<br><hr/>-->
        
        <table>
        <%
        if (request.getAttribute("grafos") != null &&
            request.getAttribute("action") == "readAll" ){
            out.print("<h2>"+request.getAttribute("initMsg")+"</h2>");
            List<Grafo> grafos = (List) request.getAttribute("grafos");
            out.print("<tr>\n"
                    + "<th>Nombre</th>\n"
                    + "<th>Número de vértices</th>\n"
                    + "<th>Número de aristas</th>\n"
                    + "<th>Lista de adyacencias</th>\n"
                    + "</tr>\n");
            for (Grafo g:grafos){
                out.print("<tr>\n"
                        + "<td>" + g.getName() + "</td>\n"
                        + "<td>" + g.getV() + "</td>\n"
                        + "<td>" + g.getEdges() + "</td>\n"
                        + "<td>" + Arrays.toString(g.getAdj()) + "</td>\n"
                        + "</tr>\n");
            }
        }
        %>
        </table>
        
        <%
            if (request.getAttribute("action") == "DFS" ){
                out.print("<h2>"+request.getAttribute("initMsg")+"</h2>");
                Grafo g=(Grafo)request.getAttribute("grafo");
                if (g != null){
                    out.print("<p>Nombre del grafo: " + g.getName() + "<br>" +
                            "DFS: "+g.DFS(0)+"<br></p>");
                }
            }
            
            if (request.getAttribute("action") == "hamiltonian" ){
                out.print("<h2>"+request.getAttribute("initMsg")+"</h2>");
                Grafo g=(Grafo)request.getAttribute("grafo");
                if (g != null){
                    out.print("<p>Nombre del grafo: " + g.getName() + "<br>" +
                            request.getAttribute("hamCycl")+"<br></p>");
                }
            }
            
            if (request.getAttribute("action") == "graficar" ){
                out.print("<h2>"+request.getAttribute("initMsg")+"</h2>");
                Grafo g=(Grafo)request.getAttribute("grafo");
                if (g != null){
                    g.graficar();
                    out.print("<p>Nombre del grafo: " + g.getName() + "<br>");
                }
            }
        %>
        
        <h3>Acciones a realizar</h3>
        <form id="action" action="http://localhost:8084/ProyectoWebMVC/Controlador/GrafoController" method="post">  
            <input type="radio" id="readAll" name="action" value="readAll">
            <label for="readAll">Leer todos los grafos en la Base de Datos</label><br>
            <input type="radio" id="graficar" name="action" value="graficar">
            <label for="graficar">Obtener imagen del grafo</label><br>
            <!--<input type="radio" id="DFS" name="action" value="DFS">
            <label for="DFS">Depth First Search (DFS)</label><br>-->
            <input type="radio" id="hamiltonian" name="action" value="hamiltonian">
            <label for="hamiltonian">¿Tiene ciclo Hamiltoniano?</label><br>
            Grafo seleccionado: <input type="text" name="grafo"><br>
            Vértice inicial para encontrar ciclo hamiltoniano:
            <input type="text" name="vertice"><br>
            <input type="submit" value="Realizar">
        </form>
    </body>
</html>
