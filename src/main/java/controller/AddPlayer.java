package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PlayerDao;
import model.Player;

/**
 * Servlet implementation class AddPlayer
 */
@WebServlet("/add-player")
public class AddPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlayer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/vue/Player/player.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerDao playerDao = new PlayerDao();
		Player player = new Player(request.getParameter("email"),request.getParameter("nickname"));
		
		if (playerDao.ajout(player)) {
			System.out.println("Joueur ajouté");
			response.sendRedirect("/scoreboard/");
		} else {
			request.setAttribute("error", "Le joueur n'a pas pu être ajouté");
			doGet(request, response);
		}
	}

}
