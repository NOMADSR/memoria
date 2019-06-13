/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asignacion_memoria;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;


public class Panel extends JPanel{
    ArrayList<Espacio>espacios=new ArrayList<Espacio>();
    
    public Panel(){
        init();
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        Font f=new Font("Helvetica", Font.BOLD, 40);
        g2d.setFont(f);
        g2d.setColor(Color.darkGray);
       for(int i=0;i<espacios.size();i++){
           g2d.draw(espacios.get(i).getEspacio());
       }
        g2d.drawString("OS8M", 120, 70);
        g2d.drawString("P18M", 120, 158);
        f=new Font("TimesRoman", Font.BOLD, 20);
        g2d.setFont(f);
        g2d.drawString("P32M", 145, 280);
        f=new Font("TimesRoman", Font.BOLD, 40);
        g2d.setFont(f);
        g2d.drawString("P58M", 120, 446);
        f=new Font("TimesRoman", Font.BOLD, 30);
        g2d.setFont(f);
        g2d.drawString("0", 50, 30);
        g2d.drawString("8", 50, 110);
        g2d.drawString("16", 30, 190);
        g2d.drawString("24", 30, 265);
        g2d.drawString("26", 30, 295);
        g2d.drawString("36", 30, 390);
        g2d.drawString("44", 30, 470);
        g2d.drawString("48", 30, 510);
        g2d.drawString("8", 310, 70);
        g2d.drawString("8", 310, 160);
        g2d.drawString("8", 310, 230);
        g2d.drawString("2", 310, 280);
        g2d.drawString("10", 300, 350);
        g2d.drawString("8", 310, 430);
        g2d.drawString("4", 310, 490);
    }
    public void init(){
        espacios.add(new Espacio(70,20,220,80));
        espacios.add(new Espacio(70,100,220,80));
        espacios.add(new Espacio(70,180,220,80));
        espacios.add(new Espacio(70,260,220,20));
        espacios.add(new Espacio(70,280,220,100));
        espacios.add(new Espacio(70,380,220,80));
        espacios.add(new Espacio(70,460,220,40));
       
        
    }
    
    
}
