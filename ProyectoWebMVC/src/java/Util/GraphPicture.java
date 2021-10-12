/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author edgar
 */

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GraphPicture extends JPanel{
    private int[][] data;
    private int x_size;
    private int y_size;

    public GraphPicture(int[][] data, int x_size, int y_size) {
        this.data = data;
        this.x_size = x_size;
        this.y_size = y_size;
    }
    
    public GraphPicture() {
    }
    
    // Se emplea para generar un archivo con la imagen de la grafica
    // emplea GRaphics2D
    public BufferedImage createGraphics() {
        int len = data.length;
        int x_center = x_size/2;
        int y_center = y_size/2;
        int radio = 200;
        int nsize = 50;
        double grad = 0;
        double step = 2*Math.PI/(len);
        int[] x=new int[len];
        int[] y=new int[len];

        // Elementos para genera guardar la imagen y enviarla.
        BufferedImage bufImage = new BufferedImage(x_size, y_size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d= bufImage.createGraphics();
        
        // Define un color en RGB
        Color bg = new Color( 237, 227, 204 );
        g2d.setBackground(bg);
        // Asegura que el fondo se restablezca al valor definido
        g2d.clearRect(0, 0, x_size, y_size);

        // Marca el centro de la grafica
        g2d.setColor(Color.black);
        g2d.fillOval(x_center, y_center, nsize/4, nsize/4);

        // Genera las posiciones de los nodos
        for (int i=0; i<len; i++){
            x[i]=(int) (Math.cos(grad+step*i)*radio)+x_center;
            y[i]=(int) (Math.sin(grad+step*i)*radio)+y_center;
        }        

        // Genera las lineas en base a la matriz de adyacencias             
        for (int i=0; i<len; i++){

            for (int j=0; j<len; j++){
                g2d.setColor(Color.BLUE);                    
                if (data[i][j] != 0){
                    g2d.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }

        // Imprime los nodos en forma circular, con etiqueta 
        for (int i=0; i<len; i++){
            
                // Nodos con color en la posicion (x,y)
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillOval(x[i]-nsize/2, y[i]-nsize/2, nsize, nsize);
                
                // Etiquetas con la fuente y el color
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font ("SansSerif", Font.PLAIN, 28));
                g2d.drawString(i+1+"", x[i]-8, y[i]+10);
        }
        g2d.dispose();
        return bufImage;        

    }
    
    /**
     *
     * @param g
     * Se utiliza para genera la imagen en un JFrame (ventana)
     * emplea Graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        int len = data.length;
        int x_center = x_size/2;
        int y_center = y_size/2;
        int radio = 200;
        int nsize = 50;
        double grad = 0;
        double step = 2*Math.PI/(len);
        int[] x=new int[len];
        int[] y=new int[len];
        super.paintComponent(g);        

        // Marca el centro de la grafica
        g.setColor(Color.black);
        g.fillOval(x_center, y_center, nsize/4, nsize/4);

        // Genera las posiciones de los nodos
        for (int i=0; i<len; i++){
            x[i]=(int) (Math.cos(grad+step*i)*radio)+x_center;
            y[i]=(int) (Math.sin(grad+step*i)*radio)+y_center;
        }        

        // Genera las lineas en base a la matriz de adyacencias             
        for (int i=0; i<len; i++){

            for (int j=0; j<len; j++){
                g.setColor(Color.BLUE);                    
                if (data[i][j] != 0){
                    g.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }

        // Imprime los nodos en forma circular, con etiqueta 
        for (int i=0; i<len; i++){
            
            // Nodos con color en la posicion (x,y)
            g.setColor(Color.LIGHT_GRAY);                    
            g.fillOval(x[i]-nsize/2, y[i]-nsize/2, nsize, nsize);
            
            // Etiquetas con la fuente y el color
            g.setColor(Color.BLACK);                    
            g.setFont(new Font ("SansSerif", Font.PLAIN, 28));
            g.drawString(i+1+"", x[i]-8, y[i]+10);
        }        
    }
}
