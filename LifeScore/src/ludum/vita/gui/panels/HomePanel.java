package ludum.vita.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class HomePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel namelbl;

	/**
	 * Create the panel.
	 */
	public HomePanel() {

		setSize(553, 381);
		setLayout(null);
		
		namelbl = new JLabel("New label");
		namelbl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		namelbl.setBounds(10, 11, 208, 27);
		add(namelbl);
		
		JLabel topMissionlbl = new JLabel("Total Five Missions:");
		topMissionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		topMissionlbl.setBounds(10, 49, 131, 21);
		add(topMissionlbl);
		
		JLabel topFiveMissionslbl = new JLabel("<html>\r\n<p>1</p>\r\n<p>2</p>\r\n<p>3</p>\r\n<p>4</p>\r\n<p>5</p>\r\n</html>");
		topFiveMissionslbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		topFiveMissionslbl.setBounds(10, 81, 208, 105);
		add(topFiveMissionslbl);
		
		JPanel acheivementPanel = new JPanel();
		acheivementPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		acheivementPanel.setBounds(10, 197, 533, 173);
		add(acheivementPanel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(232, 49, 1, 140);
		add(separator);
		
		JLabel pointslbl = new JLabel("Total Points:");
		pointslbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pointslbl.setBounds(246, 49, 87, 21);
		add(pointslbl);
		
		JLabel dailylbl = new JLabel("Daily:");
		dailylbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		dailylbl.setBounds(243, 76, 50, 21);
		add(dailylbl);
		
		JLabel weeklylbl = new JLabel("Weekly:");
		weeklylbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		weeklylbl.setBounds(243, 101, 63, 21);
		add(weeklylbl);
		
		JLabel monthlylbl = new JLabel("Monthy:");
		monthlylbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		monthlylbl.setBounds(243, 126, 63, 21);
		add(monthlylbl);
		
		JLabel yearlylbl = new JLabel("Yearly:");
		yearlylbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		yearlylbl.setBounds(243, 151, 63, 21);
		add(yearlylbl);
	}
}
