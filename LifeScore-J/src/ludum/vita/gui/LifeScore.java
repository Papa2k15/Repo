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

import ludum.vita.actions.PointsAction;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.gui.panels.HomePanel;
import ludum.vita.gui.panels.MissionControlPanel;
import ludum.vita.gui.panels.MissionTrackerPanel;

public class LifeScore extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton homebtn;
	private JPanel loggedInpnl;
	private JPanel menupnl;
	private JButton missionControlbtn;
	private MissionControlPanel missionPanel;
	private MissionTrackerPanel missionTracker;
	private DatabaseFactory factory;
	private PointsAction pointsAction;
	private String loggedLSUID;
	private JButton missionTrackerbtn;
	private HomePanel homePanel;
	private JLabel lblNewLabel;
	private JButton gamebtn;
	private JButton settingsbtn;
	private JButton logoutbtn;
	private JButton aboutbtn;
	private LSMain main;

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
					LifeScore window = new LifeScore(gregsLSUID, factory, new LSMain(factory));
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param lsMain 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public LifeScore(String loggedLSUID, DatabaseFactory factory, LSMain main) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		this.loggedLSUID = loggedLSUID;
		this.main = main;
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
		try {
			pointsAction = new PointsAction(factory, loggedLSUID);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setTitle("Life Score");
		setSize(660, 456);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		loggedInpnl = new JPanel();
		loggedInpnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		loggedInpnl.setBounds(0, 0, 654, 427);
		getContentPane().add(loggedInpnl);

		homebtn = new JButton("");
		homebtn.setToolTipText("Home");
		homebtn.addActionListener(this);
		homebtn.setIcon(new ImageIcon(getClass().getResource("/ludum/resources/images/HomeButton.png")));
		homebtn.setBorderPainted(false);
		homebtn.setFocusPainted(false);
		homebtn.setContentAreaFilled(false);
		homebtn.setRolloverEnabled(true);
		homebtn.setRolloverIcon(new ImageIcon(getClass().getResource("/ludum/resources/images/HomeButton_Hover.png")));
		homebtn.setBounds(12, 13, 68, 68);

		missionControlbtn = new JButton("");
		missionControlbtn.setToolTipText("Mission Control");
		missionControlbtn.addActionListener(this);
		missionControlbtn.setBounds(12, 92, 68, 68);
		missionControlbtn.setIcon(new ImageIcon(getClass().getResource("/ludum/resources/images/MissionControlButton.png")));
		missionControlbtn.setBorderPainted(false);
		missionControlbtn.setFocusPainted(false);
		missionControlbtn.setContentAreaFilled(false);
		missionControlbtn.setRolloverEnabled(true);
		missionControlbtn.setRolloverIcon(new ImageIcon(getClass().getResource("/ludum/resources/images/MissionControlButton_Hover.png")));

		loggedInpnl.setLayout(null);

		menupnl = new JPanel();
		menupnl.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menupnl.setBounds(89, 33, 553, 381);
		loggedInpnl.add(menupnl);
		menupnl.setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LifeScore.class.getResource("/ludum/resources/images/WelcomeLogo.png")));
		lblNewLabel.setBounds(67, 21, 413, 82);
		menupnl.add(lblNewLabel);
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
		lblWelcome.setBounds(89, 0, 244, 32);
		loggedInpnl.add(lblWelcome);

		missionTrackerbtn = new JButton("");
		missionTrackerbtn.setToolTipText("Mission Tracker");
		missionTrackerbtn.addActionListener(this);
		missionTrackerbtn.setIcon(new ImageIcon(getClass().getResource("/ludum/resources/images/TrackMissionButton.png")));
		missionTrackerbtn.setBorderPainted(false);
		missionTrackerbtn.setFocusPainted(false);
		missionTrackerbtn.setContentAreaFilled(false);
		missionTrackerbtn.setRolloverEnabled(true);
		missionTrackerbtn.setRolloverIcon(new ImageIcon(getClass().getResource("/ludum/resources/images/TrackMissionButton_Hover.png")));
		missionTrackerbtn.setBounds(12, 171, 68, 68);
		loggedInpnl.add(missionTrackerbtn);

		gamebtn = new JButton("game");
		gamebtn.setBounds(12, 250, 68, 68);
		loggedInpnl.add(gamebtn);

		settingsbtn = new JButton("settings");
		settingsbtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		settingsbtn.setBounds(12, 329, 68, 68);
		loggedInpnl.add(settingsbtn);

		logoutbtn = new JButton("logout");
		logoutbtn.addActionListener(this);
		logoutbtn.setBounds(567, 6, 77, 23);
		loggedInpnl.add(logoutbtn);

		aboutbtn = new JButton("about");
		aboutbtn.setBounds(480, 6, 77, 23);
		loggedInpnl.add(aboutbtn);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(lblNewLabel.isVisible()){
			lblNewLabel.setVisible(false);
		}
		try {
			pointsAction.checkMissionsComplete();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		if(event.getSource() == homebtn){
			if(missionTracker != null){
				menupnl.remove(missionTracker);
				missionTracker = null;
			}
			if(missionPanel != null){
				menupnl.remove(missionPanel);
				missionPanel = null;
			}
			if(homePanel == null){
				homePanel = new HomePanel(pointsAction);
				menupnl.add(homePanel);
			}
			menupnl.repaint();
			menupnl.revalidate();
			menupnl.updateUI();
		} else if (event.getSource() == missionControlbtn){
			if(missionTracker != null){
				menupnl.remove(missionTracker);
				missionTracker = null;
			}
			if(homePanel != null){
				menupnl.remove(homePanel);
				homePanel = null;
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
			if(homePanel != null){
				menupnl.remove(homePanel);
				homePanel = null;
			}
			if(missionTracker == null){
				missionTracker = new MissionTrackerPanel(loggedLSUID, factory);
				menupnl.add(missionTracker);
			}
			menupnl.repaint();
			menupnl.revalidate();
			menupnl.updateUI();
		} else if (event.getSource() == logoutbtn){
			setVisible(false);
			main.setVisible(true);;
		}
	}


}
