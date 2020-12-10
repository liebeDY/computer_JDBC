package vo;

public class Professor extends Member {

	private String dept;

	public Professor() {
		super();
	}

	public Professor(String id, String name, String tel, String addr, int type, String dept) {
		super(id, name, tel, addr, type);
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Professor [dept=" + dept + ", getId()=" + getId() + ", getName()=" + getName() + ", getTel()="
				+ getTel() + ", getAddr()=" + getAddr() + ", getType()=" + getType() + "]";
	}
}
