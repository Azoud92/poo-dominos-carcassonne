package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CoteLimiteQuartierLimite;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;

public class TuileVilleGaucheDroitePre extends TuileCarcassonne {

	public TuileVilleGaucheDroitePre() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un pré, d'un pré, d'un pré
		tuile[0] = new CotePre().getCote();

		// La droite contient une limite, un quartier, une limite
		tuile[1] = new CoteLimiteQuartierLimite().getCote();

		// Le bas contient un pré, un pré, et un pré
		tuile[2] = new CotePre().getCote();

		// La gauche contient une limite, un quartier, une limite
		tuile[3] = new CoteLimiteQuartierLimite().getCote();
	}

}
