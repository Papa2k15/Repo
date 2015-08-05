package ludum.vita.daotest;

import static org.junit.Assert.*;
import ludum.vita.beans.UserBean;
import ludum.vita.dao.DatabaseFactory;
import ludum.vita.dao.UsersDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {
	
	private DatabaseFactory factory;
	private UsersDAO userdatabase;
	private UserBean newUser1;
	private UserBean newUser2;
	private UserBean badUser;

	@Before
	public void setUp() throws Exception {
		factory = DatabaseFactory.getProductionInstance();
		userdatabase = factory.getUsersDAO();
		newUser1 = new UserBean("Gregory", "Papa2k15", "Password");
		newUser2 = new UserBean("Amber", "Amb2k15", "Fetty");
		badUser = new UserBean("I", "Dont", "Exist");
	}
	
	@Test
	public void testAddUser() throws Exception {
		assertEquals(0, userdatabase.getAllUsers().size());
		userdatabase.addUser(newUser1);
		assertEquals(1, userdatabase.getAllUsers().size());
		try {
			userdatabase.addUser(newUser1);
		} catch (Exception e){
			assertEquals("User already exists in database", e.getMessage());
		}
		assertEquals(1, userdatabase.getAllUsers().size());
		userdatabase.addUser(newUser2);
		assertEquals(2, userdatabase.getAllUsers().size());
	}
	
	@Test
	public void testUpdateAndGetUser() throws Exception {
		assertEquals(0, userdatabase.getAllUsers().size());
		userdatabase.addUser(newUser1);
		assertEquals(1, userdatabase.getAllUsers().size());
		assertEquals("Gregory", userdatabase.getUser(newUser1.getUserName()).getFirstName());
		newUser1.setFirstName("Lorenzo");
		newUser1.setLastName("Daniels");
		userdatabase.updateUser(newUser1);
		assertEquals("Lorenzo", userdatabase.getUser(newUser1.getUserName()).getFirstName());
		assertEquals("Daniels", userdatabase.getUser(newUser1.getUserName()).getLastName());
		assertEquals(1, userdatabase.getAllUsers().size());
		assertNull(userdatabase.getUser(badUser.getUserName()));
	}
	
	@Test
	public void testRemoveUser() throws Exception {
		assertEquals(0, userdatabase.getAllUsers().size());
		userdatabase.addUser(newUser1);
		assertEquals(1, userdatabase.getAllUsers().size());
		userdatabase.addUser(newUser2);
		assertEquals(2, userdatabase.getAllUsers().size());
		userdatabase.removeUser(newUser1);
		assertEquals(1, userdatabase.getAllUsers().size());
		try {
			userdatabase.removeUser(newUser1);
		}  catch (Exception e){
			assertEquals("User doesn't exist.", e.getMessage());
		}
		assertEquals(1, userdatabase.getAllUsers().size());
		userdatabase.removeUser(newUser2);
		assertEquals(0, userdatabase.getAllUsers().size());
	}


	@After
	public void tearDown() throws Exception {
		userdatabase.clearUserTable();
	}

	
}
