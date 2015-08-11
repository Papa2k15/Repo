package ludum.vita.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import ludum.vita.actions.RegisterAction;
import ludum.vita.dao.DatabaseFactory;

public class LSRegister extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstnametxtfld;
	private JTextField lastnametxtfld;
	private JTextField usernametxtfld;
	private JTextField emailtxtfld;
	private JPasswordField passwordtxtfld;
	private JPasswordField confirmtxtfld;
	private JButton returnbtn;
	private JButton registerbtn;
	private RegisterAction register;
	private LSMain main;
	
	/**
	 * Create the frame.
	 */
	public LSRegister(DatabaseFactory factory, LSMain main) {
		register = new RegisterAction(factory);
		this.main = main;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(534, 300);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel userNamelbl = new JLabel("Username:");
		userNamelbl.setFont(new Font("Arcade", Font.PLAIN, 15));
		userNamelbl.setBounds(10, 127, 70, 20);
		panel.add(userNamelbl);
		
		JLabel passwordlbl = new JLabel("Password:");
		passwordlbl.setFont(new Font("Arcade", Font.PLAIN, 15));
		passwordlbl.setBounds(10, 158, 70, 20);
		panel.add(passwordlbl);
		
		JLabel confirmPasswordlbl = new JLabel("Confirm:");
		confirmPasswordlbl.setFont(new Font("Arcade", Font.PLAIN, 15));
		confirmPasswordlbl.setBounds(256, 158, 70, 20);
		panel.add(confirmPasswordlbl);
		
		JLabel firstnamelbl = new JLabel("Firstname:");
		firstnamelbl.setFont(new Font("Arcade", Font.PLAIN, 15));
		firstnamelbl.setBounds(10, 96, 70, 20);
		panel.add(firstnamelbl);
		
		JLabel lastnamelbl = new JLabel("Lastname:");
		lastnamelbl.setFont(new Font("Arcade", Font.PLAIN, 15));
		lastnamelbl.setBounds(256, 96, 70, 20);
		panel.add(lastnamelbl);
		
		JLabel emaillbl = new JLabel("Email:");
		emaillbl.setFont(new Font("Arcade", Font.PLAIN, 15));
		emaillbl.setBounds(10, 189, 70, 20);
		panel.add(emaillbl);
		
		firstnametxtfld = new JTextField();
		firstnametxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		firstnametxtfld.setBounds(78, 98, 168, 20);
		panel.add(firstnametxtfld);
		firstnametxtfld.setColumns(10);
		
		lastnametxtfld = new JTextField();
		lastnametxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lastnametxtfld.setColumns(10);
		lastnametxtfld.setBounds(330, 98, 168, 20);
		panel.add(lastnametxtfld);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LSRegister.class.getResource("/ludum/resources/images/WelcomeLogo.png")));
		lblNewLabel.setBounds(136, 0, 372, 81);
		panel.add(lblNewLabel);
		
		usernametxtfld = new JTextField();
		usernametxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		usernametxtfld.setColumns(10);
		usernametxtfld.setBounds(78, 129, 420, 20);
		panel.add(usernametxtfld);
		
		emailtxtfld = new JTextField();
		emailtxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailtxtfld.setColumns(10);
		emailtxtfld.setBounds(78, 189, 420, 20);
		panel.add(emailtxtfld);
		
		passwordtxtfld = new JPasswordField();
		passwordtxtfld.setBounds(78, 158, 168, 20);
		panel.add(passwordtxtfld);
		
		confirmtxtfld = new JPasswordField();
		confirmtxtfld.setBounds(330, 160, 168, 20);
		panel.add(confirmtxtfld);
		
		registerbtn = new JButton("Register");
		registerbtn.addActionListener(this);
		registerbtn.setFont(new Font("ArcadeClassic", Font.PLAIN, 15));
		registerbtn.setBounds(329, 220, 121, 23);
		panel.add(registerbtn);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("ArcadeClassic", Font.PLAIN, 20));
		lblRegister.setBounds(10, 11, 116, 74);
		panel.add(lblRegister);
		
		returnbtn = new JButton("Return");
		returnbtn.addActionListener(this);
		returnbtn.setFont(new Font("ArcadeClassic", Font.PLAIN, 15));
		returnbtn.setBounds(66, 220, 121, 23);
		panel.add(returnbtn);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registerbtn){
			try {
				register.register(firstnametxtfld.getText(), lastnametxtfld.getText(), usernametxtfld.getText(), new String(passwordtxtfld.getPassword()), new String(confirmtxtfld.getPassword()), emailtxtfld.getText());
				JOptionPane.showMessageDialog(this, "Registration Successful");
				setVisible(false);
				main.setVisible(true);
				clearText();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		} else if(e.getSource() == returnbtn){
			setVisible(false);
			main.setVisible(true);
			clearText();
		}
		
	}

	private void clearText() {
		firstnametxtfld.setText("");
		lastnametxtfld.setText("");
		usernametxtfld.setText("");
		passwordtxtfld.setText("");
		confirmtxtfld.setText("");
		emailtxtfld.setText("");
	}
}
