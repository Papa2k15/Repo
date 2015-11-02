package ludum.vita.daotest;

import static org.junit.Assert.*;
import ludum.vita.beans.MissionBean;
import ludum.vita.beans.UserBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.MissionDAO;
import ludum.vita.dbtools.LSDataGenerator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MissionDAOTest {
	
	private DatabaseFactory factory = DatabaseFactory.getProductionInstance();
	private MissionDAO missions;
	private UserBean user;
	private MissionBean mission1;
	private MissionBean mission2;
	private static LSDataGenerator dataGen = new LSDataGenerator();

	@Before
	public void setUp() throws Exception {
		dataGen.generateUsers();
		dataGen.generateMissions();
		missions = factory.getMissionsDAO();
		user = factory.getUsersDAO().getUser("Papa2k15");
		mission1 = new MissionBean(user.getLSUID(), "Jog", 3, "miles");
		mission1.setStartDate("10/12/1993");
		mission1.setEndDate("12/5/2012");
		mission1.setTrackerValue(2);
		mission2 = new MissionBean(user.getLSUID(), "Write", 10, "pages");
		mission1.setStartDate("1/2/2011");
		mission1.setEndDate("2/5/2011");
		mission1.setTrackerValue(5);
	}
	
	@Test
	public void testAddMission() throws Exception {
		assertEquals(3, missions.getAllMissions().size());
		missions.addMission(mission1);
		assertEquals(4, missions.getAllMissions().size());
		missions.addMission(mission1);
		assertEquals(5, missions.getAllMissions().size());
		missions.addMission(mission2);
		assertEquals(6, missions.getAllMissions().size());
	}
	
	@Test
	public void testEditMission() throws Exception {
		assertEquals(3, missions.getAllMissions().size());
		assertEquals("Jog", missions.getMission("X1").getTitle());
		MissionBean update = missions.getMission("X1");
		update.setTitle("Jog for kids");
		missions.updateMission(update);
		assertEquals("Jog for kids", missions.getMission("X1").getTitle());		
	}

	@After
	public void tearDown() throws Exception {
		dataGen.clearAllTables();
	}

	

}
