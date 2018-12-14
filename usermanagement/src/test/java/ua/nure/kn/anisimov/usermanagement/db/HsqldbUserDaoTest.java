package ua.nure.kn.anisimov.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.anisimov.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private UserDao dao;
	private ConnectionFactory connectionFactory;

	private static final String LAST_NAME_ETALONE = "Anisimov";
	private static final String FIRST_NAME_ETALONE = "Vladyslav";

	protected void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateUser()throws DatabaseException{
		try{
			User user = new User();
			user.setFirstName(FIRST_NAME_ETALONE);
			user.setLastName(LAST_NAME_ETALONE);
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			User userExpected = dao.create(user);
			assertNotNull(userExpected);
			assertNotNull(userExpected.getId());
			assertEquals(userExpected.getFirstName(), user.getFirstName());
			assertEquals(userExpected.getLastName(), user.getLastName());
			assertEquals(userExpected.getDateOfBirth(), user.getDateOfBirth());
		} catch(DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}
	
	public void testFindAll() throws DatabaseException{
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull("Collection is null",collection);
			assertEquals("Collection size.",2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new  XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}
	
	

}
