package com.proj.poo.view;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.runner.Auxiliaire;
import com.proj.poo.view.DominosView.TuileView;



public class CarcassonneView extends JPanel{

	private static final long serialVersionUID = 4489854025470300053L;
	
	private CarcassonneController controller;
	private JPanel game, controle;
	private Dimension size;
	double scaleX, scaleY;	
	int tailleTuile;

	public CarcassonneView(Dimension size, double scaleX, double scaleY, CarcassonneController controller) {
		this.setPreferredSize(size);
		this.size = size;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.setLayout(new BorderLayout());
		this.controller = controller;
		tailleTuile = (size.height / controller.getPlateauLength());
		controle = new Controle();

		game = new JPanel(new GridLayout(controller.getPlateauLength(), controller.getPlateauLength()));
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setBackground(Color.GRAY);
		game.setLayout(null);

		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);

		
	}
	
	public TuileView placeTuile(int x, int y) {
		TuileView t = new TuileView(x,y);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());
		return t;

	}
	
	
	public Dimension getSizeView() {
		return size;
	}
	
	public Controle getControle() { return (Controle) controle; }
	
	public class TuileView extends JLabel{

		private static final long serialVersionUID = 7425520887508191851L;
		private int x, y;
		private double rotation;
		int tailleTuile = (size.height / controller.getPlateauLength());

		
		
		public TuileView (int x, int y) {
			this.x = x;
			this.y = y;
			rotation=0;
			this.setIcon(Auxiliaire.resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + controller.getActualTuile().getClass().getSimpleName() + ".png"),tailleTuile,tailleTuile));
			this.setBounds(x * tailleTuile, y * tailleTuile, tailleTuile, tailleTuile);
			

		}
		
		@Override
		public void paintComponent(Graphics g) {
			((Graphics2D) g).rotate(rotation,(double) getWidth()/2, (double) getHeight()/2);
            super.paintComponent(g);
		}

		public void tuileRotation() {
			rotation+=Math.toRadians(90);
			this.repaint();
			controller.rotationTuile();
			game.paintComponents(game.getGraphics());
		}
		
	}
	public class Controle extends JPanel{

		private static final long serialVersionUID = -4197348622134353408L;

		JButton piocheBtn, poserBtn, rotationBtn, defausserBtn, abandonBtn;
		JButton hautBtn, basBtn, gaucheBtn, droiteBtn;
		JLabel name, points, communication;
		JPanel info, boutons, commandes;
		TuileView tuileV;


		public Controle() {
			this.setPreferredSize(new Dimension(size.width - size.height, size.height));
			this.setBackground(new Color(204,229,255));
			this.setLayout(new GridLayout(3,1,10,10));

			info = new JPanel(new GridLayout(3,1));
			info.setBackground(getBackground());
			boutons = new JPanel(new GridLayout(3,1));
			boutons.setBackground(this.getBackground());
			commandes = new JPanel(new GridLayout(2,3));
			commandes.setBackground(getBackground());

			JPanel boutons_h = new JPanel(new GridLayout(1,1));
			boutons_h.setBackground(this.getBackground());
			JPanel boutons_m = new JPanel(new GridLayout(1,3));
			boutons_m.setBackground(this.getBackground());
			JPanel boutons_b = new JPanel(new GridLayout(1,1));
			boutons_b.setBackground(this.getBackground());

			piocheBtn = new JButton("Piocher");
			piocheBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(piocheBtn,new Color(0,128,255),new Color(204,229,255));
			piocheBtn.addActionListener((ActionEvent e) ->{
				tuileV = controller.piocheTuile();
				game.paintComponents(game.getGraphics());
				piocheBtn.setEnabled(false);
				poserBtn.setEnabled(true);
				rotationBtn.setEnabled(true);
				defausserBtn.setEnabled(true);
			});

			poserBtn = new JButton("Poser");
			poserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(poserBtn,new Color(0,128,255),new Color(204, 229, 255));
			poserBtn.setEnabled(false);
			/*poserBtn.addActionListener((ActionEvent e) -> {
				int x = tuileV.getTx();
				int y = tuileV.getTy();

				
				if (controller.placeTuile(x, y) == true) {
					game.remove(tuileV);
					tuileV = null;
					placeTuile(x, y);
					controller.setActualTuile(null);
					communication.setText("Bien joué, vous avez réussi à placer votre tuile");
					info.add(communication);
					this.repaint();
					this.paintComponents(this.getGraphics());
					poserBtn.setEnabled(false);
					rotationBtn.setEnabled(false);
					defausserBtn.setEnabled(false);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					tour();
				}
			});*/

			rotationBtn = new JButton("Rotation");
			rotationBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(rotationBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			rotationBtn.setEnabled(false);
			rotationBtn.addActionListener((ActionEvent e) -> {
				tuileV.tuileRotation();

			});
			defausserBtn = new JButton("Défausser");
			defausserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(defausserBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			defausserBtn.setEnabled(false);
			/*defausserBtn.addActionListener((ActionEvent e) ->{
				game.remove(tuileV);
				game.repaint();
				game.paintComponents(game.getGraphics());
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				tour();
			});*/

			abandonBtn = new JButton("Abandonner");
			abandonBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(abandonBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			/*abandonBtn.addActionListener((ActionEvent e) ->{
				controller.abandon();
				if (tuileV !=null) {
					game.remove(tuileV);
					game.repaint();
					game.paintComponents(game.getGraphics());
					}
				
				
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				tour();
			});*/


			boutons_h.add(piocheBtn);
			boutons_m.add(poserBtn);
			boutons_m.add(rotationBtn);
			boutons_m.add(defausserBtn);
			boutons_b.add(abandonBtn);


			boutons.add(boutons_h);
			boutons.add(boutons_m);
			boutons.add(boutons_b);

			hautBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_haut.png"));
			boutonsPerso(hautBtn,new Color(0,128,255),null);
			/*hautBtn.addActionListener((ActionEvent e)->{
				controller.haut();
			});*/

			basBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_bas.png"));
			boutonsPerso(basBtn,new Color(0,128,255),null);
			/*basBtn.addActionListener((ActionEvent e)->{
				controller.bas();
			});*/

			gaucheBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_gauche.png"));
			boutonsPerso(gaucheBtn,new Color(0, 128, 255), null);
			/*gaucheBtn.addActionListener((ActionEvent e)->{
				controller.gauche();				
			});*/

			droiteBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_droite.png"));
			boutonsPerso(droiteBtn, new Color(0, 128, 255),null);
			/*droiteBtn.addActionListener((ActionEvent e)->{
				controller.droite();				
			});*/


			JLabel j1 = new JLabel();
			JLabel j2 = new JLabel();

			commandes.add(j1);
			commandes.add(hautBtn);
			commandes.add(j2);
			commandes.add(gaucheBtn);
			commandes.add(basBtn);
			commandes.add(droiteBtn);


			name = new JLabel("Pseudo du joueur : ");
			name.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			name.setForeground(new Color(0, 128, 255));

			points = new JLabel("Nombre de points du joueur : ");
			points.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			points.setForeground(new Color(0, 128, 255));

			communication = new JLabel();
			communication.setFont(new Font("Arial", Font.BOLD, (int) (22 * scaleX)));
			communication.setForeground(Color.RED);

			info.add(name);
			info.add(points);

			this.add(info);
			this.add(boutons);
			this.add(commandes);
		}
		
		public void boutonsPerso(JButton b, Color bg, Color fg) {
			b.setBackground(bg);
			b.setForeground(fg);
		}
	}
	
	

}
