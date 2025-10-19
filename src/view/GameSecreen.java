package view;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.CtrGame;
import model.MarkedIcon;

public class GameSecreen {
	private JFrame frmCampoMinado;

	private CtrGame ctrGame;

	/**
	 * Launch the application.
	 */

	// public static void main(String[] args, int dificuldade) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// GameSecreen window = new GameSecreen(5, 150, 255);
	// GameSecreen window = new GameSecreen(10, 300, 400);
	// GameSecreen window = new GameSecreen(15, 450, 550);
	// GameSecreen window = new GameSecreen(20, 600, 700);
	// GameSecreen window = new GameSecreen(25, 750, 850);
	// window.frmCampoMinado.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	private int windowLength;
	private int windowHeigth;

	public GameSecreen(int fieldSize, int windowLength, int windowHeigth) {
		this.ctrGame = new CtrGame(this, fieldSize);
		this.windowLength = windowLength;
		this.windowHeigth = windowHeigth;
		this.ctrGame.playStart();
		initialize(fieldSize);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JLabel MarkedMines;
	private JLabel totalMines;
	private FieldSquare[][] field;

	private void initialize(int fieldSize) {
		frmCampoMinado = new JFrame();
		frmCampoMinado.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmCampoMinado.setResizable(false);
		frmCampoMinado.setTitle(this.label(fieldSize));
		// Set tamanho da caixa
		// frmCampoMinado.setBounds(100, 100, 750, 850);
		centerFrame(this.frmCampoMinado, this.windowLength, this.windowHeigth);

		frmCampoMinado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCampoMinado.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel Top = new JPanel();
		frmCampoMinado.getContentPane().add(Top, BorderLayout.NORTH);
		Top.setLayout(new BorderLayout(0, 0));

		JSeparator separator = new JSeparator();
		Top.add(separator, BorderLayout.SOUTH);

		JPanel topOption = new JPanel();
		Top.add(topOption, BorderLayout.NORTH);
		topOption.setLayout(new GridLayout(0, 1, 0, 0));

		JToolBar BarraOpcoes = new JToolBar();
		topOption.add(BarraOpcoes);

		JButton BtnNew = new JButton(" Reset ");
		BtnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrGame.restartGame();
			}
		});
		BtnNew.setMargin(new Insets(0, 0, 0, 0));
		BarraOpcoes.add(BtnNew);

		JButton btnMainMenu = new JButton(" Menu ");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrGame.closeWindow();
			}
		});
		btnMainMenu.setMargin(new Insets(0, 0, 0, 0));
		BarraOpcoes.add(btnMainMenu);

		JSeparator separator_2 = new JSeparator();
		Top.add(separator_2, BorderLayout.WEST);

		JPanel topInfo = new JPanel();
		Top.add(topInfo);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		topInfo.add(panel_2);

		totalMines = new JLabel();
		totalMines.setText(Integer.toString(this.ctrGame.getMinesQtd()));
		panel_2.add(totalMines);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		topInfo.add(separator_4);

		JPanel panel = new JPanel();
		topInfo.add(panel);

		MarkedIcon iconCenter = new MarkedIcon("!");
		panel.add(iconCenter);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		topInfo.add(separator_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		topInfo.add(panel_1);

		MarkedMines = new JLabel();
		MarkedMines.setText("0");
		panel_1.add(MarkedMines);

		JSeparator separator_1 = new JSeparator();
		frmCampoMinado.getContentPane().add(separator_1, BorderLayout.SOUTH);

		JPanel Center = new JPanel();
		frmCampoMinado.getContentPane().add(Center, BorderLayout.CENTER);
		Center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		field = this.ctrGame.generateButtons();

		for (int i = 0; i < fieldSize; i++) {
			for (int j = 0; j < fieldSize; j++) {
				Center.add(field[i][j]);
			}
		}
	}

	private String label(int fieldSize) {
		switch (fieldSize) {
		case 5:
			return "Campo Minado - 5X5";
		case 10:
			return "Campo Minado - 10X10";
		case 15:
			return "Campo Minado - 15X15";
		case 20:
			return "Campo Minado - 20X20";
		case 25:
			return "Campo Minado - 25X25";
		default:
			return "Campo Minado";
		}
	}

	public static void centerFrame(JFrame frame, int width, int height) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - width) / 2;
		int y = (screenSize.height - height) / 2;
		frame.setBounds(x, y, width, height);
	}

	public JFrame getFrmCampoMinado() {
		return frmCampoMinado;
	}

	public JLabel getMarkedMines() {
		return MarkedMines;
	}

	public void setMarkedMines(JLabel markedMines) {
		MarkedMines = markedMines;
	}

	public JLabel getTotalMines() {
		return totalMines;
	}

	public void setTotalMines(JLabel totalMines) {
		this.totalMines = totalMines;
	}

	public FieldSquare[][] getField() {
		return this.field;
	}

	public void setField(FieldSquare[][] field) {
		this.field = field;
	}
}
