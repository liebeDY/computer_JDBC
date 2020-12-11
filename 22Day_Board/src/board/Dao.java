package board;

import java.util.List;

public interface Dao {
	
	void insert(Board b);
	
	Board select(int num);
	
	List selectAll();
	
	void update(Board b);
	
	void delete(int num);
}
