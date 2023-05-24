package com.example.tobyspringbootgradle.dao;

import com.example.tobyspringbootgradle.domain.User;


import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public abstract class UserDao {
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
////        Map<String, String> env = getenv();
////        String dbHost = env.get("DB_HOST");
////        String dbUser = env.get("DB_USER");
////        String dbPassword = env.get("DB_PASSWORD");
////        Class.forName("com.mysql.cj.jdbc.Driver");
////        Connection conn = DriverManager.getConnection(
////                dbHost, dbUser, dbPassword
////        );
////        return conn;
//    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());
        pstmt.executeUpdate();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + Enter 누른 것과 같음

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao ud = new NUserDao();
        User user = new User();
        user.setId("4");
        user.setName("sungghun");
        user.setPassword("4aa");
        ud.add(user);

        User selectedUser = ud.get("4");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
