package model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.CtrButton;
import controller.CtrGame;
import view.FieldSquare;

public class MineButton extends JButton {

	private static final long serialVersionUID = 641068785051091705L;

	public MineButton(String text, FieldSquare square, CtrGame ctrGame, JFrame frame, FieldSquare[][] fieldSquares,
			JLabel markedMines, JLabel totalMines) {
		super(text);
		this.addMouseListener(new CtrButton(square, ctrGame, frame, fieldSquares, markedMines, totalMines));
		buildButton();
	}

	public MineButton(FieldSquare square, CtrGame ctrGame, JFrame frame, FieldSquare[][] fieldSquares,
			JLabel markedMines, JLabel totalMines) {
		super();
		this.addMouseListener(new CtrButton(square, ctrGame, frame, fieldSquares, markedMines, totalMines));
		buildButton();
	}

	public void buildButton() {

		// Define margens pequenas e fonte
		this.setMargin(new Insets(1, 4, 1, 4));
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

		// Define tamanho preferido para ser quadrado pequeno
		this.setPreferredSize(new Dimension(20, 20));
		this.setMinimumSize(new Dimension(14, 14));
		this.setMaximumSize(new Dimension(20, 20));
	}
}
