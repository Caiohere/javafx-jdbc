package com.example.demo;
import java.sql.*;
import java.util.ArrayList;

public class Model {

    public ArrayList<String>sqlResults = new ArrayList<>();
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private String query;


    public Model(String query) {
        this.query = query;
    }

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/APS", "root", "@Ca191008");


        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    public ArrayList<String> chart() {
        String chartSql = query;

        connect = connectDb();
        try {
            prepare = connect.prepareStatement(chartSql);
            result = prepare.executeQuery();
            while (result.next()) {
                sqlResults.add(result.getString(1));
                sqlResults.add(result.getString(2));
            }
            return sqlResults;
        }catch (Exception e) {e.printStackTrace();}
        return null;
    }
}
