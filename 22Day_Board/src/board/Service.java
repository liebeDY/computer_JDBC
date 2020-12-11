package board;

import java.util.List;

public interface Service {

	void write(Board b);
	
	Board getArticle(int num);
	
	List getArticles();
	
	void editArticle(Board b);
	
	void delArticle(int num);
}
