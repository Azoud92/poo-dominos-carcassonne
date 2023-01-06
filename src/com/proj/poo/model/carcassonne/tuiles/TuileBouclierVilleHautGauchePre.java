package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CoteQuartierQuartierLimite;

public class TuileBouclierVilleHautGauchePre extends TuileCarcassonne {

	public TuileBouclierVilleHautGauchePre() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, d'une limite
		tuile[0] = new CoteQuartierQuartierLimite().getCote();

		// La droite contient un pré, un pré, un pré
		tuile[1] = new CotePre().getCote();

		// Le bas contient un pré, un pré, et un pré
		tuile[2] = new CotePre().getCote();

		// La gauche contient un quartier, un quartier, une limite
		tuile[3] = new CoteQuartierQuartierLimite().getCote();
	}

}
