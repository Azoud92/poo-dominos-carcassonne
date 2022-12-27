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

		tour = 0; // indice servant à savoir qui doit jouer

		// tant qu'il reste des tuiles et qu'il y a toujours plus d'un joueur la partie continue
		while (sac.size() > 0 && players.size() > 1) {
			// IL FAUT AVERTIR LE CONTROLEUR QUE C'EST A TEL JOUEUR DE JOUER

			if (tour + 1 >= players.size()) { tour = 0; }
			else { tour++; }

			joueurActuel = players.get(tour);
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
	public int[] placeIA(Tuile t, Player p) {
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
			return placement;
		}
		// IL FAUT AVERTIR LE CONTROLEUR QUE LA TUILE A ETE DEFAUSSEE
		return null;
	}
	
	@Override
	public void placeTuile(int x, int y, Tuile t, Player p) {
		// TODO Auto-generated method stub
		plateau[x][y] = t;
		addPoints(t, tuilesAdjacentes(x, y), p);
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

	public boolean passerTour() {
		if (sac.size() <= 0 || players.size() <= 1) {
			state = State.FINISHED;
			return false;
		}
		if (tour + 1 >= players.size()) {
			tour = 0; 
		}
		else { 
			tour++; 
		}
		joueurActuel = players.get(tour);
		return true;
	}

	public Player finPartie() {
		Player winner = null;
		int maxPts = 0;
		for (Player p : players) {
			if (((PlayerDominos) p).getPoints() > maxPts) {
				maxPts = ((PlayerDominos) p).getPoints(); 
				winner = p;
			}
		}
		state = State.FINISHED;
		if (winner != null) return winner;
		else return null;
	}
	
	public void addTuile(Tuile t, int x, int y) {
		plateau[x][y]=t;
	}

	public void suppTuile(int x,int y) {
		plateau[x][y]=null;
	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	public void setState(State s) {
		state=s;
	}
}
