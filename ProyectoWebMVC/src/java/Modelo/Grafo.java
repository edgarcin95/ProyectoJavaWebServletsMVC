/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Util.GraphPicture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 *
 * @author edgar
 */
public class Grafo {
    private String name;
    private int V; //No. de vertices
    private int edges; //No. de aristas
    private LinkedList<Integer> adj[]; //Para la lista de adyacencias
    private int[][] matrix; //Matriz de adyacencias
    private boolean[][] isSetMatrix; //Ayuda a imprimir la matriz en consola
    int cycle[];

    public Grafo(String name, int vertices, int aristas) {
        this.name = name;
        this.V = vertices;
        this.edges = aristas;
        adj = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adj[i] = new LinkedList();
        matrix = new int[vertices][vertices];
        isSetMatrix = new boolean[vertices][vertices];
    }

    public String getName() {
        return name;
    }

    public int getV() {
        return V;
    }

    public int getEdges() {
        return edges;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public int[][] getMatrix() {
        return matrix;
    }
    
    //Función para añadir una arista al grafo
    public void addEdge(int v, int w){ 
        adj[v].add(w); //Agrega w a la lista v
        int valueToAdd = 1;
        matrix[v][w] = valueToAdd; //Agrega un 1 a la matriz de adyacencias en (v,w)
        isSetMatrix[v][w] = true;
        matrix[w][v] = valueToAdd; //Agrega un 1 a la matriz en (w,v)
        isSetMatrix[w][v] = true;
    }
    
    public void printMatrix() {
        System.out.println("Matriz de adyaciencias:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                //Se imprime 1 en donde se marcó, los demás imprime 0
                if (isSetMatrix[i][j])
                    System.out.format("%8s", String.valueOf(matrix[i][j]));
                else System.out.format("%8s", "0");
            }
            System.out.println();
        }
    }
    
    ArrayList<Integer> visitaDFS(int v, boolean visited[], ArrayList<Integer> ret){
        visited[v] = true;
        ret.add(v);
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) 
        {
            int n = i.next();
            if (!visited[n])
                visitaDFS(n, visited, ret);
        }
        return ret;
    }
    
    public ArrayList<Integer> DFS(int v){
        ArrayList<Integer> dfs = new ArrayList<Integer>();
        boolean visited[] = new boolean[V];
        return visitaDFS(v, visited, dfs);
    }
    
    
    public String hamiltonian(int[][] matrix, int s) {
        if (s < V){
            cycle = new int[V];
            for (int i = 0; i < V; i++)
                cycle[i] = -1;
            cycle[0] = s;
            if (hamCycleRec(matrix, cycle, 1) == false)
                return "La gráfica no tiene ciclo hamiltoniano";
            String p = Arrays.toString(cycle);  //[...]
            p = p.substring(0, p.length() - 1); //[...
            String ret = "La gráfica tiene ciclo hamiltoniano." +
                    " El siguiente es uno de ellos: " + p + ", " +cycle[0] +"]";
            return ret;
        }
        else return "Vértice fuera de límite";
    }

    boolean hamCycleRec(int[][] matrix, int cycle[], int idx) {
        if (idx == V)
            return matrix[cycle[idx-1]][cycle[0]] == 1;
        for (int v = 0; v < V; v++) {
            if (canAdd(v, matrix, cycle, idx)) {
                cycle[idx] = v;
                if (hamCycleRec(matrix, cycle, idx + 1) == true) 
                    return true;
                cycle[idx] = -1;
            }
        }
        return false;
    }

    boolean canAdd(int v, int[][] matrix, int cycle[], int idx) {
        if (matrix[cycle[idx - 1]][v] == 0) 
            return false;
        for (int i = 0; i < idx; i++)
            if (cycle[i] == v)
                return false;
        return true;
    }

    public void graficar(){
        int x_size = 650;
        int y_size = 650;
        
        GraphPicture panel = new GraphPicture(matrix, x_size, y_size);
        JFrame app = new JFrame();
//        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        app.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //HIDE_ON_CLOSE by default
        app.add(panel);
        app.setSize(x_size, y_size);
        app.setVisible(true);
    }
}
