package vo;

public class Product {

	private int p_id;
	private String name;
	private int num;
	private String co;
	private int price;
	
	public Product(int p_id, String name, int num, String co, int price) {
		super();
		this.p_id = p_id;
		this.name = name;
		this.num = num;
		this.co = co;
		this.price = price;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "力前 [力前锅龋=" + p_id + ", 力前捞抚=" + name + ", 力前荐樊=" + num + ", 力炼荤=" + co + ", 啊拜=" + price + "]";
	}
}
