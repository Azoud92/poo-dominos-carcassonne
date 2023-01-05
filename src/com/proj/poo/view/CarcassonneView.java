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
import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.runner.Auxiliaire;




public class CarcassonneView extends JPanel{

	private static final long serialVersionUID = 4489854025470300053L;
	
	private CarcassonneController controller;
	private JPanel game, controle;
	private Dimension size;
	double scaleX, scaleY;	
	int tailleTuile;
	ArrayList<Circle> liste_partisans;
	Circle partisanActuel;

	public CarcassonneView(Dimension size, double scaleX, double scaleY, CarcassonneController controller) {
		this.setPreferredSize(size);
		this.size = size;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.setLayout(new BorderLayout());
		this.controller = controller;
		tailleTuile = (size.height / controller.getPlateauLength());
		liste_partisans = new ArrayList<Circle>();
		controle = new Controle();

		game = new JPanel(new GridLayout(controller.getPlateauLength(), controller.getPlateauLength()));
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setBackground(Color.GRAY);
		game.setLayout(null);

		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);

		
	}
	
	public int getTailleTuile() {
		return tailleTuile;
	}
	
	public TuileView placeTuile(int x, int y) {
		TuileView t = new TuileView(x,y);
		game.add(t);
		t.repaint();
		t.paintComponents(t.getGraphics());
		return t;

	}
	
	public Circle getPartisanAct(){
		return partisanActuel;
	}
	public void setPartisanAct(Circle c) {
		partisanActuel = c;
	}
	
	public Dimension getSizeView() {
		return size;
	}
	
	public Controle getControle() { return (Controle) controle; }
	
	public void creerPartisan() {
		partisanActuel = new Circle(((Controle) controle).tuileV.getX() + tailleTuile/2 - (tailleTuile/5/2), ((Controle) controle).tuileV.getY() + tailleTuile/2 - (tailleTuile/5/2), controller.partisanColor());
		partisanActuel.draw(getGraphics());
		liste_partisans.add(partisanActuel);
	}
	
	public void affichePartisans() {
		for(Circle c : liste_partisans) {
			c.draw(getGraphics());
			
		}
	}
	
	
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
			this.paintComponents(this.getGraphics());
		}
		
		public int getTx() {
			return x;
		}
		public int getTy() {
			return y;
		}
		
		public void setTy(int y) {
			this.y = y;
		}
		
		public void setTx(int x) {
			this.x = x;
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
	
	public class Controle extends JPanel{

		private static final long serialVersionUID = -4197348622134353408L;

		JButton piocheBtn, poserBtn, rotationBtn, defausserBtn, abandonBtn;
		JButton hautBtn, basBtn, gaucheBtn, droiteBtn;
		JButton partisanBtn, poserPartisanBtn, noPartisanBtn;
		JLabel name, points, communication;
		JPanel info, boutons, commandes, partisanPnl;
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
				affichePartisans();
				piocheBtn.setEnabled(false);
				poserBtn.setEnabled(true);
				rotationBtn.setEnabled(true);
				defausserBtn.setEnabled(true);
			});

			poserBtn = new JButton("Poser");
			poserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(poserBtn,new Color(0,128,255),new Color(204, 229, 255));
			poserBtn.setEnabled(false);
			poserBtn.addActionListener((ActionEvent e) -> {
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
			});

			rotationBtn = new JButton("Rotation");
			rotationBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(rotationBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			rotationBtn.setEnabled(false);
			rotationBtn.addActionListener((ActionEvent e) -> {
				tuileV.tuileRotation();
				affichePartisans();

			});
			defausserBtn = new JButton("D�fausser");
			defausserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(defausserBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			defausserBtn.setEnabled(false);
			defausserBtn.addActionListener((ActionEvent e) ->{
				game.remove(tuileV);
				game.repaint();
				game.paintComponents(game.getGraphics());
				
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				tour();
				
				affichePartisans();			
				
			});

			abandonBtn = new JButton("Abandonner");
			abandonBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(abandonBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			abandonBtn.addActionListener((ActionEvent e) ->{
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
			});


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
			hautBtn.addActionListener((ActionEvent e)->{
				controller.haut();
				game.paintComponents(game.getGraphics());
				affichePartisans();
			});

			basBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_bas.png"));
			boutonsPerso(basBtn,new Color(0,128,255),null);
			basBtn.addActionListener((ActionEvent e)->{
				controller.bas();
				game.paintComponents(game.getGraphics());
				affichePartisans();
			});

			gaucheBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_gauche.png"));
			boutonsPerso(gaucheBtn,new Color(0, 128, 255), null);
			gaucheBtn.addActionListener((ActionEvent e)->{
				controller.gauche();
				game.paintComponents(game.getGraphics());
				affichePartisans();
			});

			droiteBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_droite.png"));
			boutonsPerso(droiteBtn, new Color(0, 128, 255),null);
			droiteBtn.addActionListener((ActionEvent e)->{
				controller.droite();
				game.paintComponents(game.getGraphics());
				affichePartisans();
			});


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
			
			
			partisanPnl = new JPanel();
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
			
			noPartisanBtn = new JButton("Ne pas poser de partisan");
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
			
			

			communication = new JLabel();
			communication.setFont(new Font("Arial", Font.BOLD, (int) (28 * scaleX)));
			communication.setForeground(Color.RED);

			info.add(name);
			info.add(partisanPnl);
			
			this.add(info);
			this.add(boutons);
			this.add(commandes);
		}
		
		
		
		public void tour() {
			boolean b = controller.tour();
			
			if (b) {
				piocheBtn.setEnabled(true);
				if (communication!=null) {
					info.remove(communication);
					paint();
				}
			}
			
		}
		
		public TuileView getTuileV() {
			return tuileV;
		}
		
		
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
		
		public void paint() {
			this.repaint();
			this.paintComponents(getGraphics());
		}
		
		
		public void setPseudoLabelText(String s) {
			name.setText("Pseudo du joueur : " + s);
			name.paintComponents(name.getGraphics());
		}
		
		
		
		
		public void boutonsPerso(JButton b, Color bg, Color fg) {
			b.setBackground(bg);
			b.setForeground(fg);
		}
	}
	
	

}
