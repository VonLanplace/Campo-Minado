package model;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.CtrGame;
import view.FieldSquare;

public class MarkedButton extends MineButton {
	private static final long serialVersionUID = -7201411709023896249L;

	public MarkedButton(FieldSquare square, CtrGame ctrGame, JFrame frame, FieldSquare[][] fieldSquares,
			JLabel markedMines, JLabel totalMines) {
		super("!", square, ctrGame, frame, fieldSquares, markedMines, totalMines);

		MarkedButton button = this;

		// Remove a aparência padrão do botão
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);

		// Aplica o renderizador personalizado
		button.setUI(new RedButton());
	}

}
