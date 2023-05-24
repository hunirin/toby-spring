package com.example.tobyspringbootgradle.dao;

import com.example.tobyspringbootgradle.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker cm = new DConnectionMaker();
        UserDao ud = new UserDao(cm);
        User user = new User();
        user.setId("8");
        user.setName("danghun");
        user.setPassword("1144");
        ud.add(user);

        User selectedUser = ud.get("8");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}
