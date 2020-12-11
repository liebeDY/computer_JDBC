package join;

public class ServiceImpl implements Service {

	private Dao dao = new DaoOracleImpl();
	
	@Override
	public boolean addMember(Member m) {

		int line = dao.insertMember(m);
		
		if (line <= 0) {
			return false; // 값이 없으면
		} else {
			return true;
		}
	}

	@Override
	public boolean editMemberInfo(Member m) {

		int line = dao.updateMember(m);
		
		if (line <= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean removeMember(String id) {

		int line = dao.removeMember(id);
		
		if (line <= 0) {
			return false; 
		} else {
			return true;
		}
	}

	@Override
	public boolean login(String id, String pwd) {

		return dao.login(id, pwd);
	}

	@Override
	public Member getMember(String id) {
		return dao.memberInfo(id);
	}
}
