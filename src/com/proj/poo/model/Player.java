package com.proj.poo.model;

public abstract class Player {

	protected Tuile tuileEnMain;
	public final String pseudo;
	public final int id;
	
	public final Game partie;
	
	public Player(String pseudo, int id, Game partie) {
		this.pseudo = pseudo;
		this.id = id;
		this.partie = partie;
	}
	
	public abstract void play();
	protected abstract void placerTuile(int x, int y);
	
	public void defausser() {
		tuileEnMain = null;
	}
	
	public final void piocher() {
		tuileEnMain = partie.piocher();
	}
		
	public final void abandonner() {
		partie.abandon(this);
	}
	
	public final Tuile getTuileEnMain() { return tuileEnMain; }
	public final void setTuileEnMain(Tuile t) { tuileEnMain = t; }
	
}
