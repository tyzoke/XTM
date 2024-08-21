package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class Withdrawal extends JFrame implements ActionListener {
	ImageIcon ii1, ii2;
	Image i1;
	JButton b1, b2, b3;
	JLabel l1, l2, l3, l4;
	JTextField tf;
	String cardNo;
	
	public Withdrawal(String cardNo) {
		super("WITHDRAWAL");
		this.cardNo = cardNo;
		
		try {
			ii1 = new ImageIcon(getClass().getResource("images/atm.jpg"));
			i1 = ii1.getImage().getScaledInstance(960, 999, Image.SCALE_DEFAULT);
			ii2 = new ImageIcon(i1);
			l3 = new JLabel(ii2);
			l3.setBounds(0, 0, 960, 999);
			add(l3);
		} catch (Exception e) {
			System.out.println(e + "\nImage NOT FOUND");
		}
		
		l1 = new JLabel("MAXIMUM WITHDRAWAL IS ₹ 10,000");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 20));
		l1.setBounds(185, 350, 400, 20);
		l3.add(l1);
		
		l2 = new JLabel("Please Enter Withdrawal Amount (₹)");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("System", Font.BOLD, 20));
		l2.setBounds(188, 410, 400, 20);
		l3.add(l2);
		
		tf = new JTextField();
		tf.setFont(new Font("Raleway", Font.BOLD, 28));
		tf.setBounds(205, 450, 300, 30);
		l3.add(tf);
		
		b1 = new JButton("WITHDRAWAL");
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
//		new Withdrawal("");
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String amount = tf.getText();
		LocalDate date = LocalDate.now();
		if (ae.getSource() == b1) {
			if (tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter the Withdrawal Amount");
			}
			else {
				try {
					DbConn db = new DbConn();
					ResultSet rs = db.stmt.executeQuery("SELECT * FROM bank WHERE cardno = '" + cardNo + "'");
					int balance = 0;
					while (rs.next()) {
						if (rs.getString("mode").equals("Deposit")) {
							balance += Integer.parseInt(rs.getString("amount"));
						}
						else {
							balance -= Integer.parseInt(rs.getString("amount"));
						}
					}
					if (balance < Integer.parseInt(amount)) {
						JOptionPane.showMessageDialog(null, "Insufficent Balance");
						return;
					}
					db.stmt.executeUpdate("INSERT INTO bank VALUES('" + cardNo + "', '" + date + "', 'Withdrawal', '" + amount + "')");
					JOptionPane.showMessageDialog(null, "₹ " + amount + " Debited Successfully");
					
					setVisible(false);
					new Transactions(cardNo);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error: " + e);
				}
			}
		}
		else if (ae.getSource() == b2) {
			setVisible(false);
			new Transactions(cardNo);
		}
	}
}
