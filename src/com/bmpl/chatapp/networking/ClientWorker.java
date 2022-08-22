package com.bmpl.chatapp.networking;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class ClientWorker extends Thread {
	private InputStream in;
	private JTextArea textArea;
	public ClientWorker(InputStream in, JTextArea textArea) {
		this.in = in;
		this.textArea = textArea;
	}
	
	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line = br.readLine();
				System.out.println("Line : " + line);
				textArea.setText(textArea.getText() + "\n" + line);
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
			}
			catch (Exception e) {
				//e.printStackTrace();
				System.out.println("Invalid...Something went wrong");
			}
		}
	}

}
