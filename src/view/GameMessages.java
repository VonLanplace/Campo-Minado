package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameMessages {

	public GameMessages() {
		super();
	}

	public void helpWindow(JFrame frame) {
		// Load Window
		frame.setVisible(false);
		HelpWindow helpWindow = new HelpWindow();
		JFrame newWindow = helpWindow.getFrame();

		newWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frame.setVisible(true);
			}
		});

		newWindow.setVisible(true);

	}

	public int closeWindow(JFrame frame) {
		// Load Window
		return JOptionPane.showConfirmDialog(frame, "Tem certeza que quer voltar ao menu?", "Return Menu",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

	}

	public int restartGame(JFrame frame) {
		return JOptionPane.showConfirmDialog(frame, "Tem certeza que quer reiniciar o Jogo?", "Restart Game",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

	}

	public int winGame(JFrame frame) {
		return JOptionPane.showConfirmDialog(frame, "Parabens você encontrou todas as mina, quer jogar denovo?",
				"Congratulations!!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	public int loseGame(JFrame frame) {

		return JOptionPane.showConfirmDialog(frame, "Você quer tentar novamente?", "Try Again?",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	}

}
