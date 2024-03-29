package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CoteLimiteQuartierLimite;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneCarrefour;

public class TuileVilleHautCarrefour extends TuileCarcassonne {

	public TuileVilleHautCarrefour() {
		super();
		setCentre(new Centre(new ZoneCarrefour()));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'une limite, d'un quartier, d'une limite
		tuile[0] = new CoteLimiteQuartierLimite().getCote();

		// La droite contient un pré, un chemin, un pré
		tuile[1] = new CotePreCheminPre().getCote();

		// Le bas contient un pré, un chemin, un pré
		tuile[2] = new CotePreCheminPre().getCote();

		// La gauche contient un pré, un chemin, un pré
		tuile[3] = new CotePreCheminPre().getCote();
	}

}
