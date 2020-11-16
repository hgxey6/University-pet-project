package edu.project.dao;

import edu.project.domains.Address;
import edu.project.domains.Person;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    public List<Person> findAddress(String pattern) throws SQLException;
}
