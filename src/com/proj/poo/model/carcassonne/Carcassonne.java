package com.proj.poo.model.carcassonne;

import java.util.ArrayList;
import com.proj.poo.controller.CarcassonneController;
import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.State;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.tuiles.TuileAbbaye;
import com.proj.poo.model.carcassonne.tuiles.TuileAbbayeCheminBas;
import com.proj.poo.model.carcassonne.tuiles.TuileBouclierVille;
import com.proj.poo.model.carcassonne.tuiles.TuileBouclierVilleCheminBas;
import com.proj.poo.model.carcassonne.tuiles.TuileBouclierVilleGaucheHautPreCheminDroiteBas;
import com.proj.poo.model.carcassonne.tuiles.TuileBouclierVilleHautGauchePre;
import com.proj.poo.model.carcassonne.tuiles.TuileBouclierVillePreBas;
import com.proj.poo.model.carcassonne.tuiles.TuileBouclierVillePreHautBas;
import com.proj.poo.model.carcassonne.tuiles.TuileCarrefour;
import com.proj.poo.model.carcassonne.tuiles.TuileCarrefourPreHaut;
import com.proj.poo.model.carcassonne.tuiles.TuilePreCheminGaucheBas;
import com.proj.poo.model.carcassonne.tuiles.TuilePreCheminHautBas;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleCheminBas;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleGaucheDroitePre;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleGaucheHautPreCheminDroiteBas;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautCarrefour;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautCheminGaucheBasPre;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautCheminGaucheDroitePre;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautDroitePre;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautGauchePre;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautPre;
import com.proj.poo.model.carcassonne.tuiles.TuileVilleHautPreCheminDroiteBas;
import com.proj.poo.model.carcassonne.tuiles.TuileVillePreBas;
import com.proj.poo.model.carcassonne.tuiles.TuileVillePreHautBas;


public class Carcassonne extends Game{
	
private CarcassonneController controller;
	
	public Carcassonne(ArrayList<Player> p, CarcassonneController controller) {		
		super(72, p);
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
		for (int i = 0; i < 9; i++) {
			sac.add(new TuilePreCheminGaucheBas());
		}
		
		for (int i = 0; i < 8; i++) {
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
		sac.add(new TuileCarrefour());
	}

	@Override
	public int[] placeIA(Tuile t, Player p) {
		// TODO Auto-generated method stub
		return null;
	}


}
