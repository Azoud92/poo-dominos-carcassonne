package com.proj.poo.controller;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.Carcassonne;
import com.proj.poo.model.carcassonne.tuiles.TuileCarcassonne;
import com.proj.poo.view.CarcassonneView;

public class CarcassonneController {
	
	private Carcassonne party;
	private CarcassonneView view;
	
	private Tuile actualTuile;

	
	public void setParty(Carcassonne party) { this.party = party; }


	public void setView(CarcassonneView view) { 
		this.view = view; 
		// placer tuile au milieu du plateau (view.placeTuile(getPlateauLength() / 2, getPlateauLength() / 2);)
		party.play();
		}


	public void setActualTuile(Tuile tuile) {
		this.actualTuile = (TuileCarcassonne) tuile;
	}
		


}
