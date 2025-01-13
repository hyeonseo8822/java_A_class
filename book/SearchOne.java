package book;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SearchOne extends JFrame {
	private JTextField searchField;

	public SearchOne(JTextField searchField) {    
		setTitle("책 검색 결과");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		this.searchField = searchField;

		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		//패널 크기
		resultPanel.setPreferredSize(new Dimension(400, 200));

		searchBooks(resultPanel);

		getContentPane().add(resultPanel, BorderLayout.CENTER);

		setVisible(true); 
	}

	private void searchBooks(JPanel resultPanel) {
		String query = searchField.getText();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode books = objectMapper.readTree(new File("src/books.json"));


			for (JsonNode book : books) {
				String title = book.get("title").asText();
				String author = book.get("author").asText();
				String genre = book.get("genre").asText();
				String isbn = book.get("isbn").asText();
				String summary = book.get("summary").asText();
				
				//입력한 값이 json에 들어가 있을 때
				if (title.contains(query)) {
					JLabel titleLabel = new JLabel("책 제목: " + title);
					titleLabel.setBounds(20, 20, 460, 25);
					resultPanel.add(titleLabel);

					JLabel authorLabel = new JLabel("저자: " + author);
					authorLabel.setBounds(20, 50, 460, 25);
					resultPanel.add(authorLabel);

					JLabel genreLabel = new JLabel("장르: " + genre);
					genreLabel.setBounds(20, 80, 460, 25);
					resultPanel.add(genreLabel);

					JLabel isbnLabel = new JLabel("ISBN: " + isbn);
					isbnLabel.setBounds(20, 110, 460, 25);
					resultPanel.add(isbnLabel);
					
					// 자동 줄바꿈을 위해
					JLabel summaryLabel = new JLabel("<html>설명: " + summary + "</html>");
					summaryLabel.setBounds(20, 110, 460, 100);
					resultPanel.add(summaryLabel);

					JButton btnDelete = new JButton("삭제");
					btnDelete.setBounds(20, 200, 93, 23);
					btnDelete.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							deleteBook(book);
							setVisible(false);
							new Search();
						}
					});
					resultPanel.add(btnDelete);

					JButton btnEdit = new JButton("수정");
					btnEdit.setBounds(134, 200, 93, 23);
					btnEdit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							new Edit(title,author,genre,isbn,summary);
						}
					});
					resultPanel.add(btnEdit);

					JButton btnBorrow = new JButton(book.get("isAvailable").asBoolean() ? "대출" : "반납");
					btnBorrow.setBounds(248, 200, 93, 23);
					btnBorrow.addActionListener(new ActionListener() {
					    @Override
					    public void actionPerformed(ActionEvent e) {
					        if (book.get("isAvailable").asBoolean()) {
					            borrowBook(book);
					            btnBorrow.setText("반납");
					        } else {
					            returnBook(book);
					            btnBorrow.setText("대출");
					        }
					        setVisible(false);
							new Search();
					    }
					});
					resultPanel.add(btnBorrow);
					JButton btnFinish = new JButton("완료");
					btnFinish.setBounds(363, 200, 93, 23);
					btnFinish.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							new Search();
						}
					});
					resultPanel.add(btnFinish);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void deleteBook(JsonNode book) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File("src/books.json");
			JsonNode books = objectMapper.readTree(file);
			ArrayNode updatedBooks = objectMapper.createArrayNode();

			// 삭제할 책을 제외한 나머지 책들만 새로운 배열에 추가
			for (JsonNode b : books) {
				if (!b.get("isbn").asText().equals(book.get("isbn").asText())) {
					updatedBooks.add(b);
				}
			}

			// 파일을 다시 쓰기
			objectMapper.writeValue(file, updatedBooks);

			JOptionPane.showMessageDialog(null,"삭제 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void borrowBook(JsonNode book) {
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        File file = new File("src/books.json");
	        
	        JsonNode books = objectMapper.readTree(file);
	        ArrayNode updatedBooks = objectMapper.createArrayNode();

	        //false로 변경
	        for (JsonNode b : books) {
	            if (b.get("isbn").asText().equals(book.get("isbn").asText())) {
	                ((ObjectNode) b).put("isAvailable", false);
	            }
	            updatedBooks.add(b);
	        }

	        objectMapper.writeValue(file, updatedBooks);
			JOptionPane.showMessageDialog(null,"대출 완료");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private void returnBook(JsonNode book) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        File file = new File("src/books.json");
	        
	        JsonNode books = objectMapper.readTree(file);
	        ArrayNode updatedBooks = objectMapper.createArrayNode();

	        for (JsonNode b : books) {
	            if (b.get("isbn").asText().equals(book.get("isbn").asText())) {
	                ((ObjectNode) b).put("isAvailable", true);
	            }
	            updatedBooks.add(b);
	        }

	        objectMapper.writeValue(file, updatedBooks);
	        
	        JOptionPane.showMessageDialog(null, "반납 완료");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {

	}
}

