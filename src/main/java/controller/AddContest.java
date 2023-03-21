package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContestDAO;
import dao.GameDao;
import model.Contest;

/**
 * Servlet implementation class AddContest
 */
@WebServlet("/add-contest")
public class AddContest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContest() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameDao gameDao = new GameDao();
		request.setAttribute("games",gameDao.read());
		
		request.getRequestDispatcher("/vue/Contest/contest.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContestDAO contestDao = new ContestDAO();
		
		LocalDate date = LocalDate.parse(request.getParameter("date"));
		
		
		Contest contest = new Contest(Integer.parseInt(request.getParameter("game_id")),date);
		
		
		if (contestDao.ajout(contest)){
			response.sendRedirect("/scoreboard/");
		} else {
			request.setAttribute("error", "Le tournoi n'a pas pu être ajouté");
			doGet(request, response);
		}
	}

}
