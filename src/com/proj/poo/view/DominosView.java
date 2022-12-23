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
	
	private JPanel container, game, controle;
	private Dominos partie;

	
	
	public DominosView(Dimension size, double scaleX, double scaleY) {
		container=new JPanel();
		container.setLayout(null);
		Dominos partie = new Dominos();
		controle= new JPanel();
		controle.setBounds(size.height, 0, size.width-size.height, size.height);
		controle.setBackground(Color.RED);
		game= new JPanel();
		game.setBounds(0,0,size.height,size.height);
		game.setLayout(new GridLayout(partie.getPlateau().length, partie.getPlateau().length));
		container.add(controle);
		container.add(game);
		container.setVisible(true);
		
		
	}
	
	public JPanel getMainContainer() { return container; } 
	
	public class TuileView extends JPanel{
		
		Tuile tuile;
		int x, y;
		int line=partie.getPlateau().length;
		
		public TuileView (Tuile tuile, int x, int y) {
			this.tuile= tuile;
			this.x = x;
			this.y = y;
			this.setBounds(x*100, y*100, 100, 100);
			this.setLayout(new BorderLayout(5,5));
			this.setBackground(Color.GRAY);
			creation();
		}

		private void creation() {
			int[] haut = tuile.getHaut();
			int[] bas = tuile.getBas();
			int[] gauche = tuile.getGauche();
			int[] droite = tuile.getDroite();
			
			JLabel c1 = new JLabel(" ");
			c1.setBounds(0,0,20,20);
			
			JLabel c2 = new JLabel(" ");
			c2.setBounds(80,0,20,20);
			
			JLabel c3 = new JLabel(" ");
			c3.setBounds(80,80,20,20);
			
			JLabel c4 = new JLabel(" ");
			c4.setBounds(0,80,20,20);
			
			JLabel c5 = new JLabel(" ");
			c4.setBounds(20,20,60,60);
			
			
			this.add(c1);
			this.add(c2);
			this.add(c3);
			this.add(c4);
			
			for (int i =0; i<3;i++) {
				int h= haut[i];
				int b= bas[i];
				int g= gauche[i];
				int d= droite[i];
				
				 
			}
			
			
			
			
		}
		
		
	}



}
