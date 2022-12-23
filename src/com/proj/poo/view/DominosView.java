package com.proj.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.Tuile;

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
		partie = new Dominos();
		
		controle= new JPanel();
		controle.setPreferredSize(new Dimension(size.width-size.height, size.height));
		controle.setBackground(Color.RED);
		controle.setLayout(null);
		
		game= new JPanel(new GridLayout(partie.getPlateau().length, partie.getPlateau().length));
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setBackground(Color.GRAY);
		game.setLayout(null);
		
		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);
		
		placeTuile(partie.piocher(),partie.getPlateau().length/2,partie.getPlateau().length/2);
		
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
			this.setLayout(new GridLayout(5,5,0,0));
			this.setBackground(Color.PINK);
			creation();
		}

		private void creation() {
			int[] haut = tuile.getHaut();
			int[] bas = tuile.getBas();
			int[] gauche = tuile.getGauche();
			int[] droite = tuile.getDroite();
			
			JLabel c1 = new JLabel(" ");
			JLabel c2 = new JLabel(" ");
			JLabel c3 = new JLabel(" ");
			JLabel c4 = new JLabel(" ");

			this.add(c1);
			for (int i =0; i<3;i++) {
				int h= haut[i];
				JLabel label_h = new JLabel(" "+String.valueOf(h));
				this.add(label_h);
			}
			this.add(c2);
			
			for (int i =0; i<3;i++) {
				int g= gauche[i];
				int d= droite[i];
				
				JLabel label_g = new JLabel("  "+String.valueOf(g));
				this.add(label_g);
				
				JLabel space1 = new JLabel(" ");
				this.add(space1);
				JLabel space2 = new JLabel(" ");
				this.add(space2);
				JLabel space3 = new JLabel(" ");
				this.add(space3);
				
				JLabel label_d = new JLabel(""+String.valueOf(d));
				this.add(label_d);
			}
			
			this.add(c3);
			for (int i =0; i<3;i++) {
				int b= bas[i];
				JLabel label_b = new JLabel(" "+String.valueOf(b));
				this.add(label_b);
			}
			this.add(c4);
			
		}
		
	}

}
