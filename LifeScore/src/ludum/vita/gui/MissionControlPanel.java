package ludum.vita.gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class MissionControlPanel extends JPanel {
	
	private JTextField titletxtfld;
	private JScrollPane missionScrollPane;
	private JList<String> missionList;
	private DefaultListModel<String> missionModel;
	

	/**
	 * Create the panel.
	 */
	public MissionControlPanel() {
		
		setSize(553, 381);
		setLayout(null);
		
		titletxtfld = new JTextField();
		titletxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		titletxtfld.setText("");
		titletxtfld.setBounds(52, 65, 491, 25);
		add(titletxtfld);
		titletxtfld.setColumns(10);
		
		JLabel lblMissionControl = new JLabel("Mission Control");
		lblMissionControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissionControl.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMissionControl.setBounds(10, 11, 533, 25);
		add(lblMissionControl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 34, 533, 2);
		add(separator);
		
		JLabel newMissionlbl = new JLabel("New Mission---------------------------------------------");
		newMissionlbl.setHorizontalAlignment(SwingConstants.LEFT);
		newMissionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		newMissionlbl.setBounds(10, 34, 368, 31);
		add(newMissionlbl);
		
		JLabel titlelbl = new JLabel("Title:");
		titlelbl.setHorizontalAlignment(SwingConstants.LEFT);
		titlelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		titlelbl.setBounds(10, 66, 43, 25);
		add(titlelbl);
		
		JLabel objectivelbl = new JLabel("Objectives-----------------------------");
		objectivelbl.setHorizontalAlignment(SwingConstants.LEFT);
		objectivelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		objectivelbl.setBounds(10, 101, 246, 25);
		add(objectivelbl);
		
		JLabel descriptionlbl = new JLabel("Description:");
		descriptionlbl.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		descriptionlbl.setBounds(10, 128, 72, 25);
		add(descriptionlbl);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 158, 246, 99);
		add(textPane);
	}
}
