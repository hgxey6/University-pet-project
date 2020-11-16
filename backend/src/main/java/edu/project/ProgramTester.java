package edu.project;

import edu.project.dao.PersonDaoImpl;
import edu.project.domains.Person;

import java.sql.*;
import java.util.List;

public class ProgramTester {
    public static void main(String[] args) throws SQLException {
       PersonDaoImpl pd = new PersonDaoImpl();
        List<Person> j = pd.findPerson("n");
        System.out.println(j);
    }
}
