package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContestDAO;
import dao.PlayerDao;
import model.Contest;
import model.Player;

/**
 * Servlet implementation class GestionTournoi
 */
@WebServlet("/gestion-contest")
public class GestionTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContestDAO contestDao = new ContestDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionTournoi() {
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
		int id_contest = Integer.parseInt(request.getParameter("id"));
		System.out.println(id_contest);
		ArrayList<Player> players = contestDao.getPlayersById(id_contest);
		ArrayList<Player> allPlayers = playerDao.read();
		Contest contest = contestDao.getContestById(id_contest);
		request.setAttribute("id_contest", contest.getId());
		request.setAttribute("nom_jeu", contest.getGame().getTitle());
		request.setAttribute("players", players);
		request.setAttribute("nbre_joueur", players.size());
		request.setAttribute("max_joueur", contest.getGame().getMax_players());
		request.setAttribute("min_joueur", contest.getGame().getMin_players());
		request.setAttribute("all_players", allPlayers);

		request.getRequestDispatcher("/vue/gestionContest/gestion-contest.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int contest_id = Integer.parseInt(request.getParameter("contest_id"));
		int player_id = Integer.parseInt(request.getParameter("player_id"));
		String action = request.getParameter("action");

		if (action.equals("add_player")) {
			contestDao.addPlayerToContestById(contest_id, player_id);
		}
		
		if (action.equals("winner")) {
			System.out.println("Je suis dans le winner");
			contestDao.addWinnerById(contest_id, player_id);
		}
		
		doGet(request, response);
	}

}
