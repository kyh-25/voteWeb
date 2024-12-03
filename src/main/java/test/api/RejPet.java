package test.api;

import test.dao.PetitionDao;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RejPet")
public class RejPet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 데이터베이스 연결 정보
    private static String dburl = "jdbc:mysql://localhost:3306/team?serverTimezone=Asia/Seoul&useSSL=false";
    private static String dbUser = "root";
    private static String dbpasswd = "0000";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        String petitionId = request.getParameter("petitionId");
        int petId = Integer.parseInt(petitionId);

        if (petitionId == null) {
            session.setAttribute("errorMessage", "청원 ID가 필요합니다.");
            response.sendRedirect("/test/jsp/errorPage.jsp");
            return;
        }

        PetitionDao dao = new PetitionDao();
        boolean isDeleted = dao.rejPetition(petId);

        if (isDeleted) {
            session.setAttribute("welcomeMessage", "청원 반려에 성공했습니다.");
            response.sendRedirect("/test/IndexServlet");

        } else {
            session.setAttribute("errorMessage", "청원 반려에 실패했습니다.");
            response.sendRedirect("/test/jsp/errorPage.jsp");
        }
    }
}
