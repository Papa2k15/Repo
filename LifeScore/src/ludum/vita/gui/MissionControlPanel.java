package ludum.vita.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import ludum.vita.actions.MissionController;
import ludum.vita.beans.MissionBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.gui.helpers.JTextFieldLimit;
import javax.swing.ListSelectionModel;

public class MissionControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField titletxtfld;
	private DefaultListModel<String> missionModelStringVersion;
	private DefaultListModel<MissionBean> missionModelBeanVersion;
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
	private MissionController control;
	private JLabel titlelbl;
	private JLabel descriptionlbl;
	private JLabel unitslbl;
	private JLabel goallbl;
	

	/**
	 * Create the panel.
	 * @param factory 
	 * @param loggedLSUID 
	 */
	public MissionControlPanel(String loggedLSUID, DatabaseFactory factory) {
		control = new MissionController(loggedLSUID, factory);
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
		lblMissionControl.setBounds(10, 0, 533, 36);
		add(lblMissionControl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 34, 533, 2);
		add(separator);
		
		JLabel newMissionlbl = new JLabel("Add, edit and remove missions that you will complete.");
		newMissionlbl.setHorizontalAlignment(SwingConstants.CENTER);
		newMissionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		newMissionlbl.setBounds(10, 34, 533, 31);
		add(newMissionlbl);
		
		titlelbl = new JLabel("Title:");
		titlelbl.setHorizontalAlignment(SwingConstants.LEFT);
		titlelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		titlelbl.setBounds(10, 66, 43, 25);
		add(titlelbl);
		
		JLabel objectivelbl = new JLabel("Objective-----------------------------");
		objectivelbl.setHorizontalAlignment(SwingConstants.LEFT);
		objectivelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		objectivelbl.setBounds(10, 101, 246, 25);
		add(objectivelbl);
		
		descriptionlbl = new JLabel("Description:");
		descriptionlbl.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		descriptionlbl.setBounds(10, 128, 72, 25);
		add(descriptionlbl);
		
		descriptiontxtarea = new JTextArea();
		descriptiontxtarea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		descriptiontxtarea.setLineWrap(true);
		descriptiontxtarea.setDocument(new JTextFieldLimit(200));
		descriptiontxtarea.setBounds(10, 158, 246, 99);
		add(descriptiontxtarea);
		
		unittxtfld = new JTextField();
		unittxtfld.setDocument(new JTextFieldLimit(50));
		unittxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		unittxtfld.setColumns(10);
		unittxtfld.setBounds(172, 268, 84, 25);
		add(unittxtfld);
		
		unitslbl = new JLabel("Units (miles, pages, etc.):");
		unitslbl.setHorizontalAlignment(SwingConstants.LEFT);
		unitslbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		unitslbl.setBounds(10, 268, 152, 25);
		add(unitslbl);
		
		goallbl = new JLabel("Goal:");
		goallbl.setHorizontalAlignment(SwingConstants.LEFT);
		goallbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		goallbl.setBounds(10, 315, 43, 25);
		add(goallbl);
		
		goaltxtfld = new JTextField();
		goaltxtfld.setText("");
		goaltxtfld.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		goaltxtfld.setColumns(10);
		goaltxtfld.setBounds(52, 314, 110, 25);
		add(goaltxtfld);
		
		addMissionbtn = new JButton("Add");
		addMissionbtn.addActionListener(this);
		addMissionbtn.setBounds(172, 314, 65, 25);
		add(addMissionbtn);
		
		clearbtn = new JButton("");
		clearbtn.addActionListener(this);
		clearbtn.setToolTipText("Clear current mission.");
		clearbtn.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\ClearButton.png"));
		clearbtn.setBorderPainted(false);
		clearbtn.setFocusPainted(false);
		clearbtn.setContentAreaFilled(false);
		clearbtn.setRolloverEnabled(true);
		clearbtn.setRolloverIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\ClearButton_Hover.png"));
		clearbtn.setBounds(62, 350, 65, 25);
		add(clearbtn);
		
		
		missionModelStringVersion = new DefaultListModel<String>();
		missionModelBeanVersion = new DefaultListModel<MissionBean>();
		try {
			for(MissionBean m : control.getAllMissionsForUser()){
				missionModelStringVersion.addElement(m.getTitle());
				missionModelBeanVersion.addElement(m);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		missionList = new JList<String>(missionModelStringVersion);		
		missionList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		missionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		missionPane = new JScrollPane(missionList);
		missionPane.setBounds(266, 128, 277, 209);
		add(missionPane);
		
		JLabel currentMissions = new JLabel("Current Missions-----------------------");
		currentMissions.setHorizontalAlignment(SwingConstants.LEFT);
		currentMissions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currentMissions.setBounds(281, 101, 246, 25);
		add(currentMissions);
		
		editSelectedMissionbtn = new JButton("Edit");
		editSelectedMissionbtn.addActionListener(this);
		editSelectedMissionbtn.setBounds(266, 350, 65, 25);
		add(editSelectedMissionbtn);
		
		removeSelectedMissionbtn = new JButton("Remove");
		removeSelectedMissionbtn.addActionListener(this);
		removeSelectedMissionbtn.setBounds(370, 350, 65, 25);
		add(removeSelectedMissionbtn);
		
		refreshbtn = new JButton("");
		refreshbtn.addActionListener(this);
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addMissionbtn){
			try {
				addMission();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		} else if (e.getSource() == removeSelectedMissionbtn){
			try {
				removeMission();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		} else if (e.getSource() == editSelectedMissionbtn){
			
		}
	}
	
	
	private void updateMissions() {
		missionModelStringVersion = new DefaultListModel<String>();
		missionModelBeanVersion = new DefaultListModel<MissionBean>();
		try {
			for(MissionBean m : control.getAllMissionsForUser()){
				missionModelStringVersion.addElement(m.getTitle());
				missionModelBeanVersion.addElement(m);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		missionList = new JList<String>(missionModelStringVersion);		
		missionList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		missionPane.setViewportView(missionList);
		repaint();
		revalidate();
		updateUI();
	}

	private void addMission() throws Exception {
		String problems = "";
		String title = titletxtfld.getText();
		String description = descriptiontxtarea.getText();
		int goal = 0;
		try {
			goal = Integer.parseInt(goaltxtfld.getText());
		} catch (NumberFormatException e){
			goallbl.setForeground(Color.RED);
			problems += "Goal should be of integer value (e.g. 1, 34, 490).\n";
		}
		String unit = unittxtfld.getText();
		if(title.length() <= 0){
			titlelbl.setForeground(Color.RED);
			problems += "Mission title length needs to be greater than 0.\n";
		} else {
			titlelbl.setForeground(Color.BLACK);
		}
		if(description.length() <= 0){
			descriptionlbl.setForeground(Color.RED);
			problems += "Mission description length needs to be greater than 0.\n";
		} else {
			descriptionlbl.setForeground(Color.BLACK);
		}
		if(control.getLoggedLSUID() == null && control.getLoggedLSUID() == ""){
			problems += "Valid user is not logged in.\n";
			//SPECIAL CASE
		}
		if(goal <= 0){
			goallbl.setForeground(Color.RED);
			problems += "Goal needs to have a positive value.\n";
		} else {
			goallbl.setForeground(Color.BLACK);
		}
		if(unit.length() <= 0){
			unitslbl.setForeground(Color.RED);
			problems += "Units has to be at least one character.\n";
		} else {
			unitslbl.setForeground(Color.BLACK);
		}
		if(problems.length()>0){
			String error = "Errors\n" + problems;
			throw new IllegalArgumentException(error);
		} else {
			MissionBean newMission = new MissionBean(control.getLoggedLSUID(), title, goal, unit);
			newMission.setDescription(description);
			control.addMission(newMission);
			clearText();
			updateMissions();
		}
	}
	
	private void removeMission() throws Exception{
		int selectedIndex = missionList.getSelectedIndex();
		if(selectedIndex > 0){
			String removeLSMID = missionModelBeanVersion.get(selectedIndex).getLSMID();
			control.removeMission(removeLSMID);
			updateMissions();
		} else {
			JOptionPane.showMessageDialog(this, "No mission selected.");
		}
	}

	private void clearText() {
		titletxtfld.setText("");
		titletxtfld.setForeground(Color.black);
		descriptiontxtarea.setText("");
		descriptiontxtarea.setForeground(Color.black);
		goaltxtfld.setText("");
		goaltxtfld.setForeground(Color.black);
		unittxtfld.setText("");
		unittxtfld.setForeground(Color.black);
	}
}

