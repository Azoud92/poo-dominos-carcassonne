package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CoteLimitePrePre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CoteLimiteQuartierLimite;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CoteQuartierQuartierLimite;

public class TuileBouclierVillePreHautBas extends TuileCarcassonne {

	public TuileBouclierVillePreHautBas() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'une limite, d'un pré, d'un pré
		tuile[0] = new CoteLimitePrePre().getCote();

		// La droite contient une limite, un quartier, une limite
		tuile[1] = new CoteLimiteQuartierLimite().getCote();

		// Le bas contient un pré, un pré, un pré
		tuile[2] = new CotePre().getCote();

		// La gauche contient un quartier, un quartier, une limite
		tuile[3] = new CoteQuartierQuartierLimite().getCote();
	}

}
