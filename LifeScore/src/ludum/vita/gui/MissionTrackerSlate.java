package ludum.vita.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import ludum.vita.actions.MissionController;
import ludum.vita.beans.MissionBean;
import javax.swing.ImageIcon;

public class MissionTrackerSlate extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private MissionBean mission;
	private JButton decreaseButton;
	private JButton increaseButton;
	private JLabel progressionlbl;
	private MissionController controller;
	private int interval = 1;
	private JLabel lblInterval;
	private JLabel startDatelbl;
	private JLabel endDatelbl;
	
	/**
	 * Create the panel.
	 */
	public MissionTrackerSlate(MissionBean mission, MissionController missions) {
		this.mission = mission;
		this.controller = missions;
		setLayout(null);
		setFocusable(true);
		setSize(506,MissionTrackerPanel.PANEL_HEIGHT);
		JLabel missionTitlelbl = new JLabel(mission.getTitle());
		missionTitlelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		missionTitlelbl.setBounds(10, 11, 486, 42);
		add(missionTitlelbl);
		
		JLabel descriptionlbl = new JLabel("<html><p>"+mission.getDescription()+"</p></html>");
		descriptionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		descriptionlbl.setBounds(10, 64, 233, 115);
		add(descriptionlbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 51, 486, 2);
		add(separator);
		
		JLabel goalUnitslb = new JLabel("Goal: " + mission.getTrackerGoal() + " " + mission.getUnits());
		goalUnitslb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		goalUnitslb.setBounds(253, 64, 243, 25);
		add(goalUnitslb);
		
		increaseButton = new JButton("");
		increaseButton.addActionListener(this);
		increaseButton.setFocusable(false);
		increaseButton.setIcon(new ImageIcon(MissionTrackerSlate.class.getResource("/ludum/resources/images/IncreaseButton.png")));
		increaseButton.setBorderPainted(false);
		increaseButton.setFocusPainted(false);
		increaseButton.setContentAreaFilled(false);
		increaseButton.setBounds(428, 126, 28, 25);
		add(increaseButton);
		
		decreaseButton = new JButton("");
		decreaseButton.addActionListener(this);
		decreaseButton.setFocusable(false);
		decreaseButton.setIcon(new ImageIcon(MissionTrackerSlate.class.getResource("/ludum/resources/images/DecreaseButton.png")));
		decreaseButton.setBorderPainted(false);
		decreaseButton.setFocusPainted(false);
		decreaseButton.setContentAreaFilled(false);
		decreaseButton.setBounds(393, 126, 28, 25);
		add(decreaseButton);
		
		progressionlbl = new JLabel("Progress: " + mission.getTrackerValue() + " " + mission.getUnits());
		progressionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		progressionlbl.setBounds(253, 100, 243, 25);
		add(progressionlbl);
		
		startDatelbl = new JLabel("Date Created: " + mission.getStartDateString());
		startDatelbl.setHorizontalAlignment(SwingConstants.LEFT);
		startDatelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		startDatelbl.setBounds(10, 190, 178, 25);
		add(startDatelbl);
		
		addKeyListener(this);
		
		lblInterval = new JLabel("Interval: " + interval);
		lblInterval.setHorizontalAlignment(SwingConstants.LEFT);
		lblInterval.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblInterval.setBounds(253, 126, 130, 25);
		add(lblInterval);
		
		if(mission.isMissionComplete()){
			endDatelbl = new JLabel("Date Completed: " + mission.getEndDateString());
		} else {
			endDatelbl = new JLabel("Date Completed: N/A");
		}
		endDatelbl.setHorizontalAlignment(SwingConstants.LEFT);
		endDatelbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		endDatelbl.setBounds(10, 214, 200, 25);
		add(endDatelbl);
	}

	/**
	 * @return the mission
	 */
	public MissionBean getMission() {
		return mission;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == increaseButton){
			increase();
		} else if (e.getSource() == decreaseButton){
			decrease();
		}
		try {
			updateValues();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
	}

	private synchronized void updateValues() throws Exception {
		controller.updateMission(mission);
		progressionlbl.setText("Progress: " + mission.getTrackerValue() + " " + mission.getUnits());
		if(mission.isMissionComplete()){
			endDatelbl.setText("Date Completed: " + mission.getEndDateString());
		} else {
			endDatelbl.setText("Date Completed: N/A");
		}
	}

	private synchronized void increase() {
		int current = mission.getTrackerValue();
		if(current + interval < mission.getTrackerGoal()){
			current = current + interval;
			mission.setTrackerValue(current);
		} else if (current + interval == mission.getTrackerGoal()){
			current = current + interval;
			mission.setTrackerValue(current);
			mission.setMissionComplete(true);
			mission.setEndDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		}else {
			interval = 1;
			lblInterval.setText("Interval: " + interval);
		}
	}

	private synchronized void decrease() {
		int current = mission.getTrackerValue();
		if(current - interval >= 0){
			endDatelbl.setText("Date Completed: N/A");
			mission.setEndDate("00/00/0000");
			mission.setMissionComplete(false);
			current = current - interval;
			mission.setTrackerValue(current);
		} else {
			interval = 1;
			lblInterval.setText("Interval: " + interval);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1){
			interval = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_2){
			interval = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_3){
			interval = 5;
		} else if (e.getKeyCode() == KeyEvent.VK_4){
			interval = 10;
		} else if (e.getKeyCode() == KeyEvent.VK_5){
			interval = 50;
		} else if (e.getKeyCode() == KeyEvent.VK_6){
			interval = 100;
		} else if (e.getKeyCode() == KeyEvent.VK_7){
			interval = 500;
		} else if (e.getKeyCode() == KeyEvent.VK_8){
			interval = 1000;
		} else if (e.getKeyCode() == KeyEvent.VK_9){
			interval = 10000;
		}
		lblInterval.setText("Interval: " + interval);
	} 

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
