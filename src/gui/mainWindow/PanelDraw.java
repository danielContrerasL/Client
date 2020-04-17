package gui.mainWindow;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import constant.ConstantGui;

@SuppressWarnings("serial")
public class PanelDraw extends JPanel{
	
	private Image bg;
	
	public PanelDraw() {
		bg = new ImageIcon(getClass().getResource(ConstantGui.DF_PATH)).getImage();
	}
	
	public void updateMyBg(String path) {
		bg  = new ImageIcon(path).getImage();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this.getWidth(),this.getHeight(), this);
	}

}
