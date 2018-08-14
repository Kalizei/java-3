package Lesson2.Homework;

import java.sql.*;
import java.util.Scanner;

public class MainBD {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prepared_statement;

    public static void main(String[] args) {
        new MainBD().start();
    }


    public void start(){
        try {
            connect();

            task1();
            task2_1();
            task2_2();

            System.out.println("системные каманды: prod, change, price");
            Scanner scanner = new Scanner(System.in);
            String in_text = scanner.nextLine();
            while (!in_text.equals("")) {

                task3(in_text);
                task4(in_text);
                task5(in_text);
                in_text = scanner.nextLine();
            }


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

    /**
     * создаем в базе данных таблицу сос столбцами id, prodid, title, cost
     * @throws SQLException
     */
    public void task1() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS prod " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, prodid INTEGER, title  TEXT, cost INTEGER);");

    }

    /**
     * очистка данных в таблице prod
     * @throws SQLException
     */
    public void task2_1() throws SQLException {
        statement.executeUpdate("DELETE FROM prod");

    }

    public void task2_2() throws SQLException {
        connection.setAutoCommit(false);

        prepared_statement = connection.prepareStatement(
                "INSERT INTO prod (prodid, title, cost) VALUES (?,?,?)");
        for (int i = 0; i < 10000; i++) {
            prepared_statement.setInt(1,i);
            prepared_statement.setString(2,"prod" + i);
            prepared_statement.setInt(3,i*10);
            prepared_statement.addBatch();
        }

        prepared_statement.executeBatch();
        connection.setAutoCommit(true);
    }

    /**
     *  метод поиска цены в базе данных по наименованию товара
     * @param in входная строка с консоли
     * @return результат поиска в базе даных
     * @throws SQLException
     */
    public String task3(String in) throws SQLException {
        if (in.length() > 6) {
            String key_string = in.substring(0, 4).trim(); //системное слово для поиска
            if (key_string.equals("prod")) {
                String result = findCost(in.substring(4).trim());
                System.out.println(result);
                return result;
            }
        }
        return "";

    }

    /**
     * метот поиска по заданому названию товара в базе данных
     * @param prod_name название тавара
     * @return цена тавара
     * @throws SQLException
     */
    public String findCost(String prod_name) throws SQLException{
        ResultSet result_set = statement.executeQuery("SELECT * FROM prod");
        while (result_set.next()) {
            String name_currend_prod = result_set.getString(3);//наименование тавара в базе данных для сравнения
            if (prod_name.equals(name_currend_prod)){
                return result_set.getInt(4)+"";
            }
        }
        return "Такого товара нет";
    }

    public String task4(String in) throws SQLException {
        if (in.length() > 9) {
            String key_string = in.substring(0, 6).trim();
            if (key_string.equals("change")) {
                String in_string = in.substring(6).trim();
                int index_in = in_string.indexOf(" ");
                if (index_in > 0) { //наличие пробела как разделителя
                    String prod_name = in_string.substring(0, index_in).trim();
                    String cost_change = in_string.substring(index_in).trim();
                    changeCost(prod_name,cost_change);
                }
                return "";
            }
        }
        return "Такого товара нет";
    }

    public void task5(String in) throws SQLException {
        if (in.length() > 8){
            String key_string = in.substring(0, 5).trim();
            if (key_string.equals("price")) {
                String in_string = in.substring(6).trim();
                int index_in = in_string.indexOf(" ");
                if (index_in > 0) { //наличие пробела как разделителя
                    String low_price = in_string.substring(0, index_in).trim();
                    String high_price = in_string.substring(index_in).trim();
                    outProd(low_price,high_price);
                }
            }
        }
    }

    private void outProd(String low_price, String high_price) throws SQLException {
        ResultSet result_set = statement.executeQuery("SELECT * FROM prod WHERE cost<" + high_price + " and cost>" + low_price );
        while (result_set.next()) {
            String prod_id = result_set.getString(2);
            String name_prod = result_set.getString(3);
            String cost_prod = result_set.getString(4);
            System.out.println(prod_id + " | " + name_prod + " | " + cost_prod);
        }
    }

    public void changeCost(String prod_name, String cost_change) throws SQLException {
        statement.executeUpdate("UPDATE prod SET cost = " + cost_change + " WHERE title = '" + prod_name + "'");
        System.out.println("Тавар измененен");
    }


    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:NBD.db");
        statement = connection.createStatement();
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
