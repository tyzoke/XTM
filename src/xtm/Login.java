package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
	JButton b1, b2, b3, b4;
	JLabel l1, l2, l3, l4;
	JPasswordField pf;
	JTextField tf;
	ImageIcon ii1, ii2;
	Image i1;
	
	Login() {
		setTitle("X-AUTOMATED TELLER MACHINE");
		
		try {
			ii1 = new ImageIcon(getClass().getResource("images/logo.jpeg"));
			Image i1 = ii1.getImage().getScaledInstance(150, 135, Image.SCALE_DEFAULT);
			ii2 = new ImageIcon(i1);
			l4 = new JLabel(ii2);
			l4.setBounds(70, 10, 150, 135);
			add(l4);
		}
		catch (Exception e) {
			System.out.println(e + "\nImage NOT FOUND");
		}
		
		l1 = new JLabel("WELCOME TO XTM");
		l1.setBounds(300, 40, 500, 50);
		l1.setFont(new Font("Osward", Font.BOLD, 40));
		add(l1);
		
		l2 = new JLabel("Card No:");
		l2.setBounds(260, 150, 375, 35);
		l2.setFont(new Font("Raleway", Font.BOLD, 30));
		add(l2);
		
		tf = new JTextField(15);
		tf.setBounds(410, 150, 300, 35);
		tf.setFont(new Font("Arial", Font.BOLD, 20));
		add(tf);
		
		l3 = new JLabel("PIN:");
		l3.setBounds(260, 220, 375, 35);
		l3.setFont(new Font("Raleway", Font.BOLD, 30));
		add(l3);
		
		pf = new JPasswordField(15);
		pf.setBounds(410, 220, 300, 35);
		pf.setFont(new Font("Arial", Font.BOLD, 20));
		add(pf);
		
		b1 = new JButton("SIGN IN");
		b1.setBounds(360, 300, 100, 50);
		b1.setFont(new Font("Arial", Font.BOLD, 16));
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		add(b1);
		
		b2 = new JButton("CLEAR");
		b2.setBounds(520, 300, 100, 50);
		b2.setFont(new Font("Arial", Font.BOLD, 16));
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		add(b2);
		
		b3 = new JButton("SIGN UP");
		b3.setBounds(375, 370, 230, 50);
		b3.setFont(new Font("Arial", Font.BOLD, 16));
		b3.setBackground(Color.BLACK);
		b3.setForeground(Color.WHITE);
		add(b3);
		
		b4 = new JButton("CREATE DATABASE");
		b4.setBounds(375, 438, 230, 50);
		b4.setFont(new Font("Arial", Font.BOLD, 16));
		b4.setBackground(Color.BLACK);
		b4.setForeground(Color.WHITE);
		add(b4);
			
		getContentPane().setBackground(Color.WHITE);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	
		setSize(1000, 600);
		setLocation(500, 200);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		try {
			if (ae.getSource() == b1) {
				DbConn db = new DbConn();
				String cardNo = tf.getText();
				String pin = new String(pf.getPassword());
				String q = "SELECT * FROM login WHERE cardNo = '" + cardNo + "' and pin = '" + pin + "'";
				
				ResultSet rs = db.stmt.executeQuery(q);
				if (rs.next()) {
					setVisible(false);
					new Transactions(cardNo);
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
				}
			}
			else if (ae.getSource() == b2) {
				tf.setText("");
				pf.setText("");
			}
			else if (ae.getSource() == b3) {
				setVisible(false);
				// new SignUp().setVisible(true);
			}
			else if (ae.getSource() == b4) {
				try {
					new CreateDb();
					JOptionPane.showMessageDialog(null, "DATABASE CREATED");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "DATABASE NOT CREATED");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
