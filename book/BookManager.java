package book;

import javax.swing.*;

public class BookManager{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            BookView bookView = new BookView();
            bookView.setVisible(true);
        });
	}

}
class BookController {
	
}
//뷰
class BookView extends JFrame {
	public BookView() {
        setTitle("도서 관리");
        setSize(600, 500);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
	}
}

//모델
class BookModel {
	private String title;
    private String author;
    private String summary;
    private String isbn;
    private String price;
	
	public BookModel(String title, String author, String summary, String isbn, String price) {
		super();
		this.title = title;
		this.author = author;
		this.summary = summary;
		this.isbn = isbn;
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}