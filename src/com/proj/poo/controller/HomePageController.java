package com.proj.poo.controller;

import java.awt.Dimension; 
import com.proj.poo.model.Game;
import com.proj.poo.model.GameType;
import com.proj.poo.model.carcassonne.Carcassonne;
import com.proj.poo.model.carcassonne.PlayerCarcassonne;
import com.proj.poo.model.carcassonne.PlayerColor;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.model.dominos.PlayerDominos;
import com.proj.poo.view.CarcassonneView;
import com.proj.poo.view.DominosView;

public class HomePageController {

	private Game party;
	private CarcassonneController carcassonneController;
	private DominosController dominosController;
		
	public void addDominosPlayer(String pseudo, int id, boolean isIA) {
		if (party == null) createParty(GameType.DOMINOS);
		if (!isIA) {
			party.addPlayer(new PlayerDominos(pseudo, id, party));
		}
		else {
			party.addPlayer(new IADominos(pseudo, id, party));
		}		
	}
	
	public void addCarcassonnePlayer(String pseudo, int id, boolean isIA) {
		if (party == null) createParty(GameType.CARCASSONNE);
		if (!isIA) {
			party.addPlayer(new PlayerCarcassonne(pseudo, id, PlayerColor.BLEU, party));
		}
		else {
			party.addPlayer(new PlayerCarcassonne(pseudo, id, PlayerColor.BLEU, party));//mettre la creation du bot carcassonne ici
		}		
	}
	
	public DominosView runDominosParty(Dimension size, double scaleX, double scaleY) {
		DominosView dv = new DominosView(size, scaleX, scaleY, dominosController);
		dominosController.setView(dv);
		return dv;
	}
	
	public CarcassonneView runCarcassonneParty(Dimension size, double scaleX, double scaleY) {
		CarcassonneView ccv = new CarcassonneView(size, scaleX, scaleY, carcassonneController);
		carcassonneController.setView(ccv);
		return ccv;
	}
	
	private void createParty(GameType gt) {		
		if (gt == GameType.DOMINOS) {
			dominosController = new DominosController();
			party = new Dominos(30, null, dominosController);
			dominosController.setParty((Dominos) party);
		}
		else if (gt == GameType.CARCASSONNE) {
			carcassonneController = new CarcassonneController();
			party = new Carcassonne(null, carcassonneController);
			carcassonneController.setParty((Carcassonne) party);
		}
		
	}

	public Dominos getDominosParty() {
		// TODO Auto-generated method stub
		return (Dominos) party;
	}
	
}
