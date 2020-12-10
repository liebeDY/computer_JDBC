package vo;

public class Student extends Member {

	private String school;

	public Student() {
		super();
	}

	public Student(String id, String name, String tel, String addr, int type, String school) {
		super(id, name, tel, addr, type);
		this.school = school;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Student [school=" + school + ", getId()=" + getId() + ", getName()=" + getName() + ", getTel()="
				+ getTel() + ", getAddr()=" + getAddr() + ", getType()=" + getType() + "]";
	}
}
