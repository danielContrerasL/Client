package controller;

import gui.errorManager.ErrorManager;
import gui.mainWindow.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import constant.ConstantGui;
import logic.Client;

public class Controller implements ActionListener, KeyListener{
	
	private MainWindow mainWindow;
	private Client client;
	private ErrorManager errorManager;
	
	public Controller() {
		try {
//			client = new Client("127.0.0.1", 3001);
			client = new Client("192.168.0.17", 3001);
		} catch (IOException e) {
			e.printStackTrace();
		}
		errorManager = new ErrorManager();
		mainWindow = new MainWindow(this);
		mainWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (MyAction.valueOf(e.getActionCommand())) {
		case PUBLISH_IMAGE:
			try {
				client.sendImage(mainWindow.validateHashtag(), mainWindow.getActualPath());
			} catch (Exception e1) {
				errorManager.showErrorMessage(e1.getMessage(), mainWindow);
			}
			break;
		case REQUEST_IMAGE:
			break;
		case SELECT_IMAGE:
			mainWindow.updateImage();
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		mainWindow.getToolkit().beep();
		if(mainWindow.getLengthTextarea() > ConstantGui.MAX_LENHT){
			e.consume();
			errorManager.showErrorMessage("Limite de texto alcanzado\nMáximo de caracteres permitido 40", mainWindow);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {											
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
