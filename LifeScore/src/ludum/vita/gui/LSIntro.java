package ludum.vita.gui;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import ludum.vita.dao.DatabaseFactory;

public class LSIntro extends JFrame implements MouseInputListener {

	private static final long serialVersionUID = 1L;
	private DatabaseFactory factory = DatabaseFactory.getProductionInstance();
	private LSMain main;

	public LSIntro() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Owner\\Repo\\LifeScore\\images\\Intro.gif"));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,287);
		addMouseListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) throws Exception{
		new LSIntro();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		main = new LSMain(factory);
		main.setVisible(true);
		this.setVisible(false);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
