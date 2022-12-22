package com.proj.poo.view;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.proj.poo.runner.Auxiliaire;
import com.proj.poo.dominos.Player;
import com.proj.poo.dominos.Dominos;

public class HomePageView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106898561647691052L;
	
	// on crÃ©e un container principal
	private JPanel container, top_container, game_container,bottom_container,container_test1,container_test2;
	
	// reprÃ©sente l'image de fond
	private JLabel background;
	
	// reprÃ©sente l'image du titre du jeu
	private JLabel title, players;
	
	private JButton dominosBtn, carcassonneBtn, exitBtn, ajoutBtn;
	
	public HomePageView(Dimension size) {//j'ai créé plein de panels pour pouvoir utiliser les differents layout et bien placer les boutons
		BorderLayout container_layout = new BorderLayout();
		container = new JPanel(container_layout);//panel qui contient tout les autres panels
		top_container=new JPanel();//panel du haut
		GridLayout game_layout = new GridLayout(3,1);
		game_container=new JPanel(game_layout);//panel avec le bouton des 2 jeux et les ajouts de joueurs
		GridLayout bottom_layout = new GridLayout(1,2);
		bottom_container=new JPanel(bottom_layout);//panel du bas
		container_test1= new JPanel();//panel ou il y a juste le bouton du domino (c'est pour pouvoir les placer comme je voulais)
		container_test2= new JPanel();//panel ou il y a juste le bouton de carcassonne
		
		container_layout.setVgap(10);
		bottom_layout.setHgap(20);

		
		
		
		
		//background = new JLabel(new ImageIcon(Auxiliaire.imgResourcesPath + "background.jpg"));
		//background.setBounds(0, 0, container.getWidth(), container.getHeight());
				
		title = new JLabel("Jeux de Dominos Carré et de Carcassonne");
		title.setFont(new Font("Arial", Font.BOLD, 80));
		
		players=new JLabel(); //players ca sera la place ou l'utilisateur ajoutera un joueur (bot ou vrai joueur) et mettra son pseudo
		
		dominosBtn = new JButton("DOMINOS CARRÉS");
		dominosBtn.setPreferredSize(new Dimension(800, 256));
		dominosBtn.setBackground(Color.DARK_GRAY);
		dominosBtn.setForeground(Color.ORANGE);
		dominosBtn.setFont(new Font("Arial", Font.BOLD, 80));
		dominosBtn.setEnabled(false);
		
		carcassonneBtn = new JButton(resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "carcassonne.png"), 800, 256));
		carcassonneBtn.setPreferredSize(new Dimension(800, 256));
		carcassonneBtn.setBackground(Color.DARK_GRAY);
		carcassonneBtn.setEnabled(false);
		
		exitBtn = new JButton("Quitter le jeu");
		exitBtn.setFont(new Font("Arial", Font.BOLD, 50));
		exitBtn.setBackground(Color.RED);
		exitBtn.addActionListener(e -> {
	         //j'ai pas trouvé comment fermer le jframe psk il est dans la classe gameview
	      });
		
		ajoutBtn= new JButton("Ajouter un joueur (min.2)");
		ajoutBtn.setFont(new Font("Arial", Font.BOLD, 50));
		ajoutBtn.setBackground(Color.YELLOW);
		ajoutBtn.addActionListener((ActionEvent e) ->{
			AjoutPlayer joueur= new AjoutPlayer();//nouveau ajoutPlayer
			players.add(joueur.getContainer());
			container.repaint();
		});
		
				
		//container.add(background);
		//ajout de tout les container a ceux qui correspondent
		top_container.add(title);
		container_test1.add(dominosBtn);
		container_test2.add(carcassonneBtn);
		game_container.add(container_test1);
		game_container.add(container_test2);
		game_container.add(players);
		bottom_container.add(ajoutBtn);
		bottom_container.add(exitBtn);

		//ajout de tout les container au container qui contient tout
		container.add(top_container,BorderLayout.NORTH);
		container.add(game_container,BorderLayout.CENTER);
		container.add(bottom_container,BorderLayout.SOUTH);
		
		container.setVisible(true);
	}
	
	public JPanel getMainContainer() { return container; }

	private ImageIcon resizeImage(ImageIcon i, int width, int height) {
		return new ImageIcon(i.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
		
		class AjoutPlayer extends JPanel{
			
			private static final long serialVersionUID = 1902883529203225339L;
			JTextField pseudo;
			JCheckBox isBot;
			JButton valider;
			int nbPlayers=0;
			JPanel container;
			
			public AjoutPlayer() {
				this.setBackground(Color.CYAN);
				
				this.container = new JPanel();
				this.container.setLayout(new GridLayout(4,1));
				//nouveau panel de 4 lignes pour mettre la demande de pseudo, la réponse, le champ pour savoir si c'est un bot et le dernier pour valider
				
				
				JLabel nom = new JLabel("Entrez le pseudo du joueur " + nbPlayers+1+":" );
				this.container.add(nom);
				
				pseudo = new JTextField();
				this.container.add(pseudo);
	
				if (nbPlayers!=0) {//on peut creer un bot seulement apres avoir créé au moins un vrai joueur
					isBot = new JCheckBox("IA");
					this.container.add(isBot);
				}
				
				valider = new JButton("VALIDER");
				this.container.add(valider);
				
				players.add(this.container);//on ajoute le container au panel (l'endroit que jai laissé vide en bas de la fenetre)
				players.repaint();//on repaint pour ajouter le container
				
				valider.addActionListener((ActionEvent e) ->{//des qu'on clique sur le bouton valider
					if (pseudo.getSelectedText()!=null) { //si le pseudo est non vide
						if (!isBot.isSelected() || isBot==null) {//si c'est pas un bot
							new Player(pseudo.getSelectedText(),null,true,nbPlayers);//creation du nouveau joueur
						}
						else {//si c'est un vrai joueur
							new Player(pseudo.getSelectedText(),null,false,nbPlayers);
							}
						valider.setEnabled(false);//on desactive valider
						if (nbPlayers>=2) {//si il y a au moins 2 joueurs on active les boutons de dominos et carcassonne
							carcassonneBtn.setEnabled(true);
							dominosBtn.setEnabled(true);
							}
						nbPlayers++;//on incremente le nombre de joueurs
						}
					});
				players.setLayout(new GridLayout(1,nbPlayers));//on ajoute une colonne au grid layout pour que ca soit positionné a coté
				players.add(this.container);//on réajoute le container au panel (l'endroit que jai laissé vide en bas de la fenetre)
				players.repaint();
				}
			public JPanel getContainer() {
				return this.container;
			}
			}
}



