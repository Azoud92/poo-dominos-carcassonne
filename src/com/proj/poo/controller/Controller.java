package com.proj.poo.controller;

import com.proj.poo.model.Game;
import com.proj.poo.model.Player;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.dominos.Dominos;
import com.proj.poo.model.dominos.IADominos;
import com.proj.poo.model.dominos.PlayerDominos;
import com.proj.poo.model.dominos.TuileDominos;
import com.proj.poo.view.DominosView;
import com.proj.poo.view.DominosView.TuileView;

public abstract class Controller {

	protected Game party;
	protected View view;
	protected Tuile actualTuile;
	
	
	
}
