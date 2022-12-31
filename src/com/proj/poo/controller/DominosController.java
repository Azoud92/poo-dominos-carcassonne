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
		
	public boolean tour() {
		boolean b=party.passerTour();
		if (!b) {
			PlayerDominos winner= party.finPartie();
			setWinner(winner);

		}
		else {
			if (party.getActualPlayer() instanceof IADominos) {
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
		}
		return b;
		
	}
	
	public boolean placeTuile(int x, int y) {
		if (party.isLegalPlacement(x, y, actualTuile)) {
			party.placeTuile(x, y, actualTuile, party.getActualPlayer());
			setPlayerToPlay((PlayerDominos) party.getPlayers().get(party.getTour()));
			return true;
		}
		return false;
	}
	
	public void haut() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
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
	}
	
	public void bas() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
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
	}
	
	public void gauche() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
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
	}
	
	public void droite() {
		DominosView.TuileView tuileV = view.getControle().getTuileV();
		if (tuileV != null) {
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
	}
	
	public void abandon() {
		party.abandon(party.getActualPlayer());
	}

	
	
	public DominosView.TuileView piocheTuile() {
		if(party.getPlateau()[0][0] == null) {
			party.piocher();
			return view.new TuileView(0, 0);
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
			return view.new TuileView(place_x, place_y);
			
		}
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
		party.play();
		}

	public void setWinner(PlayerDominos p) {
		// TODO Auto-generated method stub
		if (p != null) {
			view.getControle().winner(p.pseudo, p.getPoints());
		}
		else {
			view.getControle().winner(null, -1);
		}
		
	}

	public void setActualTuile(Tuile tuile) {
		// TODO Auto-generated method stub
		this.actualTuile = (TuileDominos) tuile;
	}

}
