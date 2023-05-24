package com.example.tobyspringbootgradle.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UseEnv {
    public void check() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_HOST");
        String dbPassword = env.get("DB_HOST");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("show databases");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }
}