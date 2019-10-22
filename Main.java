package ru.geekbrains.chat.DZ_2_1;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {
        try {
        connect();
        createTable();
        insertStudents("Борис","Группа 1", 28);
        insertStudents("Иван","Группа 1", 36);
        updateScore("Борис","Группа 1", 42);
        getInfoStudents();
        deleteTable();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
         }

    }

    public static void connect() throws ClassNotFoundException, SQLException {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
    }

    public static void createTable() throws SQLException {
            String query = "CREATE TABLE IF NOT EXISTS Students\n" +
                    "(\n" +
                    "  StudID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "  Name TEXT NOT NULL,\n" +
                    "  GroupName TEXT NOT NULL,\n" +
                    "  Score INTEGER NOT NULL\n" +
                    ");";
            connection.prepareStatement(query).executeUpdate();
    }

    public static void deleteTable() throws SQLException {
            String query = "DROP TABLE Students;";
            connection.prepareStatement(query).executeUpdate();
    }

    public static void getInfoStudents() throws SQLException {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                System.out.println("Студент: " + rs.getString(2) + "; группа " + rs.getString(3) + "; результат " + rs.getInt(4));
            }
    }

    public static void insertStudents(String name, String groupName, Integer score) throws SQLException {
            String query = "INSERT INTO Students (Name, GroupName, Score) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, groupName);
            ps.setInt(3, score);
            ps.executeUpdate();
    }

    public static void updateScore(String name, String groupName, Integer score) throws SQLException {
            String query = "UPDATE  Students SET  Score = '" + score + "' WHERE Name = '" + name + "' AND GroupName = '" + groupName + "';";
           connection.prepareStatement(query).executeUpdate();
    }

    public static void disconnect()  {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


