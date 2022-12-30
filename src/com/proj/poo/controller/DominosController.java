package com.proj.poo.controller;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.model.dominos.PlayerDominos;
import com.proj.poo.model.dominos.TuileDominos;
import com.proj.poo.view.DominosView;

public class DominosController {

	private Dominos party;
	private DominosView view;

	private TuileDominos actualTuile;
		
	public void tour() {
		
		if (party.getActualPlayer() instanceof IADominos) {
			party.piocher();
			int[] xy = party.placeIA(actualTuile, party.getActualPlayer());
			if (xy != null) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.placeTuile(xy[0], xy[1]);
				party.placeTuile(xy[0], xy[1], actualTuile, party.getActualPlayer());
				view.getControle().paint();
				
			}
			actualTuile = null;
			view.getControle().tour();
		}
		
	}
	
	public void haut() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
			try {
				if (party.getPlateau()[tuileV.getTx()][tuileV.getTy() - 1] == null) {
					tuileV.setLocation(tuileV.getX(),tuileV.getY() - ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuileV.getTy() - 1);
					System.out.println("tuile x: " + tuileV.getX() + "  tuile y : " + tuileV.getY());	
					System.out.println("tuile x: " + tuileV.getTx() + "  tuile y : " + tuileV.getTy());	
				}
				else{System.out.println(party.getPlateau()[tuileV.getTx()][tuileV.getTy() - 1].toString());}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				tuileV.setLocation(tuileV.getX(),(view.getSizeView().height / getPlateauLength()) * (getPlateauLength() - 1));
				tuileV.setTy(tuileV.getY() / (view.getSizeView().height / getPlateauLength()));
				System.out.println("tuile x: " + tuileV.getX() + "  tuile y : " + tuileV.getY());	
				System.out.println("tuile x: " + tuileV.getTx() + "  tuile y : " + tuileV.getTy());
			}
		}
	}
	
	public void bas() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
			try {
				if (party.getPlateau()[tuileV.getTx()][tuileV.getTy() + 1] == null) {
					tuileV.setLocation(tuileV.getX(), tuileV.getY() + ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuileV.getTy() + 1);
					System.out.println("tuile x: " + tuileV.getX() + "  tuile y : " + tuileV.getY());	
					System.out.println("tuile x: " + tuileV.getTx() + "  tuile y : " + tuileV.getTy());	
				}
				else {System.out.println(party.getPlateau()[tuileV.getTx()][tuileV.getTy() + 1].toString());}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				tuileV.setLocation(tuileV.getX(), 0);
				tuileV.setTy(0);
				System.out.println("tuile x: " + tuileV.getX()+"  tuile y : " + tuileV.getY());	
				System.out.println("tuile x: " + tuileV.getTx()+"  tuile y : " + tuileV.getTy());
			}
		}
	}
	
	public void gauche() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
			try {
				if (party.getPlateau()[tuileV.getTx() - 1][tuileV.getTy()] == null) {
					tuileV.setLocation(tuileV.getX() - ((view.getSizeView().height / getPlateauLength())), tuileV.getY());
					tuileV.setTx(tuileV.getTx() - 1);
					System.out.println("tuile x: " + tuileV.getX()+"  tuile y : " + tuileV.getY());	
					System.out.println("tuile x: " + tuileV.getTx()+"  tuile y : " + tuileV.getTy());	
				}
				else {System.out.println(party.getPlateau()[tuileV.getTx() - 1][tuileV.getTy()].toString());}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				tuileV.setLocation((view.getSizeView().height / getPlateauLength()) * (getPlateauLength() - 1), tuileV.getY());
				tuileV.setTx(tuileV.getX() / (view.getSizeView().height / getPlateauLength()));
				System.out.println("tuile x: " + tuileV.getX() + "  tuile y : " + tuileV.getY());	
				System.out.println("tuile x: " + tuileV.getTx() + "  tuile y : " + tuileV.getTy());
			}
		}
	}
	
	public void droite() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
			try {
				if (party.getPlateau()[tuileV.getTx() + 1][tuileV.getTy()] == null) {
					tuileV.setLocation(tuileV.getX() + ((view.getSizeView().height / getPlateauLength())), tuileV.getY());
					tuileV.setTx(tuileV.getTx() + 1);
					System.out.println("tuile x: " + tuileV.getX() + "  tuile y : " + tuileV.getY());	
					System.out.println("tuile x: " + tuileV.getTx() + "  tuile y : " + tuileV.getTy());
				}
				else {System.out.println(party.getPlateau()[tuileV.getTx() + 1][tuileV.getTy()].toString());}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				tuileV.setLocation(0, tuileV.getY());
				tuileV.setTx(0);
				System.out.println("tuile x: " + tuileV.getX() + "  tuile y : " + tuileV.getY());	
				System.out.println("tuile x: " + tuileV.getTx() + "  tuile y : " + tuileV.getTy());
			}
		}
	}
	
	public void abandon() {
		party.abandon(party.getActualPlayer());
	}

	public boolean placeTuile(int x, int y) {
		if (party.isLegalPlacement(x, y, actualTuile)) {
			System.out.println("true");
			party.placeTuile(x, y, actualTuile, party.getActualPlayer());
			return true;
		}
		return false;
	}
	
	public DominosView.TuileView piocheTuile() {
		if(party.getPlateau()[0][0] == null) {
			return view.new TuileView(0, 0);
		}
		else {
			int place_x = 0;
			int place_y = 0;
			while(party.getPlateau()[place_y][place_x] != null) {
				if (place_x + 1 == party.getPlateau()[place_y].length) {
					place_x = 0;
					place_y++;
				}
				party.piocher();
				return view.new TuileView(place_x, place_y);

			}
		}
		return null;
	}

	public String getHautValue(int pos) {
		return " " + actualTuile.getHaut()[pos].getValue();
	}

	public String getRightValue(int pos) {
		return "" + actualTuile.getDroite()[pos].getValue();
	}

	public String getBottomValue(int pos) {
		return " " + actualTuile.getBas()[pos].getValue();
	}

	public String getLeftValue(int pos) {
		return "  " + actualTuile.getGauche()[pos].getValue();
	}

	public void rotationTuile() {
		actualTuile.rotation();
	}

	public void setPlayerToPlay(PlayerDominos p) {
		view.getControle().setPseudoLabelText(p.pseudo);
		view.getControle().setPointsLabelText(String.valueOf(p.getPoints()));
	}

	public int getPlateauLength() {
		return party.getPlateau().length;
	}

	public void setParty(Dominos party) { this.party = party; }
	public void setView(DominosView view) { 
		this.view = view; 
		view.placeTuile(getPlateauLength() / 2, getPlateauLength() / 2); 
		party.play(); }

	public void setWinner(PlayerDominos p) {
		// TODO Auto-generated method stub
		view.getControle().winner(p.pseudo, p.getPoints());
	}

	public void setActualTuile(Tuile tuile) {
		// TODO Auto-generated method stub
		this.actualTuile = (TuileDominos) tuile;
	}

}
