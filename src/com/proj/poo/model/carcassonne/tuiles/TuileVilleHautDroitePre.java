package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CoteLimiteQuartierLimite;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;

public class TuileVilleHautDroitePre extends TuileCarcassonne {

	public TuileVilleHautDroitePre() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'une limite, d'un quartier, d'une limite
		tuile[0] = new CoteLimiteQuartierLimite().getCote();

		// La droite contient une limite, un quartier, une limite
		tuile[1] = new CoteLimiteQuartierLimite().getCote();

		// Le bas contient un pré, un pré, et un pré
		tuile[2] = new CotePre().getCote();

		// La gauche contient un pré, un pré, un pré
		tuile[3] = new CotePre().getCote();
	}

}
