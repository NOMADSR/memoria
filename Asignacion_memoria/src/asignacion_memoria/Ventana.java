
package asignacion_memoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Ventana extends JFrame implements ActionListener{
    JLabel lP=new JLabel("Proceso:");
    JTextField tP=new JTextField();
    JLabel lM=new JLabel("Megas:");
    JTextField tM=new JTextField();
    
    /*
    JRadioButton first=new JRadioButton();
    JRadioButton next=new JRadioButton();
    JRadioButton best=new JRadioButton();
    JRadioButton worst=new JRadioButton();
    */
    JButton b=new JButton("Asignar");
    JButton bBorrar=new JButton("Limpiar");
    JComboBox<String> asignaciones=new JComboBox<String>();
    Panel p=new Panel();
    
    ArrayList<Espacio_libre> es=new ArrayList<Espacio_libre>();
    ArrayList<Mensaje> me=new ArrayList<Mensaje>();
    ArrayList<Rectangle2D> r=new ArrayList<Rectangle2D>();
    public Ventana(){
        
        super("Asignacion de memoria");
        es.add(new Espacio_libre(70,180,8,1));
        es.add(new Espacio_libre(70,280,10,3));
        es.add(new Espacio_libre(70,460,4,5));
       /* first.setText("First fit");
        next.setText("Next fit");
        best.setText("Best fit");
        worst.setText("Worst fit");*/
        Font fontP=new Font("Arial", Font.BOLD, 20);
        Font fontM=new Font("Arial", Font.BOLD, 20);

        Container c=getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);
        c.add(p);
        p.setBounds(150,35,350,610);
        p.setBackground(Color.white);
        c.add(lP);
        lP.setBounds(10,50,120,30);
        lP.setFont(fontP);
        c.add(tP);
        
        tP.setBounds(40,80,60,30);
        
        lM.setBounds(10,120,110,35);
        lM.setFont(fontM);
        c.add(lM);
        c.add(tM);
        tM.setBounds(45,160,60,30);
        c.add(b);
        b.setBounds(10,340,150,50);
        c.add(bBorrar);
        bBorrar.setBounds(10,440,150,50);
        b.addActionListener(this);
        b.setActionCommand("Dibujar");
        bBorrar.addActionListener(this);
        bBorrar.setActionCommand("Borrar");
        
        asignaciones.addItem("first");
        asignaciones.addItem("next");
        asignaciones.addItem("best");
        asignaciones.addItem("worst");
        asignaciones.setBounds(10,250,100,40);
        c.add(asignaciones);
                
                
                
        /*
        
        c.add(first);
        first.setBounds(10,85,100,40);
        c.add(next);
        next.setBounds(10,125,100,40);
        c.add(best);
        best.setBounds(10,165,100,40);
        c.add(worst);
        worst.setBounds(10,205,100,40);
        
        best.setSelected(true);
        
        first.addActionListener(this);
        first.setActionCommand("f");
        next.addActionListener(this);
        next.setActionCommand("n");
        best.addActionListener(this);
        best.setActionCommand("b");
        worst.addActionListener(this);
        worst.setActionCommand("w");
        */
        
        this.setSize(500,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dato=(String) this.asignaciones.getSelectedItem();
        //JOptionPane.showMessageDialog(null,dato);
                  
       /*if(e.getActionCommand().equals("f")){
           first.setSelected(true);
           next.setSelected(false);
           best.setSelected(false);
           worst.setSelected(false);
       }
       if(e.getActionCommand().equals("n")){
           first.setSelected(false);
           next.setSelected(true);
           best.setSelected(false);
           worst.setSelected(false);
       }
       if(e.getActionCommand().equals("b")){
           first.setSelected(false);
           next.setSelected(false);
           best.setSelected(true);
           worst.setSelected(false);
       }
       if(e.getActionCommand().equals("w")){
           first.setSelected(false);
           next.setSelected(false);
           best.setSelected(false);
           worst.setSelected(true);
       }*/
       if(e.getActionCommand().equals("Borrar")){
           r.clear();
           me.clear();
           es.clear();
           es.add(new Espacio_libre(70,180,8,1));
           es.add(new Espacio_libre(70,280,10,3));
           es.add(new Espacio_libre(70,460,4,5));
           this.p.repaint();
       }
       if(e.getActionCommand().equals("Dibujar")){
           if(tM.getText().equals("") || tP.getText().equals("")){
              JFrame frame=new JFrame();
              JOptionPane.showMessageDialog(frame, "Ingrese todos los datos para hacer el proceso");
           }else{
               try{
               boolean b=false;
               int p=Integer.parseInt(this.tP.getText()),m=Integer.parseInt(this.tM.getText());
               for(int i=0;i<3;i++){
                       if(m<=es.get(i).m){
                           b=true;
                           break;
                       }
                       
                       
                   }
               if(b==false){
                   JFrame frame=new JFrame();
                       JOptionPane.showMessageDialog(frame, "No hay espacio de memoria suficiente");
               }else{
               if(this.hallar(p)){
                   JFrame frame=new JFrame();
                    JOptionPane.showMessageDialog(frame, "Ya es un proceso existente o nulo, intente de nuevo");  
               }else{    
                    if(dato.equals("first")){
                   
                   
           
                   
                  
                  
                        
                         for(int i=0;i<3;i++){
                             if(es.get(i).m>=m){
                                 r.add(new Rectangle2D.Double(es.get(i).x,es.get(i).y,220,m*10));
                                 es.get(i).m-=m;
                                 es.get(i).p=p;
                                 es.get(i).y+=m*10;
                                 int tam;
                                 if(m>=7){
                                     tam=70;
                                 }else{
                                     tam=m*10;
                                 }
                                 me.add(new Mensaje("P"+p+"M"+m,tam,140-tam,es.get(i).y));
                                 break;
                                 
                             }
                             
                           
                   }
                       this.dibujar();
                   
                       
                   
                   
                  
               }
               if(dato.equals("next")){
                 int ind=-1;
                    for(int i=0;i<3;i++){
                             if(es.get(i).m>=m){
                                
                                ind=i;
                                break;
                             }
               }
                int p1=p-es.get(ind).p;
                for(int i=0;i<3;i++){
                             if(es.get(i).m>=m && es.get(i).p<p  && Math.abs(p-es.get(i).p)<=Math.abs(p1) ){
                                
                                ind=i;
                                p1=p-es.get(ind).p;
                             }
               }
               r.add(new Rectangle2D.Double(es.get(ind).x,es.get(ind).y,220,m*10));
               es.get(ind).m-=m;
               es.get(ind).p=p;
               es.get(ind).y+=m*10;
               int tam;
                                 if(m>=7){
                                     tam=70;
                                 }else{
                                     tam=m*10;
                                 }
                                 
                                me.add(new Mensaje("P"+p+"M"+m,tam,140-tam,es.get(ind).y));
               this.dibujar();    
               
           }
               if(dato.equals("best")){
                
                int a[]=new int [3];
                for(int i=0;i<3;i++){
                    a[i]=es.get(i).m-m;
                }
                int ind=0;
                for(int i=1;i<3;i++){
                   if(a[i]>=0 && (a[i]<a[ind] || a[ind]<0)){
                       ind=i;
                       
                   }
                }
                r.add(new Rectangle2D.Double(es.get(ind).x,es.get(ind).y,220,m*10));
                System.out.println(es.get(ind).m);
                   
                es.get(ind).m-=m;
                System.out.println(es.get(ind).m);
                es.get(ind).p=p;
                es.get(ind).y+=m*10;
                 int tam;
                                 if(m>=7){
                                     tam=70;
                                 }else{
                                     tam=m*10;
                                 }
                                 
                                me.add(new Mensaje("P"+p+"M"+m,tam,140-tam,es.get(ind).y));
                                this.dibujar();
                   
               }
               if(dato.equals("worst")){
                int a[]=new int [3];
                for(int i=0;i<3;i++){
                    a[i]=es.get(i).m-m;
                }
                int ind=0;
                for(int i=1;i<3;i++){
                   if(a[i]>=0 && a[i]>a[ind]){
                       ind=i;
                       
                   }
                }
                r.add(new Rectangle2D.Double(es.get(ind).x,es.get(ind).y,220,m*10));
                es.get(ind).m-=m;
                es.get(ind).p=p;
                es.get(ind).y+=m*10;
                int tam;
                                 if(m>=7){
                                     tam=70;
                                 }else{
                                     tam=m*10;
                                 }
                                 
                                 me.add(new Mensaje("P"+p+"M"+m,tam,140-tam,es.get(ind).y));
                this.dibujar();   
               }
           }
               
       }
           }catch(Exception ex){
           JFrame frame=new JFrame();
           JOptionPane.showMessageDialog(frame, "Ingrese solo numeros");  
       }
    }
    }
    }
    
    
    //---------------------------------------------------------------------------------------------
    public boolean hallar(int p){
        for(int i=0;i<this.es.size();i++){
            if(es.get(i).p==p){
                return true;
                
            }
        }
        if(p==0 || p==1 || p==3 || p== 5){
            return true;
        }else{
            return false;
        }
        
    }
    public void dibujar(){
        Graphics2D g2d =(Graphics2D)p.getGraphics();
        g2d.setColor(Color.red);
        Font f=new Font("Arial", Font.BOLD, 20);
        g2d.setFont(f);
       
        for(int i=0;i<r.size();i++){
            g2d.draw(r.get(i));
        }        
        for(int i=0;i<me.size();i++){
            f=new Font("Arial", Font.BOLD, me.get(i).tam);
            g2d.setFont(f);
            g2d.drawString(me.get(i).m,me.get(i).x,me.get(i).y);
        }   
    }
}
