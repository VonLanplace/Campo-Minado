package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
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

	private int valueSquare;
	private boolean isOpen;
	private boolean isMarked;

	private ArrayList<FieldSquare> vizinhos;

	/*
	 * Fonte Padrao btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
	 */
	public FieldSquare(int valueSquare, CtrGame ctrGame, JFrame frame, FieldSquare[][] fieldSquares, JLabel markedMines,
			JLabel totalMines) {
		super();
		this.valueSquare = valueSquare;
		this.isOpen = false;
		this.isMarked = false;

		FieldSquare panel = this;
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(new CardLayout(0, 0));

		this.markedButton = new MarkedButton(panel, ctrGame, frame, fieldSquares, markedMines, totalMines);
		panel.add(markedButton);

		this.mineButton = new MineButton("", panel, ctrGame, frame, fieldSquares, markedMines, totalMines);
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
		return this.isOpen;
	}

	public void setOpen(boolean open) {
		if (open) {
			this.isOpen = true;
		} else {
			this.isOpen = false;
		}
	}

	public boolean isMarked() {
		return this.isMarked;
	}

	public void setMarked(boolean marked) {
		this.isMarked = marked;
	}

	public void flipMarked() {
		if (this.isMarked)
			this.isMarked = false;
		else
			this.isMarked = true;
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
