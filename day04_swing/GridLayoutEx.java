package day04_swing;

import javax.swing.*;
import java.awt.*;

public class GridLayoutEx extends JFrame{
	public GridLayoutEx() {
		setTitle("GridLayout sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫음과 동시에 프로그램 종료

		GridLayout gird = new GridLayout(4,2);
		// 4행 2열
		gird.setVgap(5); // 간격

		Container c = getContentPane();
		c.setLayout(gird);

		c.add(new JLabel(" 이름"));
		c.add(new JTextField(""));
		c.add(new JLabel(" 학번"));
		c.add(new JTextField(""));
		c.add(new JLabel(" 학과"));
		c.add(new JTextField(""));
		c.add(new JLabel(" 과목"));
		c.add(new JTextField(""));

		setSize(300,200);
		setVisible(true);


	}
	public static void main(String[] args) {
		new GridLayoutEx();
	}

}
