package com.bmpl.chatapp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import com.bmpl.chatapp.networking.Client;

public class ChatScreen extends JFrame {
	private JTextField textField;
	private Client client;
	private JTextArea textArea = new JTextArea();
	
	private void sendMessage() throws IOException {
		String message = textField.getText();
//		System.out.println("Message : " + message);
		client.sendMessage(message);
	}
	
	public ChatScreen() throws UnknownHostException, IOException {
		client = new Client(textArea);
		setBounds(100, 100, 962, 578);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setBounds(10, 10, 928, 421);
		getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Send Message");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sendMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(674, 483, 264, 48);
		getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(10, 483, 638, 48);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		ChatScreen screen = new ChatScreen();
		screen.setVisible(true);
	}

}
