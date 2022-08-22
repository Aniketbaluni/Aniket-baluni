package com.bmpl.chatapp.networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<ServerWorker>();
	
	public Server() throws IOException {
		int PORT = 9999;
		serverSocket = new ServerSocket(PORT);
		while(true) {
			System.out.println("Server Started...Waiting for Client...");
			handleClient();
		}
	}
	
	public void handleClient() throws IOException {
		Socket socket = serverSocket.accept();
		System.out.println("Connected with Client...");
		
		//Thread per client
		ServerWorker serverWorker = new ServerWorker(socket, this);
		workers.add(serverWorker);
		serverWorker.start();
	}
	
	// For Single Client
//	public Server() throws IOException {
//		int PORT = 9999;
//		serverSocket = new ServerSocket(PORT);
//		System.out.println("Server Started...Waiting for client...");
//		Socket socket = serverSocket.accept();	// handshaking with client
//		System.out.println("Connected with Client...");
////		read bytes from network
//		InputStream in = socket.getInputStream();
//		byte []arr = in.readAllBytes();
//		String str = new String(arr);
//		System.out.println("Client : " + str);
//		in.close();
////		serverSocket.close();
//	}
	
	public static void main(String[] args) throws IOException {
		Server obj = new Server();
	}

}
