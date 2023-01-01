package com.proj.poo.view;

import java.awt.BorderLayout;    
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.controller.DominosController;
import com.proj.poo.runner.Auxiliaire;

public class DominosView extends JPanel {

	private static final long serialVersionUID = -7758253179910801855L;

	private DominosController controller;
	
	private JPanel game, controle;
	private Dimension size;
	double scaleX, scaleY;	

	public DominosView(Dimension size, double scaleX, double scaleY, DominosController controller) {
		this.setPreferredSize(size);
		this.size = size;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.setLayout(new BorderLayout());
		this.controller = controller;
		controle = new Controle();

		game = new JPanel(new GridLayout(controller.getPlateauLength(), controller.getPlateauLength()));
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setBackground(Color.GRAY);
		game.setLayout(null);

		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);

		
	}

	public void placeTuile(int x, int y) {
		TuileView t= new TuileView(x, y);
		t.setBackground(Color.PINK);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());

	}
	public TuileView initTuile(int x, int y) {
		TuileView t = new TuileView(x, y);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());
		return t;
	}
	
	public Dimension getSizeView() {
		return size;
	}
	
	public Controle getControle() { return (Controle) controle; }
	
	public class TuileView extends JPanel {

		private static final long serialVersionUID = 8825429997330574373L;

		private int x, y;
		int tailleTuile = (size.height / controller.getPlateauLength());

		public TuileView (int x, int y) {
			this.x = x;
			this.y = y;
			this.setBounds(x * tailleTuile, y * tailleTuile, tailleTuile, tailleTuile);
			this.setLayout(new GridLayout(5,5,0,0));
			this.setBackground(Color.CYAN);
			creation();
		}
		public int getTx() {
			return x;
		}
		public int getTy() {
			return y;
		}

		private void creation() {

			JLabel c1 = new JLabel(" ");
			JLabel c2 = new JLabel(" ");
			JLabel c3 = new JLabel(" ");
			JLabel c4 = new JLabel(" ");

			this.add(c1);
			for (int i = 0; i < 3;i++) {
				JLabel label_h = new JLabel(controller.getHautValue(i));
				label_h.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_h);
			}
			this.add(c2);

			for (int i = 0; i < 3;i++) {
				JLabel label_g = new JLabel(controller.getLeftValue(i));
				label_g.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_g);

				JLabel space1 = new JLabel(" ");
				this.add(space1);
				JLabel space2 = new JLabel(" ");
				this.add(space2);
				JLabel space3 = new JLabel(" ");
				this.add(space3);

				JLabel label_d = new JLabel(controller.getRightValue(i));
				label_d.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_d);
			}

			this.add(c3);
			for (int i = 0; i < 3;i++) {
				JLabel label_b = new JLabel(controller.getBottomValue(i));
				label_b.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_b);
			}
			this.add(c4);
		}

		public void tuileRotation() {
			this.removeAll();
			controller.rotationTuile();
			creation();
			game.paintComponents(game.getGraphics());
		}
		public void setTy(int y) {
			this.y = y;
		}
		
		public void setTx(int x) {
			this.x = x;
		}

	}

	public class Controle extends JPanel{

		private static final long serialVersionUID = -4197348622134353408L;

		JButton piocheBtn, poserBtn, rotationBtn, defausserBtn, abandonBtn;
		JButton hautBtn, basBtn, gaucheBtn, droiteBtn;
		JLabel name, points, communication;
		TuileView tuileV;
		JPanel info, boutons, commandes;


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
				
				game.add(tuileV);
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
			poserBtn.addActionListener((ActionEvent e) -> {
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
			});

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
			defausserBtn.addActionListener((ActionEvent e) ->{
				game.remove(tuileV);
				game.repaint();
				game.paintComponents(game.getGraphics());
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				tour();
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
			});

			basBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_bas.png"));
			boutonsPerso(basBtn,new Color(0,128,255),null);
			basBtn.addActionListener((ActionEvent e)->{
				controller.bas();
			});

			gaucheBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_gauche.png"));
			boutonsPerso(gaucheBtn,new Color(0, 128, 255), null);
			gaucheBtn.addActionListener((ActionEvent e)->{
				controller.gauche();				
			});

			droiteBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_droite.png"));
			boutonsPerso(droiteBtn, new Color(0, 128, 255),null);
			droiteBtn.addActionListener((ActionEvent e)->{
				controller.droite();				
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
		
		public TuileView getTuileV() {
			return tuileV;
		}
		
		public void tour() {
			boolean b = controller.tour();
			
			if (b) {
				piocheBtn.setEnabled(true);
				if (communication!=null) {
					info.remove(communication);
				}
			}
		}
		
		public void winner(String pseudo, int pts) {
			info.remove(name);
			info.remove(points);
			abandonBtn.setEnabled(false);
			piocheBtn.setEnabled(false);
			if (pseudo != null && pts != -1) {
				communication.setText("Bravo à " + pseudo + " qui gagné la partie avec " + pts + " points.");
				communication.setVisible(true);
				info.add(communication);
				communication.paintComponents(communication.getGraphics());
			}
			else{
				communication.setText("Pas de vainqueur pour cette partie, match nul !");
				communication.setVisible(true);
				info.add(communication);
			}
			JButton exitBtn = new JButton("Quitter le jeu");
			exitBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			exitBtn.setBackground(Color.RED);
			exitBtn.addActionListener(e -> {
				// action Ã  effectuer pour fermer le programme
				System.exit(0);
			});
			info.add(exitBtn);
			this.paintComponents(this.getGraphics());
		}
		
		public void paint() {
			this.repaint();
			this.paintComponents(getGraphics());
		}

		public void boutonsPerso(JButton b, Color bg, Color fg) {
			b.setBackground(bg);
			b.setForeground(fg);
		}
		
		public void setPseudoLabelText(String s) {
			name.setText("Pseudo du joueur : " + s);
			name.paintComponents(name.getGraphics());
		}
		
		public void setPointsLabelText(String s) {
			points.setText("Nombre de points du joueur : " + s);
			points.paintComponents(points.getGraphics());
		}
	}
}
