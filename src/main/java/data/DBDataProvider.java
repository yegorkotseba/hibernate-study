package data;

import org.h2.tools.RunScript;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBDataProvider {

    public static void createTables() {
        try {
            System.out.println("-- Starting DB creation --");
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "", "");
            System.out.println("-- Established connection --");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE AGENTS");
            stmt.executeUpdate("DROP TABLE CUSTOMER");
            stmt.executeUpdate("DROP TABLE ORDERS");
            System.out.println("-- Dropped tables --");
            RunScript.execute(conn, new FileReader("src/main/resources/database.sql"));
        }
        catch (Exception e){}
    }
}
