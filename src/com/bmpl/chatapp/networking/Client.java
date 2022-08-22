package com.bmpl.chatapp.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	JTextArea textArea;
	ClientWorker clientWorker;
	
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = 9999;
		String IP = "192.168.1.33";
		socket = new Socket(IP, PORT);
		System.out.println("Client Arrived...");
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		readMessage();
		
//		Scanner scanner = new Scanner(System.in);
//		String msg = scanner.nextLine();
//		OutputStream out = socket.getOutputStream();	// write bytes on network
//		out.write(msg.getBytes());	// convert message into bytes
//		out.close();
//		socket.close();
	}
	
	public void readMessage() {
		clientWorker = new ClientWorker(in, textArea);
		clientWorker.start();
	}
	
	public void sendMessage(String msg) throws IOException {
//		System.out.println("Message : " + msg);
		out.write((msg+"\n").getBytes());
	}
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		Client obj = new Client();
//	}

}
