package Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.mycls.ssm.entity.Users;
import com.mycls.ssm.entity.UsersExample;
import com.mycls.ssm.entity.UsersExample.Criteria;

import mapper.UsersMapper;
import util.DButil;

public class UserService {
	
	//�����û�����ȡ��Ϣ
	public Users getUserbyname(String name){
		SqlSession sqlsession = DButil.getSqlsession();
		UsersMapper mapper = sqlsession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(name);
		List<Users> userlist = mapper.selectByExample(example);
		sqlsession.close();
		for(Users user : userlist){
			return user ;
		}
		return null;
	}
	//��¼
	public boolean login(Users user,HttpServletRequest request) {
		boolean flag = false ;
		SqlSession sqlsession = DButil.getSqlsession();
		UsersMapper mapper = sqlsession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria c = example.createCriteria();
		c.andUsernameEqualTo(user.getUsername());
		List<Users> userslist = mapper.selectByExample(example);
		if(userslist.size() > 0) {
			for(Users users : userslist) {
				request.getSession().setAttribute("user", users);			
				if(users.getPassword().equals(user.getPassword())) {
					flag = true;
				}
			}
		}
		sqlsession.close();
		return flag ;
	}
	
	//ע��
	public boolean register(Users user) {
		boolean flag = false ; 
		SqlSession sqlsession = DButil.getSqlsession();
		UsersMapper mapper = sqlsession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria c = example.createCriteria();
		c.andUsernameEqualTo(user.getUsername());
		List<Users> userslist = mapper.selectByExample(example);
		int size = userslist.size();
		if(size == 0) {
			int insert = mapper.insert(user);
			flag = true;
		}
		sqlsession.close();
		return flag ;
	}
	
	//��������
	public List<Users> selfinfo( HttpServletRequest request) {
		boolean flag = false ; 
		SqlSession sqlsession = DButil.getSqlsession();
		UsersMapper mapper = sqlsession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria condition = example.createCriteria();
		Users user = (Users)request.getSession().getAttribute("user");
		if(user != null){
			Criteria andUsernameEqualTo = condition.andUsernameEqualTo(user.getUsername());
			List<Users> userlist = mapper.selectByExample(example);
			sqlsession.close();
			return userlist ;
		}
		return null;
	}
	
	//�޸ĸ�����Ϣ
	public int updateuser(Users user) {
		SqlSession sqlsession = DButil.getSqlsession();
		UsersMapper mapper = sqlsession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		Criteria condition = example.createCriteria();
		condition.andUidEqualTo(user.getUid());
		int updateByPrimaryKey = mapper.updateByPrimaryKey(user);
		sqlsession.close();
		return updateByPrimaryKey ;
	}
}
