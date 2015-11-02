package ludum.vita.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import ludum.vita.actions.MissionController;
import ludum.vita.dao.DatabaseFactory;
import javax.swing.JScrollBar;
import javax.swing.event.MouseInputListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MissionTrackerPanel extends JLayeredPane implements AdjustmentListener, MouseWheelListener, MouseInputListener{

	private static final long serialVersionUID = 1L;
	private MissionController controller;
	private JScrollBar mainScroll;
	private List<MissionTrackerSlate> missions;
	private int initialScrollY;
	public static final int PANEL_HEIGHT = 250;

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
				MissionTrackerSlate m = new MissionTrackerSlate(controller.getAllMissionsForUser().get(i), controller);
				m.setBackground(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
				m.setBounds(10, 47+(i*PANEL_HEIGHT), 506, PANEL_HEIGHT);
				m.addMouseListener(this);
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
			mainScroll.setMaximum(PANEL_HEIGHT*controller.getAllMissionsForUser().size());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		initialScrollY = 0;
		add(mainScroll);
		addMouseWheelListener(this);

		JLabel missionStatslbl = new JLabel("Mission Stats:");
		missionStatslbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		missionStatslbl.setBounds(10, 77, 106, 21);
		add(missionStatslbl);
	}

	//BETA....STILL NEED TO FIGURE THIS OUT
	public void reconstruct(){
		//DESTROY
		for(Component m : getComponents()){
			if(m instanceof MissionTrackerSlate && !m.isVisible()){
				remove(m);
			}
		}
		//REBUILD
		LinkedList<MissionTrackerSlate> temp = new LinkedList<MissionTrackerSlate>();
		int visibleSlates = 0;
		try {
			for(int i = 0; i < missions.size();i++){
				if(missions.get(i).isVisible()){
					MissionTrackerSlate m = new MissionTrackerSlate(missions.get(i).getMission(), controller);
					m.setBackground(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
					m.setBounds(10, 47+(i*PANEL_HEIGHT), 506, PANEL_HEIGHT);
					m.addMouseListener(this);
					temp.add(m);
					visibleSlates++;
					add(m,2,0); //Lower layer
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		missions = temp;
		remove(mainScroll);
		mainScroll = new JScrollBar();
		mainScroll.setUnitIncrement(100);
		mainScroll.addAdjustmentListener(this);
		mainScroll.setBounds(526, 47, 17, 323);
		try {
			mainScroll.setMaximum(PANEL_HEIGHT*visibleSlates);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		initialScrollY = 0;
		add(mainScroll);
		repaint();
		revalidate();
		updateUI();
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
		if (e.getWheelRotation() < 0) {
			mainScroll.setValue(mainScroll.getValue()-50);
		} else {
			mainScroll.setValue(mainScroll.getValue()+50);
		}
		repaint();
		revalidate();
		updateUI();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setFocusable(false);
		arg0.getComponent().requestFocus();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setFocusable(true);		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
