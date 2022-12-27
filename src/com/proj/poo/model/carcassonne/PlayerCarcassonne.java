package com.proj.poo.model.carcassonne;

import java.util.ArrayList;

import com.proj.poo.model.Game;
import com.proj.poo.model.Player;

public class PlayerCarcassonne extends Player {

	private ArrayList<Partisan> partisans;
	
	public PlayerCarcassonne(String pseudo, int id, PlayerColor color, Game partie) {
		super(pseudo, id, partie);
		// TODO Auto-generated constructor stub
		
		partisans = new ArrayList<Partisan>();
		for (int i = 0; i < 8; i++) {
			partisans.add(new Partisan(color));
		}
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unused")
	private void placerPartisan() {
		
	}

	@Override
	protected void placerTuile(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
