package ludum.vita.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;

import ludum.vita.dao.DatabaseFactory;

public class LifeScore extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton homebtn;
	private JPanel loggedInpnl;
	private JPanel menupnl;
	private JButton missionControlbtn;
	private MissionControlPanel missionPanel;
	private MissionTrackerPanel missionTracker;
	private DatabaseFactory factory;
	private String loggedLSUID;
	private JButton missionTrackerbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Testing 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseFactory factory = DatabaseFactory.getProductionInstance(); 
					String gregsLSUID = "28cf1e5615f9a36b98084f30336c6a19554baead";
					LifeScore window = new LifeScore(gregsLSUID, factory);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public LifeScore(String loggedLSUID, DatabaseFactory factory) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		this.loggedLSUID = loggedLSUID;
		initialize(factory);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize(DatabaseFactory factory) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		this.factory = factory;
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setTitle("Life Score");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setResizable(false);
		setVisible(false);
		setBounds(100, 100, 660, 456);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		loggedInpnl = new JPanel();
		loggedInpnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		loggedInpnl.setBounds(0, 0, 654, 427);
		getContentPane().add(loggedInpnl);

		homebtn = new JButton("");
		homebtn.addActionListener(this);
		homebtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\HomeButton.png"));
		homebtn.setBorderPainted(false);
		homebtn.setFocusPainted(false);
		homebtn.setContentAreaFilled(false);
		homebtn.setRolloverEnabled(true);
		homebtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\HomeButton_Hover.png"));
		homebtn.setBounds(12, 13, 68, 68);

		missionControlbtn = new JButton("");
		missionControlbtn.addActionListener(this);
		missionControlbtn.setBounds(12, 92, 68, 68);
		missionControlbtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\MissionControlButton.png"));
		missionControlbtn.setBorderPainted(false);
		missionControlbtn.setFocusPainted(false);
		missionControlbtn.setContentAreaFilled(false);
		missionControlbtn.setRolloverEnabled(true);
		missionControlbtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\MissionControlButton_Hover.png"));

		loggedInpnl.setLayout(null);

		menupnl = new JPanel();
		menupnl.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menupnl.setBounds(89, 33, 553, 381);
		loggedInpnl.add(menupnl);
		menupnl.setLayout(null);
		loggedInpnl.add(homebtn);
		loggedInpnl.add(missionControlbtn);

		JLabel lblWelcome = null;
		try {
			lblWelcome = new JLabel("Welcome " + factory.getUsersDAO().getUserWithLSUID(loggedLSUID).getUserName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			System.exit(0);
		}
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(89, 0, 553, 32);
		loggedInpnl.add(lblWelcome);

		missionTrackerbtn = new JButton("");
		missionTrackerbtn.addActionListener(this);
		missionTrackerbtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\TrackMissionButton.png"));
		missionTrackerbtn.setBorderPainted(false);
		missionTrackerbtn.setFocusPainted(false);
		missionTrackerbtn.setContentAreaFilled(false);
		missionTrackerbtn.setRolloverEnabled(true);
		missionTrackerbtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\TrackMissionButton_Hover.png"));
		missionTrackerbtn.setBounds(12, 171, 68, 68);
		loggedInpnl.add(missionTrackerbtn);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == homebtn){
			if(missionTracker != null){
				menupnl.remove(missionTracker);
				missionTracker = null;
			}
			if(missionPanel != null){
				menupnl.remove(missionPanel);
				missionPanel = null;
			}
			menupnl.repaint();
			menupnl.revalidate();
			menupnl.updateUI();
		} else if (event.getSource() == missionControlbtn){
			if(missionTracker != null){
				menupnl.remove(missionTracker);
				missionTracker = null;
			}
			if(missionPanel == null){
				missionPanel = new MissionControlPanel(loggedLSUID, factory);
				menupnl.add(missionPanel);
			}
			menupnl.repaint();
			menupnl.revalidate();
			menupnl.updateUI();
		} else if(event.getSource() == missionTrackerbtn){
			if(missionPanel != null){
				menupnl.remove(missionPanel);
				missionPanel = null;
			}
			if(missionTracker == null){
				missionTracker = new MissionTrackerPanel(loggedLSUID, factory);
				menupnl.add(missionTracker);
			}
			menupnl.repaint();
			menupnl.revalidate();
			menupnl.updateUI();
		}
	}

	public void clearPanel(){

	}
}
