package Lesson2.Lesson;

import java.sql.*;

public class MainBD {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prepared_statement;


    public static void start(){
        try {
            connect();
            //test1();
            connection.setAutoCommit(false);
            //System.out.println(timeTest());

            prepared_statement = connection.prepareStatement(
                    "INSERT INTO student (name, score, faculty) VALUES (?,?,?)");
            for (int i = 0; i < 100; i++) {
                prepared_statement.setString(1,"Bob" + i);
                prepared_statement.setInt(2,i);
                prepared_statement.setString(3,"rty");
                prepared_statement.addBatch();
            }

            prepared_statement.executeBatch();
            connection.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new WindowTest();
    }

    public static void test1() throws SQLException {
        ResultSet result_set = statement.executeQuery("SELECT * FROM student");
        ResultSetMetaData result_set_meta_data = result_set.getMetaData();

        for (int i = 1; i <= result_set_meta_data.getColumnCount(); i++) {
            System.out.println(result_set_meta_data.getColumnName(i));
        }

        while (result_set.next()){
            System.out.println(result_set.getInt(1) + " | " + result_set.getString("name"));
        }
    }

    public static void add() throws SQLException {
        statement.executeUpdate("INSERT INTO student ( name, score, faculty)\n" +
        "VALUES ('bob', 70, 'ew')\n");
    }

    public static void droptable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS student");
    }

    public static long timeTest() throws SQLException {
        long t = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            add();
        }

        return System.currentTimeMillis() - t;
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:NBD.db");
        statement = connection.createStatement();
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }
}
