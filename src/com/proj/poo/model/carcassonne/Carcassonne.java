package com.proj.poo.model.carcassonne;

import java.util.ArrayList;
import java.util.Collections;

import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.State;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.tuiles.*;
import com.proj.poo.runner.Auxiliaire;

public class Carcassonne extends Game{
	
private CarcassonneController controller;
	
	public Carcassonne(ArrayList<Player> p, CarcassonneController controller) {		
		super(72, p);
		this.controller = controller;
		
		controller.setActualTuile(plateau[plateau.length / 2][plateau.length / 2]);
		state = State.PLAYING;
	}
	
	private void printPlateau() {
		// ces ArrayList servent à afficher correctement les tuiles : pour chaque ligne, on imprime les côtés des tuiles
		ArrayList<String> toPrint = new ArrayList<String>();
		ArrayList<Integer> spToPrint = new ArrayList<Integer>();

		int spaceToPrint = 0; // sert à compter le nombre d'espaces à ajouter pour l'alignement avec la partie haute de la tuile

		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				TuileCarcassonne x = (TuileCarcassonne) plateau[j][i];
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

		if (state == State.FINISHED) {
			return;
		}
		state = State.PLAYING;
		controller.setPlayerToPlay( (PlayerCarcassonne) players.get(tour));
		
	}
	
	@Override
	public Tuile piocher() {
		Tuile t = super.piocher();
		if (controller != null) controller.setActualTuile(t);
		printPlateau();
		return t;		
	}
	

	@Override
	protected void remplirSac() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			sac.add(new TuilePreCheminGaucheBas());
		}
		
		for (int i = 0; i < 8; i++) {
			sac.add(new TuilePreCheminHautBas());
		}
		
		for (int i = 0; i < 5; i++) {
			sac.add(new TuileVilleHautPre());
		}
		
		for (int i = 0; i < 4; i++) {
			sac.add(new TuileCarrefourPreHaut());
			sac.add(new TuileAbbaye());
			sac.add(new TuileVilleHautCheminGaucheDroitePre());
		}
		
		for (int i = 0; i < 3; i++) {
			sac.add(new TuileVilleHautPreCheminDroiteBas());
			sac.add(new TuileVilleGaucheHautPreCheminDroiteBas());
			sac.add(new TuileVilleHautCarrefour());
			sac.add(new TuileVillePreBas());
			sac.add(new TuileVilleHautGauchePre());
			sac.add(new TuileVilleHautCheminGaucheBasPre());
			sac.add(new TuileVilleGaucheDroitePre());
		}
		
		for (int i = 0; i < 2; i++) {
			sac.add(new TuileBouclierVilleGaucheHautPreCheminDroiteBas());
			sac.add(new TuileVilleHautDroitePre());
			sac.add(new TuileAbbayeCheminBas());
			sac.add(new TuileBouclierVillePreHautBas());
			sac.add(new TuileBouclierVilleCheminBas());
			sac.add(new TuileBouclierVilleHautGauchePre());
		}
		
		sac.add(new TuileVilleCheminBas());
		sac.add(new TuileBouclierVillePreBas());
		sac.add(new TuileVillePreHautBas());
		sac.add(new TuileBouclierVille());
		sac.add(new TuileCarrefour());
		
		Collections.shuffle(sac); // on mélange le sac pour créer du hasard
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
		return true;
	}	
}
