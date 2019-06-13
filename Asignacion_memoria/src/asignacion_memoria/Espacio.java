/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asignacion_memoria;

import java.awt.geom.Rectangle2D;


public class Espacio {
    int alto,ancho;
    int p,m;
    int x,y;
    public Espacio(int x, int y, int alto,int ancho) {
        this.alto = alto;
        this.ancho=ancho;
        this.x = x;
        this.y = y;
    }
    public Rectangle2D getEspacio(){
        
        return new Rectangle2D.Double(x,y,alto,ancho);
    }
    
    
}
