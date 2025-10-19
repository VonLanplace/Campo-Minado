package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MarkedIcon extends MarkedButton {

	private static final long serialVersionUID = -750567282211668078L;

	public MarkedIcon(String text) {
		super(null, null);
		MarkedIcon button = this;

		// Remove listeners of a specific class type
		for (MouseListener listener : this.getMouseListeners()) {
			if (listener instanceof MouseAdapter) {
				this.removeMouseListener(listener);
			}
		}

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
				} else if (SwingUtilities.isRightMouseButton(e)) {
				}
			}
		});
	}
}
