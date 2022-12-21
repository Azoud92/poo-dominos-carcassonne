package com.proj.poo.runner;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Classe servant à "héberger" les différentes vues (écran d'accueil, dominos, carcassonne...)
public class GameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1577294716114661773L;

	// Taille de l'écran en soustrayant celle de la barre des tâches et du haut de la fenêtre
	private Dimension size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
	
	private JFrame frame;
	private JPanel container;
	
	public GameView() {
		EventQueue.invokeLater(() -> { // on programme de façon thread-safe
			frame = new JFrame();		
			frame.setSize(size.width, size.height); // par défaut la taille est de 3/4 la taille de l'écran
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Jeu de Dominos et de Carcassonne / Projet 2022 - Team 92");
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			container = new JPanel(); // ce panel contiendra soit l'�cran d'accueil, soit la vue des dominos, soit la vue de carcassonne
			container.setBounds(0,0,size.width,size.height);
			
			
			
		
			JButton btn_dominos = new JButton("Dominos Carr�s");
			btn_dominos.setBounds((size.width / 2 - 800) / 2, size.height / 2 - 128, 800, 256);
			btn_dominos.setFont(new Font("Arial", Font.ITALIC, 105));
			btn_dominos.setOpaque(false);
			btn_dominos.setContentAreaFilled(false);
			btn_dominos.setBorderPainted(false);
			
			JButton btn_carcassonne = new JButton(new ImageIcon("src/com/proj/poo/runner/ressources/carcassonne_title.png"));
			btn_carcassonne.setBounds((size.width / 2 - 800) / 2 + size.width / 2, size.height / 2 - 128, 800, 256);
			btn_carcassonne.setOpaque(false);
			btn_carcassonne.setContentAreaFilled(false);
			btn_carcassonne.setBorderPainted(false);
			
			
			
			container.setLayout(null);
			JLabel background = new JLabel(new ImageIcon("src/com/proj/poo/runner/ressources/background.jpg"));

			background.add(btn_dominos);
			background.add(btn_carcassonne);
			frame.add(background);
			frame.setVisible(true);

			
		});				
	}
	
}
