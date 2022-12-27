package com.proj.poo.runner;

import java.awt.Image; 

import javax.swing.ImageIcon;

// Classe contenant toutes les méthodes auxiliaires; non extensible.
public final class Auxiliaire {
	
	// renvoie un nombre d'espaces souhaité
	public static String space(int nb) { return " ".repeat(nb); }
	
	// renvoie un message personnalisé avec un séparateur haut-bas
	public static String sepMsg(String msg) {
		return ("-".repeat(30) + "\n" + msg + "\n" + "-".repeat(60));
	}
	
	// Chemin d'accès aux resources images
	public static String imgResourcesPath = "src/com/proj/poo/resources/img/";
	
	public static ImageIcon resizeImage(ImageIcon i, int width, int height) {
		return new ImageIcon(i.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
}
