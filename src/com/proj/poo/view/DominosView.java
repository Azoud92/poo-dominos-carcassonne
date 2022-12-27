package com.proj.poo.view;

import java.awt.BorderLayout;    
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.model.dominos.TuileDominos;

public class DominosView extends JPanel {

	private static final long serialVersionUID = -7758253179910801855L;
	
	private JPanel game, controle;
	private Dominos partie;
	private Dimension size;
	private Player jActuel;
	private Tuile tActuelle;
	private JFrame jf;
	double scaleX, scaleY;

	
	
	public DominosView(Dimension size, double scaleX, double scaleY, Dominos partie, JFrame frame) {
		this.setPreferredSize(size);
		this.size = size;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.setLayout(new BorderLayout());
		this.partie = partie;
		jActuel = partie.getPlayers().get(0);
		jf = frame;
		controle= new Controle();
		
		
		game= new JPanel(new GridLayout(partie.getPlateau().length, partie.getPlateau().length));
		game.setPreferredSize(new Dimension(size.height,size.height));
		game.setBackground(Color.GRAY);
		game.setLayout(null);
		
		this.add(game,BorderLayout.WEST);
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);
		partie.setState(State.READY);
		
		tActuelle= partie.piocher();
		partie.addTuile(tActuelle,partie.getPlateau().length/2, partie.getPlateau().length/2);
		placeTuile(tActuelle,partie.getPlateau().length/2,partie.getPlateau().length/2);
		partie.setState(State.PLAYING);

		
	}
	
	
	public void placeTuile(TuileDominos tuile, int x, int y) {
		TuileView t= new TuileView(tuile, x, y);
		t.setBackground(Color.PINK);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());

	}
	public TuileView initTuile(Tuile tuile, int x, int y) {
		TuileView t= new TuileView(tuile, x, y);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());
		return t;
		}

	

	
	
	public class TuileView extends JPanel{
		

		private static final long serialVersionUID = 8825429997330574373L;
		
		TuileDominos tuile;
		int x, y;
		int tailleTuile = (size.height / partie.getPlateau().length);
		
		public TuileView (TuileDominos tuile, int x, int y) {
			this.tuile= tuile;
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
				label_h.setFont(new Font("Arial", Font.BOLD, (size.height / partie.getPlateau().length)/7));
				this.add(label_h);
			}
			this.add(c2);
			
			for (int i =0; i<3;i++) {
				int g= gauche[i];
				int d= droite[i];
				
				JLabel label_g = new JLabel("  "+String.valueOf(g));
				label_g.setFont(new Font("Arial", Font.BOLD, (size.height / partie.getPlateau().length)/7));
				this.add(label_g);
				
				JLabel space1 = new JLabel(" ");
				this.add(space1);
				JLabel space2 = new JLabel(" ");
				this.add(space2);
				JLabel space3 = new JLabel(" ");
				this.add(space3);
				
				JLabel label_d = new JLabel(""+String.valueOf(d));
				label_d.setFont(new Font("Arial", Font.BOLD, (size.height / partie.getPlateau().length)/7));
				this.add(label_d);
			}
			
			this.add(c3);
			for (int i =0; i<3;i++) {
				int b= bas[i];
				JLabel label_b = new JLabel(" "+String.valueOf(b));
				label_b.setFont(new Font("Arial", Font.BOLD, (size.height / partie.getPlateau().length)/7));
				this.add(label_b);
			}
			this.add(c4);
			
		}

		public void tuileRotation() {
			this.removeAll();
			tuile.rotation();
			creation();
			game.paintComponents(game.getGraphics());
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
			this.setPreferredSize(new Dimension(size.width-size.height, size.height));
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
				if (partie.getPlateau()[0][0]==null) {
					tActuelle=partie.piocher();
					tuileV = new TuileView(tActuelle,0,0);
					
				}		
				else {
					int place_x = 0;
					int place_y = 0;
					while(partie.getPlateau()[place_y][place_x]!=null) {
						if (place_x+1 == partie.getPlateau()[place_y].length) {
							place_x=0;
							place_y++;
						}
						if (place_y+1 == partie.getPlateau().length) {
							Player winner =partie.finPartie();
							info.remove(name);
							info.remove(points);
							abandonBtn.setEnabled(false);
							if (winner!=null) {
								communication.setText(winner.pseudo + " a gagn� la partie avec "+winner.getPoints()+" points.");
								info.add(communication);
								info.paintComponents(info.getGraphics());
								break;
							}
							else{
								communication.setText("Pas de vainqueur pour cette partie, match nul !");
								communication.setVisible(true);
								info.add(communication);
								info.setVisible(true);
								communication.paintComponents(communication.getGraphics());
								break;
								}
						}
						place_x++;
					}
					tActuelle=partie.piocher();
					tuileV = new TuileView(tActuelle,place_x,place_y);

				}
				game.add(tuileV);
				game.paintComponents(game.getGraphics());
				System.out.println(tActuelle.toString());
				piocheBtn.setEnabled(false);
				poserBtn.setEnabled(true);
				rotationBtn.setEnabled(true);
				defausserBtn.setEnabled(true);
				System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
				System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
			});
			
			poserBtn = new JButton("Poser");
			poserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(poserBtn,new Color(0,128,255),new Color(204,229,255));
			poserBtn.setEnabled(false);
			poserBtn.addActionListener((ActionEvent e) -> {
				int x = tuileV.getTx();
				int y = tuileV.getTy();
				System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
				System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
				System.out.println(tActuelle.toString());
				if (partie.isLegalPlacement(x,y, tActuelle)) {
					System.out.println("true");
					game.remove(tuileV);
					tuileV=null;
					placeTuile(tActuelle,x,y);
					partie.placer(x,y, tActuelle);
					tActuelle=null;
					communication.setText("Bien jou�, vous avez r�ussi � placer votre tuile");
					info.add(communication);
					this.repaint();
					this.paintComponents(getGraphics());
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
				partie.printPlateau();
				
			});
			
			rotationBtn = new JButton("Rotation");
			rotationBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(rotationBtn,new Color(0,128,255),new Color(204,229,255));
			rotationBtn.setEnabled(false);
			rotationBtn.addActionListener((ActionEvent e) -> {
				tuileV.tuileRotation();
				partie.printPlateau();
				System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
				System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
				
			});
			defausserBtn = new JButton("D�fausser");
			defausserBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(defausserBtn,new Color(0,128,255),new Color(204,229,255));
			defausserBtn.setEnabled(false);
			defausserBtn.addActionListener((ActionEvent e) ->{
				game.remove(tuileV);
				this.repaint();
				this.paintComponents(this.getGraphics());
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				tour();
			});
			
			abandonBtn = new JButton("Abandonner");
			abandonBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			boutonsPerso(abandonBtn,new Color(0,128,255),new Color(204,229,255));
			abandonBtn.addActionListener((ActionEvent e) ->{
				partie.abandon(jActuel);
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
				if (tuileV != null) {
					try {
						if (partie.getPlateau()[tuileV.x][tuileV.y-1]==null) {
							tuileV.setLocation(tuileV.getX(),tuileV.getY()-((size.height / partie.getPlateau().length)));
							tuileV.y--;
							System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
							System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());	
						}
						else{System.out.println(partie.getPlateau()[tuileV.x][tuileV.y-1].toString());}
					}
					catch (ArrayIndexOutOfBoundsException e1) {
						tuileV.setLocation(tuileV.getX(),(size.height / partie.getPlateau().length)*(partie.getPlateau().length-1));
						tuileV.y=tuileV.getY()/(size.height / partie.getPlateau().length);
						System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
						System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
					}
				}
			});
			
			basBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_bas.png"));
			boutonsPerso(basBtn,new Color(0,128,255),null);
			basBtn.addActionListener((ActionEvent e)->{
				if (tuileV != null) {
					try {
						if (partie.getPlateau()[tuileV.x][tuileV.y+1]==null) {
							tuileV.setLocation(tuileV.getX(),tuileV.getY()+((size.height / partie.getPlateau().length)));
							tuileV.y++;
							System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
							System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());	
						}
						else {System.out.println(partie.getPlateau()[tuileV.x][tuileV.y+1].toString());}
					}
					catch (ArrayIndexOutOfBoundsException e1) {
						tuileV.setLocation(tuileV.getX(),0);
						tuileV.y=0;
						System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
						System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
					}
				}
			});
			
			gaucheBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_gauche.png"));
			boutonsPerso(gaucheBtn,new Color(0,128,255),null);
			gaucheBtn.addActionListener((ActionEvent e)->{
				if (tuileV != null) {
					try {
						if (partie.getPlateau()[tuileV.x-1][tuileV.y]==null) {
							tuileV.setLocation(tuileV.getX()-((size.height / partie.getPlateau().length)),tuileV.getY());
							tuileV.x--;
							System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
							System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());	
						}
						else {System.out.println(partie.getPlateau()[tuileV.x-1][tuileV.y].toString());}
					}
					catch (ArrayIndexOutOfBoundsException e1) {
						tuileV.setLocation((size.height / partie.getPlateau().length)*(partie.getPlateau().length-1),tuileV.getY());
						tuileV.x=tuileV.getX()/(size.height / partie.getPlateau().length);
						System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
						System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
					}
				}
			});
			
			droiteBtn = new JButton(new ImageIcon(Auxiliaire.imgResourcesPath + "touche_droite.png"));
			boutonsPerso(droiteBtn,new Color(0,128,255),null);
			droiteBtn.addActionListener((ActionEvent e)->{
				if (tuileV != null) {
					try {
						if (partie.getPlateau()[tuileV.x+1][tuileV.y]==null) {
						tuileV.setLocation(tuileV.getX()+((size.height / partie.getPlateau().length)),tuileV.getY());
						tuileV.x++;
						System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
						System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
						}
						else {System.out.println(partie.getPlateau()[tuileV.x+1][tuileV.y].toString());}
					}
					catch (ArrayIndexOutOfBoundsException e1) {
						tuileV.setLocation(0,tuileV.getY());
						tuileV.x=0;
						System.out.println("tuile x: "+tuileV.getX()+"  tuile y : "+tuileV.getY());	
						System.out.println("tuile x: "+tuileV.getTx()+"  tuile y : "+tuileV.getTy());
					}
				}
			});
			
			
			JLabel j1 =new JLabel();
			JLabel j2 =new JLabel();
			
			commandes.add(j1);
			commandes.add(hautBtn);
			commandes.add(j2);
			commandes.add(gaucheBtn);
			commandes.add(basBtn);
			commandes.add(droiteBtn);
			
			
			name= new JLabel("Pseudo du joueur : " + jActuel.pseudo);
			name.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			name.setForeground(new Color(0,128,255));
			
			points= new JLabel("Nombre de points du joueur : " + jActuel.getPoints());
			points.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			points.setForeground(new Color(0,128,255));
			
			communication=new JLabel();
			communication.setFont(new Font("Arial", Font.BOLD, (int) (22 * scaleX)));
			communication.setForeground(Color.RED);
			
			info.add(name);
			info.add(points);
			
			this.add(info);
			this.add(boutons);
			this.add(commandes);
			
			
		}
		public void tour (){
			boolean b=partie.passerTour();
			if(!b) {
				Player winner=partie.finPartie();
				info.remove(name);
				info.remove(points);
				abandonBtn.setEnabled(false);
				if (winner!=null) {
					communication.setText("Bravo � "+ winner.pseudo + " qui a gagn� la partie avec "+winner.getPoints()+" points.");
					info.add(communication);
					info.paintComponents(info.getGraphics());
				}
				else{
					communication.setText("Pas de vainqueur pour cette partie, match nul !");
					communication.setVisible(true);
					info.add(communication);
					info.setVisible(true);
				}
				JButton exitBtn = new JButton("Quitter le jeu");
				exitBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
				exitBtn.setBackground(Color.RED);
				exitBtn.addActionListener(e -> {
					// action à effectuer pour fermer le programme
					jf.dispose();
			      });
				info.add(exitBtn);
				info.paintComponents(info.getGraphics());
			}
			else {
				jActuel=partie.joueurActuel;
				
				piocheBtn.setEnabled(true);
				if (communication!=null) {
					info.remove(communication);
				}
				if(!jActuel.isPlayer) {
					tActuelle= partie.piocher();
					int[] xy = partie.placeIA(tActuelle);
					if (xy!=null) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						placeTuile(tActuelle,xy[0],xy[1]);
						partie.placer(xy[0], xy[1], tActuelle);
						this.repaint();
						this.paintComponents(getGraphics());
						}
					tActuelle=null;
					tour();
					
				}
				name.setText("Pseudo du joueur : " + jActuel.pseudo);
				points.setText("Nombre de points du joueur : " + jActuel.getPoints());
				
				
			}
		}
		
		
		public void boutonsPerso(JButton b, Color bg, Color fg) {
			b.setBackground(bg);
			b.setForeground(fg);
		}
		
		
		
		
		
		
	}

}
