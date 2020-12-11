package product;

import java.util.List;

import product.Dao;
import product.DaoImpl;

public class ServiceImpl implements Service {

	private Dao dao = new DaoImpl();
	@Override
	public void addProduct(Product p) {
		dao.insert(p);
	}

	@Override
	public Product getByNum(int num) {
		return dao.selectByNum(num);
	}

	@Override
	public Product getNyName(String name) {
		return dao.selectByName(name);
	}

	@Override
	public List getAll() {
		return dao.selectAll();
	}

	@Override
	public void editProduct(Product p) {
		dao.update(p);
	}

	@Override
	public void delProduct(int num) {
		dao.delete(num);
	}
}
