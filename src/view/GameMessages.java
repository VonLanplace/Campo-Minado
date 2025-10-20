package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.CtrMain;

public class GameMessages {

	private int difficulty;

	public GameMessages(int difficulty) {
		super();
		this.difficulty = difficulty;
	}

	public void helpWindow(JFrame frame) {
		// Load Window
		frame.setVisible(false);
		HelpWindow helpWindow = new HelpWindow();
		JFrame newWindow = helpWindow.getFrame();

		// Adiciona listener para quando o frame fechar
		newWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frame.setVisible(true);
			}
		});

		newWindow.setVisible(true);

	}

	public void closeWindow(JFrame frame) {
		// Load Window
		int opc = JOptionPane.showConfirmDialog(frame, "Tem certeza que quer voltar ao menu?", "Return Menu",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// Load Window
		if (opc == JOptionPane.OK_OPTION) {
			frame.dispose();
		}
	}

	public void restartGame(JFrame frame) {
		int opc = JOptionPane.showConfirmDialog(frame, "Tem certeza que quer reiniciar o Jogo?", "Restart Game",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// Load Window
		if (opc == JOptionPane.OK_OPTION) {
			GameSecreen gameSecreen = createGameScreen(this.difficulty / 5);
			JFrame newFrame = gameSecreen.getFrmCampoMinado();

			newWindow(newFrame, frame);
		}
	}

	public void winGame(JFrame frame) {
		int opc = JOptionPane.showConfirmDialog(frame, "Parabens você encontrou todas as mina, quer jogar denovo?",
				"Congratulations!!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Load Window
		if (opc == JOptionPane.YES_OPTION) {
			GameSecreen gameSecreen = createGameScreen(this.difficulty / 5);
			JFrame newFrame = gameSecreen.getFrmCampoMinado();

			newWindow(newFrame, frame);
		} else {
			frame.dispose();
		}
	}

	public void loseGame(JFrame frame) {

		int opc = JOptionPane.showConfirmDialog(frame, "Você quer tentar novamente?", "Try Again?",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

		// Load Window
		if (opc == JOptionPane.YES_OPTION) {
			GameSecreen gameSecreen = createGameScreen(this.difficulty / 5);
			JFrame newFrame = gameSecreen.getFrmCampoMinado();

			newWindow(newFrame, frame);
		} else {
			frame.dispose();
		}
	}

	public void newWindow(JFrame frame, JFrame parent) {
		JFrame frmCampoMinado = parent;
		frmCampoMinado.setVisible(false);

		// Adiciona listener para quando o frame fechar
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frmCampoMinado.dispose();
			}
		});

		frame.setVisible(true);
	}

	public void overlapWindow(JFrame frame, JFrame parent) {
		JFrame frmCampoMinado = parent;

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frmCampoMinado.setVisible(false);
				frmCampoMinado.dispose();
			}
		});

		frame.setVisible(true);
	}

	private static GameSecreen createGameScreen(int difficulty) {
		CtrMain ctrMain = new CtrMain();
		return ctrMain.createGameScreen(difficulty);
	}
}
