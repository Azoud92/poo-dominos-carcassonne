package com.proj.poo.dominos;

import java.util.Scanner;

public class Player {

	private Tuile tuileEnMain; // tuile actuellement en mains pour le joueur (null = aucune);
	private int points; 
	public final String pseudo;
	public final boolean isPlayer; // joueur réel ou IA
	public final int num; // identifiant unique
	
	public final Dominos partie; // la partie d'appartenance au joueur
	
	private Scanner sc = new Scanner(System.in);
	
	public Player(String pseudo, Dominos partie, boolean isPlayer, int num) {
		tuileEnMain = null;
		points = 0;
		this.pseudo = pseudo;
		this.partie = partie;
		this.isPlayer = isPlayer;
		this.num = num;
	}
	
	// demande au joueur ce qu'il souhaite faire
	public void play() {
		
		tuileEnMain = partie.piocher();
		
		if (isPlayer) {
			System.out.println("Voici votre tuile : " + tuileEnMain.toString());
			
			String r = "";
			while (!r.equalsIgnoreCase("J") && !r.equalsIgnoreCase("D") && !r.equalsIgnoreCase("A")) {
				System.out.println("Tapez 'J' pour jouer cette tuile, 'D' pour la défausser, ou 'A' pour abandonner.");
				r = sc.next();
			}
			
			switch(r) {
			case "A": // le joueur souhaite abandonner
				partie.abandon(this);
				break;
			case "D":
				tuileEnMain = null;
				break;
			case "J": // si le joueur veut jouer mais qu'il ne peut rien placer, son tour est passé
				place();
				break;
			}	
		}
		else {
			partie.placeIA(tuileEnMain, num);
			tuileEnMain = null;
		}			
	}
	
	private void place() {
		if (isPlayer == true) {
			boolean legalPlacement = false;
						
			while (legalPlacement == false) {	
				System.out.println("Pour retourner la tuile, tapez 'R'. Pour défausser, tapez 'D'. Pour abandonner, tapez 'A'.\nSinon, tapez la coordonnée X où vous souhaitez placer votre tuile.");
				
				String resp = sc.next();
				
				if (resp.equalsIgnoreCase("R")) {
					tuileEnMain.rotation();
					System.out.println("Voici votre tuile : " + tuileEnMain.toString());
				}
				else if (resp.equalsIgnoreCase("D")) {
					tuileEnMain = null;
					return;
				}
				else if (resp.equalsIgnoreCase("A")) {
					partie.abandon(this);
					return;
				}
				else {
					try {
						int x = Integer.valueOf(resp);
						System.out.println("Veuillez maintenant entrer la coordonnée Y où vous souhaitez placer votre tuile.");
						
						resp = sc.next();
						int y = Integer.valueOf(resp);
						
						if (partie.isLegalPlacement(x, y, tuileEnMain)) {
							partie.placer(x, y, tuileEnMain, num);
							return;
						}
						System.out.println("Impossible de placer une tuile à cet endroit. Veuillez vérifier vos coordonnées et réessayer.");
					}
					catch (NumberFormatException e) {
						System.out.println("Erreur : entrée non reconnue.");
					}					
				}
			}
		}
	}
	
	public Tuile getTuileEnMain() { return tuileEnMain; }
	public void setTuileEnMain(Tuile t) { tuileEnMain = t; }
	
	public int getPoints() { return points; }
	public void addPoints(int nb) { points += nb; }
	
}
