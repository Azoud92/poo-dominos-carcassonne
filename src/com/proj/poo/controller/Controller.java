package com.proj.poo.controller;

import com.proj.poo.exceptions.InvalidCoordinatesException;
import com.proj.poo.exceptions.InvalidPlacementException;
import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.Tuile;
import com.proj.poo.view.GameView;

public abstract class Controller {

	protected Game party;
	protected GameView view;
	protected Tuile actualTuile;
	
	public final void setParty(Game party) { this.party = party; }
	
	public final void setView(GameView view) { 
		this.view = view; 
		view.placeTuile(getPlateauLength() / 2, getPlateauLength() / 2, 0);
		party.play();
	}
	
	public final void setActualTuile(Tuile tuile) {
		// TODO Auto-generated method stub
		this.actualTuile = tuile;
	}
	
	public abstract boolean tour();
	
	public final boolean placeTuile(int x, int y) {
		try {
			party.isLegalPlacement(x, y, actualTuile);
			party.placeTuile(x, y, actualTuile, party.getActualPlayer());
			setPlayerToPlay(party.getPlayers().get(party.getTour()));
			return true;
		}
		catch (InvalidPlacementException e) {
			view.getControle().setInfoLabelText("La tuile est mal placée");
		}
		catch (InvalidCoordinatesException e) {
			
		}
		
		
		return false;
	}
	
	public abstract void haut();
	
	public abstract void bas();
	
	public abstract void gauche();
	
	public abstract void droite();
	
	public final void abandon() {
		party.abandon(party.getActualPlayer());
	}
	
	public final Tuile getActualTuile() {
		return actualTuile;
	}
	
	public abstract GameView.TuileView piocheTuile();

	public final void rotationTuile() {
		actualTuile.rotation();
	}

	public void setPlayerToPlay(Player player) {
		view.getControle().setPseudoLabelText(player.pseudo);
	}

	public final int getPlateauLength() {
		return party.getPlateau().length;
	}	
}
