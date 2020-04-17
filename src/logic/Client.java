package logic;

import java.io.IOException;

import constant.MyCommand;

public class Client extends Connection {

	public Client(String ip, int port) throws IOException {
		super(ip, port);
	}

	@Override
	void readServerResponse(String serverResponse) {
		System.out.println(serverResponse);

	}
	
	public void sendImage(String hastag, String path) {
		try {
			super.send(MyCommand.PUBLISH_IMAGE.getType() + hastag);
			super.sendImage(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println("Client start... Sending...");
//		try {
//			Client client = new Client("127.0.0.1", 3001);
//			client.send("/post");
//			client.send("panda.png");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//	}

}