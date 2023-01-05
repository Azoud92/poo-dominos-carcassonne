package com.proj.poo.model.carcassonne;

import java.util.ArrayList;

import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.State;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.tuiles.TuilePreCheminGaucheBas;

public class Carcassonne extends Game{
	
private CarcassonneController controller;
	
	public Carcassonne(ArrayList<Player> p, CarcassonneController controller) {		
		super(72, p);
		this.controller = controller;
		
		controller.setActualTuile(plateau[plateau.length / 2][plateau.length / 2]);
		state = State.PLAYING;
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Tuile piocher() {
		Tuile t = super.piocher();
		if (controller != null) controller.setActualTuile(t);
		return t;
	}
	

	@Override
	protected void remplirSac() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			sac.add(new TuilePreCheminGaucheBas());
		}
		
		/*for (int i = 0; i < 8; i++) {
			sac.add(new TuilePreCheminHautBas());
		}
		
		for (int i = 0; i < 5; i++) {
			sac.add(new TuileVilleHautPre());
		}
		
		for (int i = 0; i < 4; i++) {
			sac.add(new TuileCarrefourPreHaut());
			sac.add(new TuileAbbaye());
			sac.add(new TuileVilleHautCheminGaucheDroitePre());
		}
		
		for (int i = 0; i < 3; i++) {
			sac.add(new TuileVilleHautPreCheminDroiteBas());
			sac.add(new TuileVilleGaucheHautPreCheminDroiteBas());
			sac.add(new TuileVilleHautCarrefour());
			sac.add(new TuileVillePreBas());
			sac.add(new TuileVilleHautGauchePre());
			sac.add(new TuileVilleHautCheminGaucheBasPre());
			sac.add(new TuileVilleGaucheDroitePre());
		}
		
		for (int i = 0; i < 2; i++) {
			sac.add(new TuileBouclierVilleGaucheHautPreCheminDroiteBas());
			sac.add(new TuileVilleHautDroitePre());
			sac.add(new TuileAbbayeCheminBas());
			sac.add(new TuileBouclierVillePreHautBas());
			sac.add(new TuileBouclierVilleCheminBas());
			sac.add(new TuileBouclierVilleHautGauchePre());
		}
		
		sac.add(new TuileVilleCheminBas());
		sac.add(new TuileBouclierVillePreBas());
		sac.add(new TuileVillePreHautBas());
		sac.add(new TuileBouclierVille());
		sac.add(new TuileCarrefour());*/
	}

	@Override
	public int[] placeIA(Tuile t, Player p) {
		// TODO Auto-generated method stub
		return null;
	}
}
