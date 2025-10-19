package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import controller.CtrGame;

public class FieldSquare extends JPanel {
	private static final long serialVersionUID = 8412514173336013671L;

	private JButton button;
	private JLabel label;
	private CtrGame ctrGame;

	private int valueSquare;
	private int isOpen;
	private int isMarked;

	private ArrayList<FieldSquare> vizinhos;

	/*
	 * Fonte Padrao btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
	 */
	public FieldSquare(int valueSquare, CtrGame ctrGame) {
		super();
		this.valueSquare = valueSquare;
		this.isOpen = 0;
		this.ctrGame = ctrGame;

		FieldSquare panel = this;
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(new CardLayout(0, 0));

		this.button = new JButton("");
		JButton button = this.button;
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (!panel.isMarked()) {
						ctrGame.openPanel(panel);
						if (panel.valueSquare < 0) {
							ctrGame.loseGame();
						}

					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (panel.isMarked()) {
						button.setBackground(Color.GRAY);
						panel.flipMarked();
					} else {
						if (ctrGame.getMarkedQtd() < ctrGame.getMinesQtd()) {
							button.setBackground(Color.DARK_GRAY);
							panel.flipMarked();
						}
					}
				}
				ctrGame.testWin();
			}
		});

		button.setMargin(new Insets(7, 7, 7, 7));
		button.setFont(new Font("Dialog", Font.BOLD, 14));

		panel.add(button);

		if (this.valueSquare >= 0)
			this.label = new JLabel(Integer.toString(valueSquare));
		else
			this.label = new JLabel("*");

		JLabel label = this.label;
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(label);
	}

	public void setVizinhos(ArrayList<FieldSquare> vizinhos) {
		this.vizinhos = vizinhos;
	}

	public ArrayList<FieldSquare> getVizinhos() {
		return vizinhos;
	}

	public int getValueSquare() {
		return valueSquare;
	}

	public boolean isOpen() {
		if (this.isOpen == 0)
			return false;
		else
			return true;
	}

	public void setOpen(boolean open) {
		if (open) {
			this.isOpen = 1;
		} else {
			this.isOpen = 0;
		}
	}

	public boolean isMarked() {
		if (this.isMarked == 0)
			return false;
		else
			return true;
	}

	public void flipMarked() {
		if (this.isMarked == 0) {
			this.ctrGame.countMarked();
			this.isMarked = 1;
		} else {
			this.ctrGame.countUnmarked();
			this.isMarked = 0;
		}
	}

}
