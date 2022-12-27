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

		// IL FAUT NOTIFIER LE CONTROLEUR QUE LA TUILE A ETE PIOCHEE POUR L'AFFICHER		
		// IL FAUT NOTIFIER LE CONTROLEUR POUR ACTIVER LES BOUTONS ABANDON, DEFAUSSER, PLACER, ...
	}

	@Override
	public void defausser() {
		super.defausser();
		// IL FAUT NOTIFIER LE CONTROLEUR QUE LA TUILE DE L'IA A ETE DEFAUSSEE
	}
	
	@Override
	protected void placerTuile(int x, int y) {
		// TODO Auto-generated method stub
		try {				
			if (partie.isLegalPlacement(x, y, tuileEnMain)) {
				partie.placeTuile(x, y, tuileEnMain, this);
				// ON DOIT AVERTIR LE CONTROLEUR DU PLACEMENT DE LA TUILE
				return;
			}
			// ON DOIT AVERTIR LE CONTROLEUR QUE LA TUILE N'A PAS ETE PLACEE
			System.out.println("Impossible de placer une tuile à cet endroit. Veuillez vérifier vos coordonnées et réessayer.");
		}
		catch (NumberFormatException e) {
			System.out.println("Erreur : entrée non reconnue.");
			// IDEM
		}
	}

	public int getPoints() { return points; }
	public void addPoints(int nb) { points += nb; }

}
