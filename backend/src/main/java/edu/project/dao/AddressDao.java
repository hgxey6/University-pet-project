package edu.project.dao;

import edu.project.domains.Address;
import edu.project.domains.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDao {

    private static final String TEST = "SELECT * FROM address WHERE UPPER(country) LIKE UPPER(?) " +
            "OR UPPER(city) LIKE UPPER(?)";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public List<Address> findAddress(String pattern) throws SQLException {
        List<Address> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(TEST)) {

            stmt.setString(1, "%" + pattern + "%");
            stmt.setString(2, "%" + pattern + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Address address = new Address();
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setBuilding(rs.getString("building"));
                address.setApartment(rs.getString("apartment"));

                result.add(address);
            }
        }
        return result;
    }
}
