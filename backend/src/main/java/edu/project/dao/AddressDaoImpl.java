package edu.project.dao;

import edu.project.domains.Address;
import edu.project.domains.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    private static final String GET_PERSON_BY_ADDRESS =
            "SELECT person_name, person_surname, person_patronymic, " +
                    "person_date_of_birth, person_sex, " +
                    "person_number, person_email, " +
                    "country, city, street, building, apartment " +
                    "FROM person LEFT OUTER JOIN address " +
                    "ON(person.person_address_id = address.address_id) " +
                    "WHERE UPPER(country) LIKE UPPER(?) OR " +
                    "UPPER(city) LIKE UPPER(?) OR " +
                    "UPPER(street) LIKE UPPER(?);";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public List<Person> findAddress(String pattern) throws SQLException {
        List<Person> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PERSON_BY_ADDRESS)) {

            stmt.setString(1, "%" + pattern + "%");
            stmt.setString(2, "%" + pattern + "%");
            stmt.setString(3, "%" + pattern + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setName(rs.getString("person_name"));
                person.setSurname(rs.getString("person_surname"));
                person.setPatronymic(rs.getString("person_patronymic"));
                person.setDateOfBirth(rs.getDate("person_date_of_birth").toLocalDate());
                person.setSex(rs.getString("person_sex"));

                person.setPhoneNumber(rs.getString("person_number"));
                person.setEmail(rs.getString("person_email"));

                Address address = new Address();
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setBuilding(rs.getString("building"));
                address.setApartment(rs.getString("apartment"));
                person.setAddress(address);

                result.add(person);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return result;
    }
}
