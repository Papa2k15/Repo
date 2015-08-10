package ludum.vita.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import ludum.vita.beans.MissionBean;

public class MissionTrackerSlate extends JPanel {

	private static final long serialVersionUID = 1L;
	private MissionBean mission;
	
	/**
	 * Create the panel.
	 */
	public MissionTrackerSlate(MissionBean mission) {
		this.mission = mission;
		setLayout(null);
		
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
		
		JButton button = new JButton("+");
		button.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		button.setBounds(404, 128, 92, 23);
		add(button);
		
		JButton button_1 = new JButton("-");
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		button_1.setBounds(253, 128, 92, 23);
		add(button_1);
		
		JLabel label = new JLabel("Progress :" + mission.getTrackerValue());
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(253, 100, 243, 25);
		add(label);
		
		JLabel label_1 = new JLabel(mission.getEndDateString());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_1.setBounds(253, 154, 243, 25);
		add(label_1);

	}

	/**
	 * @return the mission
	 */
	public MissionBean getMission() {
		return mission;
	}
}
