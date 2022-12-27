package com.proj.poo.controller;

import com.proj.poo.model.Game;
import com.proj.poo.model.GameType;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.model.dominos.PlayerDominos;
import com.proj.poo.view.HomePageView;

public class HomePageController {

	HomePageView view;
	Game party;
	
	public void setView(HomePageView hpv) {
		view = hpv;
	}
	
	public void addDominosPlayer(String pseudo, int id, boolean isIA) {
		if (party == null) createParty(GameType.DOMINOS);
		if (!isIA) {
			party.addPlayer(new PlayerDominos(pseudo, id, party));
		}
		else {
			party.addPlayer(new IADominos(pseudo, id, party));
		}		
	}
	
	private void createParty(GameType gt) {
		if (gt == GameType.DOMINOS) party = new Dominos(50, null);
	}

	public Dominos getDominosParty() {
		// TODO Auto-generated method stub
		return (Dominos) party;
	}
	
}
