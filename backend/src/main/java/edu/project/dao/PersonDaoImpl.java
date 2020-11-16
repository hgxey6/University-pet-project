package edu.project.dao;

import edu.project.domains.Address;
import edu.project.domains.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private static final String GET_PERSON =
            "SELECT person_name, person_surname, person_date_of_birth, person_sex, " +
                    "person_number, person_email, " +
                    "country, city, street, building, apartment " +
                    "FROM person LEFT OUTER JOIN address " +
                    "ON(person.person_address_id = address.address_id) " +
                    "WHERE UPPER(person_name) LIKE UPPER(?) OR " +
                    "UPPER(person_surname) LIKE UPPER(?);";



    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public List<Person> findPerson(String pattern) throws SQLException {
        List<Person> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PERSON)) {

            stmt.setString(1, "%" + pattern + "%");
            stmt.setString(2, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setName(rs.getString("person_name"));
                person.setSurname(rs.getString("person_surname"));
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }
}
