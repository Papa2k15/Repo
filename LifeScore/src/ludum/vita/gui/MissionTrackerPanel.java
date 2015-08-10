package ludum.vita.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ludum.vita.actions.MissionController;
import ludum.vita.dao.DatabaseFactory;

import javax.swing.JScrollBar;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MissionTrackerPanel extends JLayeredPane implements AdjustmentListener, MouseWheelListener{

	private static final long serialVersionUID = 1L;
	private MissionController controller;
	private JScrollBar mainScroll;
	private List<MissionTrackerSlate> missions;
	private int initialScrollY;

	/**
	 * Create the panel.
	 * @param factory 
	 */
	public MissionTrackerPanel(String loggedLSUID, DatabaseFactory factory) {
		controller = new MissionController(loggedLSUID, factory);
		missions = new LinkedList<MissionTrackerSlate>();
		//CONSTRUCT
		try {
			for(int i = 0; i < controller.getAllMissionsForUser().size();i++){
				MissionTrackerSlate m = new MissionTrackerSlate(controller.getAllMissionsForUser().get(i));
				m.setBackground(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
				m.setBounds(10, 47+(i*200), 506, 200);
				missions.add(m);
				add(m,2,0); //Lower layer
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		setSize(553, 381);
		setLayout(null);

		JLabel missionTrackerlbl = new JLabel("Mission Tracker");
		missionTrackerlbl.setOpaque(true);
		missionTrackerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		missionTrackerlbl.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		missionTrackerlbl.setBounds(0, 0, 553, 36);
		add(missionTrackerlbl,3,0); //Upper layer

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 34, 533, 2);
		add(separator);

		mainScroll = new JScrollBar();
		mainScroll.setUnitIncrement(100);
		mainScroll.addAdjustmentListener(this);
		mainScroll.setBounds(526, 47, 17, 323);
		try {
			mainScroll.setMaximum(200*controller.getAllMissionsForUser().size());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		initialScrollY = 0;
		add(mainScroll);
		addMouseWheelListener(this);
		
		JLabel missionStatslbl = new JLabel("Mission Stats:");
		missionStatslbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		missionStatslbl.setBounds(10, 77, 106, 21);
		add(missionStatslbl);
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		if(arg0.getSource() == mainScroll){
			int nextAdjust = arg0.getAdjustable().getValue();
			int delta = initialScrollY-nextAdjust;
			for(Component mission: getComponents()){
				if(mission instanceof MissionTrackerSlate){
					mission.setBounds(mission.getX(),mission.getY()+delta, mission.getWidth(), mission.getHeight());
				}
			}
			initialScrollY = nextAdjust;
			repaint();
			revalidate();
			updateUI();
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
//		int notches = e.getWheelRotation();
//		if (notches < 0) {
//			for(Component mission: getComponents()){
//				if(mission instanceof MissionTrackerSlate){
//					mission.setBounds(mission.getX(),mission.getY()-notches, mission.getWidth(), mission.getHeight());
//				}
//			}
//		} else {
//			for(Component mission: getComponents()){
//				if(mission instanceof MissionTrackerSlate){
//					mission.setBounds(mission.getX(),mission.getY()+notches, mission.getWidth(), mission.getHeight());
//				}
//			}
//		}
	}
}
