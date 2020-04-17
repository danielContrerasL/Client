package logic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Connection {

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private Thread thread;

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		serverResponse();
	}

	public Connection(String ip, int port) throws IOException {
		this.socket = new Socket(ip, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		serverResponse();
	}

	public void send(String data) throws IOException {
		output.writeUTF(data);
	}

	public String readResponse() throws IOException {
		return input.readUTF();
	}

	protected void sendImage(String path) {
		Image a = new ImageIcon(path).getImage();

		BufferedImage bImage = toBufferedImage(a);

		RenderedImage rImage = (RenderedImage) bImage;
		try {
			ImageIO.write(rImage, "JPG", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = bimage.createGraphics();
		g2.drawImage(img, 0, 0, null);
		g2.dispose();

		return bimage;
	}

	private void serverResponse() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						readServerResponse(readResponse());
					} catch (IOException e) {
					}
				}

			}
		});
		thread.start();
	}


	public void close() throws IOException {
		output.close();
		input.close();
		socket.close();
	}

	abstract void readServerResponse(String serverResponse);
}