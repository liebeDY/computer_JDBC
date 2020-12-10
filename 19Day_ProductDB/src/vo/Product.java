package vo;

public class Product {

	private int num; // ������ = �ڸ��� ä���ָ�(0) �˾Ƽ� �����ȴ�
	private String name;
	private int price;
	
	public Product() {
		super();
	}
	public Product(int num, String name, int price) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", price=" + price + "]";
	}
}
