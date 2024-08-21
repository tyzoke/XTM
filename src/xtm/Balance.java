package xtm;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Balance extends JFrame implements ActionListener {
	String cardNo;
	JLabel l1, l2, l3;
	JButton b1, b2, b3;
	JTextField tf1, tf2;
	ImageIcon ii1, ii2;
	Image i1;
	
	Balance(String cardNo) {
		super("BALANCE");
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
		
		l1 = new JLabel("Balance");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 30));
		l1.setBounds(300, 400, 700, 35);
		l3.add(l1);
		
		l2 = new JLabel();
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("System", Font.BOLD, 30));
		l2.setBounds(300, 470, 700, 35);
		l3.add(l2);
		
		b1 = new JButton("BACK");
		b1.setBounds(402, 625, 150, 35);
		l3.add(b1);
		
		int balance = 0;
		
		try {
			DbConn db = new DbConn();
			ResultSet rs = db.stmt.executeQuery("SELECT * FROM bank WHERE cardno = '" + cardNo + "'");
			while (rs.next()) {
				if (rs.getString("mode").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				}
				else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		l2.setText("₹ " + balance);
		
		b1.addActionListener(this);
		
		setSize(960, 1027);
		setLocation(500, 20);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Balance("");
//
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		setVisible(false);
		new Transactions(cardNo);
	}

}
