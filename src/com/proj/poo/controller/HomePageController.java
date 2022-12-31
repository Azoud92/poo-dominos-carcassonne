package com.proj.poo.controller;

import java.awt.Dimension;

import com.proj.poo.model.Game;
import com.proj.poo.model.GameType;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.model.dominos.PlayerDominos;
import com.proj.poo.view.DominosView;

public class HomePageController {

	private Game party;
	
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
	
	public DominosView runParty(Dimension size, double scaleX, double scaleY) {
		DominosView dv = new DominosView(size, scaleX, scaleY, dominosController);
		dominosController.setView(dv);
		return dv;
	}
	
	private void createParty(GameType gt) {		
		if (gt == GameType.DOMINOS) {
			dominosController = new DominosController();
			party = new Dominos(30, null, dominosController);
			dominosController.setParty((Dominos) party);
		}
		
	}

	public Dominos getDominosParty() {
		// TODO Auto-generated method stub
		return (Dominos) party;
	}
	
}
