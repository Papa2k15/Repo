package ludum.vita.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

public class LifeScore {

	private JFrame lifeScoreFrame;
	private JButton homebtn;
	private JPanel loggedInpnl;
	private JPanel menupnl;
	private JButton missionControlbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LifeScore window = new LifeScore();
					window.lifeScoreFrame.setVisible(true);
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
	public LifeScore() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		lifeScoreFrame = new JFrame();
		lifeScoreFrame.setTitle("Life Score");
		lifeScoreFrame.setResizable(false);
		lifeScoreFrame.setBounds(100, 100, 660, 456);
		lifeScoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lifeScoreFrame.getContentPane().setLayout(null);
		
		loggedInpnl = new JPanel();
		loggedInpnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		loggedInpnl.setBounds(0, 0, 654, 427);
		lifeScoreFrame.getContentPane().add(loggedInpnl);
		
		homebtn = new JButton("");
		homebtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\HomeButton.png"));
		homebtn.setBorderPainted(false);
		homebtn.setFocusPainted(false);
		homebtn.setContentAreaFilled(false);
		homebtn.setRolloverEnabled(true);
		homebtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\HomeButton_Hover.png"));
		homebtn.setBounds(12, 13, 68, 68);

		
		missionControlbtn = new JButton("");
		missionControlbtn.setBounds(12, 92, 68, 68);
		missionControlbtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\MissionControlButton.png"));
		missionControlbtn.setBorderPainted(false);
		missionControlbtn.setFocusPainted(false);
		missionControlbtn.setContentAreaFilled(false);
		missionControlbtn.setRolloverEnabled(true);
		missionControlbtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\MissionControlButton_Hover.png"));
		
		loggedInpnl.setLayout(null);
		
		menupnl = new JPanel();
		menupnl.setBounds(89, 13, 553, 401);
		loggedInpnl.add(menupnl);
		loggedInpnl.add(homebtn);
		loggedInpnl.add(missionControlbtn);
	}
}
