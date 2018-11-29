package ua.nure.kn.anisimov.usermanagement;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	private static final String FULL_NAME_ETALONE = "Anisimov, Vladyslav";
	private static final String LAST_NAME_ETALONE = "Anisimov";
	private static final String FIRST_NAME_ETALONE = "Vladyslav";
	private User user;


	private static final int YEAR_OF_BIRTH = 1998;

	private static final int ETALONE_AGE_1 = 20;
	private static final int DAY_OF_BIRTH_1 = 15;
	private static final int MONTH_OF_BIRTH_1 = Calendar.NOVEMBER;
	
	
	@Before
	public void setUp() throws Exception {
		user  = new User();
	}

	@Test
	public void getFullName() {
		user.setFirstName(FIRST_NAME_ETALONE);
		user.setLastName(LAST_NAME_ETALONE);
		assertEquals(FULL_NAME_ETALONE, user.getFullName());
	}
    @Test
    public void testGetAge(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_1, user.getAge());
    }

    @Test
    public void testGetAgeWhenMonthAfterBDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, Calendar.getInstance().get(Calendar.MONTH)+1, DAY_OF_BIRTH_1);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_1-1, user.getAge());
    }
    @Test
    public void testGetAgeWhenMonthBeforeBDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, Calendar.getInstance().get(Calendar.MONTH)-1, DAY_OF_BIRTH_1);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_1, user.getAge());
    }
    @Test
    public void testGetAgeWhenDayAfterBDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, Calendar.getInstance().get(Calendar.MONTH) , Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1 );
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_1-1, user.getAge());
    }
    @Test
    public void testGetAgeWhenDayBeforeBday()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, Calendar.getInstance().get(Calendar.MONTH) , Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_1, user.getAge());
    }
}
