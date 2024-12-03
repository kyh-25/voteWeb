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
 * Servlet implementation class DeletePet
 */
@WebServlet("/test/DeletePet")
public class DeletePet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Check if user is logged in
        if (username == null) {
            session.setAttribute("welcomeMessage", "청원 삭제는 로그인 이후에 가능합니다.");
            response.sendRedirect("/test/jsp/login.jsp");
            return;
        }

        String petitionId = request.getParameter("petitionId");
        if (petitionId == null) {
            session.setAttribute("errorMessage", "삭제할 청원 ID가 필요합니다.");
            response.sendRedirect("/test/jsp/errorPage.jsp");
            return;
        }

        PetitionDao dao = new PetitionDao();
        boolean isDeleted = dao.deletePetition(petitionId);

        if (isDeleted) {
            session.setAttribute("welcomeMessage", "청원 삭제에 성공했습니다.");
            RequestDispatcher requestdispatcher = request.getRequestDispatcher("IndexServlet");
            requestdispatcher.forward(request, response);
        } else {
            session.setAttribute("errorMessage", "청원 삭제에 실패했습니다.");
            response.sendRedirect("/test/jsp/errorPage.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Check if user is logged in
        if (username == null) {
            session.setAttribute("welcomeMessage", "청원 삭제는 로그인 이후에 가능합니다.");
            response.sendRedirect("/test/jsp/login.jsp");
            return;
        }

        String petitionId = request.getParameter("petitionId");
        if (petitionId == null) {
            session.setAttribute("errorMessage", "삭제할 청원 ID가 필요합니다.");
            response.sendRedirect("/test/jsp/errorPage.jsp");
            return;
        }

        PetitionDao dao = new PetitionDao();
        boolean isDeleted = dao.deletePetition(petitionId);

        if (isDeleted) {
            RequestDispatcher requestdispatcher = request.getRequestDispatcher("IndexServlet");
            requestdispatcher.forward(request, response);
        } else {
            session.setAttribute("errorMessage", "청원 삭제에 실패했습니다.");
            response.sendRedirect("/test/jsp/errorPage.jsp");
        }
    }
}
