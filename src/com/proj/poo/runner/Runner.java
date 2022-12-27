package com.proj.poo.runner;

import com.proj.poo.model.dominos.console.DominosConsole;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Dominos d1 = new Dominos();
		//d1.gameStart();
		
		//@SuppressWarnings("unused")
		//GameView gw = new GameView();
		
		DominosConsole dc = new DominosConsole(50);
		dc.gameStart();
	}

}
