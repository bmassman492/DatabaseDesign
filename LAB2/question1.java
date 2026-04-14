package LAB2;

import java.sql.*;

public class question1 {

    static final String databasePrefix = "cs366-2261_massmanbs23";
    static final String netID = "massmanbs23";
    static final String hostName = "washington.uww.edu";
    static final String databaseURL = "jdbc:mariadb://" + hostName + "/" + databasePrefix;
    static final String password = "bm2921";

    private Connection connection = null;

    public void connect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(databaseURL, netID, password);
            System.out.println("Successfully connected to the database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTotalFaculty() {
        try {
            CallableStatement stmt = connection.prepareCall("{call getTotalFaculty(?)}");
            stmt.registerOutParameter(1, Types.BIGINT);
            stmt.execute();
            int total = stmt.getInt(1);
            System.out.println("Total number of faculty: " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        question1 demo = new question1();
        demo.connect();
        demo.getTotalFaculty();
    }
}