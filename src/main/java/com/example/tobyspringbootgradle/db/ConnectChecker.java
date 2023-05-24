package com.example.tobyspringbootgradle.db;

import java.sql.*;

public class ConnectChecker {
    public void check() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-3-35-194-204.ap-northeast-2.compute.amazonaws.com:3306/spring-db",
                "root", "12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();

        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-3-35-194-204.ap-northeast-2.compute.amazonaws.com:3306/spring-db",
                "root", "12345678");

        PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setInt(1, 1);
        pstmt.setString(2, "panghun");
        pstmt.setString(3, "1234");
        pstmt.executeUpdate();
    }

    public void select() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-3-35-194-204.ap-northeast-2.compute.amazonaws.com:3306/spring-db",
                "root", "12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();

        while(rs.next()){
            String col1 =rs.getString(1);
            String col2 =rs.getString(2);
            String col3 =rs.getString(3);
            System.out.printf("%s %s %s\n", col1, col2, col3);
        }
    }


        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectChecker cc = new ConnectChecker();
        cc.select();
    }





}
