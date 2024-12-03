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
import org.springframework.boot.web.servlet.ServletComponentScan;


@WebServlet("/test/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
    	PetitionDao dao = new PetitionDao();
		
    	List<Petition> petList = dao.getPet();
		List<Petition> anspetList = dao.getAnsPet();
		List<Petition> toppetList = dao.getTopPet();
		List<Petition> rejpetList = dao.getRejPet();
		int count = dao.PetitionCount();
		
		request.setAttribute("petList",petList);
		request.setAttribute("petCount",count);
		request.setAttribute("anspetList",anspetList);
		request.setAttribute("toppetList",toppetList);
		request.setAttribute("rejpetList",rejpetList);
		
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("jsp/index.jsp");
		requestdispatcher.forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
