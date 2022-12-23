package com.proj.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.dominos.Dominos;
import com.proj.poo.dominos.Tuile;

public class DominosView extends JPanel {

	private static final long serialVersionUID = -7758253179910801855L;
	
	private JPanel game, controle;
	private Dominos partie;
	private Dimension size;
	double scaleX, scaleY;

	
	
	public DominosView(Dimension size, double scaleX, double scaleY) {
		this.size = size;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.setLayout(new BorderLayout());
		Dominos partie = new Dominos();
		
		controle= new JPanel();
		controle.setPreferredSize(new Dimension(size.width-size.height, size.height));
		controle.setBackground(Color.RED);
		
		game= new JPanel();
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setLayout(new GridLayout(partie.getPlateau().length, partie.getPlateau().length));
		game.setBackground(Color.GRAY);
		
		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);
		
		Tuile t = partie.piocher();
		placeTuile(t,0,0);
		
	}
	
	
	public void placeTuile(Tuile tuile, int x, int y) {
		TuileView t= new TuileView(tuile, x, y);
		game.add(t);
		game.repaint();
		//ajouter changement couleur
	}
	
	
	public class TuileView extends JPanel{
		

		private static final long serialVersionUID = 8825429997330574373L;
		
		Tuile tuile;
		int x, y;
		int tailleTuile = (size.height / partie.getPlateau().length);
		
		public TuileView (Tuile tuile, int x, int y) {
			this.tuile= tuile;
			this.x = x;
			this.y = y;
			this.setBounds(x * tailleTuile, y * tailleTuile, tailleTuile, tailleTuile);
			this.setLayout(new BorderLayout(5,5));
			this.setBackground(Color.BLACK);
			creation();
		}

		private void creation() {
			int[] haut = tuile.getHaut();
			int[] bas = tuile.getBas();
			int[] gauche = tuile.getGauche();
			int[] droite = tuile.getDroite();
			
			JLabel c1 = new JLabel(" ");
			c1.setBounds(0, 0, tailleTuile/5, tailleTuile/5);
			
			JLabel c2 = new JLabel(" ");
			c2.setBounds(tailleTuile/5 * 4, 0, tailleTuile/5, tailleTuile/5);
			
			JLabel c3 = new JLabel(" ");
			c3.setBounds(tailleTuile/5 * 4, tailleTuile/5 * 4, tailleTuile/5, tailleTuile/5);
			
			JLabel c4 = new JLabel(" ");
			c4.setBounds(0, tailleTuile/5 * 4, tailleTuile/5, tailleTuile/5);
			
			JLabel c5 = new JLabel(" ");
			c4.setBounds(tailleTuile/5, tailleTuile/5, tailleTuile/5 * 3, tailleTuile/5 * 3);
			
			
			this.add(c1);
			this.add(c2);
			this.add(c3);
			this.add(c4);
			
			for (int i =0; i<3;i++) {
				int h= haut[i];
				int b= bas[i];
				int g= gauche[i];
				int d= droite[i];
				
				
				JLabel label_h = new JLabel(String.valueOf(h));
				label_h.setLocation((tailleTuile / 5 ) + i * (tailleTuile / 5), 0);
				this.add(label_h);
				
				JLabel label_b = new JLabel(String.valueOf(b));
				label_b.setLocation((tailleTuile / 5 ) + i * (tailleTuile / 5), (tailleTuile / 5) * 4);
				this.add(label_b);
				
				JLabel label_g = new JLabel(String.valueOf(g));
				label_g.setLocation(0, (tailleTuile / 5 ) + i * (tailleTuile / 5));
				this.add(label_g);
				
				JLabel label_d = new JLabel(String.valueOf(d));
				label_d.setLocation((tailleTuile / 5) * 4, (tailleTuile/5 )+ i * (tailleTuile/5));
				this.add(label_d);
			
			}
			
			this.add(c5);
			
		}
		
	}

}
