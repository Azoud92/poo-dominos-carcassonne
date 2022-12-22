package com.proj.poo.runner;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.proj.poo.view.HomePageView;

// Classe servant à "héberger" les différentes vues (écran d'accueil, dominos, carcassonne...)
public class GameView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1577294716114661773L;

	// Taille de l'écran en soustrayant celle de la barre des tâches et du haut de la fenêtre
	private Dimension size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
	private double scaleX, scaleY; // représente le coef. multiplicateur à appliquer selon la résolution du client
	
	private JFrame frame;
	private JPanel container;
	
	public GameView() {
		scaleX = 1360 / size.width; // on suppose qu'on compare la résolution du client à 1360x768 pixels
		scaleY = 768 / size.height;
		
		EventQueue.invokeLater(() -> { // on programme de façon thread-safe
			frame = new JFrame();		
			frame.setSize(size.width, size.height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Jeu de Dominos et de Carcassonne / Projet 2022 - Team 92");
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			container = new JPanel(); // ce panel contiendra soit l'�cran d'accueil, soit la vue des dominos, soit la vue de carcassonne
			container.setBounds(0,0,size.width,size.height);
			
			HomePageView hpv = new HomePageView(size, scaleX, scaleY);
			container.add(hpv.getMainContainer());
			
			frame.add(container);
			frame.setVisible(true);

			
		});				
	}
	
}
