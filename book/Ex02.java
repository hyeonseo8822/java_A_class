package book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex02 {

	public static void main(String[] args) {
		try {
            // 파싱할 URL 설정 (예시: 구글 홈페이지)
            String url = "https://www.google.com";
            
            // URL에서 HTML 문서 로드
            Document doc = Jsoup.connect(url).get();
            
            // <a> 태그를 모두 찾아서 링크를 출력
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                System.out.println("Link: " + link.attr("href"));
                System.out.println("Text: " + link.text());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
