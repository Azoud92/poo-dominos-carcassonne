package com.proj.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.controller.Controller;
import com.proj.poo.runner.Auxiliaire;
import com.proj.poo.view.CarcassonneView.Circle;
import com.proj.poo.view.CarcassonneView.Controle;
import com.proj.poo.view.CarcassonneView.TuileView;

public abstract class GameView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4557073736316076902L;

	protected Controller controller;
	protected JPanel game, controle;
	protected Dimension size;
	protected double scaleX, scaleY;	
	
	public GameView(Dimension size, double scaleX, double scaleY, Controller controller) {
		this.setPreferredSize(size);
		this.size = size;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.setLayout(new BorderLayout());
		this.controller = controller;
		
		game = new JPanel(new GridLayout(controller.getPlateauLength(), controller.getPlateauLength()));
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setBackground(Color.GRAY);
		game.setLayout(null);

		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);	
		
		// INSTANCIER LE CONTROLE
	}
	
	public abstract TuileView placeTuile(int x, int y);
	
	public Dimension getSizeView() {
		return size;
	}
	
	public Controle getControle() { return (Controle) controle; }
	
	public abstract class TuileView extends JPanel {

		private static final long serialVersionUID = 8825429997330574373L;

		private int x, y;
		int tailleTuile = (size.height / controller.getPlateauLength());

		public TuileView (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getTx() {
			return x;
		}
		public int getTy() {
			return y;
		}		

		public abstract void tuileRotation(); 
		
		public void setTy(int y) {
			this.y = y;
		}
		
		public void setTx(int x) {
			this.x = x;
		}
	}
	
	public abstract class Controle extends JPanel{

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
			piocheBtn.addActionListener((ActionEvent e) -> {
				piocheBtnAction();
			});

			poserBtn = new JButton("Poser");
			poserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(poserBtn,new Color(0,128,255),new Color(204, 229, 255));
			poserBtn.setEnabled(false);
			poserBtn.addActionListener((ActionEvent e) -> {
				poserBtnAction();
			});

			rotationBtn = new JButton("Rotation");
			rotationBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(rotationBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			rotationBtn.setEnabled(false);
			rotationBtn.addActionListener((ActionEvent e) -> {
				rotationBtnAction();
			});
			
			defausserBtn = new JButton("D�fausser");
			defausserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(defausserBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			defausserBtn.setEnabled(false);
			defausserBtn.addActionListener((ActionEvent e) -> {
				defausserBtnAction();
			});

			abandonBtn = new JButton("Abandonner");
			abandonBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(abandonBtn,new Color(0, 128, 255),new Color(204, 229, 255));
			abandonBtn.addActionListener((ActionEvent e) -> {
				abandonBtnAction();
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
			hautBtn.addActionListener((ActionEvent e) -> {
				hautBtnAction();
			});

			basBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_bas.png"));
			boutonsPerso(basBtn,new Color(0,128,255),null);
			basBtn.addActionListener((ActionEvent e) -> {
				basBtnAction();
			});

			gaucheBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_gauche.png"));
			boutonsPerso(gaucheBtn,new Color(0, 128, 255), null);
			gaucheBtn.addActionListener((ActionEvent e) -> {
				gaucheBtnAction();
			});

			droiteBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_droite.png"));
			boutonsPerso(droiteBtn, new Color(0, 128, 255),null);
			droiteBtn.addActionListener((ActionEvent e) -> {
				droiteBtnAction();
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
		
		protected abstract ActionListener piocheBtnAction();
		protected abstract ActionListener poserBtnAction();
		protected abstract ActionListener rotationBtnAction();
		protected abstract ActionListener defausserBtnAction();
		protected abstract ActionListener abandonBtnAction();
		
		protected ActionListener hautBtnAction() {
			controller.haut();
		};		
		protected ActionListener basBtnAction() {
			controller.bas();
		};
		protected ActionListener gaucheBtnAction() {
			controller.gauche();
		};
		protected ActionListener droiteBtnAction() {
			controller.droite;
		};
		
		public TuileView getTuileV() {
			return tuileV;
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
		
		public void paint() {
			this.repaint();
			this.paintComponents(getGraphics());
		}

		public abstract void finPartie();
		
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
