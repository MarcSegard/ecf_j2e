package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContestDAO;

/**
 * Servlet implementation class DeleteUserContest
 */
@WebServlet("/delete-user-contest")
public class DeleteUserContest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserContest() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contest_id = Integer.parseInt(request.getParameter("id_contest"));
		int player_id = Integer.parseInt(request.getParameter("id_user"));
		
		ContestDAO contestDao = new ContestDAO();
		contestDao.deletePlayerFromContestById(contest_id, player_id);
		request.setAttribute("id_contest", contest_id);
		System.out.println(contest_id + " " + player_id);
		
		//request.getRequestDispatcher("/vue/gestionContest/gestion-contest.jsp").forward(request, response);
		response.sendRedirect("/scoreboard/gestion-contest?id="+contest_id);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
