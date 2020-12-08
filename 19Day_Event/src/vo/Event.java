package vo;

public class Event {

	private int num;
	private String email;
	
	public Event(int num, String email) {
		super();
		this.num = num;
		this.email = email;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Event [num=" + num + ", email=" + email + "]";
	}
}
