package ludum.vita.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;

import ludum.vita.actions.LoginAction;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.gui.helpers.JTextFieldLimit;

public class LSMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField usernametxtfld;
	private JPasswordField passwordtxtfld;
	private JButton signInbtn;
	private JButton registerbtn;
	private DatabaseFactory factory;
	private LoginAction logAction;
	private JButton quitbtn;
	private JLabel usernamelbl;
	private JLabel passwordlbl;
	/**
	 * Create the application.
	 * @param factory 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public LSMain(DatabaseFactory factory) {
		this.factory = factory;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() {
		logAction = new LoginAction(factory);
		setBounds(100, 100, 400, 300);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JPanel welcomepnl = new JPanel();
		getContentPane().add(welcomepnl, BorderLayout.CENTER);
		welcomepnl.setLayout(null);
		JLabel lifeScoreTitlelbl = new JLabel("");
		lifeScoreTitlelbl.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\WelcomeLogo.png"));
		lifeScoreTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		lifeScoreTitlelbl.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		lifeScoreTitlelbl.setBounds(10, 11, 384, 90);
		welcomepnl.add(lifeScoreTitlelbl);
		JLabel returnUserlbl = new JLabel("Returning User");
		returnUserlbl.setFont(new Font("ArcadeClassic", Font.PLAIN, 17));
		returnUserlbl.setBounds(10, 112, 134, 18);
		welcomepnl.add(returnUserlbl);

		usernamelbl = new JLabel("Username");
		usernamelbl.setFont(new Font("ArcadeClassic", Font.PLAIN, 17));
		usernamelbl.setBounds(10, 140, 80, 18);
		welcomepnl.add(usernamelbl);

		passwordlbl = new JLabel("Password");
		passwordlbl.setFont(new Font("ArcadeClassic", Font.PLAIN, 17));
		passwordlbl.setBounds(10, 169, 80, 18);
		welcomepnl.add(passwordlbl);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 128, 134, 2);
		welcomepnl.add(separator);
		
		usernametxtfld = new JTextField();
		usernametxtfld.setBounds(100, 141, 274, 19);
		usernametxtfld.setColumns(10);
		usernametxtfld.setDocument(new JTextFieldLimit(30));
		welcomepnl.add(usernametxtfld);

		passwordtxtfld = new JPasswordField();
		passwordtxtfld.setDocument(new JTextFieldLimit(60));
		passwordtxtfld.setColumns(10);
		passwordtxtfld.setBounds(100, 169, 274, 19);
		welcomepnl.add(passwordtxtfld);

		JLabel newUserlbl = new JLabel("New User");
		newUserlbl.setFont(new Font("ArcadeClassic", Font.PLAIN, 17));
		newUserlbl.setBounds(10, 233, 134, 18);
		welcomepnl.add(newUserlbl);
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 249, 134, 2);
		welcomepnl.add(separator_1);

		signInbtn = new JButton("Sign In");
		signInbtn.addActionListener(this);
		signInbtn.setFont(new Font("heavyLOUDedge", Font.PLAIN, 12));
		signInbtn.setBounds(110, 199, 171, 23);
		welcomepnl.add(signInbtn);

		registerbtn = new JButton("Register");
		registerbtn.addActionListener(this);
		registerbtn.setFont(new Font("heavyLOUDedge", Font.PLAIN, 12));
		registerbtn.setBounds(10, 262, 171, 23);
		welcomepnl.add(registerbtn);

		quitbtn = new JButton("Quit");
		quitbtn.addActionListener(this);
		quitbtn.setFont(new Font("heavyLOUDedge", Font.PLAIN, 12));
		quitbtn.setBounds(213, 263, 171, 23);
		welcomepnl.add(quitbtn);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == signInbtn){
			if(usernametxtfld.getText().length() > 0){
				if(new String(passwordtxtfld.getPassword()).length() > 0){
					try {
						if(logAction.login(usernametxtfld.getText(), new String(passwordtxtfld.getPassword()))){
							JOptionPane.showMessageDialog(this, "Login Succssful.");
						} else {
							JOptionPane.showMessageDialog(this, "Login Failed.");
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, e.getMessage());
					}
				} else {
					passwordlbl.setForeground(Color.RED);
					JOptionPane.showMessageDialog(this, "Username length must be greater than 6.");
				}
			} else {
				usernamelbl.setForeground(Color.RED);
				JOptionPane.showMessageDialog(this, "Username length must be greater than 8.");
			}
		} else if (action.getSource() == registerbtn){

		} else if (action.getSource() == quitbtn) {
			System.exit(0);
		}
	}
}
