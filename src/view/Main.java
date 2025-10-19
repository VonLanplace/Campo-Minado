package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

import controller.CtrMain;
import net.miginfocom.swing.MigLayout;

public class Main {

	private JFrame frmCampoMinado;
	private CtrMain ctrMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		this.ctrMain = new CtrMain(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCampoMinado = new JFrame();
		frmCampoMinado.setTitle("Campo Minado - Main Menu");
		frmCampoMinado.setBounds(100, 100, 300, 200);
		centerFrame(frmCampoMinado, 300, 230);
		frmCampoMinado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmCampoMinado.getContentPane().add(topPanel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Escolha a dificuldade");
		topPanel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		frmCampoMinado.getContentPane().add(separator, BorderLayout.SOUTH);

		JPanel midPanel = new JPanel();
		frmCampoMinado.getContentPane().add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new MigLayout("", "[82px][][62px][72px][70px][101px]", "[27px][][][][]"));

		JButton btnTut = new JButton("Tutorial");
		btnTut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(1);
			}
		});
		midPanel.add(btnTut, "cell 3 0,alignx center,aligny center");

		JButton btnEasy = new JButton("Facil");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(2);
			}
		});
		midPanel.add(btnEasy, "cell 3 1,alignx center,aligny center");

		JButton btnMed = new JButton("Medio");
		btnMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(3);
			}
		});
		midPanel.add(btnMed, "cell 3 2,alignx center,aligny center");

		JButton btnDif = new JButton("Dificil");
		btnDif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(4);
			}
		});
		midPanel.add(btnDif, "cell 3 3,alignx center,aligny center");

		JButton btnImp = new JButton("Impossivel");
		btnImp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(5);
			}
		});
		midPanel.add(btnImp, "cell 3 4,alignx center,aligny center");
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
}
