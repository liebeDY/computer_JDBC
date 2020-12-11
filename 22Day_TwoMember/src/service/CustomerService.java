package service;

import vo.Customer;

public class CustomerService  {

	public Dao2 dao2;
	
	public CustomerService() {
		dao2 = new CusDAO();
	}
	
	public void insert(Customer b) {
		dao2.Cusinsert(b);
	}
	
	public void printAll() {
		dao2.CusselectAll();
	}
	
	public void update(Customer b) {
		dao2.Cusupdate(b);
	}
	
	public void delete(String name) {
		dao2.Cusdelete(name);
	}
	
	public Customer findCustomer(String name) {
		return dao2.Cusselect(name);
	}
	
}

