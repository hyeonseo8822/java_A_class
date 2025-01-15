package day07_08_db;
import java.io.*;
import java.sql.*;

public class JDBC_Ex2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB 연결
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", "1234");
            System.out.println("DB 연결 완료");

            // Statement 객체 생성
            stmt = conn.createStatement();

            // 데이터 삽입
            stmt.executeUpdate("INSERT INTO student (name, id, dept) VALUES ('아무개', '0893012', '컴퓨터공학')");
            printTable(stmt);

            // 데이터 수정
            stmt.executeUpdate("UPDATE student SET id = '0189011' WHERE name = '아무개'");
            printTable(stmt);

            // 데이터 삭제
            stmt.executeUpdate("DELETE FROM student WHERE name = '아무개'");
            printTable(stmt);

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("SQL 실행 에러");
            e.printStackTrace();
        } 
    }

    // 데이터 출력 메서드
    private static void printTable(Statement stmt) throws SQLException {
        // 데이터 조회
        ResultSet srs = stmt.executeQuery("SELECT * FROM student");
        
        // 데이터 출력
        while (srs.next()) {
            System.out.print(srs.getString("name"));
            System.out.print("\t|\t" + srs.getString("id"));
            System.out.println("\t|\t" + srs.getString("dept"));
        }
    }
}
