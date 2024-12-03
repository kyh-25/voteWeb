package test.api;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import test.dao.PetitionDao;
import test.dto.Petition;

/**
 * Servlet implementation class PetitionBoardServlet
 */
@WebServlet("/test/PetitionBoardServlet")
public class PetitionBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetitionBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String category = request.getParameter("category");
		String order = request.getParameter("order");
		
		PetitionDao dao = new PetitionDao();
		
		if(category != null) {
			if(category.equals("전체")) {
				List<Petition> petList = dao.getPet();
				request.setAttribute("petList",petList);
			} else {
				List<Petition> petList = dao.getPetByCategory(category);
				request.setAttribute("petList",petList);
			}
		} else if(order != null) {
			if(order.equals("recent")) {
				List<Petition> petList = dao.getPet();
				request.setAttribute("petList",petList);
			}else {
				List<Petition> petList = dao.getTopNoAnsPet();
				request.setAttribute("petList",petList);
			}
		}else {
			List<Petition> petList = dao.getPet();
			request.setAttribute("petList",petList);
		}
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("jsp/petitionBoard.jsp");
		requestdispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
