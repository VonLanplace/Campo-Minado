package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import view.GameSecreen;
import view.Main;

public class CtrMain {
	private Main main;

	public CtrMain(Main main) {
		this.main = main;
	}

	public CtrMain() {
		this.main = null;
	}

	public void startGame(int dificulty) {
		JFrame frmCampoMinado = this.main.getFrmCampoMinado();
		frmCampoMinado.setVisible(false);

		// Chama o GameSecreen de forma que espere o fechamento
		GameSecreen gameSecreen = criarGameSecreenPorDificuldade(dificulty); // Configuração do tutorial
		JFrame tutorialFrame = gameSecreen.getFrmCampoMinado();

		// Adiciona listener para quando o tutorial fechar
		tutorialFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frmCampoMinado.setVisible(true);
			}
		});

		tutorialFrame.setVisible(true);
	}

	public GameSecreen criarGameSecreenPorDificuldade(int difficulty) {
		return switch (difficulty) {
		case 1 -> new GameSecreen(5, 150, 255);
		case 2 -> new GameSecreen(10, 300, 400);
		case 3 -> new GameSecreen(15, 450, 550);
		case 4 -> new GameSecreen(20, 600, 700);
		case 5 -> new GameSecreen(25, 750, 850);
		default -> new GameSecreen(10, 300, 400);
		};
	}
}
