package service;

import java.util.ArrayList;

import vo.Customer;

public interface Dao2 {

	void con();
	
	void discon();
	
	void Cusinsert(Customer b);
	
	Customer Cusselect(String name);
	
	void Cusupdate(Customer b);
	
	void Cusdelete(String name);
	
	ArrayList<Customer> CusselectAll();
}
