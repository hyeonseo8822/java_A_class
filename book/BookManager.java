package book;

import java.io.IOException;

import javax.swing.*;

public class BookManager{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            BookView bookView = null;
			try {
				bookView = new BookView();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            bookView.setVisible(true);
        });
	}

}

