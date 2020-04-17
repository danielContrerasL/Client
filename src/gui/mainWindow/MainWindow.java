package gui.mainWindow;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import constant.ConstantGui;
import controller.Controller;
import controller.MyAction;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel jPanelButton;
	private JButton publishImage;
	private JButton requestImage;
	private PanelDraw panelDraw;
	private JPanel jPanelImage;
	private JPanel jPanelEditButton;
	private JTextArea jTAHastag;
	private JButton jBSelectImage;
	private String actualPath;

	public MainWindow(Controller controller) {
		setWindowSize();
		setTitle(ConstantGui.WINDOW_NAME);
		setIconImage(new ImageIcon(getClass().getResource(ConstantGui.DF_ICON_PATH)).getImage());
		initButton(controller);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initPrincipalPanel(controller);

	}

	private void initPrincipalPanel(Controller controller) {
		actualPath = "";
		panelDraw = new PanelDraw();
		
		jPanelImage = new JPanel();
		jPanelImage.setLayout(new BorderLayout());
		
		jPanelEditButton = new JPanel();
		
		jTAHastag = new JTextArea("#");
		jTAHastag.setBackground(this.getBackground());
		jTAHastag.addKeyListener(controller);
		jBSelectImage = new JButton("Select image");
		jBSelectImage.setActionCommand(MyAction.SELECT_IMAGE.toString());
		jBSelectImage.addActionListener(controller);
		
		jPanelEditButton.add(jTAHastag);
		jPanelEditButton.add(jBSelectImage);
		
		jPanelImage.add(panelDraw, BorderLayout.CENTER);
		jPanelImage.add(jPanelEditButton, BorderLayout.SOUTH);
		add(jPanelImage, BorderLayout.CENTER);
	}
	
	public void updateImage() {
		panelDraw.updateMyBg(getImageFile());
	}

	private void initButton(Controller controller) {
		jPanelButton = new JPanel();
		publishImage = new JButton("Publicar");
		publishImage.addActionListener(controller);
		publishImage.setActionCommand(MyAction.PUBLISH_IMAGE.toString());
		jPanelButton.add(publishImage);
		requestImage = new JButton("Solicitar imagenes");
		requestImage.addActionListener(controller);
		requestImage.setActionCommand(MyAction.REQUEST_IMAGE.toString());
		jPanelButton.add(requestImage);
		add(jPanelButton, BorderLayout.SOUTH);
	}
	
	public int getLengthTextarea() {
		return jTAHastag.getText().length();
	}
	
	public String validateHashtag() throws Exception {
		String aux = jTAHastag.getText();
		if (aux.length() > ConstantGui.MAX_LENHT) {
			jTAHastag.setText("#");
			throw new Exception("Limite de caractez exedido.");
		}else if (!aux.contains("#")) {
			jTAHastag.setText("#");
			throw new Exception("No se encontro #, en la publicacion.");
		}
		aux.replaceAll(" ", "_");
		aux.replaceAll(";", "_");
		return ";" + aux;
	}
	
	public String getImageFile() {
		actualPath = getClass().getResource(ConstantGui.DF_PATH).getPath();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"IMAGE file", "jpg", "png", "gif");
		chooser.setFileFilter(filter);
		chooser.setFileHidingEnabled(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			actualPath = chooser.getSelectedFile().getAbsolutePath();
		return actualPath;
	}
	
	public String getActualPath() {
		return actualPath;
	}

	private void setWindowSize() {
		setSize(ConstantGui.MY_WIDTH, ConstantGui.MY_HEIGTH);
		setMinimumSize(getSize());

	}

}
