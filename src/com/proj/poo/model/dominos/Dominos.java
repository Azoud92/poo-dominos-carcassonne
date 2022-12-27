package com.proj.poo.model.dominos;

import java.util.ArrayList;

import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.State;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.ZoneTuile;

public class Dominos extends Game {

	public Dominos(int tailleSac, ArrayList<Player> p) {
		super(tailleSac, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void remplirSac() {
		// TODO Auto-generated method stub
		for (int i = 0; i < tailleSac; i++) {
			sac.add(new TuileDominos());
		}
	}

	@Override
	protected void gameStart() {
		// TODO Auto-generated method stub
		if (state != State.READY) {
			return;
		}
		state = State.PLAYING;
		
		int tour = 0; // indice servant à savoir qui doit jouer
		
		// tant qu'il reste des tuiles et qu'il y a toujours plus d'un joueur la partie continue
		while (sac.size() > 0 && players.size() > 1) {
			// IL FAUT AVERTIR LE CONTROLEUR QUE C'EST A TEL JOUEUR DE JOUER
			
			players.get(tour).play();
			if (tour + 1 >= players.size()) { tour = 0; }
			else { tour++; }			
		}
		
		PlayerDominos winner = null;
		int maxPts = 0;
		for (Player p : players) {
			if (((PlayerDominos) p).getPoints() > maxPts) {
				maxPts = ((PlayerDominos) p).getPoints(); 
				winner = (PlayerDominos) p;
			}
		}
		
		if (winner != null) {
			// IL FAUT AVERTIR LE CONTROLEUR DU VAINQUEUR DE LA PARTIE
		}
		else {
			// IL FAUT AVERTIR LE CONTROLEUR QUE LE MATCH EST NUL
		}
		
		state = State.FINISHED;
	}

	@Override
	public void placeIA(Tuile t, Player p) {
		// TODO Auto-generated method stub
		ArrayList<int[]> p1acements = getAllLegalPlacementsIA(t);
		if (p1acements.size() > 0) { // si des placements sont possibles
			int index = rd.nextInt(p1acements.size()); // on en sélectionne un au hasard
			int[] placement = p1acements.get(index);
			if (placement[2] > 0) {
				for (int i = 0; i < placement[2]; i++) t.rotation(); // on fait autant de rotations de la tuile qu'indiquées par le placement
			}

			plateau[placement[0]][placement[1]] = t;
			// IL FAUT AVERTIR LE CONTROLEUR QUE LA TUILE A ETE PLACEE
			addPoints(t, tuilesAdjacentes(placement[0], placement[1]), p);
			return;
		}
		// IL FAUT AVERTIR LE CONTROLEUR QUE LA TUILE A ETE DEFAUSSEE
	}

	// On calcule le nombre de points en regardant sur les adjacences quels côtés ne sont pas nuls
	protected void addPoints(Tuile t, Tuile[] adja, Player p) {
		int nbPoints = 0;

		// On ne revérifie pas si les côtés correspondent, car c'est forcément le cas vu que la méthode est utilisée à la toute fin
		if (adja[0] != null) {
			for (ZoneTuile n : t.getHaut()) {
				nbPoints += n.getValue();
			}
		}
		if (adja[1] != null) {
			for (ZoneTuile n : t.getDroite()) {
				nbPoints += n.getValue();
			}
		}
		if (adja[2] != null) {
			for (ZoneTuile n : t.getBas()) {
				nbPoints += n.getValue();
			}
		}
		if (adja[3] != null) {
			for (ZoneTuile n : t.getGauche()) {
				nbPoints += n.getValue();
			}
		}

		System.out.println(p.pseudo + " gagne " + nbPoints + " point(s)");
		((PlayerDominos) p).addPoints(nbPoints);

	}

}
