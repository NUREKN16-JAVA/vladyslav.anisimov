package ua.nure.kn.anisimov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	
	private final Properties properties;
	private static final String USER_DAO = "dao.ua.nure.kn.anisimov.usermanagement.db.UserDao";
    private static final String CONNECTION_DRIVER = "connection.driver";
    private static final String CONNECTION_URL = "connection.url";
    private static final String CONNECTION_USER = "connection.user";
    private static final String CONNECTION_PASSWORD = "connection.password";
    private static final String PROPERTIES_FILE = "settings.properties";
    private final static DaoFactory INSTANCE = new DaoFactory();
    
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
	public DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	 private ConnectionFactory getConnectionFactory() {
	        String driver = properties.getProperty(CONNECTION_DRIVER);
	        String url = properties.getProperty(CONNECTION_URL);
	        String user = properties.getProperty(CONNECTION_USER);
	        String password = properties.getProperty(CONNECTION_PASSWORD);
	        return new ConnectionFactoryImpl(driver, url, user, password);
	    }
	
	public UserDao getUserDao() {
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
		
	}
	
}
