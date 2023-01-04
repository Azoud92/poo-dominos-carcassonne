package com.proj.poo.controller;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.Carcassonne;
import com.proj.poo.model.carcassonne.tuiles.TuileCarcassonne;
import com.proj.poo.view.CarcassonneView;
import com.proj.poo.view.DominosView;
import com.proj.poo.view.DominosView.TuileView;

public class CarcassonneController {
	
	private Carcassonne party;
	private CarcassonneView view;
	
	private TuileCarcassonne actualTuile;

	
	public void setParty(Carcassonne party) { this.party = party; }


	public void setView(CarcassonneView view) { 
		this.view = view; 
		view.placeTuile(getPlateauLength() / 2, getPlateauLength() / 2);
		party.play();
		}


	public void setActualTuile(Tuile tuile) {
		this.actualTuile = (TuileCarcassonne) tuile;
	}
	
	public int getPlateauLength() {
		return party.getPlateau().length;
	}
		
	public TuileCarcassonne getActualTuile() {
		return actualTuile;
	}
	
	public void piocheTuile() {
		if(party.getPlateau()[0][0] == null) {
			party.piocher();
			view.placeTuile(0,0);
		}
		else {
			int place_x = 0;
			int place_y = 0;
			while(party.getPlateau()[place_x][place_y] != null) { //on suppose qu'il y a forcement une case libre dans le plateau
				if (place_x + 1 == party.getPlateau()[place_y].length) {
					place_x = 0;
					place_y++;
				}
				place_x++;
			}
			party.piocher();
			view.placeTuile(place_x,place_y);
		}
	}
	
	public void rotationTuile() {
		actualTuile.rotation();
	}

}
