package xtm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener {
	String cardNo;
	ImageIcon ii1, ii2;
	Image i1;
	JButton b1, b2;
	JLabel l1, l2, l3, l4;
	JPasswordField pf1, pf2;
	
	public Pin(String cardNo) {
		super("PIN");
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
		
		l1 = new JLabel("CHANGE YOUR PIN");
		l1.setFont(new Font("System", Font.BOLD, 25));
		l1.setForeground(Color.WHITE);
		l1.setBounds(240, 350, 800, 35);
		l4.add(l1);
		
		l2 = new JLabel("New PIN:");
		l2.setFont(new Font("System", Font.BOLD, 18));
		l2.setForeground(Color.WHITE);
		l2.setBounds(180, 410, 150, 35);
		l4.add(l2);
		
		l3 = new JLabel("Re-Enter New PIN:");
		l3.setFont(new Font("System", Font.BOLD, 18));
		l3.setForeground(Color.WHITE);
		l3.setBounds(180, 460, 200, 35);
		l4.add(l3);
		
		pf1 = new JPasswordField();
		pf1.setFont(new Font("Raleway", Font.BOLD, 25));
		pf1.setBounds(355, 415, 180, 23);
		l4.add(pf1);
		
		pf2 = new JPasswordField();
		pf2.setFont(new Font("Raleway", Font.BOLD, 25));
		pf2.setBounds(355, 465, 180, 23);
		l4.add(pf2);
		
		b1 = new JButton("CHANGE");
		b1.setBounds(275, 520, 150, 35);
		l4.add(b1);
		
		b2 = new JButton("BACK");
		b2.setBounds(402, 625, 150, 35);
		l4.add(b2);
		
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
//		new Pin("");
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String npin = new String(pf1.getPassword());
		String rpin = new String(pf2.getPassword());
		
		if (ae.getSource() == b1) {
			if (npin.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter New PIN");
			}
			if (rpin.equals("")) {
				JOptionPane.showMessageDialog(null, "Re-Enter New PIN");
			}
			
			if (!npin.equals(rpin)) {
				JOptionPane.showMessageDialog(null, "Entered PIN does not match");
				return;
			}
			
			try {
				DbConn db = new DbConn();
				db.stmt.executeUpdate("UPDATE login SET pin = '" + npin + "' WHERE cardno = '" + cardNo + "'");
				// db.stmt.executeUpdate("UPDATE signup3 SET pin = '" + npin + "' WHERE cardno = '" + cardNo + "'");
				
				JOptionPane.showMessageDialog(null, "PIN changed successfully");
				setVisible(false);
				new Transactions(cardNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (ae.getSource() == b2) {
			setVisible(false);
			new Transactions(cardNo);
		}
	}

}
