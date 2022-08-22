package com.bmpl.chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread {
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream();
		out = clientSocket.getOutputStream();
	}
	
	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line = br.readLine();
				System.out.println("Line : " + line);
				// Broadcast the message to rest of the client
				for(ServerWorker serverWorker : server.workers) {
					serverWorker.out.write((line + "\n").getBytes());
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				if(br != null) {
					br.close();
				}
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
				if(clientSocket != null) {
					clientSocket.close();
				}
			}
			catch (Exception e) {
				//e.printStackTrace();
				System.out.println("Invalid...Something went wrong");
			}
		}
	}
	
}
