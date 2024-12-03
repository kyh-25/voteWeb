package test.api;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import test.dao.UserDao;
import test.dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/test/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String id = (String)request.getParameter("id");
		String pw = (String)request.getParameter("pw");

		UserDao dao = new UserDao();
		User user = dao.getUserByIdPw(id, pw);
		
		if(user.getName()==null) {
			response.sendRedirect("/test/jsp/login.jsp?wrong=1");
		}else {
			session.setAttribute("username", user.getName());
			session.setAttribute("userid", user.getId());
			session.setAttribute("welcomeMessage", "환영합니다, " + user.getName() + "님!");
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("IndexServlet");
			requestdispatcher.forward(request, response);
		}
	}

}
