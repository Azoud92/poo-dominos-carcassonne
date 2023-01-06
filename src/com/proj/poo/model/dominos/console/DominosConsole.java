package com.proj.poo.model.dominos.console;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.proj.poo.model.Player;
import com.proj.poo.model.State;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.TuileDominos;
import com.proj.poo.runner.Auxiliaire;

public class DominosConsole extends Dominos {

	private Scanner sc = new Scanner(System.in);
	public DominosConsole() {		
		super(70, null, null);
		// TODO Auto-generated constructor stub
		
		int nbPlayers = requestNbPlayers();
		requestTypeAndPseudoPlayers(nbPlayers);
		
		state = State.READY;
		play();
		
		
	}
	
	private int requestNbPlayers() {
		System.out.println(Auxiliaire.sepMsg("Bienvenue au jeu de Dominos Carrés") + "\n");
		
		// On demande le nombre de joueurs
		System.out.println("Combien de joueurs êtes-vous ? (min. 2)");
		int nbPlayers = -1;
		while (nbPlayers < 2) {
			try { 
				nbPlayers = sc.nextInt();
				if (nbPlayers < 2) System.out.println("Erreur : il doit y avoir au moins deux joueurs.");
			}
			catch (InputMismatchException e) {
				System.out.println("Erreur : vous devez entrer un nombre valide. Combien de joueurs êtes-vous ? (min. 2)");
				sc.next();
				nbPlayers = -1;
			}			
		}
		return nbPlayers;
	}
	
	private void requestTypeAndPseudoPlayers(int nbPlayers) {
		// Leur type et leur pseudo
		for (int i = 0; i < nbPlayers; i++) {			
			String type = "";
			while (!type.equalsIgnoreCase("IA") && !type.equalsIgnoreCase("J")) {
				System.out.println("Quel est le type du joueur " + (i + 1) + " ? Tapez 'IA' ou 'J'");
				type = sc.next();				
			}

			if (type.equalsIgnoreCase("IA")) {
				players.add(new IADominosConsole("IA " + (i + 1), true, i, this));
			}
			else {
				System.out.println("Quel est le pseudo du joueur " + (i + 1) + " ?");
				String pseudo = "";
				while (pseudo == null || pseudo.isEmpty() || pseudo.isBlank()) {
					try {
						pseudo = sc.next();
						players.add(new PlayerDominosConsole(pseudo, false, i, this));
						System.out.println("Pseudo enregistré pour le joueur " + (i + 1));
					}
					catch (InputMismatchException e) {
						System.out.println("Erreur : le pseudo ne doit pas être vide. Veuillez entrer à nouveau le pseudo.");
						pseudo = "";
						sc.next();
					}			
				}
			}
		}
	}
	
	private void printPlateau() {
		// ces ArrayList servent à afficher correctement les tuiles : pour chaque ligne, on imprime les côtés des tuiles
		ArrayList<String> toPrint = new ArrayList<String>();
		ArrayList<Integer> spToPrint = new ArrayList<Integer>();

		int spaceToPrint = 0; // sert à compter le nombre d'espaces à ajouter pour l'alignement avec la partie haute de la tuile

		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				TuileDominos x = (TuileDominos) plateau[j][i];
				if (x == null) {
					String s = "(" + Auxiliaire.space(String.valueOf(plateau.length).length() - String.valueOf(i).length()) + i + ";" + Auxiliaire.space(String.valueOf(plateau[i].length).length() - String.valueOf(j).length()) + j + ") "; // une tuile nulle est représentée par une coordonnée "(x, y)"
					System.out.print(s);
					spaceToPrint += s.length();
				}
				else {
					String s = x.toStringHaut();
					System.out.print(s); // sinon on affiche d'abord le haut de la tuile
					toPrint.add(x.toStringMilieuBas());
					spToPrint.add(spaceToPrint);
					spaceToPrint = 0;
				}

			}
			spaceToPrint = 0;
			System.out.println(); // on passe à la ligne
			if (toPrint.size() > 0) { // s'il y a des tuiles à afficher, on le fait
				for (int y = 0; y < 4; y++) {
					for (int z = 0; z < toPrint.size(); z++) {
						String[] sx = toPrint.get(z).split("\n");
						int sp = spToPrint.get(z);
						System.out.print(Auxiliaire.space(sp) + sx[y]); // on met le nombre d'espaces requis pour l'alignement et on affiche le "morceau" de tuile
					}
					System.out.println();
				}
				toPrint.removeAll(toPrint);
				spToPrint.removeAll(spToPrint);
			}


		}
		System.out.println();
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		if (state != State.READY) {
			return;
		}
		state = State.PLAYING;
		
		int tour = 0; // indice servant à savoir qui doit jouer
		
		// tant qu'il reste des tuiles et qu'il y a toujours plus d'un joueur la partie continue
		while (sac.size() > 0 && players.size() > 1) {
			printPlateau();
			
			System.out.println("C'est au tour de " + players.get(tour).pseudo + " de jouer");
			
			players.get(tour).play();
			if (tour + 1 >= players.size()) { tour = 0; }
			else { tour++; }			
		}
		
		PlayerDominosConsole winner = null;
		int maxPts = 0;
		for (Player p : players) {
			if (((PlayerDominosConsole) p).getPoints() > maxPts) {
				maxPts = ((PlayerDominosConsole) p).getPoints(); 
				winner = (PlayerDominosConsole) p;
			}
		}
		printPlateau();
		
		if (winner != null) {
			System.out.println("Le vainqueur de cette partie est " + winner.pseudo + " avec " + maxPts + " point(s) marqué(s)");
		}
		else {
			System.out.println("Pas de vainqueur pour cette partie, match nul !");
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
			System.out.println("Tuile jouée : (" + placement[0] + "; " + placement[1] + ")");
			addPoints(t, tuilesAdjacentes(placement[0], placement[1]), p);
			return placement;
		}
		System.out.println("Tuile défaussée");
		return null;
	}	

}
