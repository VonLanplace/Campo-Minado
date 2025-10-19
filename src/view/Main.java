package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.CtrMain;
import model.MainButtons;
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
		frmCampoMinado.setBounds(100, 100, 214, 235);
		centerFrame(frmCampoMinado, frmCampoMinado.getWidth(), frmCampoMinado.getHeight());
		frmCampoMinado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		frmCampoMinado.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Escolha o nivel");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		topPanel.add(separator, BorderLayout.SOUTH);

		JPanel midPanel = new JPanel();
		frmCampoMinado.getContentPane().add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new MigLayout("", "[82px][][62px][72px][70px][101px]", "[27px][][][][]"));

		MainButtons btnTut = new MainButtons("Extra Facil - 5X5");
		btnTut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(1);
			}
		});
		midPanel.add(btnTut, "cell 3 0,alignx center,aligny center");

		MainButtons btnEasy = new MainButtons("Facil - 10X10");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(2);
			}
		});
		midPanel.add(btnEasy, "cell 3 1,alignx center,aligny center");

		MainButtons btnMed = new MainButtons("Medio - 15X15");
		btnMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(3);
			}
		});
		midPanel.add(btnMed, "cell 3 2,alignx center,aligny center");

		MainButtons btnDif = new MainButtons("Dificil - 20X20");
		btnDif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrMain.startGame(4);
			}
		});
		midPanel.add(btnDif, "cell 3 3,alignx center,aligny center");

		MainButtons btnImp = new MainButtons("Impossivel - 25X25");
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
