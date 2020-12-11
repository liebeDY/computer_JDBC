package product;

import java.util.List;

public interface Dao {

	void insert(Product p);
	
	Product selectByNum(int num);
	
	Product selectByName(String name);
	
	List selectAll();
	
	void update(Product p);
	
	void delete(int num);
}
