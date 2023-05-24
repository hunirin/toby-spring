package com.example.tobyspringbootgradle.dao;

import com.example.tobyspringbootgradle.domain.User;


import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {
    SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeNewConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());
        pstmt.executeUpdate();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeNewConnection();

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
        UserDao ud = new UserDao();
        User user = new User();
        user.setId("5");
        user.setName("minhun");
        user.setPassword("887");
        ud.add(user);

        User selectedUser = ud.get("5");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
