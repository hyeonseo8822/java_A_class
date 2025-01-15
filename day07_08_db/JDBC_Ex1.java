package day07_08_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Ex1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?user=root&password=1234");
            System.out.println("데이터베이스에 성공적으로 연결되었습니다!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver가 없습니다!");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다!");
        }
    }
}

