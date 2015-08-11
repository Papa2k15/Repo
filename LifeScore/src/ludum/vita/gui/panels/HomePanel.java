package ludum.vita.gui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

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
		
		JLabel lblTotalMissions = new JLabel("Total Missions");
		lblTotalMissions.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotalMissions.setBounds(10, 116, 167, 21);
		add(lblTotalMissions);
	}
}
