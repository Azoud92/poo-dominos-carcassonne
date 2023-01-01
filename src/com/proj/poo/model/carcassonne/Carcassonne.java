package com.proj.poo.model.carcassonne;

import java.util.ArrayList;
import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.State;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.dominos.TuileDominos;


public class Carcassonne extends Game{
	
private CarcassonneController controller;
	
	public Carcassonne(int tailleSac, ArrayList<Player> p, CarcassonneController controller) {		
		super(tailleSac, p);
		this.controller = controller;
		
		//ajouter tuile au milieu (controller.setActualTuile(plateau[plateau.length / 2][plateau.length / 2]);)
		state = State.PLAYING;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remplirSac() {
		// TODO Auto-generated method stub
		for (int i = 0; i < tailleSac; i++) {
			sac.add(new TuileDominos());
		}
	}

	@Override
	public int[] placeIA(Tuile t, Player p) {
		// TODO Auto-generated method stub
		return null;
	}


}
