package com.proj.poo.model.dominos.console;

public class IADominosConsole extends PlayerDominosConsole {

	public IADominosConsole(String pseudo, boolean isIA, int id, DominosConsole partie) {
		super(pseudo, isIA, id, partie);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void play() {
		tuileEnMain = partie.piocher();
		
		partie.placeIA(tuileEnMain, this);
		tuileEnMain = null;
	}
	
	@Override
	protected void placerTuile(int a, int b) {
		throw new UnsupportedOperationException();
	}

}
