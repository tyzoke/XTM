package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;

public class Deposit extends JFrame implements ActionListener {
	ImageIcon ii1, ii2;
	Image i1;
	String cardNo;
	JTextField tf1;
	JButton b1, b2;
	JLabel l1, l2, l3;
	
	public Deposit(String cardNo) {
		super("DEPOSIT");
		this.cardNo = cardNo;
		
		try {
			ii1 = new ImageIcon(getClass().getResource("images/atm.jpg"));
			i1 = ii1.getImage().getScaledInstance(960, 999, Image.SCALE_DEFAULT);
			ii2 = new ImageIcon(i1);
			l3 = new JLabel(ii2);
			l3.setBounds(0, 0, 960, 999);
			add(l3);
		} catch (Exception e) {
			System.out.println(e + " \nImage NOT FOUND");
		}
		
		l1 = new JLabel("Enter Amount to Deposit (₹)");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 25));
		l1.setBounds(195, 370, 700, 35);
		l3.add(l1);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("Raleway", Font.BOLD, 22));
		tf1.setBounds(210, 420, 290, 25);
		l3.add(tf1);
		
		b1 = new JButton("DEPOSIT");
		b1.setBounds(280, 500, 150, 35);
		l3.add(b1);
		
		b2 = new JButton("BACK");
		b2.setBounds(402, 625, 150, 35);
		l3.add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		setSize(960, 1027);
		setLocation(500, 20);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Deposit("");
//
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String amount = tf1.getText();
		LocalDate date = LocalDate.now();
		if (ae.getSource() == b1) {
			if (tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(null,  "Please enter the Amount that you want to Deposit");
			}
			else {
				try {
					DbConn db = new DbConn();
					db.stmt.executeUpdate("INSERT INTO bank VALUES('" + cardNo + "', '" + date + "', 'Deposit', '" + amount + "')");
					JOptionPane.showMessageDialog(null, "₹ " + amount + " Deposited Successfully");
					setVisible(false);
					new Transactions(cardNo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if (ae.getSource() == b2) {
			setVisible(false);
			new Transactions(cardNo);
		}
		
	}

}
