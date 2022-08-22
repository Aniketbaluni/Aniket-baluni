package com.bmpl.chatapp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.bmpl.chatapp.dao.UserDAO;
import com.bmpl.chatapp.dto.UserDTO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		LoginScreen window = new LoginScreen();
	}
	UserDAO userDAO = new UserDAO();
	public LoginScreen() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Screen");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(338, 10, 276, 67);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Email ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(88, 138, 318, 42);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(88, 262, 318, 42);
		getContentPane().add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(411, 138, 409, 47);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(411, 263, 409, 47);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doLogin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnNewButton.setBounds(147, 402, 225, 67);
		getContentPane().add(btnNewButton);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRegister();
			}
		});
		
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnRegister.setBounds(544, 402, 225, 67);
		getContentPane().add(btnRegister);
		initialize();
	}
	
	private void doLogin() throws UnknownHostException, IOException {
		String userid = textField.getText();
		char []password = passwordField.getPassword();
		UserDTO dto = new UserDTO(userid, password);
		try {
			boolean res = userDAO.login(dto);
			if(res) {
				JOptionPane.showMessageDialog(this, "Login Success...");
				setVisible(false);
				dispose();
				ChatScreen window = new ChatScreen();
				window.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Login Failed...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void doRegister() {
		String userid = textField.getText();
//		String password = passwordField.getText();
		char []password = passwordField.getPassword();
//		System.out.println(userid + "," + password);
		UserDTO dto = new UserDTO(userid, password);
		try {
			int record = userDAO.register(dto);
			if(record > 0) {
				JOptionPane.showMessageDialog(this, "Registred Successfully...");
			}
			else {
				JOptionPane.showMessageDialog(this, "Register Fail...");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void initialize() {
		setBounds(100, 100, 962, 578);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
