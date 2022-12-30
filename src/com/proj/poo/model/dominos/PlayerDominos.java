package com.proj.poo.model.dominos;

import com.proj.poo.model.Game;
import com.proj.poo.model.Player;

public class PlayerDominos extends Player {

	private int points = 0;
	public PlayerDominos(String pseudo, int id, Game partie) {
		super(pseudo, id, partie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

		tuileEnMain = partie.piocher();	
	}

	@Override
	public void defausser() {
		super.defausser();
	}
	
	@Override
	protected void placerTuile(int x, int y) {
		// TODO Auto-generated method stub
		try {				
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

	public int getPoints() { return points; }
	public void addPoints(int nb) { points += nb; }

}
