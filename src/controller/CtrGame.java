package controller;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import view.FieldSquare;
import view.GameMessages;
import view.GameSecreen;

public class CtrGame {
	private int sideSize;
	private int minesQtd;
	private int markedQtd;

	public CtrGame(int sideSize) {
		super();
		this.sideSize = sideSize;
		this.minesQtd = sideSize * (sideSize / 5);
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

	public FieldSquare[][] generateButtons(JFrame frmCampoMinado, JLabel markedMines, JLabel totalMines) {
		CtrCampo field = new CtrCampo(this.sideSize, this.minesQtd);
		FieldSquare[][] buttons = new FieldSquare[this.sideSize][this.sideSize];

		for (int i = 0; i < this.sideSize; i++)
			for (int j = 0; j < this.sideSize; j++)
				buttons[i][j] = new FieldSquare(field.getSquare(i, j), this, frmCampoMinado, buttons, markedMines,
						totalMines);

		// Apresentar os vizinhos
		for (int i = 0; i < this.sideSize; i++) {
			for (int j = 0; j < this.sideSize; j++) {
				ArrayList<FieldSquare> vizinhos = new ArrayList<FieldSquare>();

				for (int k = -1; k < 2; k++) {
					for (int l = -1; l < 2; l++) {
						int x = i + k, y = j + l;

						if (x >= 0 && x < this.sideSize) {
							if (y >= 0 && y < this.sideSize) {
								if (x == i && y == j) {
								} else {
									vizinhos.add(buttons[x][y]);
								}
							}
						}
					}
				}

				buttons[i][j].setVizinhos(vizinhos);
			}
		}

		// System.out.println(field.toString());
		return buttons;
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

	private void flipBlockFrame(JFrame frmCampoMinado) {
		if (frmCampoMinado.isEnabled()) {
			frmCampoMinado.setEnabled(false);
		} else {
			frmCampoMinado.setEnabled(true);
		}
	}

	public void helpWindow(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		GameMessages gameMessages = new GameMessages();
		gameMessages.helpWindow(frmCampoMinado);
		flipBlockFrame(frmCampoMinado);
	}

	private void winGame(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playWin();
		GameMessages gameMessages = new GameMessages();
		int opc = gameMessages.winGame(frmCampoMinado);

		if (opc == JOptionPane.NO_OPTION)
			frmCampoMinado.dispose();
		else
			newGame(frmCampoMinado);

		flipBlockFrame(frmCampoMinado);
	}

	public void restartGame(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		GameMessages gameMessages = new GameMessages();
		int opc = gameMessages.restartGame(frmCampoMinado);

		if (opc != JOptionPane.CANCEL_OPTION)
			newGame(frmCampoMinado);

		flipBlockFrame(frmCampoMinado);
	}

	public void closeWindow(JFrame frmCampoMinado) {
		flipBlockFrame(frmCampoMinado);
		GameMessages gameMessages = new GameMessages();
		int opc = gameMessages.closeWindow(frmCampoMinado);

		if (opc == JOptionPane.OK_OPTION || opc == JOptionPane.YES_OPTION)
			frmCampoMinado.dispose();

		flipBlockFrame(frmCampoMinado);
	}

	public void loseGame(JFrame frmCampoMinado, FieldSquare[][] field) {
		flipBlockFrame(frmCampoMinado);
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playLose();

		for (FieldSquare[] i : field)
			for (FieldSquare j : i)
				if (j.getValueSquare() < 0)
					openPanel(j);

		GameMessages gameMessages = new GameMessages();
		int opc = gameMessages.loseGame(frmCampoMinado);

		if (opc == JOptionPane.NO_OPTION)
			frmCampoMinado.dispose();
		else
			newGame(frmCampoMinado);

		flipBlockFrame(frmCampoMinado);
	}

	private void newGame(JFrame frmCampoMinado) {
		System.out.println(this.sideSize);
		// Load Window
		frmCampoMinado.setVisible(false);
		CtrMain ctrMain = new CtrMain();
		GameSecreen gameSecreen = ctrMain.createGameScreen(this.sideSize / 5);
		JFrame newWindow = gameSecreen.getFrmCampoMinado();

		newWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frmCampoMinado.dispose();
			}
		});

		newWindow.setVisible(true);
	}

	public void playStart() {
		BuzzerBiip bepp = new BuzzerBiip();
		bepp.playStart();
	}

}
