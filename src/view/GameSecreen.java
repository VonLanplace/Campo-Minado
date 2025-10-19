package view;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.CtrGame;

public class GameSecreen {
	private JFrame frmCampoMinado;

	private int windowHeigth;
	private int windowLength;
	private CtrGame ctrGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSecreen window = new GameSecreen(5, 150, 255);
					// GameSecreen window = new GameSecreen(10, 300, 400);
					// GameSecreen window = new GameSecreen(15, 450, 550);
					// GameSecreen window = new GameSecreen(20, 600, 700);
					window.frmCampoMinado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameSecreen(int fieldSize, int windowLength, int windowHeigth) {
		this.windowHeigth = windowHeigth;
		this.windowLength = windowLength;

		this.ctrGame = new CtrGame(this, fieldSize);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JLabel MarkedMines;
	private JLabel totalMines;
	private FieldSquare[][] Field;

	private void initialize() {
		frmCampoMinado = new JFrame();
		frmCampoMinado.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmCampoMinado.setResizable(false);
		frmCampoMinado.setTitle("Campo Minado");
		// Set tamanho da caixa
		// frmCampoMinado.setBounds(100, 100, windowLength, windowHeigth);
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

		JButton BtnNew = new JButton("New");
		BtnNew.setMargin(new Insets(0, 0, 0, 0));
		BarraOpcoes.add(BtnNew);

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

		JLabel iconCenter = new JLabel("*");
		iconCenter.setFont(new Font("Dialog", Font.BOLD, 20));
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

		Field = this.ctrGame.generateButtons();

		for (int i = 0; i < Field.length; i++) {
			for (int j = 0; j < Field.length; j++) {
				Center.add(Field[i][j]);
			}
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
		return Field;
	}

	public void setField(FieldSquare[][] field) {
		Field = field;
	}
}
