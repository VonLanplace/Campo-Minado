package model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import controller.BuzzerBiip;
import controller.CtrGame;
import view.FieldSquare;

public class MineButton extends JButton {

	private static final long serialVersionUID = 641068785051091705L;

	public MineButton(String text, FieldSquare square, CtrGame ctrGame) {
		super(text);
		MineButton button = this;

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (!square.isMarked()) {
						ctrGame.openPanel(square);
						if (square.getValueSquare() < 0) {
							ctrGame.loseGame();
						}
					} else {
						BuzzerBiip buzzerBiip = new BuzzerBiip();
						buzzerBiip.playBad();
					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (square.isMarked()) {
						square.nextCard();
						square.flipMarked();
					} else {
						if (ctrGame.getMarkedQtd() < ctrGame.getMinesQtd()) {
							square.previousCard();
							square.flipMarked();
						} else {
							BuzzerBiip buzzerBiip = new BuzzerBiip();
							buzzerBiip.playBad();
						}
					}
				}
				ctrGame.testWin();
			}
		});

		// Define margens pequenas e fonte
		button.setMargin(new Insets(1, 4, 1, 4));
		button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

		// Define tamanho preferido para ser quadrado pequeno
		button.setPreferredSize(new Dimension(20, 20));
		button.setMinimumSize(new Dimension(14, 14));
		button.setMaximumSize(new Dimension(20, 20));
	}
}
