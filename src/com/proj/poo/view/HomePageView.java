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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.proj.poo.controller.HomePageController;
import com.proj.poo.runner.Auxiliaire;

public class HomePageView extends JPanel {

	private static final long serialVersionUID = 5106898561647691052L;	

	// on crée un container principal
	private JPanel container, top_container, game_container, bottom_container, container_1, container_2, players;

	// représente l'image du titre du jeu
	private JLabel title;

	private JButton dominosBtn, carcassonneBtn, exitBtn, ajoutBtn;
	
	private HomePageController controller;
	
	private ArrayList<String> color;

	// création de plusieurs panels afin de pouvoir utiliser les differents layout et bien placer les boutons
	public HomePageView(Dimension size, double scaleX, double scaleY, double scaleXY, JFrame frame, HomePageController hpc) {
		color=new ArrayList<String>();
		color.add("Bleu");
		color.add("Jaune");
		color.add("Vert");
		color.add("Rouge");
		this.setPreferredSize(size);

		BorderLayout container_layout = new BorderLayout();
		container = new JPanel(container_layout);// panel principal qui contient tout les autres panels
		top_container = new JPanel(); // panel du haut
		GridLayout game_layout = new GridLayout(3, 1);
		game_container = new JPanel(game_layout);// contient bouton des 2 jeux et les ajouts de joueurs
		GridLayout bottom_layout = new GridLayout(1, 2);
		bottom_container = new JPanel(bottom_layout); // le bas
		container_1 = new JPanel(); // contient uniq. le bouton du domino (pour pouvoir les placer comme souhaité)
		container_2 = new JPanel(); // contient uniq. le bouton de carcassonne

		container_layout.setVgap((int) (10 * scaleY));
		bottom_layout.setHgap((int) (20 * scaleX));		

		title = new JLabel("Jeux de Dominos Carr�s et de Carcassonne");
		title.setFont(new Font("Arial", Font.BOLD, (int) (60 * scaleX)));

		// contiendra le texte entré par l'utilisateur lorsque celui-ci ajoutera un joueur (qu'il soit IA ou Réel) et qu'il mettra son pseudo
		players = new JPanel();
		
		controller = hpc;

		dominosBtn = new JButton("DOMINOS CARR�S");
		dominosBtn.setPreferredSize(new Dimension((int) (700 * scaleX), (int) (156 * scaleY)));
		dominosBtn.setBackground(Color.DARK_GRAY);
		dominosBtn.setForeground(Color.ORANGE);
		dominosBtn.setFont(new Font("Arial", Font.BOLD, (int) (50 * scaleX)));
		dominosBtn.setEnabled(false);
		dominosBtn.addActionListener(e -> {
			container.setVisible(false);
			DominosView dcv = controller.runDominosParty(size, scaleX, scaleY);
			frame.add(dcv);
		});

		carcassonneBtn = new JButton(Auxiliaire.resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "carcassonne.png"), (int) (700 * scaleX), (int) (156 * scaleY)));
		carcassonneBtn.setPreferredSize(new Dimension((int) (700 * scaleX), (int) (156 * scaleY)));
		carcassonneBtn.setBackground(Color.DARK_GRAY);
		carcassonneBtn.setEnabled(false);
		carcassonneBtn.addActionListener(e -> {
			container.setVisible(false);
			CarcassonneView ccv = controller.runCarcassonneParty(size, scaleX, scaleY);
			frame.add(ccv);
			
		});

		exitBtn = new JButton("Quitter le jeu");
		exitBtn.setFont(new Font("Arial", Font.BOLD, (int) (50 * scaleX)));
		exitBtn.setBackground(Color.RED);
		exitBtn.addActionListener(e -> {
			// action à effectuer pour fermer le programme
			frame.dispose();
		});

		ajoutBtn = new JButton("Ajouter un joueur (min. 2)");
		ajoutBtn.setFont(new Font("Arial", Font.BOLD, (int) (50 * scaleX)));
		ajoutBtn.setBackground(Color.YELLOW);
		ajoutBtn.addActionListener((ActionEvent e) ->{			
			ajoutBtn.setEnabled(false);

			AjoutPlayer joueur = new AjoutPlayer(); // nouvel ajoutPlayer
			players.add(joueur.getCtn());	

			joueur.getCtn().revalidate();
			joueur.getCtn().repaint();
		});

		// ajout de tous les container à ceux correspondant
		top_container.add(title);
		container_1.add(dominosBtn);
		container_2.add(carcassonneBtn);
		game_container.add(container_1);
		game_container.add(container_2);
		game_container.add(players);
		bottom_container.add(ajoutBtn);
		bottom_container.add(exitBtn);

		// ajout de tous les container au container principal
		container.add(top_container, BorderLayout.NORTH);
		container.add(game_container, BorderLayout.CENTER);
		container.add(bottom_container, BorderLayout.SOUTH);

		// requis pour que le fond d'écran soit visible
		container.setOpaque(false);
		top_container.setOpaque(false);
		container_1.setOpaque(false);
		container_2.setOpaque(false);
		game_container.setOpaque(false);
		players.setOpaque(false);
		bottom_container.setOpaque(false);

		container.setVisible(true);
	}

	public JPanel getMainContainer() { return container; }

	private class AjoutPlayer extends JPanel{

		private static final long serialVersionUID = 1902883529203225339L;
		JTextField pseudo;
		JCheckBox isBot;
		JButton valider;
		static int NB_PLAYERS_D = 0;
		static int NB_PLAYERS_C = 0;
		JPanel container;
		JComboBox<String> choix_partie, choix_color;

		public AjoutPlayer() {


			/* nouveau panel de 4 lignes contenant la requête de pseudo, sa réponse,
			 * le champ pour connaître le type de joueur (IA / Réel), et le dernier pour valider
			 */
			container = new JPanel();
			container.setBackground(Color.CYAN);
			container.setLayout(new GridLayout(6,1));

			JLabel nom = new JLabel("Pseudo du joueur : " );
			container.add(nom);

			pseudo = new JTextField();
			container.add(pseudo);
			
			if(NB_PLAYERS_C <4) {
				String[] t = {"Dominos Carr�s", "Carcassonne"};
				choix_partie= new JComboBox<String>(t);
				container.add(choix_partie);
				}
			else {
				String[] t = {"Dominos Carr�s"};
				choix_partie= new JComboBox<String>(t);
				container.add(choix_partie);
			}
			
			
			if (color.size()>0) {
				String[] array = color.toArray(new String[color.size()]);
				choix_color= new JComboBox<String>(array);
				choix_color.setEnabled(false);
				container.add(choix_color);
			}
			
			isBot = new JCheckBox("IA");
			if ((NB_PLAYERS_D != 0 && choix_partie.getSelectedIndex()==0) || (NB_PLAYERS_C != 0 && choix_partie.getSelectedIndex()==1)) { // on peut creer un bot seulement apres avoir cr�� au moins un vrai joueur
				isBot.setEnabled(true);
			}
        	else {
        		isBot.setEnabled(false);
        	}
			container.add(isBot);
			
			choix_partie.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	            	if ((NB_PLAYERS_D != 0 && choix_partie.getSelectedIndex()==0) || (NB_PLAYERS_C != 0 && choix_partie.getSelectedIndex()==1)) { // on peut creer un bot seulement apres avoir cr�� au moins un vrai joueur
	    				isBot.setEnabled(true);
	    			}
	            	else {
	            		isBot.setSelected(false);
	            		isBot.setEnabled(false);
	            	}
	            	
	            	if (choix_partie.getSelectedIndex()==1) {choix_color.setEnabled(true);}
	            	else if (NB_PLAYERS_C <4){choix_color.setEnabled(false);}
	            	
	            }
	            });
			
			valider = new JButton("VALIDER");
			container.add(valider);
			
			valider.addActionListener((ActionEvent e) ->{ // dès qu'on clique sur le bouton valider
				if (!pseudo.getText().isEmpty() && !pseudo.getText().isBlank()) {// si le pseudo est non vide
					valider.setEnabled(false);
					if (!isBot.isSelected()) { // si c'est pas un bot
						// IL FAUT AJOUTER UN PARAMETRE POUR SAVOIR SI ON VEUT AJOUTER UN JOUEUR DE DOMINOS OU DE CARCASSONNE
						if (choix_partie.getSelectedIndex()==0) {
							NB_PLAYERS_D++;
							controller.addDominosPlayer(pseudo.getText(), NB_PLAYERS_D, false);
							}
						else {
							NB_PLAYERS_C++;
							controller.addCarcassonnePlayer(pseudo.getText(), NB_PLAYERS_C, false, (String) choix_color.getSelectedItem());
							color.remove(choix_color.getSelectedItem());
						}
					}
					else { 
						if (choix_partie.getSelectedIndex()==0 && NB_PLAYERS_D != 0) {
							NB_PLAYERS_D++;
							controller.addDominosPlayer(pseudo.getText(), NB_PLAYERS_D, true);
							}
						else if (choix_partie.getSelectedIndex() == 1 && NB_PLAYERS_C != 0) {
							NB_PLAYERS_C++;
							controller.addCarcassonnePlayer(pseudo.getText(), NB_PLAYERS_C, true, (String) choix_color.getSelectedItem());
							color.remove(choix_color.getSelectedItem());
							
						}
					}
					if (NB_PLAYERS_D >= 2) { // si il y a au moins 2 joueurs on active les boutons de dominos et carcassonne
						dominosBtn.setEnabled(true);
					}
					if (NB_PLAYERS_C >= 2) { 
						carcassonneBtn.setEnabled(true);
					}
					if(NB_PLAYERS_C + NB_PLAYERS_D < 14) {
						ajoutBtn.setEnabled(true);
					}
				}
			});
		}
		
		public JPanel getCtn() {
			return this.container;
		}
	}
}