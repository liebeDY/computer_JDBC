package join;

public interface Dao {
	
	int insertMember(Member m);
	
	int updateMember(Member m);
	
	int removeMember(String id);
	
	boolean login(String id, String pwd);
	
	Member memberInfo(String id);
}
