package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.CtrGame;
import model.MarkedButton;
import model.MineButton;

public class FieldSquare extends JPanel {
	private static final long serialVersionUID = 8412514173336013671L;

	private MineButton mineButton;
	private MarkedButton markedButton;
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

		this.markedButton = new MarkedButton(panel, ctrGame);
		panel.add(markedButton);

		this.mineButton = new MineButton("", panel, ctrGame);
		panel.add(mineButton);

		CardLayout cl = (CardLayout) panel.getLayout();
		cl.next(panel);

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

	public void nextCard() {
		CardLayout cl = (CardLayout) this.getLayout();
		cl.next(this);

	}

	public void previousCard() {
		CardLayout cl = (CardLayout) this.getLayout();
		cl.previous(this);

	}
}
