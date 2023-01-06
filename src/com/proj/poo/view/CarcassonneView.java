package com.proj.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.controller.Controller;
import com.proj.poo.runner.Auxiliaire;
import com.proj.poo.controller.CarcassonneController;

public class CarcassonneView extends GameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1818114304386621614L;
	
	private int tailleTuile;
	private ArrayList<Circle> liste_partisans;
	private Circle partisanActuel;

	public CarcassonneView(Dimension size, double scaleX, double scaleY, Controller controller) {
		super(size, scaleX, scaleY, controller);

		tailleTuile = (size.height / controller.getPlateauLength());
		liste_partisans = new ArrayList<Circle>();
		controle = new Controle();
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);	
		// TODO Auto-generated constructor stub
	}
	
	public int getTailleTuile() {
		return tailleTuile;
	}

	@Override
	public TuileView placeTuile(int x, int y, int r) {
		TuileView t = new TuileView(x,y);
		if (r != 0) {
			for (int i = 0; i < r; i++) {
				t.tuileRotation();
			}
		}
		game.add(t);
		t.repaint();
		t.paintComponents(t.getGraphics());
		game.repaint();
		game.paintComponents(game.getGraphics());
		
		return t;
	}
	
	public Circle getPartisanAct(){
		return partisanActuel;
	}
	public void setPartisanAct(Circle c) {
		partisanActuel = c;
	}
	
	public void creerPartisan() {
		partisanActuel = new Circle(((Controle) controle).tuileV.getX() + tailleTuile/2 - (tailleTuile/5/2), ((Controle) controle).tuileV.getY() + tailleTuile/2 - (tailleTuile/5/2), ((CarcassonneController) controller).partisanColor());
		partisanActuel.draw(getGraphics());
		liste_partisans.add(partisanActuel);
	}
	
	public void affichePartisans() {
		for(Circle c : liste_partisans) {
			c.draw(getGraphics());			
		}
	}
	
	public class TuileView extends GameView.TuileView {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4915955738710999129L;
		private double rotation;
		
		JLabel img;
		
		public TuileView(int x, int y) {
			super(x, y);
			rotation=0;
			
			img = new JLabel();
			img.setIcon(Auxiliaire.resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + controller.getActualTuile().getClass().getSimpleName() + ".png"),tailleTuile,tailleTuile));
						
			this.setLayout(new BorderLayout());
			this.setBounds(x * tailleTuile, y * tailleTuile, tailleTuile, tailleTuile);
			this.add(img);
		
			// TODO Auto-generated constructor stub
		}
		
		public JLabel getImgLabel() {
			return img;
		}
		
		@Override
		public void paintComponent(Graphics g) {
			((Graphics2D) g).rotate(rotation,(double) getWidth()/2, (double) getHeight()/2);
            super.paintComponent(g);
		}

		@Override
		public void tuileRotation() {
			rotation+=Math.toRadians(90);
			this.repaint();
			this.paintComponents(this.getGraphics());
		}
		
		public void setRotation(double d) {
			rotation=d;
		}
		
	}
	
	public class Circle {

	    private int x, y;
	    private Color c;

	    public Circle(int x, int y, Color c) {
	        this.x = x;
	        this.y = y;
	        this.c = c;
	    }
	    
	    public void deplace(int x, int y) {
	    	this.x = x;
	    	this.y = y;
	    	draw(getGraphics());
	    }

	    public void draw(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, tailleTuile/5, tailleTuile/5);
	        
	        g2d.setColor(c);
	        g2d.fill(circle);
	    }
	    
	    public int getX() {
	    	return x;
	    }
	    
	    public int getY() {
	    	return y;
	    }

	}
	
	public class Controle extends GameView.Controle {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6815678984610206865L;

		JButton partisanBtn, poserPartisanBtn, noPartisanBtn;
		JPanel partisanPnl;
		
		public Controle() {
			super();
			partisanPnl = new JPanel();
			partisanPnl.setLayout(new GridLayout(1, 3));
			partisanPnl.setBackground(getBackground());
			
			partisanBtn = new JButton("Partisan");
			partisanBtn.setFont(new Font("Arial", Font.BOLD, (int) (25 * scaleX)));
			boutonsPerso(partisanBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			partisanBtn.setEnabled(false);
			partisanBtn.addActionListener((ActionEvent e) ->{
				partisanBtn.setEnabled(false);
				noPartisanBtn.setEnabled(false);
				poserPartisanBtn.setEnabled(true);
				creerPartisan();
				controller.setActualTuile(null);
			});
			
			noPartisanBtn = new JButton("Ne rien poser");
			noPartisanBtn.setFont(new Font("Arial", Font.BOLD, (int) (25 * scaleX)));
			boutonsPerso(noPartisanBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			noPartisanBtn.setEnabled(false);
			noPartisanBtn.addActionListener((ActionEvent e) ->{
				partisanBtn.setEnabled(false);
				noPartisanBtn.setEnabled(false);
				tuileV = null;
				controller.setActualTuile(null);
				tour();
			});
			
			poserPartisanBtn = new JButton("Poser partisan");
			poserPartisanBtn.setFont(new Font("Arial", Font.BOLD, (int) (25 * scaleX)));
			boutonsPerso(poserPartisanBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			poserPartisanBtn.setEnabled(false);
			poserPartisanBtn.addActionListener((ActionEvent e) ->{
				poserPartisanBtn.setEnabled(false);
				tuileV=null;
				partisanActuel=null;
				tour();
			});
			
			partisanPnl.add(partisanBtn);
			partisanPnl.add(poserPartisanBtn);
			partisanPnl.add(noPartisanBtn);
			
			info.add(partisanPnl);
		}
		
		@Override
		protected void piocheBtnAction() {
			tuileV = controller.piocheTuile();
			game.paintComponents(game.getGraphics());
			affichePartisans();
			piocheBtn.setEnabled(false);
			poserBtn.setEnabled(true);
			rotationBtn.setEnabled(true);
			defausserBtn.setEnabled(true);
		}

		@Override
		protected void poserBtnAction() {
			int x = tuileV.getTx();
			int y = tuileV.getTy();

			
			if (controller.placeTuile(x, y) == true) {
				
				communication.setText("Bien jou�, vous avez r�ussi � placer votre tuile");
				info.add(communication);
				this.repaint();
				this.paintComponents(this.getGraphics());
				affichePartisans();
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				try {
					Thread.sleep(700);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				partisanBtn.setEnabled(true);
				noPartisanBtn.setEnabled(true);
			}
		}

		@Override
		protected void rotationBtnAction() {
			tuileV.tuileRotation();
			controller.rotationTuile();
			affichePartisans();
		}

		@Override
		protected void defausserBtnAction() {
			
			game.remove(tuileV);
			JPanel efface = new JPanel();
			efface.setBounds(tuileV.getX(), tuileV.getY(), tailleTuile, tailleTuile);
			efface.setBackground(game.getBackground());
			game.add(efface);
			efface.repaint();
			((TuileView)tuileV).repaint();
			
			poserBtn.setEnabled(false);
			rotationBtn.setEnabled(false);
			defausserBtn.setEnabled(false);
			tour();
			
			affichePartisans();	
		}

		@Override
		protected void abandonBtnAction() {
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
			affichePartisans();
		}
		
		@Override
		protected void hautBtnAction() {
			super.hautBtnAction();
			game.paintComponents(game.getGraphics());
			affichePartisans();
		};	
		
		@Override
		protected void basBtnAction() {
			super.basBtnAction();
			game.paintComponents(game.getGraphics());
			affichePartisans();
		};
		
		@Override
		protected void gaucheBtnAction() {
			super.gaucheBtnAction();
			game.paintComponents(game.getGraphics());
			affichePartisans();
		};
		
		@Override
		protected void droiteBtnAction() {
			super.droiteBtnAction();
			game.paintComponents(game.getGraphics());
			affichePartisans();
		};

		@Override
		public void finPartie() {
			info.remove(name);
			info.remove(partisanPnl);
			abandonBtn.setEnabled(false);
			piocheBtn.setEnabled(false);
			communication.setText("La partie est termin�e");
			communication.setVisible(true);
			info.add(communication);
			JButton exitBtn = new JButton("Quitter le jeu");
			exitBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			exitBtn.setBackground(Color.RED);
			exitBtn.addActionListener(e -> {
				// action à effectuer pour fermer le programme
				System.exit(0);
			});
			info.add(exitBtn);
			this.paintComponents(this.getGraphics());

		}
		
	
	}


}
