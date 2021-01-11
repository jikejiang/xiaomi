/**
 * 
 */
package JUnit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mycls.ssm.entity.Cart;
import com.mycls.ssm.entity.CartExample;
import com.mycls.ssm.entity.CartExample.Criteria;

import mapper.CartMapper;
import util.DButil;

public class test {

	@Test
	public void test() {
		CartMapper mapper = DButil.getSqlsession().getMapper(CartMapper.class);
		CartExample example = new CartExample();
		Criteria c = example.createCriteria();
		c.andGoodIdEqualTo(9);
		List<Cart> Cartlist = mapper.selectByExample(example );
		for(Cart cart : Cartlist) {
			System.out.println(cart);
		}
	}

}
