package model;

import java.awt.Dimension;

import javax.swing.JButton;

public class MainButtons extends JButton {
	private static final long serialVersionUID = 9177047808118788403L;

	public MainButtons(String text) {
		super(text);
		configButton();
	}

	public MainButtons() {
		super();
		configButton();
	}

	private void configButton() {
		// Define tamanho preferido para ser quadrado pequeno
		this.setPreferredSize(new Dimension(200, 200));

	}
}
