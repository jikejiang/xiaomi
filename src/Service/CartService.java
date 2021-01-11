package Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mycls.ssm.entity.Cart;
import com.mycls.ssm.entity.CartExample;
import com.mycls.ssm.entity.Good;
import com.mycls.ssm.entity.GoodExample.Criteria;
import com.mycls.ssm.entity.Users;
import com.mycls.ssm.entity.UsersExample;

import mapper.CartMapper;
import mapper.UsersMapper;
import util.DButil;

public class CartService {

	//��ѯ���ﳵ��Ϣ
	public List<Cart> getAllCart(Good good,Users user){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		CartExample example = new CartExample();
		com.mycls.ssm.entity.CartExample.Criteria c = example.createCriteria();
		c.andGoodIdEqualTo(good.getGoodId());
		c.andUidEqualTo(user.getUid());
		List<Cart> cartlist = mapper.selectByExample(example);
		sqlsession.close();
		return cartlist ;
	}
	
	//����������ѯ�û�
	public List<Users> getUser(String name){
		SqlSession sqlsession = DButil.getSqlsession();
		UsersMapper mapper = sqlsession.getMapper(UsersMapper.class);
		UsersExample example = new UsersExample();
		com.mycls.ssm.entity.UsersExample.Criteria c = example.createCriteria();
	    c.andUsernameEqualTo(name);
	    List<Users> userlist = mapper.selectByExample(example);
	    sqlsession.close();
	    return userlist ;
	}
	
	//��ӹ��ﳵ
	public int addcart(Cart cart){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		int result =  mapper.insert(cart);
		sqlsession.close();
		return result;
	}
	public int addscart(Cart cart){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		int result =  mapper.updateByPrimaryKey(cart);
		sqlsession.close();
		return result;
	}
	
	//查询是否为首次添加购物车
	public List<Cart> selectcartbyuidgid(int uid,int gid ,int cid){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		CartExample example = new CartExample();
		com.mycls.ssm.entity.CartExample.Criteria createCriteria = example.createCriteria();
		if(uid != 0){
			createCriteria.andUidEqualTo(uid);
		}
		if(gid != 0){
			createCriteria.andGoodIdEqualTo(gid);
		}
		if(cid != 0) {
			createCriteria.andPreIdEqualTo(cid);
		}
		List<Cart> cartlist = mapper.selectByExample(example);
		sqlsession.close();
		return cartlist ;
	}
	
	//根据购物车id删除购物车
	public boolean deleteCart(int cid){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		int result = mapper.deleteByPrimaryKey(cid);
		sqlsession.close();
		return result > 0 ? true:false ;
	}
	
	//修改购物车信息
	public boolean updateCart(Cart cart){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		int result = mapper.updateByPrimaryKey(cart);
		return result>0?true:false;
	}
	
	//查询购物车
	public List<Cart> selctCart(Cart cart){
		SqlSession sqlsession = DButil.getSqlsession();
		CartMapper mapper = sqlsession.getMapper(CartMapper.class);
		CartExample example = new CartExample();
		com.mycls.ssm.entity.CartExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andGoodNumEqualTo(cart.getGoodNum());
		createCriteria.andPriceEqualTo(cart.getPrice());
		List<Cart> cartlist = mapper.selectByExample(example);
		return cartlist ; 
	}
}
