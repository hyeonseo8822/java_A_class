package book;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Search extends JFrame {
    private JTextArea resultArea;
    private JTextField searchField;
    private JButton searchButton;

    public Search() {
        setTitle("책 검색");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
		setLocationRelativeTo(null);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        
        //스크롤기능
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        searchField = new JTextField();
        searchButton = new JButton("검색");

        inputPanel.add(searchField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new SearchOne(searchField);
                setVisible(false);
            }
        });
        loadBooks();
    }

    private void loadBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode books = objectMapper.readTree(new File("src/books.json"));

            StringBuilder list = new StringBuilder();

            // 책 제목 출력
            for (JsonNode book : books) {
                String title = book.get("title").asText();
                list.append(title).append("\n");
            }

            resultArea.setText(list.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public static void main(String[] args) {
    	new Search();
    }
}