package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AjudaPanel;

public class HelpWindow {

	private JFrame frmHelp;

	/**
	 * Launch the application.
	 */

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// HelpWindow window = new HelpWindow();
	// window.frmHelp.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public HelpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHelp = new JFrame();
		frmHelp.setTitle("Campo Minado - Help");
		frmHelp.setBounds(100, 100, 640, 360);
		centerFrame(frmHelp, frmHelp.getWidth(), frmHelp.getHeight());
		frmHelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmHelp.getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHelp.dispose();
			}
		});
		panel.add(btnNewButton_2);

		AjudaPanel ajudaPanel = new AjudaPanel();
		frmHelp.getContentPane().add(ajudaPanel, BorderLayout.CENTER);

	}

	public JFrame getFrame() {
		return frmHelp;
	}

	public static void centerFrame(JFrame frame, int width, int height) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - width) / 2;
		int y = (screenSize.height - height) / 2;
		frame.setBounds(x, y, width, height);
	}

}
