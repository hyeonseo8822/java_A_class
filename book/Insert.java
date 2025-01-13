package book;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Insert extends JFrame {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField isbnField;
    private JTextField summaryField;

    public Insert() {

        setTitle("책 정보 입력");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
		setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("책 제목");
        titleLabel.setBounds(20, 20, 100, 25);
        add(titleLabel);
        titleField = new JTextField();
        titleField.setBounds(120, 20, 200, 25);
        add(titleField);

        JLabel authorLabel = new JLabel("저자");
        authorLabel.setBounds(20, 60, 100, 25);
        add(authorLabel);
        authorField = new JTextField();
        authorField.setBounds(120, 60, 200, 25);
        add(authorField);

        JLabel genreLabel = new JLabel("장르");
        genreLabel.setBounds(20, 100, 100, 25);
        add(genreLabel);
        genreField = new JTextField();
        genreField.setBounds(120, 100, 200, 25);
        add(genreField);

        JLabel isbnLabel = new JLabel("ISBN");
        isbnLabel.setBounds(20, 140, 100, 25);
        add(isbnLabel);
        isbnField = new JTextField();
        isbnField.setBounds(120, 140, 200, 25);
        add(isbnField);

        JLabel summaryLabel = new JLabel("책 설명");
        summaryLabel.setBounds(20, 180, 100, 25);
        add(summaryLabel);
        summaryField = new JTextField();
        summaryField.setBounds(120, 180, 200, 25);
        add(summaryField);

        JButton insertButton = new JButton("입력");
        insertButton.setBounds(150, 220, 130, 25);
        insertButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                String isbn = isbnField.getText();
                String summary = summaryField.getText();

                // JSON 파일에 저장
                try {
                    save(title, author, genre, isbn, summary);
                    setVisible(false);
                    new BookView();
                } catch (IOException ioException) {
                    ioException.getMessage();
                }
                
            }
        });
        add(insertButton);

        setVisible(true);
    }

    public static void save(String title, String author, String genre, String isbn, String summary) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/books.json");
        ArrayNode books = objectMapper.createArrayNode();

        if (file.exists()) {
            JsonNode rootNode = objectMapper.readTree(file);  // 파일을 읽어서 JSON 트리로 변환
            if (rootNode.isArray()) {  // 읽은 JSON이 배열인지 확인
                books = (ArrayNode) rootNode;  // JSON 배열을 books 변수에 저장
            }
        }

        ObjectNode bookNode = objectMapper.createObjectNode(); //빈 JSON 객체를 만들 때 사용하는 메서드
        bookNode.put("id", books.size() + 1);
        bookNode.put("title", title);
        bookNode.put("author", author);
        bookNode.put("summary", summary);
        bookNode.put("isbn", isbn);
        bookNode.put("genre", genre);
        bookNode.put("isAvailable", true);

        books.add(bookNode);

        objectMapper.writeValue(file, books);
    }

    public static void main(String[] args) {
        new Insert();
    }
}
