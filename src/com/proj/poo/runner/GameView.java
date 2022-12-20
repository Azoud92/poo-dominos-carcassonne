package com.proj.poo.runner;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
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
			frame.setSize(size.width * 3/4, size.height * 3/4); // par défaut la taille est de 3/4 la taille de l'écran
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Jeu de Dominos et de Carcassonne / Projet 2022 — Team 92");
			
			container = new JPanel(); // ce panel contiendra soit l'écran d'accueil, soit la vue des dominos, soit la vue de carcassonne
			
			frame.add(container);		
			frame.setVisible(true);
		});				
	}
	
}