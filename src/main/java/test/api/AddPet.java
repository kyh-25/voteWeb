package test.api;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import test.dao.PetitionDao;

/**
 * Servlet implementation class AddPet
 */
@WebServlet("/test/AddPet")
public class AddPet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			session.setAttribute("welcomeMessage", "청원 신청은 로그인 이후에 가능합니다.");
			response.sendRedirect("/test/jsp/login.jsp");
		}else {
			String category = (String)request.getParameter("category");
			String title = (String)request.getParameter("title");
			String writer = (String)request.getParameter("writer");
			String writerId = (String)request.getParameter("writerId");
			String problem = (String)request.getParameter("problem");
			String solution = (String)request.getParameter("solution");
			String effect = (String)request.getParameter("effect");
			
			
			PetitionDao dao = new PetitionDao();
			dao.addPetition(category,title,writer,writerId,problem,solution,effect);
			
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("IndexServlet");
			requestdispatcher.forward(request, response);
		}
	}

}
