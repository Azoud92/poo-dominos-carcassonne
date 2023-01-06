package com.proj.poo.runner;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.proj.poo.controller.HomePageController;
import com.proj.poo.view.HomePageView;

// Classe servant à "héberger" les différentes vues (écran d'accueil, dominos, carcassonne...)
public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1577294716114661773L;

	private Dimension size;
	private double scaleX, scaleY, scaleXY; // représente le coef. multiplicateur à appliquer selon la résolution du client

	private JFrame frame;
	private JPanel container;

	private Image background = new ImageIcon(Auxiliaire.imgResourcesPath + "background.jpg").getImage();
	
	private boolean drawBg = true;
	
	public Window() {
		// Taille de l'écran en soustrayant celle de la barre des tâches et du haut de la fenêtre
		size = new Dimension();
		size.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		size.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom - 15;

		scaleX = (double) size.width / 1360; // on suppose qu'on compare la résolution du client à 1360x768 pixels (donc 1360x705 car on prend la taille effective de la fenêtre) 
		scaleY = (double) size.height / 705;
		scaleXY = (double) (size.width * size.height) / (1360.0 * 705.0);

		EventQueue.invokeLater(() -> { // on programme de façon thread-safe
			frame = new JFrame();		
			
			frame.setPreferredSize(size);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Jeu de Dominos et de Carcassonne / Projet 2022 - Team 92");			
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			frame.setMinimumSize(size);
			frame.setMaximumSize(size);			

			// Afin de redéfinir la méthode "paintComponent" servant à afficher le fond d'écran, on procède par un bloc d'initialisation

			container = new JPanel() {

				private static final long serialVersionUID = 5873722615660821503L;

				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					if (drawBg) g.drawImage(background, 0, 0, size.width, size.height, this);
					
				}
			}; 
			container.setBounds(0,0,size.width,size.height);
			container.setOpaque(false);

			HomePageController hpc = new HomePageController();
			HomePageView hpv = new HomePageView(size, scaleX, scaleY, scaleXY, frame, hpc, this);
			container.add(hpv.getMainContainer());

			frame.add(container);
			frame.setVisible(true);
		});		
	}
	
	public void disableBg() {
		this.drawBg = false;
	}
}
