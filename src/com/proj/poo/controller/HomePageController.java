package com.proj.poo.controller;

import java.awt.Dimension; 
import com.proj.poo.model.Game;
import com.proj.poo.model.GameType;
import com.proj.poo.model.carcassonne.Carcassonne;
import com.proj.poo.model.carcassonne.IACarcassonne;
import com.proj.poo.model.carcassonne.PlayerCarcassonne;
import com.proj.poo.model.carcassonne.PlayerColor;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.model.dominos.PlayerDominos;
import com.proj.poo.view.CarcassonneView;
import com.proj.poo.view.DominosView;

public class HomePageController {

	private Dominos party_dominos;
	private Carcassonne party_carca;
	private CarcassonneController carcassonneController;
	private DominosController dominosController;
		
	public void addDominosPlayer(String pseudo, int id, boolean isIA) {
		if (party_dominos == null) createParty(GameType.DOMINOS);
		if (!isIA) {
			party_dominos.addPlayer(new PlayerDominos(pseudo, id, party_dominos));
		}
		else {
			party_dominos.addPlayer(new IADominos(pseudo, id, party_dominos));
		}		
	}
	
	public void addCarcassonnePlayer(String pseudo, int id, boolean isIA, String color) {
		PlayerColor plColor =null;
		switch(color) {
		case "Jaune" : plColor=PlayerColor.JAUNE;break;
		case "Bleu" : plColor=PlayerColor.BLEU;break;
		case "Rouge" : plColor=PlayerColor.ROUGE;break;
		case "Vert" : plColor=PlayerColor.VERT;break;
		}
		
		if (party_carca == null) createParty(GameType.CARCASSONNE);
		if (!isIA) {
			party_carca.addPlayer(new PlayerCarcassonne(pseudo, id, plColor, party_carca));
		}
		else {
			party_carca.addPlayer(new IACarcassonne(pseudo, id, plColor, party_carca));
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
			party_dominos = new Dominos(30, null, dominosController);
			dominosController.setParty(party_dominos);
		}
		else if (gt == GameType.CARCASSONNE) {
			carcassonneController = new CarcassonneController();
			party_carca = new Carcassonne(null, carcassonneController);
			carcassonneController.setParty(party_carca);
		}
		
	}

	public Dominos getDominosParty() {
		// TODO Auto-generated method stub
		return party_dominos;
	}
	
	public Carcassonne getCarcassonneParty() {
		// TODO Auto-generated method stub
		return party_carca;
	}
	
}
