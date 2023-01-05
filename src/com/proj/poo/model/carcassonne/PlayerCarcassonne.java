package com.proj.poo.model.carcassonne;

import java.util.ArrayList;

import com.proj.poo.model.Game;
import com.proj.poo.model.Player;

public class PlayerCarcassonne extends Player {

	private ArrayList<Partisan> partisans;
	private ArrayList<int[]> partisansPlaces;
	
	public PlayerCarcassonne(String pseudo, int id, PlayerColor color, Game partie) {
		super(pseudo, id, partie);
		// TODO Auto-generated constructor stub
		
		partisans = new ArrayList<Partisan>();
		for (int i = 0; i < 8; i++) {
			partisans.add(new Partisan(color));
		}
		
		partisansPlaces = new ArrayList<int[]>();
	}
		
	public void placerPartisan(int x, int y) {
		if (partisans.size() > 0) {
			int[] c = {x, y};
			partisansPlaces.add(c);
			partisans.remove(0);
		}
	}
	
	public ArrayList<Partisan> getPartisans(){
		return partisans;
	}

}
