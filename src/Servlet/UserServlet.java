package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mycls.ssm.entity.Users;
import com.mycls.ssm.entity.UsersExample;
import com.mycls.ssm.entity.UsersExample.Criteria;

import Service.UserService;
import mapper.UsersMapper;
import net.sf.json.JSONObject;
import util.DButil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userservice = new UserService();
		
		//�˳���¼ UserServlet?operate=logout
		String operate = request.getParameter("operate");
		if(("logout").equals(operate)){
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		}
		
		if("selfinfo".equals(operate)) {
			List<Users> userlist = userservice.selfinfo(request);
			if(userlist != null){
				for(Users user1 : userlist) {
					request.getSession().setAttribute("user", user1);
				}
				request.getRequestDispatcher("/self_info.jsp").forward(request, response);
				return ;
			}
			request.getSession().invalidate();			
			response.sendRedirect("login.jsp");
		}

		if("edit".equals(operate)) {
			Users user = (Users)request.getSession().getAttribute("user");
			request.setAttribute("user", user);
			request.getRequestDispatcher("edituser.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operate = request.getParameter("operate");
		String uid = request.getParameter("uid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phonenumber = request.getParameter("phonenumber");
		String address = request.getParameter("address");
		String hobby = request.getParameter("hobby");
		String sign = request.getParameter("sign");
		
		Users user = new Users(username, password, phonenumber, address, hobby, sign);
		
		UserService usersservice = new UserService();
		//��֤��֤���Ƿ���ȷ
		String codeimg = request.getParameter("image");
		String code = (String) request.getSession().getAttribute("code");
		
		//��֤�Ƿ���ע������
		if("register".equals(operate)){
			if(!codeimg.equals(code)) {
				response.getWriter().write("验证码错误");
				return ;
			}
			boolean register = usersservice.register(user);
			if(register) {
				response.getWriter().write("注册成功");
			}else {
				response.getWriter().write("用户易存在");
			}
		}else if("login".equals(operate)){
			if(!codeimg.equals(code)) {
				response.getWriter().write("验证码错误");
				return ;
			}
			boolean login = usersservice.login(user , request);
			if(login) {
				Users users = (Users)request.getSession().getAttribute("user");
				request.getSession().setAttribute("username", users.getUsername());
			}else {
				response.getWriter().write("用户名或密码错误");
			}
		}else if("editupdate".equals(operate)) {
			user.setUid(Integer.valueOf(uid));
			int updateuser = usersservice.updateuser(user);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/self_info.jsp").forward(request, response);
		}
	}
}
