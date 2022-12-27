package com.proj.poo.model.dominos.console;

import java.util.Scanner;

import com.proj.poo.model.dominos.PlayerDominos;

public class PlayerDominosConsole extends PlayerDominos {

	private Scanner sc = new Scanner(System.in);
	public PlayerDominosConsole(String pseudo, boolean isIA, int id, DominosConsole partie) {
		super(pseudo, id, partie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
		tuileEnMain = partie.piocher();
		
		System.out.println("Voici votre tuile : " + tuileEnMain.toString());
		
		String r = "";
		while (!r.equalsIgnoreCase("J") && !r.equalsIgnoreCase("D") && !r.equalsIgnoreCase("A")) {
			System.out.println("Tapez 'J' pour jouer cette tuile, 'D' pour la défausser, ou 'A' pour abandonner.");
			r = sc.next();
		}
		
		switch(r.toUpperCase()) {
		case "A": // le joueur souhaite abandonner
			partie.abandon(this);
			break;
		case "D":
			tuileEnMain = null;
			break;
		case "J": // si le joueur veut jouer mais qu'il ne peut rien placer, son tour est passé
			placerTuile(-1, -1);
			break;
		}
	}

	@Override
	protected void placerTuile(int a, int b) { // étant donné qu'il s'agit d'un Override, on doit spécifier les paramètres; mais on ne les utilisera pas ici
		// TODO Auto-generated method stub
		
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
						partie.placeTuile(x, y, tuileEnMain, this);
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
