package product;

public class Product {

	private int num;
	private String name;
	private int price;
	private int quantity;
	private String id;
	
	public Product() {
		super();
	}

	public Product(int num, String name, int price, int quantity, String id) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.id = id;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", id=" + id
				+ "]";
	}
}
