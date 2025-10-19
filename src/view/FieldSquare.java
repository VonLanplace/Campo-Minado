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

import controller.CtrButton;

public class FieldSquare extends JPanel {
	private static final long serialVersionUID = 8412514173336013671L;
	private static CtrButton ctrButton;
	private JButton button;
	private JLabel label;
	private int valueSquare;
	private boolean isOpen;
	private boolean isMarked;

	private ArrayList<FieldSquare> vizinhos;

	/*
	 * Fonte Padrao btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
	 */
	public FieldSquare(int valueSquare) {
		super();
		this.valueSquare = valueSquare;
		this.isOpen = false;
		// CtrButton ctrButton = this.ctrButton;

		FieldSquare panel = this;
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(new CardLayout(0, 0));

		this.button = new JButton("");
		JButton button = this.button;
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (!panel.isMarked) {
						panel.openPanel();
						if (panel.valueSquare < 0) {
							getCtrButton().loseGame();
						}
						getCtrButton().testWin();
					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (panel.isMarked) {
						button.setBackground(Color.GRAY);
					} else {
						button.setBackground(Color.DARK_GRAY);
					}
					panel.flipMarked();
				}
			}
		});

		button.setMargin(new Insets(7, 7, 7, 7));
		button.setFont(new Font("Dialog", Font.BOLD, 14));

		panel.add(button);

		this.label = new JLabel(Integer.toString(valueSquare));
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

	public void flipMarked() {
		if (this.isMarked) {
			this.isMarked = false;
		} else {
			this.isMarked = true;
		}
	}

	public void openPanel() {
		if (!this.isOpen) {
			this.isOpen = true;
			CardLayout cl = (CardLayout) this.getLayout();
			cl.next(this);

			if (this.valueSquare == 0) {
				for (FieldSquare i : vizinhos) {
					if (i.getValueSquare() == 0) {
						i.openPanel();
					}
				}
			}
		}
	}

	public static CtrButton getCtrButton() {
		return ctrButton;
	}

	public static void setCtrButton(CtrButton ctrButton) {
		FieldSquare.ctrButton = ctrButton;
	}

}
