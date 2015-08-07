package ludum.vita.gui;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ludum.vita.gui.helpers.JTextFieldLimit;

public class MissionControlPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField titletxtfld;
	private DefaultListModel<String> missionModel;
	private JTextField unittxtfld;
	private JTextField goaltxtfld;
	private JButton clearbtn;
	private JButton addMissionbtn;
	private JList<String> missionList;
	private JScrollPane missionPane;
	private JButton editSelectedMissionbtn;
	private JButton removeSelectedMissionbtn;
	private JButton refreshbtn;
	private JTextArea descriptiontxtarea;
	

	/**
	 * Create the panel.
	 */
	public MissionControlPanel() {
		
		setSize(553, 381);
		setLayout(null);
		
		titletxtfld = new JTextField();
		titletxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		titletxtfld.setDocument(new JTextFieldLimit(100));
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
		
		JLabel objectivelbl = new JLabel("Objective-----------------------------");
		objectivelbl.setHorizontalAlignment(SwingConstants.LEFT);
		objectivelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		objectivelbl.setBounds(10, 101, 246, 25);
		add(objectivelbl);
		
		JLabel descriptionlbl = new JLabel("Description:");
		descriptionlbl.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		descriptionlbl.setBounds(10, 128, 72, 25);
		add(descriptionlbl);
		
		descriptiontxtarea = new JTextArea();
		descriptiontxtarea.setDocument(new JTextFieldLimit(200));
		descriptiontxtarea.setBounds(10, 158, 246, 99);
		add(descriptiontxtarea);
		
		unittxtfld = new JTextField();
		unittxtfld.setDocument(new JTextFieldLimit(50));
		unittxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		unittxtfld.setColumns(10);
		unittxtfld.setBounds(172, 268, 84, 25);
		add(unittxtfld);
		
		JLabel unitslbl = new JLabel("Units (miles, pages, etc.):");
		unitslbl.setHorizontalAlignment(SwingConstants.LEFT);
		unitslbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		unitslbl.setBounds(10, 268, 152, 25);
		add(unitslbl);
		
		JLabel lblGoal = new JLabel("Goal:");
		lblGoal.setHorizontalAlignment(SwingConstants.LEFT);
		lblGoal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGoal.setBounds(10, 315, 43, 25);
		add(lblGoal);
		
		goaltxtfld = new JTextField();
		goaltxtfld.setText("");
		goaltxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		goaltxtfld.setColumns(10);
		goaltxtfld.setBounds(52, 314, 110, 25);
		add(goaltxtfld);
		
		addMissionbtn = new JButton("Add");
		addMissionbtn.setBounds(172, 314, 65, 25);
		add(addMissionbtn);
		
		clearbtn = new JButton("");
		clearbtn.setToolTipText("Clear current mission.");
		clearbtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\ClearButton.png"));
		clearbtn.setBorderPainted(false);
		clearbtn.setFocusPainted(false);
		clearbtn.setContentAreaFilled(false);
		clearbtn.setRolloverEnabled(true);
		clearbtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\ClearButton_Hover.png"));
		clearbtn.setBounds(62, 350, 65, 25);
		add(clearbtn);
		
		
		missionModel = new DefaultListModel<String>();
		for(int i = 0; i < 100; i++){
			missionModel.addElement("DUMMYDATA"+i);
		}
		
		missionList = new JList<String>(missionModel);		
		missionPane = new JScrollPane(missionList);
		missionPane.setBounds(266, 128, 277, 209);
		add(missionPane);
		
		JLabel currentMissions = new JLabel("Current Missions-----------------------");
		currentMissions.setHorizontalAlignment(SwingConstants.LEFT);
		currentMissions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currentMissions.setBounds(281, 101, 246, 25);
		add(currentMissions);
		
		editSelectedMissionbtn = new JButton("Edit");
		editSelectedMissionbtn.setBounds(266, 350, 65, 25);
		add(editSelectedMissionbtn);
		
		removeSelectedMissionbtn = new JButton("Remove");
		removeSelectedMissionbtn.setBounds(370, 350, 65, 25);
		add(removeSelectedMissionbtn);
		
		refreshbtn = new JButton("");
		refreshbtn.setToolTipText("Refresh all missions.");
		refreshbtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\RefreshButton.png"));
		refreshbtn.setBorderPainted(false);
		refreshbtn.setFocusPainted(false);
		refreshbtn.setContentAreaFilled(false);
		refreshbtn.setRolloverEnabled(true);
		refreshbtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\RefreshButton_Hover.png"));
		refreshbtn.setBounds(478, 350, 65, 25);
		add(refreshbtn);
		
	}
}
