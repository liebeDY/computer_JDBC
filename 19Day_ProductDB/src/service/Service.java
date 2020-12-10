package service;

import java.util.ArrayList;

import vo.Product;

public interface Service {

	void addProduct();
	
	Product getProduct();
	
	ArrayList<Product> getProducts();
	
	void editProduct();
	
	void delProdcut();
}
