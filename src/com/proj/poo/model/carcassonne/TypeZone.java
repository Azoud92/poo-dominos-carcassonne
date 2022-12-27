package com.proj.poo.model.carcassonne;

public enum TypeZone {

	// Indice entre 0 et 5 : représente un objet spécial (situé au centre)
	/* Sinon, les objets dont la valeur se suit sont "liés" (une limite de ville ne peut pas être acollée 
	 * à un pré par exemple, sinon ce n'est pas cohérent).
	 */
	ABBAYE(10),
	CARREFOUR(15),
	QUARTIER_VILLE(20),
	LIMITE_VILLE(21),	
	PRE(30),
	CHEMIN(40);

	private final int val;
	private TypeZone(int val) {
		// TODO Auto-generated constructor stub
		this.val = val;
	}
	
	public int getVal() {
		return val;
	}
}
