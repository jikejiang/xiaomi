package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycls.ssm.entity.Cart;
import com.mycls.ssm.entity.Good;
import com.mycls.ssm.entity.Users;

import Service.CartService;
import Service.GoodService;
import Service.UserService;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GoodServlet
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
    public GoodServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodService goodservice = new GoodService();
		String operate = request.getParameter("operate");
		
		if(("detail").equals(operate)){
			String goodname = request.getParameter("good_name");
			List<Good> goodsList = goodservice.getAllgood(goodname);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("goodcList", goodsList);
			request.setAttribute("goodtList", goodsList);
			request.getRequestDispatcher("/goods_details.jsp").forward(request, response);
		}else if(("xiaomi").equals(operate)) {
			List<Good> goodsList = goodservice.getAllgood("xiaomi");
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodService goodservice = new GoodService();
		String operate = request.getParameter("operate");
		//模糊查询
		if(("search").equals(operate)){
			String goodname = request.getParameter("good_name");
			if(goodname != "") {
				List<Good> goodsList = goodservice.getAllgood(goodname);
				request.setAttribute("goodsList", goodsList);
				request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
				return ;
			}
			request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
		}else if(("buy").equals(operate)) {
			Users user = (Users)request.getSession().getAttribute("user");
			CartService cartservice = new CartService();
			if(user != null){
				String type = request.getParameter("type");
				String color = request.getParameter("color");
				String goodname = request.getParameter("good_name");
				List<Good> goodlist = goodservice.tiaojianchaxun(0,type, 0f, goodname, color);
				Good good = null;
				List<Cart> cartlist = null;
				if(goodlist.size() != 0){
					good = goodlist.get(0);
				
				//查询是否为首次添加购物车
				cartlist = cartservice.selectcartbyuidgid(user.getUid(), good.getGoodId() ,0);
				}
				Cart cart = new Cart();
				System.out.println(cartlist);
				if(cartlist.size() != 0){
					//非首次添加
					cart.setGoodId(good.getGoodId());
					cart.setGoodNum(cartlist.get(0).getGoodNum()+1);
					cart.setPrice(good.getGoodPrice()*cart.getGoodNum());
					cart.setStatus(0);
					cart.setUid(user.getUid());
					cart.setPreId(cartlist.get(0).getPreId());
					cartservice.addscart(cart);
				}else {
					//首次添加
					cart.setGoodId(good.getGoodId());
					cart.setGoodNum(1);
					cart.setPrice(good.getGoodPrice());
					cart.setStatus(0);
					cart.setUid(user.getUid());
					int addcart = cartservice.addcart(cart);
				}
				request.getRequestDispatcher("/success_add_cart.jsp").forward(request, response);
			}
			
		}else if(("typeforcolor").equals(operate)){
			String username = (String)request.getSession().getAttribute("username");
			if(username != null){
				String type = request.getParameter("type");
				String color = request.getParameter("color");
				String goodname = request.getParameter("goodname");
				String price = request.getParameter("price");
				
				String REGEX_CHINESE = "[\u4e00-\u9fa5]";
				price = price.replaceAll(REGEX_CHINESE, "");
				
				request.getSession().setAttribute("good_name", goodname);
				
				List<Good> goodlist = goodservice.tiaojianchaxun(0,type, Float.valueOf(price), goodname , null);
				JSONArray ja = JSONArray.fromObject(goodlist);
				
				response.getWriter().append(ja.toString());
			}
		}
	}
}
