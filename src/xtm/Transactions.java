package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener {
	Image i1;
	ImageIcon ii1, ii2;
	JLabel l1, l2;
	JButton b1, b2, b3, b4, b5, b6, b7;
	String cardNo;
	
	Transactions(String cardNo) {
		super("TRANSACTIONS");
		this.cardNo = cardNo;
		
		try {
			ii1 = new ImageIcon(getClass().getResource("images/atm.jpg"));
			i1 = ii1.getImage().getScaledInstance(960, 999, Image.SCALE_DEFAULT);
			ii2 = new ImageIcon(i1);
			l2 = new JLabel(ii2);
			l2.setBounds(0, 0, 960, 999);
			add(l2);
		} catch (Exception e) {
			System.out.println(e + " \nImage NOT FOUND");
		}
		
		l1 = new JLabel("Please Select Your Transaction");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 20));
		l1.setBounds(211, 400, 700, 35);
		l2.add(l1);
		
		b1 = new JButton("DEPOSIT");
		b1.setBounds(165, 500, 150, 35);
		l2.add(b1);
		
		b2 = new JButton("CASH WITHDRAWAL");
		b2.setBounds(402, 500, 150, 35);
		l2.add(b2);
		
		b3 = new JButton("FAST CASH");
		b3.setBounds(165, 541, 150, 35);
		l2.add(b3);
		
		b4 = new JButton("MINI STATEMENT");
		b4.setBounds(402, 541, 150, 35);
		l2.add(b4);
		
		b5 = new JButton("PIN CHANGE");
		b5.setBounds(165, 583, 150, 35);
		l2.add(b5);
		
		b6 = new JButton("BALANCE ENQUIRY");
		b6.setBounds(402, 583, 150, 35);
		l2.add(b6);
		
		b7 = new JButton("EXIT");
		b7.setBounds(165, 625, 150, 35);
		l2.add(b7);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		
		setSize(960, 1027);
		setLocation(500, 20);
		setLayout(null);
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Transactions("");

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == b1) {
			setVisible(false);
			new Deposit(cardNo);
		}
		else if (ae.getSource() == b2) {
			setVisible(false);
			new Withdrawal(cardNo);
		}
		else if (ae.getSource() == b3) {
			setVisible(false);
			new FastCash(cardNo);
		}
		else if (ae.getSource() == b4) {
			setVisible(false);
			new MiniStatement(cardNo);
		}
		else if (ae.getSource() == b5) {
			setVisible(false);
			new Pin(cardNo);
		}
		else if (ae.getSource() == b6) {
			setVisible(false);
			new Balance(cardNo);
		}
		else if (ae.getSource() == b7) {
			System.exit(0);
		}
		
	}

}
