package com.proj.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.proj.poo.runner.Auxiliaire;

public class HomePageView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106898561647691052L;
	
	// on crée un container principal
	private JPanel container, top_container, game_container,bottom_container,container_test1,container_test2;
	
	// représente l'image de fond
	private JLabel background;
	
	// représente l'image du titre du jeu
	private JLabel title,space;
	
	private JButton dominosBtn, carcassonneBtn, exitBtn, ajoutBtn;
	
	public HomePageView(Dimension size) {
		BorderLayout container_layout = new BorderLayout();
		container = new JPanel(container_layout);
		top_container=new JPanel();
		GridLayout game_layout = new GridLayout(3,1);
		game_container=new JPanel(game_layout);
		GridLayout bottom_layout = new GridLayout(1,2);
		bottom_container=new JPanel(bottom_layout);
		container_test1= new JPanel();
		container_test2= new JPanel();
		

		bottom_layout.setHgap(20);

		
		
		
		
		//background = new JLabel(new ImageIcon(Auxiliaire.imgResourcesPath + "background.jpg"));
		//background.setBounds(0, 0, container.getWidth(), container.getHeight());
				
		title = new JLabel("Jeux de Dominos Carr� et de Carcassonne");
		title.setFont(new Font("Arial", Font.BOLD, 80));
		
		space=new JLabel(" "); //space c'est en attendant -> ca sera la place ou lutilisateur ajoutera un joueur (bot ou vrai joueur) et mettra son pseudo
		space.setFont(new Font("Arial", Font.BOLD, 80));
		
		dominosBtn = new JButton(resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "dominos.png"), 800, 256));
		dominosBtn.setPreferredSize(new Dimension(800, 256));
		dominosBtn.setBackground(Color.DARK_GRAY);
		
		carcassonneBtn = new JButton(resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "carcassonne.png"), 800, 256));
		carcassonneBtn.setPreferredSize(new Dimension(800, 256));
		carcassonneBtn.setBackground(Color.DARK_GRAY);
		
		exitBtn = new JButton("Quitter le jeu");
		exitBtn.setFont(new Font("Arial", Font.BOLD, 50));
		exitBtn.setBackground(Color.RED);
		ajoutBtn= new JButton("Ajouter un joueur (min.2)");
		ajoutBtn.setFont(new Font("Arial", Font.BOLD, 50));
		ajoutBtn.setBackground(Color.YELLOW);
				
		//container.add(background);
		top_container.add(title);
		container_test1.add(dominosBtn);
		container_test2.add(carcassonneBtn);
		game_container.add(container_test1);
		game_container.add(container_test2);
		game_container.add(space);
		bottom_container.add(ajoutBtn);
		bottom_container.add(exitBtn);

		
		container.add(top_container,BorderLayout.NORTH);
		container.add(game_container,BorderLayout.CENTER);
		container.add(bottom_container,BorderLayout.SOUTH);
		
		container.setVisible(true);
	}
	
	public JPanel getMainContainer() { return container; }

	private ImageIcon resizeImage(ImageIcon i, int width, int height) {
		return new ImageIcon(i.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
}
