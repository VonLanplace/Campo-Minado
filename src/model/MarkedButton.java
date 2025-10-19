package model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;

import controller.CtrGame;
import view.FieldSquare;

public class MarkedButton extends MineButton {
	private static final long serialVersionUID = -7201411709023896249L;

	public MarkedButton(FieldSquare square, CtrGame ctrGame) {
		super("!", square, ctrGame);

		MarkedButton button = this;

		// Remove a aparência padrão do botão
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(false);

		// Define cores para o gradiente vermelho
		Color topColor = new Color(220, 60, 60); // Vermelho mais claro (topo)
		Color bottomColor = new Color(180, 30, 30); // Vermelho mais escuro (base)
		Color pressedTopColor = new Color(200, 40, 40); // Vermelho pressionado (topo)
		Color pressedBottomColor = new Color(160, 20, 20); // Vermelho pressionado (base)

		// Aplica o renderizador personalizado
		button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				JButton btn = (JButton) c;
				ButtonModel model = btn.getModel();

				// Define as cores baseadas no estado do botão
				Color startColor, endColor;

				if (model.isPressed()) {
					startColor = pressedTopColor;
					endColor = pressedBottomColor;
				} else if (model.isRollover()) {
					// Efeito hover - gradiente um pouco mais claro
					startColor = topColor.brighter();
					endColor = bottomColor.brighter();
				} else {
					startColor = topColor;
					endColor = bottomColor;
				}

				// Cria o gradiente
				GradientPaint gradient = new GradientPaint(new Point2D.Float(0, 0), startColor,
						new Point2D.Float(0, btn.getHeight()), endColor);

				// Preenche o fundo com gradiente
				g2.setPaint(gradient);
				g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), 10, 10);

				// Adiciona uma borda sutil
				g2.setColor(new Color(120, 20, 20));
				g2.drawRoundRect(0, 0, btn.getWidth() - 1, btn.getHeight() - 1, 10, 10);

				// Desenha o texto do botão
				g2.setColor(Color.WHITE);
				g2.setFont(btn.getFont());
				FontMetrics fm = g2.getFontMetrics();
				String text = btn.getText();
				int textX = (btn.getWidth() - fm.stringWidth(text)) / 2;
				int textY = (btn.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
				g2.drawString(text, textX, textY);

				g2.dispose();
			}
		});

	}

}
