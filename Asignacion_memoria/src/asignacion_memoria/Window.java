/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.SystemColor;

/**
 *
 * @author OSCAR, Santiago
 */
public class Window extends JFrame implements ActionListener {

	JLabel lP = new JLabel("P:");
	JTextField tP = new JTextField();
	JLabel lM = new JLabel("M:");
	JTextField tM = new JTextField();
	JRadioButton first = new JRadioButton();
	JRadioButton next = new JRadioButton();
	JRadioButton best = new JRadioButton();
	JRadioButton worst = new JRadioButton();
	JButton b = new JButton("Asignacion de memoria");
	JButton bBorrar = new JButton("Borrar");
	Panel p = new Panel();
	ArrayList<Espacio_libre> es = new ArrayList<Espacio_libre>();
	ArrayList<Mensaje> me = new ArrayList<Mensaje>();
	ArrayList<Rectangle2D> r = new ArrayList<Rectangle2D>();

	public Window() {
		super("Asignacion de memoria");
		setBackground(Color.RED);
		es.add(new Espacio_libre(70, 180, 8, 1));
		es.add(new Espacio_libre(70, 280, 10, 3));
		es.add(new Espacio_libre(70, 460, 4, 5));
		first.setSelected(true);
		first.setText("Primer ajuste (first fit)");
		next.setText("Proximo ajuste (next fit)");
		best.setText("Mejor Ajuste (best fit)");
		worst.setText("Ultimo ajuste (Worst fit)");
		Container c = getContentPane();
		c.setLayout(null);
		p.setForeground(Color.RED);
		c.add(p);
		p.setBounds(409, 219, 334, 589);
		p.setBackground(SystemColor.menu);
		c.add(lP);
		lP.setBounds(10, 10, 51, 41);
		c.add(tP);
		tP.setBounds(96, 11, 100, 40);
		c.add(lM);
		lM.setBounds(10, 62, 51, 41);
		c.add(tM);
		tM.setBounds(96, 62, 100, 41);
		c.add(b);
		b.setBounds(544, 120, 193, 88);
		c.add(bBorrar);
		bBorrar.setBounds(219, 49, 100, 20);
		c.add(first);
		first.setBounds(94, 120, 166, 40);
		c.add(next);
		next.setBounds(94, 168, 166, 40);
		c.add(best);
		best.setBounds(344, 120, 183, 40);
		c.add(worst);
		worst.setBounds(344, 173, 183, 40);
		b.addActionListener(this);
		b.setActionCommand("Dibujar");
		bBorrar.addActionListener(this);
		bBorrar.setActionCommand("Borrar");
		first.addActionListener(this);
		first.setActionCommand("f");
		next.addActionListener(this);
		next.setActionCommand("n");
		best.addActionListener(this);
		best.setActionCommand("b");
		worst.addActionListener(this);
		worst.setActionCommand("w");

		Panel panel = new Panel();
		panel.setForeground(Color.RED);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(20, 219, 334, 589);
		getContentPane().add(panel);
		this.setSize(802, 920);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("f")) {
			first.setSelected(true);
			next.setSelected(false);
			best.setSelected(false);
			worst.setSelected(false);
		}
		if (e.getActionCommand().equals("n")) {
			first.setSelected(false);
			next.setSelected(true);
			best.setSelected(false);
			worst.setSelected(false);
		}
		if (e.getActionCommand().equals("b")) {
			first.setSelected(false);
			next.setSelected(false);
			best.setSelected(true);
			worst.setSelected(false);
		}
		if (e.getActionCommand().equals("w")) {
			first.setSelected(false);
			next.setSelected(false);
			best.setSelected(false);
			worst.setSelected(true);
		}
		if (e.getActionCommand().equals("Borrar")) {
			r.clear();
			me.clear();
			es.clear();
			es.add(new Espacio_libre(70, 180, 8, 1));
			es.add(new Espacio_libre(70, 280, 10, 3));
			es.add(new Espacio_libre(70, 460, 4, 5));
			this.p.repaint();
		}
		if (e.getActionCommand().equals("Dibujar")) {
			if (tM.getText().equals("") || tP.getText().equals("")) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Ingresa todos los datos  ");
			} else {
				try {
					boolean b = false;
					int p = Integer.parseInt(this.tP.getText()), m = Integer.parseInt(this.tM.getText());
					for (int i = 0; i < 3; i++) {
						if (m <= es.get(i).m) {
							b = true;
							break;
						}

					}
					if (b == false) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "No hay espacio suficiente");
					} else {
						if (this.hallar(p)) {
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "P es un numero ya tomado o cero intente de nuevo");
						} else {
							if (this.first.isSelected()) {

								for (int i = 0; i < 3; i++) {
									if (es.get(i).m >= m) {
										r.add(new Rectangle2D.Double(es.get(i).x, es.get(i).y, 220, m * 10));
										es.get(i).m -= m;
										es.get(i).p = p;
										es.get(i).y += m * 10;
										int tam;
										if (m >= 7) {
											tam = 70;
										} else {
											tam = m * 10;
										}
										me.add(new Mensaje("p" + p + "M" + m, tam, 140 - tam, es.get(i).y));
										break;

									}

								}
								this.dibujar();

							}
							if (this.next.isSelected()) {
								int ind = -1;
								for (int i = 0; i < 3; i++) {
									if (es.get(i).m >= m) {

										ind = i;
										break;
									}
								}
								int p1 = p - es.get(ind).p;
								for (int i = 0; i < 3; i++) {
									if (es.get(i).m >= m && es.get(i).p < p
											&& Math.abs(p - es.get(i).p) <= Math.abs(p1)) {

										ind = i;
										p1 = p - es.get(ind).p;
									}
								}
								r.add(new Rectangle2D.Double(es.get(ind).x, es.get(ind).y - 1, 220, m * 10));
								es.get(ind).m -= m;
								es.get(ind).p = p;
								es.get(ind).y += m * 10;
								int tam;
								if (m >= 7) {
									tam = 70;
								} else {
									tam = m * 10;
								}

								me.add(new Mensaje("p" + p + "M" + m, tam, 140 - tam, es.get(ind).y));
								this.dibujar();

							}
							if (this.best.isSelected()) {

								int a[] = new int[3];
								for (int i = 0; i < 3; i++) {
									a[i] = es.get(i).m - m;
								}
								int ind = 0;
								for (int i = 1; i < 3; i++) {
									if (a[i] >= 0 && (a[i] < a[ind] || a[ind] < 0)) {
										ind = i;

									}
								}
								r.add(new Rectangle2D.Double(es.get(ind).x, es.get(ind).y - 1, 220, m * 10));
								System.out.println(es.get(ind).m);

								es.get(ind).m -= m;
								System.out.println(es.get(ind).m);
								es.get(ind).p = p;
								es.get(ind).y += m * 10;
								int tam;
								if (m >= 7) {
									tam = 70;
								} else {
									tam = m * 10;
								}

								me.add(new Mensaje("p" + p + "M" + m, tam, 140 - tam, es.get(ind).y));
								this.dibujar();

							}
							if (this.worst.isSelected()) {
								int a[] = new int[3];
								for (int i = 0; i < 3; i++) {
									a[i] = es.get(i).m - m;
								}
								int ind = 0;
								for (int i = 1; i < 3; i++) {
									if (a[i] >= 0 && a[i] > a[ind]) {
										ind = i;

									}
								}
								r.add(new Rectangle2D.Double(es.get(ind).x, es.get(ind).y, 220, m * 10));
								es.get(ind).m -= m;
								es.get(ind).p = p;
								es.get(ind).y += m * 10;
								int tam;
								if (m >= 7) {
									tam = 70;
								} else {
									tam = m * 10;
								}

								me.add(new Mensaje("p" + p + "M" + m, tam, 140 - tam, es.get(ind).y));
								this.dibujar();
							}
						}

					}
				} catch (Exception ex) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Ingrese solo numeros");
				}
			}
		}
	}

	public boolean hallar(int p) {
		for (int i = 0; i < this.es.size(); i++) {
			if (es.get(i).p == p) {
				return true;

			}
		}
		if (p == 0 || p == 1 || p == 3 || p == 5) {
			return true;
		} else {
			return false;
		}

	}

	public void dibujar() {
		Graphics2D g2d = (Graphics2D) p.getGraphics();
		g2d.setColor(Color.red);
		Font f = new Font("Arial", Font.BOLD, 20);
		g2d.setFont(f);

		for (int i = 0; i < r.size(); i++) {
			g2d.draw(r.get(i));
		}
		for (int i = 0; i < me.size(); i++) {
			f = new Font("Arial", Font.BOLD, me.get(i).tam);
			g2d.setFont(f);
			g2d.drawString(me.get(i).m, me.get(i).x, me.get(i).y);
		}
	}
}
