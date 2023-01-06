package com.proj.poo.model.carcassonne;

public enum TypeZone {

	ABBAYE(10),
	CARREFOUR(15),
	QUARTIER_VILLE(20),
	LIMITE_VILLE(25),	
	PRE(30),
	CHEMIN(35);

	private final int val;
	private TypeZone(int val) {
		// TODO Auto-generated constructor stub
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
}
