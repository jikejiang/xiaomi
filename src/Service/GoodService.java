package Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mycls.ssm.entity.Cart;
import com.mycls.ssm.entity.CartExample;
import com.mycls.ssm.entity.Good;
import com.mycls.ssm.entity.GoodExample;
import com.mycls.ssm.entity.Users;
import com.mycls.ssm.entity.UsersExample;
import com.mycls.ssm.entity.GoodExample.Criteria;

import mapper.GoodMapper;
import mapper.UsersMapper;
import mapper.CartMapper;
import util.DButil;

public class GoodService {

	//��ȡ��Ϣ
	public List<Good> getAllGood(Cart cart){
		SqlSession sqlsession = DButil.getSqlsession();
		GoodMapper mapper = sqlsession.getMapper(GoodMapper.class);
		GoodExample example = new GoodExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andGoodIdEqualTo(cart.getGoodId());
		List<Good> Goodlist = mapper.selectByExample(example);
		sqlsession.close();
		return Goodlist ;
	}
	////�����ֻ�����ȡ��Ϣ
	public Good getGoodbyname(String name){
		SqlSession sqlsession = DButil.getSqlsession();
		GoodMapper mapper = sqlsession.getMapper(GoodMapper.class);
		GoodExample example = new GoodExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andGoodNameEqualTo(name);
		List<Good> goodlist = mapper.selectByExample(example);
		sqlsession.close();
		for(Good good : goodlist){
			return good ;
		}
		return null;
	}
	public List<Good> getAllgood(String goodname){
		SqlSession sqlsession = DButil.getSqlsession();
		GoodMapper mapper = sqlsession.getMapper(GoodMapper.class);
		GoodExample example = new GoodExample();
		Criteria c = example.createCriteria();
		c.andGoodNameLike("%"+goodname+"%");
		List<Good> goodlist = mapper.selectByExample(example);
		sqlsession.close();
		return goodlist ;
	}
	
	//��ӹ��ﳵ
	public List<Good> selectgood(Good good) {
		SqlSession sqlsession = DButil.getSqlsession();
		GoodMapper mapper = sqlsession.getMapper(GoodMapper.class);
		GoodExample example = new GoodExample();
		Criteria c = example.createCriteria();
		c.andGoodNameEqualTo(good.getGoodName());
		List<Good> goodlist = mapper.selectByExample(example);
		sqlsession.close();
		return goodlist ; 
	}
	
	//条件查询
	public List<Good> tiaojianchaxun(int id , String type , Float price , String goodname ,String color) {
		SqlSession sqlsession = DButil.getSqlsession();
		GoodMapper mapper = sqlsession.getMapper(GoodMapper.class);
		GoodExample example = new GoodExample();
		Criteria createCriteria = example.createCriteria();
		
		if(id != 0){
			createCriteria.andGoodIdEqualTo(id);
		}
		if(price != 0){
			createCriteria.andGoodPriceEqualTo(price);
		}
		if(color != null){
			createCriteria.andGoodColorEqualTo(color);
		}
		if(type != null){
			createCriteria.andGoodTypeEqualTo(type);
		}
		if(goodname != null){
			createCriteria.andGoodNameEqualTo(goodname);
		}
		List<Good> selectByExample = mapper.selectByExample(example);
		sqlsession.close();
		return selectByExample ; 
	}
	
}
