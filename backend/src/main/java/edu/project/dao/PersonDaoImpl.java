package edu.project.dao;

import edu.project.domains.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private static final String GET_PERSON = "SELECT * FROM person " +
            "WHERE UPPER(person_name) LIKE UPPER(?);";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public List<Person> findPerson(String pattern) throws SQLException {
        List<Person> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PERSON)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Person str = new Person(
                        rs.getLong("person_id"),
                        rs.getString("person_name"),
                        rs.getString("person_surname"));
                result.add(str);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
