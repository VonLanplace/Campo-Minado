package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import controller.CtrBuilder;

public class GameSecreen {

	private JFrame frmCampoMinado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSecreen window = new GameSecreen();
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
	public GameSecreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCampoMinado = new JFrame();
		frmCampoMinado.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmCampoMinado.setResizable(false);
		frmCampoMinado.setTitle("Campo Minado");
		// Set tamanho da caixa
		frmCampoMinado.setBounds(100, 100, 175, 260);
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

		JToolBar toolBar = new JToolBar();
		topOption.add(toolBar);

		JButton NewGame = new JButton("New");
		NewGame.setMargin(new Insets(0, 0, 0, 0));
		toolBar.add(NewGame);

		JSeparator separator_2 = new JSeparator();
		Top.add(separator_2, BorderLayout.WEST);

		JPanel topInfo = new JPanel();
		Top.add(topInfo);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		topInfo.add(panel_2);

		JLabel TotalMines = new JLabel("000");
		TotalMines.setForeground(new Color(0, 0, 0));
		panel_2.add(TotalMines);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		topInfo.add(separator_4);

		JPanel panel = new JPanel();
		topInfo.add(panel);

		JLabel label = new JLabel("*");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(label);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		topInfo.add(separator_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		topInfo.add(panel_1);

		JLabel MarkedMines = new JLabel("000");
		panel_1.add(MarkedMines);

		JSeparator separator_1 = new JSeparator();
		frmCampoMinado.getContentPane().add(separator_1, BorderLayout.SOUTH);

		JPanel Center = new JPanel();
		frmCampoMinado.getContentPane().add(Center, BorderLayout.CENTER);
		Center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		CtrBuilder ctrBuilder = new CtrBuilder(5);
		JPanel[][] Field = ctrBuilder.generateButtons();

		for (int i = 0; i < Field.length; i++) {
			for (int j = 0; j < Field.length; j++) {
				Center.add(Field[i][j]);
			}
		}
	}
}
