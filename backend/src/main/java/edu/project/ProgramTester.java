package edu.project;

import edu.project.dao.PersonDaoImpl;
import edu.project.domains.Person;

import java.sql.*;
import java.util.List;

public class ProgramTester {
    public static void main(String[] args) throws SQLException {
        PersonDaoImpl pd = new PersonDaoImpl();
        List<Person> j = pd.findPerson("");
        for (Person person : j)
            System.out.println(person);

//        AddressDao ad = new AddressDao();
//        List<Address> addresses = ad.findAddress("");
//        for (Address a : addresses)
//            System.out.println(a);
    }
}
