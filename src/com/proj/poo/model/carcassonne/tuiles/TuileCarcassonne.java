package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.ZoneTuile;

public abstract class TuileCarcassonne extends Tuile {

	private Centre centre;
	
	public TuileCarcassonne() {
		super(3);
	}
	
	public Centre getCentre() {
		return centre;
	}
	
	public void setCentre(Centre centre) { this.centre = centre; }

	public class Centre {
		private ZoneTuile zt_centre;
		
		public Centre(ZoneTuile zt_centre) {
			this.zt_centre = zt_centre;
		}

		public ZoneTuile getZt_centre() {
			return zt_centre;
		}
	}

	

}
