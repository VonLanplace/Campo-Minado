package model;

import java.awt.Dimension;

import javax.swing.JButton;

public class MainButtons extends JButton {
	private static final long serialVersionUID = 9177047808118788403L;

	public MainButtons(String text) {
		super(text);
		confiButton();
	}

	public MainButtons() {
		super();
		confiButton();
	}

	private void confiButton() {
		// Define tamanho preferido para ser quadrado pequeno
		this.setPreferredSize(new Dimension(200, 200));

	}
}
