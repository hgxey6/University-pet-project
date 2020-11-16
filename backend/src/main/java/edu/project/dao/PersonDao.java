package edu.project.dao;

import edu.project.domains.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    List<Person> findPerson(String pattern) throws SQLException;
}
