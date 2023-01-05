package com.proj.poo.model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game {

	protected final int tailleSac;
	protected ArrayList<Tuile> sac;
	protected ArrayList<Player> players;
	
	protected int tour;

	protected Tuile[][] plateau;
	protected State state;

	protected final Random rd = new Random();

	public Game(int tailleSac, ArrayList<Player> players) {
		this.tailleSac = tailleSac;
		sac = new ArrayList<Tuile>();
		if (players == null) this.players = new ArrayList<Player>();
		else {
			this.players = players;
			tour = 0;
		}

		remplirSac();

		// choix d'une tuile au hasard à poser sur la table
		int taillePlat = (int) Math.sqrt(tailleSac * 2);
		if (taillePlat % 2 == 0) taillePlat++; // pour que la tuile de départ se place bien au milieu du plateau
		plateau = new Tuile[taillePlat][taillePlat]; // pour laisser assez de place pour chaque tuile du sac
		plateau[taillePlat / 2][taillePlat / 2] = piocher();

		state = State.READY;
	}

	public abstract void play();
	
	protected abstract void remplirSac();	
	
	public Tuile piocher() {
		Tuile t = sac.get(rd.nextInt(sac.size()));
		sac.remove(t);
		return t;
	}
	
	public int[] placeIA(Tuile t, Player p) {
		// TODO Auto-generated method stub
		ArrayList<int[]> p1acements = getAllLegalPlacementsIA(t);
		if (p1acements.size() > 0) { // si des placements sont possibles
			int index = rd.nextInt(p1acements.size()); // on en sélectionne un au hasard
			int[] placement = p1acements.get(index);
			if (placement[2] > 0) {
				for (int i = 0; i < placement[2]; i++) t.rotation(); // on fait autant de rotations de la tuile qu'indiquées par le placement
			}
			return placement;
		}
		return null;
	}

	// Ces méthodes ne peuvent pas être redéfinies étant donné qu'elles ne peuvent, par définition, pas dériver
	
	protected final ArrayList<int[]> getAllLegalPlacementsIA(Tuile t) {
		ArrayList<int[]> res = new ArrayList<int[]>();

		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				if (plateau[i][j] == null) { // l'emplacement est libre
					Tuile tmp = t; // on crée une tuile temporaire pour la faire tourner
					for (int rot = 0; rot < 4; rot++) { // on fait tourner la tuile et on enregistre l'indice de rotation (0 = pas de rotation)
						if (isLegalPlacement(i, j, tmp)) {
							int[] pos = {i, j, rot};
							res.add(pos);
						}
						tmp.rotation();
					}					
				}
			}
		}		
		return res;
	}
		
	protected final Tuile[] tuilesAdjacentes(int x, int y) {
		/* pour gérer très simplement cette fonction, on va
		* enregistrer les différentes possibilités d'adjacentes dans un tableau,
		* et pour chacune d'entre-elles, on va faire un try-catch pour
		* accéder au tableau.
		* Si une erreur est générée --> on est sur une bordure.
		*/
		
		int[][] adj = { {x, y - 1}, {x + 1, y}, {x, y + 1}, {x - 1, y} }; // on prend toutes les adjacences possibles
		Tuile[] res = new Tuile[4]; // il peut y avoir jusqu'à 4 tuiles adjacentes
		boolean isNull = true;
		
		int index = 0;
		for (int[] l : adj) {
			try {
				if (plateau[l[0]][l[1]] != null) {
					res[index] = plateau[l[0]][l[1]];
					isNull = false;
				}				
			}
			catch (IndexOutOfBoundsException e) {
				res[index] = null;
			}
			finally {
				index++; // dans tous les cas on incrémente l'index
			}
		}
		if (isNull) return null;
		return res;	// res[0] = adjacences en haut, res[1] à droite, "" en bas, "" à gauche
	}
	
	public final boolean isLegalPlacement(int x, int y, Tuile t) {
		Tuile p;
		try { // on vérifie si les coordonnées sont valides
			p = plateau[x][y];
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
				
		if (p != null) return false; // si l'emplacement est occupé on ne peut rien placer
		
		Tuile[] adja = tuilesAdjacentes(x, y);
		if (adja == null) return false; // s'il n'y a aucune adjacence 
		
		// Si l'une des adjacences qui n'est pas nulle ne correspond pas à la tuile, on renvoie false
		for (int i = 0; i < 4; i++) {
			if (adja[i] != null) {
				if ((i == 0 && !adja[0].bottomEquals(t.getHaut())) ||
						(i == 1 && !adja[1].leftEquals(t.getDroite())) ||
						(i == 2 && !adja[2].topEquals(t.getBas())) ||
						(i == 3 && !adja[3].rightEquals(t.getGauche()))) return false;
			}
		}
				
		return true;
	}
	
	public void placeTuile(int x, int y, Tuile t, Player p) {
		plateau[x][y] = t;
	}

	public final void addPlayer(Player p) {
		players.add(p);
	}

	public final void removePlayer(Player p) {
		players.add(p);
	}
	
	public final void abandon(Player p) {
		players.remove(p);
	}
	
	public final Tuile[][] getPlateau(){
		return plateau;
	}
	
	public final Player getActualPlayer() {
		if (players.size() > 0) return players.get(tour);
		return null;
	}
	
	public final void setState(State s) {
		state = s;
	}

}
