package com.proj.poo.model.carcassonne;

public enum TypeZone {

	ABBAYE(0),
	CARREFOUR(1),
	QUARTIER_VILLE(2),
	LIMITE_VILLE(3),	
	PRE(4),
	CHEMIN(5);

	private final int val;
	private TypeZone(int val) {
		// TODO Auto-generated constructor stub
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
}
