package book;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookView extends JFrame {

    public BookView() throws IOException {
    	String filePath = "src/books.json";
		//json파일을 읽기 위해 사용
		ObjectMapper objectMapper = new ObjectMapper();
		//readTree는 json을 jsonNode(트리 구조로 저장하는 클래스) 객체로 읽음
		JsonNode rootNode = objectMapper.readTree(new File(filePath));


		setTitle("도서 관리");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(105, 159, 214));
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT,10, 5));

		// 메뉴바 두께 늘리기
		menuBar.setPreferredSize(new java.awt.Dimension(600, 40));

		JMenu insertMenu = new JMenu("Insert");
		insertMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		JMenuItem addBookItem = new JMenuItem("책 추가");
		insertMenu.add(addBookItem);
		
		JMenu searchMenu = new JMenu("Search");
		searchMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		JMenuItem searchBookItem  = new JMenuItem("책 검색");
		searchMenu.add(searchBookItem);
		

		menuBar.add(insertMenu);
		menuBar.add(searchMenu);

		setJMenuBar(menuBar);

		JLabel lblNewLabel_1 = new JLabel("관심 장르 ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 82, 27);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("추천 독서");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(75, 88, 127, 27);
		getContentPane().add(lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(75, 2, 82, 23);
		getContentPane().add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("서양의 철학사");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(48, 51, 174, 27);
		getContentPane().add(lblNewLabel_2);


		// 값이 없음
		Set<String> genreSet = new HashSet<>();
		for (JsonNode bookNode : rootNode) {
			//asText를 사용하지 않으면 JsonNode 객체를 반환
			String genre = bookNode.path("genre").asText();
			genreSet.add(genre);
		}
		for (String genre : genreSet) {
			comboBox.addItem(genre);
		}
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String genre = (String) comboBox.getSelectedItem();
				ArrayList<String> books = new ArrayList<>();
				
				for (JsonNode bookNode : rootNode) {
					String bookGenre = bookNode.path("genre").asText();
					boolean isAvailable = bookNode.path("isAvailable").asBoolean(); 	
					if (bookGenre.equals(genre) && isAvailable) {
						String bookTitle = bookNode.path("title").asText();
						books.add(bookTitle);
					}
				}
				if (books.size() > 0) {
		            int rand = (int)(Math.random() * books.size());
		            lblNewLabel_2.setText(books.get(rand));
		        } else {
		            lblNewLabel_2.setText("대출 가능한 책이 없습니다.");
		        }
			}
		});
		
		addBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new Insert();
			}
		});
		searchBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new Search();
			}
		});
		setVisible(true); 
    }

}
