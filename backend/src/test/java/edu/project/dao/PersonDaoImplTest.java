package edu.project.dao;

import edu.project.domains.Person;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImplTest.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void patternTest() throws SQLException {
        PersonDaoImpl pd = new PersonDaoImpl();
        List<Person> j = pd.searchByFullName("");
        for (Person person : j)
            System.out.println(person);

        AddressDaoImpl ad = new AddressDaoImpl();
        List<Person> g = ad.findAddress("rus");
        for (Person person : g)
            System.out.println(person);
    }


    @Test
    public void testFullName() throws SQLException {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        List<Person> people = new PersonDaoImpl().searchByFullName("Bob");
        assertEquals(1, people.size());
    }

}