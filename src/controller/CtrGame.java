package controller;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import view.FieldSquare;
import view.GameMessages;
import view.GameSecreen;

public class CtrGame {
	private int difficulty;
	private int minesQtd;
	private int markedQtd;

	private GameSecreen gameSecreen;

	public CtrGame(GameSecreen gameSecreen, int difficulty) {
		super();
		this.gameSecreen = gameSecreen;
		this.difficulty = difficulty;
		this.minesQtd = difficulty * (difficulty / 5);
		this.markedQtd = 0;
	}

	public int getMinesQtd() {
		return minesQtd;
	}

	public int getMarkedQtd() {
		return markedQtd;
	}

	public void testWin() {
		FieldSquare[][] field = this.gameSecreen.getField();
		int correctMarked = 0;

		for (FieldSquare[] i : field)
			for (FieldSquare j : i)
				if (j.isMarked() && j.getValueSquare() < 0)
					correctMarked++;

		if (correctMarked == this.minesQtd)
			winGame();
	}

	public void openPanel(FieldSquare square) {
		if (!square.isOpen()) {
			CardLayout cl = (CardLayout) square.getLayout();

			if (square.isMarked()) {
				cl.next(square);
				square.flipMarked();
			}

			square.setOpen(true);
			cl.next(square);

			if (square.getValueSquare() == 0) {
				for (FieldSquare i : square.getVizinhos()) {
					if (i.getValueSquare() >= 0) {
						openPanel(i);
					}
				}
			}
		}
	}

	public void count() {
		JLabel MarkedMines = this.gameSecreen.getMarkedMines();
		JLabel totalMines = this.gameSecreen.getTotalMines();

		totalMines.setText(Integer.toString(this.minesQtd - this.markedQtd));
		totalMines.validate();

		MarkedMines.setText(Integer.toString(this.markedQtd));
		MarkedMines.validate();
	}

	public void countUnmarked() {
		this.markedQtd--;
		count();

	}

	public void countMarked() {
		this.markedQtd++;
		count();
	}

	public FieldSquare[][] generateButtons() {
		CtrCampo field = new CtrCampo(this.difficulty, this.minesQtd);
		FieldSquare[][] buttons = new FieldSquare[this.difficulty][this.difficulty];

		for (int i = 0; i < this.difficulty; i++)
			for (int j = 0; j < this.difficulty; j++)
				buttons[i][j] = new FieldSquare(field.getSquare(i, j), this);

		// Apresentar os vizinhos
		for (int i = 0; i < this.difficulty; i++) {
			for (int j = 0; j < this.difficulty; j++) {
				ArrayList<FieldSquare> vizinhos = new ArrayList<FieldSquare>();

				for (int k = -1; k < 2; k++) {
					for (int l = -1; l < 2; l++) {
						int x = i + k, y = j + l;

						if (x >= 0 && x < this.difficulty) {
							if (y >= 0 && y < this.difficulty) {
								if (x == i && y == j) {
									// System.out.println("no" + x + "|" + y);
								} else {
									// System.out.println("in" + x + "|" + y);
									vizinhos.add(buttons[x][y]);
								}
							}
						}
					}
				}

				/*
				 * for (int k = 0; k < vizinhos.size(); k++) {
				 * System.out.print(vizinhos.get(k).getValueSquare()); System.out.print(","); }
				 * 
				 * System.out.println();
				 */
				buttons[i][j].setVizinhos(vizinhos);
			}
		}

		System.out.println(field.toString());
		return buttons;
	}

	private void flipBlockFrame() {
		JFrame frame = this.gameSecreen.getFrmCampoMinado();
		if (frame.isEnabled()) {
			frame.setEnabled(false);
		} else {
			frame.setEnabled(true);
		}
	}

	private void winGame() {
		flipBlockFrame();
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playWin();
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.winGame(this.gameSecreen.getFrmCampoMinado());
		flipBlockFrame();
	}

	public void restartGame() {
		flipBlockFrame();
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.restartGame(this.gameSecreen.getFrmCampoMinado());
		flipBlockFrame();
	}

	public void closeWindow() {
		flipBlockFrame();
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.closeWindow(this.gameSecreen.getFrmCampoMinado());
		flipBlockFrame();
	}

	public void loseGame() {
		flipBlockFrame();
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playLose();
		FieldSquare[][] field = this.gameSecreen.getField();

		for (FieldSquare[] i : field)
			for (FieldSquare j : i)
				openPanel(j);

		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.loseGame(this.gameSecreen.getFrmCampoMinado());
		flipBlockFrame();

	}

	public void playStart() {
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playStart();
	}

}
