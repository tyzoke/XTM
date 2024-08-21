package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {
	ImageIcon ii1, ii2;
	Image i1;
	JButton b1;
	JLabel l1, l2, l3, l4;
	JScrollPane sp;
	JTextArea ja;
	String cardNo;
	
	public MiniStatement(String cardNo) {
		super("Mini Statement");
		this.cardNo = cardNo;
		
		try {
			ii1 = new ImageIcon(getClass().getResource("images/atm.jpg"));
			i1 = ii1.getImage().getScaledInstance(960, 999, Image.SCALE_DEFAULT);
			ii2 = new ImageIcon(i1);
			l4 = new JLabel(ii2);
			l4.setBounds(0, 0, 960, 999);
			add(l4);
		} catch (Exception e) {
			System.out.println(e + " \nImage NOT FOUND");
		}
		
		l1 = new JLabel("YOUR PREVIOUS TRANSACTIONS");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 20));
		l1.setBounds(190, 345, 700, 35);
		l4.add(l1);
		
		try {
			l2 = new JLabel("Card Number: " + cardNo.substring(0, 4) + "XXXXXXXX" + cardNo.substring(12));
			l2.setForeground(Color.WHITE);
			l2.setFont(new Font("System", Font.BOLD, 17));
			l2.setBounds(213, 380, 300, 20);
			l4.add(l2);
		} catch (Exception e) {};
		
		l3 = new JLabel();
		l3.setForeground(Color.WHITE);
		l3.setFont(new Font("System", Font.BOLD, 17));
		l3.setBounds(250, 510, 400, 190);
		l4.add(l3);
		
		ja = new JTextArea();
		ja.setForeground(Color.BLACK);
		ja.setFont(new Font("System", Font.BOLD, 22));
		
		sp = new JScrollPane(ja);
		sp.setBounds(170, 410, 375, 175);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		l4.add(sp);
		
		int balance = 0;
		
		try {
			DbConn db = new DbConn();
			ResultSet rs = db.stmt.executeQuery("SELECT * FROM bank WHERE cardno = '" + cardNo + "'");
			while (rs.next()) {
				ja.append("    " + rs.getString("date") + "  " + rs.getString("mode") + "  " + rs.getString("amount") + "\n");
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

		l3.setText("Your Total Balance is ₹ " + balance);
		b1 = new JButton("BACK");
		b1.setBounds(402, 625, 150, 35);
		l4.add(b1);
		
		b1.addActionListener(this);
		
		setSize(960, 1027);
		setLocation(500, 20);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new MiniStatement("1234123412341234");
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		setVisible(false);
		new Transactions(cardNo);
	}

}
