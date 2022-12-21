package com.proj.poo.view;

import java.awt.Dimension;
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
	private JPanel container;
	
	// représente l'image de fond
	private JLabel background;
	
	// représente l'image du titre du jeu
	private JLabel title;
	
	private JButton dominosBtn, carcassonneBtn, exitBtn;
	
	public HomePageView(Dimension size) {
		container = new JPanel();
		
		container.setLayout(new GridLayout(5, 1));
		
		//background = new JLabel(new ImageIcon(Auxiliaire.imgResourcesPath + "background.jpg"));
		//background.setBounds(0, 0, container.getWidth(), container.getHeight());
				
		title = new JLabel(new ImageIcon(Auxiliaire.imgResourcesPath + "title.png"));
		title.setPreferredSize(new Dimension(size.width, size.height / 10));
		
		dominosBtn = new JButton(resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "dominos.png"), size.width / 3, size.height / 10));
		
		carcassonneBtn = new JButton(resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "carcassonne.png"), size.width / 3, size.height / 10));
		
		exitBtn = new JButton(resizeImage(new ImageIcon(Auxiliaire.imgResourcesPath + "exit.png"), size.width / 3, size.height / 10));
				
		//container.add(background);
		container.add(title);
		container.add(dominosBtn);
		container.add(carcassonneBtn);
		container.add(exitBtn);
		
		container.setVisible(true);
	}
	
	public JPanel getMainContainer() { return container; }

	private ImageIcon resizeImage(ImageIcon i, int width, int height) {
		return new ImageIcon(i.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
}
