package book;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Ex01 extends JFrame {

    public Ex01() {
        setTitle("도서 관리");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 메뉴 바
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(105, 159, 214));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        
        //메뉴바 두께 늘리기
        menuBar.setPreferredSize(new java.awt.Dimension(600, 40));
        
        JMenu insertMenu = new JMenu("Insert");
        insertMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        JMenu searchMenu = new JMenu("Search");
        searchMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        JMenu listMenu = new JMenu("List");
        listMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        
        menuBar.add(insertMenu);
        menuBar.add(searchMenu);
        menuBar.add(listMenu);
        
        setJMenuBar(menuBar);
        
        setVisible(true); 
    }

    public static void main(String[] args) {
        new Ex01();
    }
}
