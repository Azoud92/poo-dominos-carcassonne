package com.proj.poo.model.dominos;

import com.proj.poo.model.Game;

public class IADominos extends PlayerDominos {

	public IADominos(String pseudo, int id, Game partie) {
		super(pseudo, id, partie);
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
