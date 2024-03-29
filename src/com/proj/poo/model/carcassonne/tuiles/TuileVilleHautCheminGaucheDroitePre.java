package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CoteLimiteQuartierLimite;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;

public class TuileVilleHautCheminGaucheDroitePre extends TuileCarcassonne {

	public TuileVilleHautCheminGaucheDroitePre() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'une limite, d'un quartier, d'une limite
		tuile[0] = new CoteLimiteQuartierLimite().getCote();

		// La droite contient un pré, un chemin, un pré
		tuile[1] = new CotePreCheminPre().getCote();

		// Le bas contient un pré, un pré, et un pré
		tuile[2] = new CotePre().getCote();

		// La gauche contient un pré, un chemin, un pré
		tuile[3] = new CotePreCheminPre().getCote();
	}

}
