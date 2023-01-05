package com.proj.poo.model.carcassonne;

import com.proj.poo.model.Game;

public class IACarcassonne extends PlayerCarcassonne {

	public IACarcassonne(String pseudo, int id, PlayerColor color, Game partie) {
		super(pseudo, id, color, partie);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		super.play();
		
		partie.placeIA(tuileEnMain, this);
		tuileEnMain = null;
	}

	@Override
	protected void placerTuile(int x, int y) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}


}
