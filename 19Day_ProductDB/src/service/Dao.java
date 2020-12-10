package service;

import java.util.ArrayList;

import vo.Product;

public interface Dao {

	void insert(Product p);
	
	Product select(int num);
	
	ArrayList<Product> selectAll();
	
	void delete(int num);
	
	void update(int num, Product p);
}
