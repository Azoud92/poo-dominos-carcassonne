package com.proj.poo.controller;

import java.awt.Color; 

import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.Carcassonne;
import com.proj.poo.model.carcassonne.IACarcassonne;
import com.proj.poo.model.carcassonne.PlayerCarcassonne;
import com.proj.poo.model.carcassonne.tuiles.TuileCarcassonne;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.view.CarcassonneView;
import com.proj.poo.view.CarcassonneView.Circle;
import com.proj.poo.view.CarcassonneView.TuileView;

public class CarcassonneController {
	
	private Carcassonne party;
	private CarcassonneView view;
	
	private TuileCarcassonne actualTuile;

	
	public void setParty(Carcassonne party) { this.party = party; }


	public void setView(CarcassonneView view) { 
		this.view = view; 
		view.placeTuile(getPlateauLength() / 2, getPlateauLength() / 2);
		party.play();
		}


	public void setActualTuile(Tuile tuile) {
		this.actualTuile = (TuileCarcassonne) tuile;
	}
	
	
	public boolean tour() {
		boolean b = party.passerTour();
		if (!b) {
			view.getControle().finPartie();

		}
		else {
			if (party.getActualPlayer() instanceof IACarcassonne) {
				party.piocher();
				int[] xy = party.placeIA(actualTuile, party.getActualPlayer());
				if (xy != null) {
					try {
						view.getControle().paint();
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
			else {setPlayerToPlay((PlayerCarcassonne) party.getPlayers().get(party.getTour()));}
		}
		
		return b;
		
	}
	
	public void haut() {
		TuileView tuileV = view.getControle().getTuileV();
		if (view.getPartisanAct()==null && tuileV!=null) {
			try {
				if (party.getPlateau()[tuileV.getTx()][tuileV.getTy() - 1] == null) {
					tuileV.setLocation(tuileV.getX(),tuileV.getY() - ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuileV.getTy() - 1);

				}
				else if (party.getPlateau()[tuileV.getTx()][tuileV.getTy() - 1] != null) {
					int tuile_y = tuileV.getTy() - 1;
					while(party.getPlateau()[tuileV.getTx()][tuile_y] != null) {
						if (tuile_y==tuileV.getTy()) {
							break;
							}
						tuile_y--;
					}
					tuileV.setLocation(tuileV.getX(),tuile_y * ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuile_y);
					
					
				}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				if (party.getPlateau()[tuileV.getTx()][party.getPlateau()[tuileV.getTx()].length-1] == null) {
					tuileV.setLocation(tuileV.getX(),(view.getSizeView().height / getPlateauLength()) * (getPlateauLength() - 1));
					tuileV.setTy(tuileV.getY() / (view.getSizeView().height / getPlateauLength()));

				}
				else if (party.getPlateau()[tuileV.getTx()][party.getPlateau()[tuileV.getTx()].length-1] != null) {
					int tuile_y = party.getPlateau()[tuileV.getTx()].length-1;
					while(party.getPlateau()[tuileV.getTx()][tuile_y] != null) {
						if (tuile_y==tuileV.getTy()) {
							break;
							}
						tuile_y--;
					}
					tuileV.setLocation(tuileV.getX(),tuile_y * ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuile_y);
					
					
				}
			}
		}
		else {
			Circle c  = view.getPartisanAct();
			if (c != null) {
				if(c.getY() != tuileV.getY()) {
					c.deplace(c.getX(), c.getY() - view.getTailleTuile()/2 + view.getTailleTuile()/5/2);
				}
			}
			
		}
		
	}
	
	public void bas() {
		TuileView tuileV = view.getControle().getTuileV();
		if (view.getPartisanAct()==null && tuileV!=null) {
			try {
				if (party.getPlateau()[tuileV.getTx()][tuileV.getTy() + 1] == null) {
					tuileV.setLocation(tuileV.getX(), tuileV.getY() + ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuileV.getTy() + 1);
	
				}
				else if (party.getPlateau()[tuileV.getTx()][tuileV.getTy() + 1] != null) {
					int tuile_y=tuileV.getTy()+1;
					while(party.getPlateau()[tuileV.getTx()][tuile_y] != null) {
						if (tuile_y==tuileV.getTy()) {
							break;
							}
						tuile_y++;
					}
					tuileV.setLocation(tuileV.getX(),tuile_y * (view.getSizeView().height / getPlateauLength()));
					tuileV.setTy(tuile_y);
					
					
				}
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				if(party.getPlateau()[tuileV.getTx()][0] == null) {
					tuileV.setLocation(tuileV.getX(), 0);
					tuileV.setTy(0);

				}
				else if (party.getPlateau()[tuileV.getTx()][0] != null) {
					int tuile_y = 0;
					while(party.getPlateau()[tuileV.getTx()][tuile_y] != null) {
						if (tuile_y==tuileV.getTy()) {
							break;
							}
						tuile_y++;
					}
					tuileV.setLocation(tuileV.getX(),tuile_y * ((view.getSizeView().height / getPlateauLength())));
					tuileV.setTy(tuile_y);
					
					
				}
			}
		}
		else {
			Circle c  = view.getPartisanAct();
			if (c != null) {
				if(c.getY() != tuileV.getY() + view.getTailleTuile() - view.getTailleTuile()/5) {
					c.deplace(c.getX(), c.getY() + view.getTailleTuile()/2 - view.getTailleTuile()/5/2);
				}
			}
			
		}
	}
	
	public void gauche() {
		TuileView tuileV = view.getControle().getTuileV();
		if (view.getPartisanAct()==null && tuileV!=null) {
			try {
				if (party.getPlateau()[tuileV.getTx() - 1][tuileV.getTy()] == null) {
					tuileV.setLocation(tuileV.getX() - ((view.getSizeView().height / getPlateauLength())), tuileV.getY());
					tuileV.setTx(tuileV.getTx() - 1);

				}
				else if (party.getPlateau()[tuileV.getTx()-1][tuileV.getTy()] != null) {
					int tuile_x=tuileV.getTx()-1;
					while(party.getPlateau()[tuile_x][tuileV.getTy()] != null) {
						if (tuile_x==tuileV.getTx()) {
							break;
							}
						tuile_x--;
					}
					tuileV.setLocation(tuile_x * (view.getSizeView().height / getPlateauLength()),tuileV.getY());
					tuileV.setTx(tuile_x);
					
					
				}

			}
			catch (ArrayIndexOutOfBoundsException e1) {
				if (party.getPlateau()[getPlateauLength()-1][tuileV.getTy()] == null) {
					tuileV.setLocation((view.getSizeView().height / getPlateauLength()) * (getPlateauLength() - 1), tuileV.getY());
					tuileV.setTx(tuileV.getX() / (view.getSizeView().height / getPlateauLength()));

				}
				else if (party.getPlateau()[getPlateauLength()-1][tuileV.getTy()] != null) {
					int tuile_x = getPlateauLength()-1;
					while(party.getPlateau()[tuile_x][tuileV.getTy()] != null) {
						if (tuile_x==tuileV.getTx()) {
							break;
							}
						tuile_x--;
					}
					tuileV.setLocation(tuile_x * (view.getSizeView().height / getPlateauLength()),tuileV.getY());
					tuileV.setTx(tuile_x);
					
					
				}
			}
			
		}
		else {
			Circle c  = view.getPartisanAct();
			if (c != null) {
				if(c.getX() != tuileV.getX()) {
					c.deplace(c.getX() - view.getTailleTuile()/2 + view.getTailleTuile()/5/2, c.getY());
				}
			}
			
		}
	}
	
	public void droite() {
		TuileView tuileV = view.getControle().getTuileV();
		if (view.getPartisanAct()==null && tuileV!=null) {
			try {
				if (party.getPlateau()[tuileV.getTx() + 1][tuileV.getTy()] == null) {
					tuileV.setLocation(tuileV.getX() + ((view.getSizeView().height / getPlateauLength())), tuileV.getY());
					tuileV.setTx(tuileV.getTx() + 1);

				}
				else if (party.getPlateau()[tuileV.getTx()+1][tuileV.getTy()] != null) {
					int tuile_x=tuileV.getTx()+1;
					while(party.getPlateau()[tuile_x][tuileV.getTy()] != null) {
						if (tuile_x==tuileV.getTx()) {
							break;
							}
						tuile_x++;
					}
					tuileV.setLocation(tuile_x * (view.getSizeView().height / getPlateauLength()),tuileV.getY());
					tuileV.setTx(tuile_x);
					}

			}
			catch (ArrayIndexOutOfBoundsException e1) {
				if(party.getPlateau()[0][tuileV.getTy()] == null) {
					tuileV.setLocation(0, tuileV.getY());
					tuileV.setTx(0);
				}
				else if (party.getPlateau()[0][tuileV.getTy()] != null) {
					int tuile_x = 0;
					while(party.getPlateau()[tuile_x][tuileV.getTy()] != null) {
						if (tuile_x==tuileV.getTx()) {
							break;
							}
						tuile_x++;
					}
					tuileV.setLocation(tuile_x * (view.getSizeView().height / getPlateauLength()),tuileV.getY());
					tuileV.setTx(tuile_x);
					
					
				}
			}
		}
		else {
			Circle c  = view.getPartisanAct();
			if (c != null) {
				if(c.getX() != tuileV.getX() + view.getTailleTuile() - view.getTailleTuile()/5) {
					c.deplace(c.getX() + view.getTailleTuile()/2 - view.getTailleTuile()/5/2, c.getY());
				}
			}
			
		}
	}
	
	public Color partisanColor() {
		switch(((PlayerCarcassonne) party.getActualPlayer()).getPartisans().get(0).getColor()) {
		case JAUNE : return Color.YELLOW;
		case BLEU : return Color.BLUE;
		case ROUGE : return Color.RED;
		case VERT : return Color.GREEN;
		}
		return null;
	}
	
	public void abandon() {
		party.abandon(party.getActualPlayer());
	}
	
	
	public int getPlateauLength() {
		return party.getPlateau().length;
	}
		
	public TuileCarcassonne getActualTuile() {
		return actualTuile;
	}
	
	public CarcassonneView.TuileView piocheTuile() {
		if(party.getPlateau()[0][0] == null) {
			party.piocher();
			return view.placeTuile(0,0);
		}
		else {
			int place_x = 0;
			int place_y = 0;
			while(party.getPlateau()[place_x][place_y] != null) { //on suppose qu'il y a forcement une case libre dans le plateau
				if (place_x + 1 == party.getPlateau()[place_y].length) {
					place_x = 0;
					place_y++;
				}
				place_x++;
			}
			party.piocher();
			return view.placeTuile(place_x,place_y);
		}
	}
	
	public boolean placeTuile(int x, int y) {
		if (party.isLegalPlacement(x, y, actualTuile)) {
			party.placeTuile(x, y, actualTuile, party.getActualPlayer());
			setPlayerToPlay((PlayerCarcassonne) party.getPlayers().get(party.getTour()));
			return true;
		}
		return false;
	}
	
	public void setPlayerToPlay(PlayerCarcassonne p) {
		view.getControle().setPseudoLabelText(p.pseudo);
	}
	
	public void rotationTuile() {
		actualTuile.rotation();
	}

}
