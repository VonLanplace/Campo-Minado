package controller;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import view.FieldSquare;
import view.GameMessages;

public class CtrGame {
	private int difficulty;
	private int minesQtd;
	private int markedQtd;

	public CtrGame(int difficulty) {
		super();
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

	public void testWin(JFrame frmCampoMinado, FieldSquare[][] field) {
		int correctMarked = 0;

		for (FieldSquare[] i : field)
			for (FieldSquare j : i)
				if (j.isMarked() && j.getValueSquare() < 0)
					correctMarked++;

		if (correctMarked == this.minesQtd)
			winGame(frmCampoMinado);
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

	public void count(JLabel markedMines, JLabel totalMines) {

		totalMines.setText(Integer.toString(this.minesQtd - this.markedQtd));
		totalMines.validate();

		markedMines.setText(Integer.toString(this.markedQtd));
		markedMines.validate();
	}

	public void countUnmarked(JLabel markedMines, JLabel totalMines) {
		this.markedQtd--;
		count(markedMines, totalMines);

	}

	public void countMarked(JLabel markedMines, JLabel totalMines) {
		this.markedQtd++;
		count(markedMines, totalMines);
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

	private void flipBlockFrame(JFrame frmCampoMinado) {
		if (frmCampoMinado.isEnabled()) {
			frmCampoMinado.setEnabled(false);
		} else {
			frmCampoMinado.setEnabled(true);
		}
	}

	private void winGame(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playWin();
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.winGame(frmCampoMinado);
		flipBlockFrame(frmCampoMinado);
	}

	public void restartGame(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.restartGame(frmCampoMinado);
		flipBlockFrame(frmCampoMinado);
	}

	public void closeWindow(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.closeWindow(frmCampoMinado);
		flipBlockFrame(frmCampoMinado);
	}

	public void loseGame(JFrame frmCampoMinado, FieldSquare[][] field) {
		flipBlockFrame(frmCampoMinado);
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playLose();

		for (FieldSquare[] i : field)
			for (FieldSquare j : i)
				openPanel(j);

		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.loseGame(frmCampoMinado);
		flipBlockFrame(frmCampoMinado);

	}

	public void playStart() {
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playStart();
	}

	public void helpWindow(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		GameMessages gameMessages = new GameMessages(difficulty);
		gameMessages.helpWindow(frmCampoMinado);
		flipBlockFrame(frmCampoMinado);
	}

}
