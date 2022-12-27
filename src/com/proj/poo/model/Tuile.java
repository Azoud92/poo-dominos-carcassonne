package com.proj.poo.model;

public abstract class Tuile {

	protected ZoneTuile[][] tuile;
	protected final int nbZones;
	
	public Tuile(int nbZones) {
		this.nbZones = nbZones;
		tuile = new ZoneTuile[4][nbZones]; // une tuile possède quatre côtés car elle est carrée
		
		remplirTuile();
	}
	
	public abstract void remplirTuile();
	
	public final void rotation() {
		ZoneTuile[][] rotated = { {tuile[3][2], tuile[3][1], tuile[3][0] },
				tuile[0],
				{ tuile[1][2], tuile[1][1], tuile[1][0] },
				tuile[2] };
		tuile = rotated;
	}
	
	public final ZoneTuile[] getHaut() { return tuile[0]; }	
	public final ZoneTuile[] getDroite() { return tuile[1]; }	
	public final ZoneTuile[] getBas() {	return tuile[2]; }	
	public final ZoneTuile[] getGauche() { return tuile[3]; }
	
	public final boolean topEquals(ZoneTuile[] zt) {
		// TODO Auto-generated method stub
		if (zt.length != tuile[0].length) return false;
		
		for (int i = 0; i < tuile[0].length; i++) {
			if (tuile[0][i].getValue() != zt[i].getValue()) return false;
		}
		
		return true;
	}

	public final boolean rightEquals(ZoneTuile[] zt) {
		// TODO Auto-generated method stub
		if (zt.length != tuile[1].length) return false;
		
		for (int i = 0; i < tuile[1].length; i++) {
			if (tuile[1][i].getValue() != zt[i].getValue()) return false;
		}
		
		return true;
	}

	public final boolean bottomEquals(ZoneTuile[] zt) {
		// TODO Auto-generated method stub
		if (zt.length != tuile[2].length) return false;

		for (int i = 0; i < tuile[2].length; i++) {
			if (tuile[2][i].getValue() != zt[i].getValue()) return false;
		}

		return true;
	}

	public final boolean leftEquals(ZoneTuile[] zt) {
		// TODO Auto-generated method stub
		if (zt.length != tuile[3].length) return false;

		for (int i = 0; i < tuile[3].length; i++) {
			if (tuile[3][i].getValue() != zt[i].getValue()) return false;
		}

		return true;
	}
	
}
