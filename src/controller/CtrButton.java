package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import view.FieldSquare;

public class CtrButton extends MouseAdapter {

	private FieldSquare square;

	private static CtrGame ctrGame;
	private static JFrame frame;
	private static FieldSquare[][] fieldSquares;
	private static JLabel markedMines;
	private static JLabel totalMines;

	public CtrButton(FieldSquare square, CtrGame ctrGame, JFrame frame, FieldSquare[][] fieldSquares,
			JLabel markedMines, JLabel totalMines) {
		this.square = square;
		CtrButton.ctrGame = ctrGame;
		CtrButton.frame = frame;
		CtrButton.fieldSquares = fieldSquares;
		CtrButton.markedMines = markedMines;
		CtrButton.totalMines = totalMines;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (!square.isMarked()) {
				ctrGame.openPanel(square);
				if (square.getValueSquare() < 0) {
					ctrGame.loseGame(frame, fieldSquares);
				}
			} else {
				BuzzerBiip buzzerBiip = new BuzzerBiip();
				buzzerBiip.playBad();
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (square.isMarked()) {
				square.nextCard();
				flipMarked(square);
			} else {
				if (ctrGame.getMarkedQtd() < ctrGame.getMinesQtd()) {
					square.previousCard();
					flipMarked(square);
				} else {
					BuzzerBiip buzzerBiip = new BuzzerBiip();
					buzzerBiip.playBad();
				}
			}
		}
		ctrGame.testWin(frame, fieldSquares);
	}

	public void flipMarked(FieldSquare square) {
		if (square.isMarked()) {
			CtrButton.ctrGame.countMarked(markedMines, totalMines);
			square.setMarked(false);
		} else {
			CtrButton.ctrGame.countUnmarked(markedMines, totalMines);
			square.setMarked(true);
		}
	}
}
