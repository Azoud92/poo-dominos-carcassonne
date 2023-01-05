package com.proj.poo.model.dominos;

import com.proj.poo.model.Game;
import com.proj.poo.model.Player;

public class PlayerDominos extends Player {

	private int points = 0;
	public PlayerDominos(String pseudo, int id, Game partie) {
		super(pseudo, id, partie);
		// TODO Auto-generated constructor stub
	}
	
	public int getPoints() { return points; }
	public void addPoints(int nb) { points += nb; }

}
