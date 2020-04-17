package gui.errorManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorManager {
	
	public void showErrorMessage(String message, JFrame frame) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
