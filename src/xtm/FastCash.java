package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class FastCash extends JFrame implements ActionListener {
	String cardNo;
	ImageIcon ii1, ii2;
	Image i1;
	JButton b1, b2, b3, b4, b5, b6, b7;
	JLabel l1, l2;
	
	public FastCash(String cardNo) {
		super("FASTCASH");
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
		
		l1 = new JLabel("SELECT WITHDRAWAL AMOUNT");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 20));
		l1.setBounds(200, 360, 700, 35);
		l2.add(l1);
		
		
		b1 = new JButton("₹ 100");
		b1.setBounds(165, 500, 150, 35);
		l2.add(b1);
		
		b2 = new JButton("₹ 500");
		b2.setBounds(402, 500, 150, 35);
		l2.add(b2);
		
		b3 = new JButton("₹ 1000");
		b3.setBounds(165, 541, 150, 35);
		l2.add(b3);
		
		b4 = new JButton("₹ 2000");
		b4.setBounds(402, 541, 150, 35);
		l2.add(b4);
		
		b5 = new JButton("₹ 5000");
		b5.setBounds(165, 583, 150, 35);
		l2.add(b5);
		
		b6 = new JButton("₹ 10000");
		b6.setBounds(402, 583, 150, 35);
		l2.add(b6);
		
		b7 = new JButton("BACK");
		b7.setBounds(402, 625, 150, 35);
		l2.add(b7);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		
		setSize(960, 1027);
		setLocation(500, 20);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new FastCash("");
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String amount = ((JButton)ae.getSource()).getText().substring(2);
		LocalDate date = LocalDate.now();
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
		
		if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
			JOptionPane.showMessageDialog(null, "Insufficient Balance");
			return;
		}
		
		if (ae.getSource() == b7) {
			setVisible(false);
			new Transactions(cardNo);
		}
		else {
			try {
				DbConn db = new DbConn();
				db.stmt.executeUpdate("INSERT INTO bank VALUES('" + cardNo + "', '" + date + "', 'Withdrawal', '" + amount + "')");
				JOptionPane.showMessageDialog(null, "₹ " + amount + " Debited Successfully");
				setVisible(false);
				new Transactions(cardNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
