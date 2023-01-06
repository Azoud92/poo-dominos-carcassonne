package com.proj.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.proj.poo.controller.DominosController;
import com.proj.poo.controller.GameController;

public class DominosView extends GameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 607256897312570488L;

	public DominosView(Dimension size, double scaleX, double scaleY, GameController controller) {
		super(size, scaleX, scaleY, controller);
		controle = new Controle();
		this.add(controle,BorderLayout.EAST);
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TuileView placeTuile(int x, int y, int r) {
		TuileView t= new TuileView(x, y);
		t.setBackground(Color.PINK);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());
		return null;
	}
	
	public TuileView initTuile(int x, int y) {
		TuileView t = new TuileView(x, y);
		game.add(t);
		game.repaint();
		game.paintComponents(game.getGraphics());
		return t;
	}
	
	public class TuileView extends GameView.TuileView {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2593965992186915203L;

		int tailleTuile = (size.height / controller.getPlateauLength());
		
		public TuileView(int x, int y) {
			super(x, y);
			this.setBounds(x * tailleTuile, y * tailleTuile, tailleTuile, tailleTuile);
			this.setLayout(new GridLayout(5,5,0,0));
			this.setBackground(Color.CYAN);
			creation();
		}
		
		private void creation() {

			JLabel c1 = new JLabel(" ");
			JLabel c2 = new JLabel(" ");
			JLabel c3 = new JLabel(" ");
			JLabel c4 = new JLabel(" ");

			this.add(c1);
			for (int i = 0; i < 3;i++) {
				JLabel label_h = new JLabel(((DominosController) controller).getHautValue(i));
				label_h.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_h);
			}
			this.add(c2);

			for (int i = 0; i < 3;i++) {
				JLabel label_g = new JLabel(((DominosController) controller).getLeftValue(i));
				label_g.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_g);

				JLabel space1 = new JLabel(" ");
				this.add(space1);
				JLabel space2 = new JLabel(" ");
				this.add(space2);
				JLabel space3 = new JLabel(" ");
				this.add(space3);

				JLabel label_d = new JLabel(((DominosController) controller).getRightValue(i));
				label_d.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_d);
			}

			this.add(c3);
			for (int i = 0; i < 3;i++) {
				JLabel label_b = new JLabel(((DominosController) controller).getBottomValue(i));
				label_b.setFont(new Font("Arial", Font.BOLD, (size.height / controller.getPlateauLength()) / 7));
				this.add(label_b);
			}
			this.add(c4);
		}

		@Override
		public void tuileRotation() {
			this.removeAll();
			controller.rotationTuile();
			creation();
			game.paintComponents(game.getGraphics());
		}
		
	}
	
	public class Controle extends GameView.Controle {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7781182297179951040L;

		JLabel points;
		
		public Controle() {
			super();
			
			points = new JLabel("Nombre de points du joueur : ");
			points.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			points.setForeground(new Color(0, 128, 255));
			info.add(points);
		}
		
		@Override
		protected void piocheBtnAction() {
			tuileV = controller.piocheTuile();
			
			game.add(tuileV);
			game.paintComponents(game.getGraphics());
			piocheBtn.setEnabled(false);
			poserBtn.setEnabled(true);
			rotationBtn.setEnabled(true);
			defausserBtn.setEnabled(true);
		}

		@Override
		protected void poserBtnAction() {
			int x = tuileV.getTx();
			int y = tuileV.getTy();

			
			if (controller.placeTuile(x, y) == true) {
				game.remove(tuileV);
				tuileV = null;
				placeTuile(x, y, 0);
				controller.setActualTuile(null);
				communication.setText("Bien jou�, vous avez r�ussi � placer votre tuile");
				info.add(communication);
				this.repaint();
				this.paintComponents(this.getGraphics());
				poserBtn.setEnabled(false);
				rotationBtn.setEnabled(false);
				defausserBtn.setEnabled(false);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				tour();
			}
		}

		@Override
		protected void rotationBtnAction() {
			// TODO Auto-generated method stub
			tuileV.tuileRotation();
		}

		@Override
		protected void defausserBtnAction() {
			// TODO Auto-generated method stub
			game.remove(tuileV);
			game.repaint();
			game.paintComponents(game.getGraphics());
			poserBtn.setEnabled(false);
			rotationBtn.setEnabled(false);
			defausserBtn.setEnabled(false);
			tour();
		}

		@Override
		protected void abandonBtnAction() {
			// TODO Auto-generated method stub
			controller.abandon();
			if (tuileV !=null) {
				game.remove(tuileV);
				game.repaint();
				game.paintComponents(game.getGraphics());
				}
			
			
			poserBtn.setEnabled(false);
			rotationBtn.setEnabled(false);
			defausserBtn.setEnabled(false);
			tour();
		}

		@Override
		public void finPartie() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
		public void winner(String pseudo, int pts) {
			info.remove(name);
			info.remove(points);
			abandonBtn.setEnabled(false);
			piocheBtn.setEnabled(false);
			if (pseudo != null && pts != -1) {
				communication.setText("Bravo � " + pseudo + " qui gagn� la partie avec " + pts + " points.");
				communication.setVisible(true);
				info.add(communication);
				communication.paintComponents(communication.getGraphics());
			}
			else{
				communication.setText("Pas de vainqueur pour cette partie, match nul !");
				communication.setVisible(true);
				info.add(communication);
			}
			JButton exitBtn = new JButton("Quitter le jeu");
			exitBtn.setFont(new Font("Arial", Font.BOLD, (int) (30 * scaleX)));
			exitBtn.setBackground(Color.RED);
			exitBtn.addActionListener(e -> {
				// action à effectuer pour fermer le programme
				System.exit(0);
			});
			info.add(exitBtn);
			this.paintComponents(this.getGraphics());
		}
		
		public void setPointsLabelText(String s) {
			points.setText("Nombre de points du joueur : " + s);
			points.paintComponents(points.getGraphics());
		}
		
	}

}
