package book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Edit extends JFrame {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField summaryField;
    
    public Edit(String title, String author, String genre, String isbn, String summary) {

        setTitle("책 정보 입력");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
		setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("책 제목");
        titleLabel.setBounds(20, 20, 100, 25);
        add(titleLabel);
        titleField = new JTextField(title);
        titleField.setBounds(120, 20, 200, 25);
        add(titleField);

        JLabel authorLabel = new JLabel("저자");
        authorLabel.setBounds(20, 60, 100, 25);
        add(authorLabel);
        authorField = new JTextField(author);
        authorField.setBounds(120, 60, 200, 25);
        add(authorField);

        JLabel genreLabel = new JLabel("장르");
        genreLabel.setBounds(20, 100, 100, 25);
        add(genreLabel);
        genreField = new JTextField(genre);
        genreField.setBounds(120, 100, 200, 25);
        add(genreField);


        JLabel summaryLabel = new JLabel("책 설명");
        summaryLabel.setBounds(20, 180, 100, 25);
        add(summaryLabel);
        summaryField = new JTextField(summary);
        summaryField.setBounds(120, 180, 200, 25);
        add(summaryField);

        JButton editButton = new JButton("수정");
        editButton.setBounds(150, 220, 130, 25);
        add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    edit(isbn);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "오류 발생");
                }
                setVisible(false);
                new Search();
            }
        });

        setVisible(true);
    }

    private void edit(String isbn) throws IOException {
        String Title = titleField.getText();
        String Author = authorField.getText();
        String Genre = genreField.getText();
        String Summary = summaryField.getText();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/books.json");
        ArrayNode books;

        if (file.exists()) {
            JsonNode root = objectMapper.readTree(file);
            books = (ArrayNode) root;
        } else {
            books = objectMapper.createArrayNode();
        }

        // 데이터 수정
        boolean updated = false;
        for (JsonNode book : books) {
            if (book.get("isbn").asText().equals(isbn)) {
                ((ObjectNode) book).put("title", Title);
                ((ObjectNode) book).put("author", Author);
                ((ObjectNode) book).put("genre", Genre);
                ((ObjectNode) book).put("summary", Summary);
                updated = true;
                break;
            }
        }

        if (updated) {
            objectMapper.writeValue(file, books);
            JOptionPane.showMessageDialog(null, "수정 완료");
        } else {
            JOptionPane.showMessageDialog(null, "수정 못함");
        }
    }
    public static void main(String[] args) {
    }
}
