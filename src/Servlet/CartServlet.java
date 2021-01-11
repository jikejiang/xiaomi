package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mycls.ssm.entity.Cart;
import com.mycls.ssm.entity.CartExample;
import com.mycls.ssm.entity.Good;
import com.mycls.ssm.entity.GoodExample;
import com.mycls.ssm.entity.CartExample.Criteria;
import com.mycls.ssm.entity.CartandGood;
import com.mycls.ssm.entity.Cartlist;
import com.mycls.ssm.entity.Users;

import Service.CartService;
import Service.GoodService;
import mapper.CartMapper;
import mapper.GoodMapper;
import util.DButil;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
       
    public CartServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users)request.getSession().getAttribute("user");
		GoodService goodservice = new GoodService();
		CartService cartservice = new CartService();
		
		
		String operate = request.getParameter("operate");
		//结算 
		if("jiesuan".equals(operate)){
			String cids[] = request.getParameterValues("cart_id");
			String goodprice[] = request.getParameterValues("good_price");
			for(String a : cids){
				System.out.println(a);
			}
			for(String p : goodprice){
				System.out.println(p);
			}
		}
		if("change_number".equals(operate)){
			
			String num[] = request.getParameterValues("good_num");
			String cids[] = request.getParameterValues("cart_id");
			String goodprice[] = request.getParameterValues("good_price");
			
			List<Cart> cartlist = null ;
			boolean updateCart = false ;
				for(int i = 0 ; i < cids.length ; i ++ ){
					List<Cart> cart1 = cartservice.selectcartbyuidgid(0, 0, Integer.valueOf(cids[i]));
					for(Cart cart : cart1){
						cart.setGoodNum(Integer.valueOf(num[i]));
						cart.setPrice(Integer.valueOf(num[i]) * Float.valueOf(goodprice[i]));
						cart.setPreId(Integer.valueOf(cids[i]));
						updateCart = cartservice.updateCart(cart);
					}
				}
				if(updateCart){
					CartServlet.gouwuche(request, response);
				}
		}else 
			//删除购物车信息
			//CartServlet?operate=deleteCart&id=${list.preId}"
			if("deleteCart".equals(operate)){
			String id = request.getParameter("id");
			boolean result = cartservice.deleteCart(Integer.valueOf(id));
			if(result == true){
				List<Cart> cartlist = cartservice.selectcartbyuidgid(user.getUid(),0, 0);
				for(Cart cart : cartlist){

					List<Good> goodlist = goodservice.tiaojianchaxun(cart.getGoodId(), null, 0f, null, null);
					for(Good good : goodlist){
						cart.setG(good);
					}
				}
				request.setAttribute("cartlist", cartlist);
				request.getRequestDispatcher("/cartlist.jsp").forward(request, response);
				return ;
			}else {
				request.setAttribute("rmsg", "删除失败，请联系管理员");
				request.getRequestDispatcher("/cartlist.jsp").forward(request, response);
			}
	}else {
		CartServlet.gouwuche(request, response);
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);			
	}
	public static void gouwuche(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Users user = (Users)request.getSession().getAttribute("user");
		if(user != null){
			
			CartService cartservice = new CartService();
			List<Cart> cartlist = cartservice .selectcartbyuidgid(user.getUid(), 0,0);

			for(Cart cart : cartlist){

				GoodService goodservice = new GoodService();
				List<Good> goodlist = goodservice .tiaojianchaxun(cart.getGoodId(), null, 0f, null, null);
				for(Good good : goodlist){
					cart.setG(good);
				}
			}
			request.setAttribute("cartlist", cartlist);
			request.getRequestDispatcher("/cartlist.jsp").forward(request, response);
			return ;
		}else {
			response.sendRedirect("errorempty.jsp");
		}
	}
}
