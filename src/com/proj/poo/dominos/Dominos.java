package com.proj.poo.dominos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import com.proj.poo.runner.Auxiliaire;

public class Dominos {

	private final static int tailleSac = 5; // on suppose que l'on dispose de 20 tuiles dans le sac
	
	private ArrayList<Tuile> sac; // sac de tuiles
	private ArrayList<Player> players; // liste des joueurs

	private Tuile[][] plateau; // représentation du plateau
	
	private Random rd = new Random();
	private Scanner sc = new Scanner(System.in);
	
	private State state;
		
	public Dominos() {
		players = new ArrayList<Player>();
		
		welcome();
		
		// remplissage du sac		
		sac = new ArrayList<Tuile>();
		for (int i = 0; i < tailleSac; i++) { 
			sac.add(new Tuile());
		}
		
		// choix d'une tuile au hasard à poser sur la table
		plateau = new Tuile[tailleSac * 2][tailleSac * 2]; // 2 * tailleSac pour laisser pile assez de place dans le cas extrême
		plateau[tailleSac][tailleSac] = piocher();
		
		state = State.READY;
	}
	
	// Souhaite la bienvenue dans le jeu de Dominos, demande le nombre de joueurs, leur type (IA, joueur) et leur pseudo
	private void welcome() {
		System.out.println(Auxiliaire.sepMsg("Bienvenue au jeu de Dominos Carrés") + "\n");
		
		// On demande le nombre de joueurs
		System.out.println("Combien de joueurs êtes-vous ? (min. 2)");
		int nbJoueurs = -1;
		while (nbJoueurs < 2) {
			try { 
				nbJoueurs = sc.nextInt();
				if (nbJoueurs < 2) System.out.println("Erreur : il doit y avoir au moins deux joueurs.");
			}
			catch (InputMismatchException e) {
				System.out.println("Erreur : vous devez entrer un nombre valide. Combien de joueurs êtes-vous ? (min. 2)");
				sc.next();
				nbJoueurs = -1;
			}			
		}
		
		// Leur type et leur pseudo
		for (int i = 0; i < nbJoueurs; i++) {			
			String type = "";
			while (!type.equals("IA") && !type.equals("J")) {
				System.out.println("Quel est le type du joueur " + (i + 1) + " ? Tapez 'IA' ou 'J'");
				type = sc.next();				
			}
			
			if (type.equals("IA")) {
				players.add(new Player("IA " + (i + 1), this, false, i));
			}
			else {
				System.out.println("Quel est le pseudo du joueur " + (i + 1) + " ?");
				String pseudo = "";
				while (pseudo == null || pseudo.isEmpty() || pseudo.isBlank()) {
					try {
						pseudo = sc.next();
						players.add(new Player(pseudo, this, true, i));
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
	
	// affichage du plateau
	private void printPlateau() {
		// ces ArrayList servent à afficher correctement les tuiles : pour chaque ligne, on imprime les côtés des tuiles
		ArrayList<String> toPrint = new ArrayList<String>();
		ArrayList<Integer> spToPrint = new ArrayList<Integer>();
		
		int spaceToPrint = 0; // sert à compter le nombre d'espaces à ajouter pour l'alignement avec la partie haute de la tuile
		
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				Tuile x = plateau[i][j];
				if (x == null) {
					String s = "(" + i + "; " + j + ") "; // une tuile nulle est représentée par une coordonnée "(x, y)"
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
		System.out.println("\n");
	}
	
	public void gameStart() {
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
		
		// TODO: AFFICHER LE VAINQUEUR
		state = State.FINISHED;
	}
	
	// enlève une tuile du sac au hasard et la renvoie
	public Tuile piocher() {
		Tuile piochee = sac.get(rd.nextInt(sac.size()));
		sac.remove(piochee);
		return piochee;
	}

	// cette fonction place, s'il y a possibilité, la tuile piochée par une IA
	public void placeIA(Tuile t, int num) {
		ArrayList<int[]> p = getAllLegalPlacementsIA(t);
		if (p.size() > 0) { // si des placements sont possibles
			int index = rd.nextInt(p.size()); // on en sélectionne un au hasard
			int[] placement = p.get(index);
			if (placement[2] > 0) {
				for (int i = 0; i < placement[2]; i++) t.rotation(); // on fait autant de rotations de la tuile qu'indiquées par le placement
			}
			
			plateau[placement[0]][placement[1]] = t;
			addPoints(num);
			System.out.println("Tuile jouée : (" + placement[0] + "; " + placement[1] + ")");
			return;
		}
		System.out.println("Tuile défaussée");
	}
	
	// cherche tous les emplacements possibles pour placer notre tuile pour l'IA
	private ArrayList<int[]> getAllLegalPlacementsIA(Tuile t) {
		ArrayList<int[]> res = new ArrayList<int[]>();
		
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				if (plateau[i][j] == null) { // l'emplacement est libre
					Tuile tmp = t; // on crée une tuile temporaire pour la faire tourner
					for (int rot = 0; rot < 3; rot++) { // on fait tourner la tuile et on enregistre l'indice de rotation (0 = pas de rotation)
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
	
	// on regarde si des tuiles adjacentes sont présentes ou non par rapport aux coordonnées de celle fournie
	private Tuile[] adjacentesPresentes(int x, int y) {
		/* pour gérer très simplement cette fonction, on va
		* enregistrer les différentes possibilités d'adjacentes dans un tableau,
		* et pour chacune d'entre-elles, on va faire un try-catch pour
		* accéder au tableau.
		* Si une erreur est générée --> on est sur une bordure.
		*/
		
		int[][] adj = { {x - 1, y}, {x + 1, y}, {x, y - 1}, {x, y + 1} };
		Tuile[] res = new Tuile[4]; // il peut y avoir jusqu'à 4 tuiles adjacentes
		
		int index = 0;
		for (int[] l : adj) {
			try {
				if (plateau[l[0]][l[1]] != null) {
					res[index] = plateau[l[0]][l[1]];
				}
				index++;
			}
			catch (IndexOutOfBoundsException e) {
				res[index] = null;
			}
		}
		return res;		
	}
	
	// On vérifie si l'on peut placer notre tuile (sans faire de rotation) aux coordonnées données
	public boolean isLegalPlacement(int x, int y, Tuile t) {
		Tuile p;
		try { // on vérifie si les coordonnées sont valides
			p = plateau[x][y];
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
				
		if (p != null) return false; // si l'emplacement est occupé on ne peut rien placer
		
		Tuile[] adja = adjacentesPresentes(x, y);		
		if (adja[0] == null) return false; // s'il n'y a aucune adjacence 
		
		for (int i = 0; i < adja.length; i++) { // on doit vérifier si tous les côtés correspondent à ceux des tuiles adjacentes
			if (adja[i] == null) break; // si on a vu toutes les adjacences on s'arrête
			if (t.getGauche() != adja[i].getGauche() && // si un des côtés correspond on renvoie true
					t.getDroite() != adja[i].getDroite() &&
					t.getHaut() != adja[i].getHaut() &&
					t.getBas() != adja[i].getBas()) {
				return false; // si une seule tuile adjacente dont le côté est collé à la nôtre ne correspond pas, on renvoie false;
			}
		}
		return true;
	}
	
	private void addPoints(int num) {
		// TODO: Calculer les points et les ajouter
	}
		
	// un joueur qui appelle cette méthode abandonne la partie et est supprimé de la liste
	public void abandon(Player player) {
		// TODO Auto-generated method stub
		players.remove(player);		
	}

	public void placer(int x, int y, Tuile tuileEnMain, int num) {
		// TODO Auto-generated method stub
		plateau[x][y] = tuileEnMain;
		addPoints(num);
	}
	
}
