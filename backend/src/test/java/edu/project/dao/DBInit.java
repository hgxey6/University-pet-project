package edu.project.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DBInit
{
    public static void startUp() throws Exception {
        URL url1 = PersonDaoImplTest.class.getClassLoader()
                .getResource("find_project.sql");
        URL url2 = PersonDaoImplTest.class.getClassLoader()
                .getResource("data.sql");

        assert url1 != null;
        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql1 = String.join("", str1);

        assert url2 != null;
        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));
        String sql2 = String.join("", str2);

        try (Connection con = ConnectionBuilder.getConnection();
             Statement stmt = con.createStatement();
        ) {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        }
    }
}
