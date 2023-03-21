package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContestDAO;
import dao.GameDao;
import dao.PlayerDao;
import model.Contest;
import model.Player;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accueil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PlayerDao playerDao = new PlayerDao();

		request.setAttribute("joueurs", playerDao.read());
		request.setAttribute("games", new GameDao().read());

		ArrayList<Contest> contests = new ContestDAO().read();

		// For each pour mettre en place les bons paramètres
		// du winner
		for (Contest contest : contests) {
			if (contest.getWinner().getId() != 0) {
				Player winner = playerDao.getById(contest.getWinner().getId());
				contest.getWinner().setEmail(winner.getEmail());
				contest.getWinner().setNickname(winner.getNickname());
			} else {
				contest.getWinner().setNickname("Pas de gagnant");
			}
		}

		request.setAttribute("contests", contests);

		// Les contests in progress sont les contest où les winner sont à null
		// Je considère que le vainqueur n'a pas encore été désigné
		request.setAttribute("contests_in_progress", new ContestDAO().contestInProgress());

		request.getRequestDispatcher("/vue/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
