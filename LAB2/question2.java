package LAB2;

import java.sql.*;
import java.util.Scanner;

public class question2 {

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

    public void getStudentInfo(int studentID) {
        try {
            CallableStatement stmt = connection.prepareCall("{call getStudentInfo(?)}");
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Student Number : " + rs.getInt("snum"));
                System.out.println("Name           : " + rs.getString("sname"));
                System.out.println("Major          : " + rs.getString("major"));
                System.out.println("Level          : " + rs.getString("level"));
                System.out.println("Age            : " + rs.getInt("age"));
            } else {
                System.out.println("No student found with ID: " + studentID);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        question2 demo = new question2();
        demo.connect();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a student ID: ");
        int studentID = scanner.nextInt();
        scanner.close();

        demo.getStudentInfo(studentID);
    }
}