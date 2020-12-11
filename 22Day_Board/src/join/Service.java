package join;

public interface Service {
	
	boolean addMember(Member m);
	
	boolean editMemberInfo(Member m);
	
	boolean removeMember(String id);
	
	boolean login(String id, String pwd);
	
	Member getMember(String id);
}
