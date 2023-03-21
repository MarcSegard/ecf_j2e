package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

import model.Contest;
import model.Game;
import model.Player;


public class ContestDAO {

	Connection connect = Connect.getConnection();

	PreparedStatement req;
	ResultSet rs;


	public boolean ajout(Contest contest) {
		try {
			req = connect.prepareStatement("INSERT INTO contest (game_id,start_date) values (?,?)");
			req.setInt(1, contest.getId());
			req.setDate(2, java.sql.Date.valueOf(contest.getStart_date()));
			
			req.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("Insertion KO");
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Contest> read(){
		ArrayList<Contest> contests = new ArrayList<>();
		Contest contest;
		Game game;
		Player winner;

		try {
			req = connect.prepareStatement("select *, count(*) as total from contest \n"
					+ "inner join game on contest.game_id=game.id\n"
					+ "inner join player_contest on contest.id=player_contest.contest_id\n"
					+ "inner join player on player_contest.player_id=player.id group by contest.id order by contest.start_date DESC");
			rs = req.executeQuery();

			while (rs.next()) {
				game = new Game(rs.getString("title"),rs.getInt("min_players"),rs.getInt("max_players"));
				winner = new Player(rs.getString("email"),rs.getString("nickname"));
				contest = new Contest(rs.getInt(1),game,Instant.ofEpochMilli(rs.getDate("start_date").getTime()).atZone(ZoneId.systemDefault()).toLocalDate(),winner, rs.getInt("total"));
				int winner_id = rs.getInt("winner_id");
				
				contests.add(contest);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contests;
	}
	
	public ArrayList<Player> getPlayersById(int contest_id){
		ArrayList<Player> players = new ArrayList<>();
		Player player= null;

		try {
			req = connect.prepareStatement("select * from contest \n"
					+ "inner join game on contest.game_id=game.id\n"
					+ "inner join player_contest on contest.id=player_contest.contest_id\n"
					+ "inner join player on player_contest.player_id=player.id where contest.id=?");
			req.setInt(1, contest_id);
			rs = req.executeQuery();

			while (rs.next()) {
				player = new Player(rs.getInt(12),rs.getString("email"),rs.getString("nickname"));
				players.add(player);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}
	
	public ArrayList<Contest> contestInProgress(){
		ArrayList<Contest> contests = new ArrayList<>();
		Contest contest;
		Game game;
		Player winner = null;

		try {
			req = connect.prepareStatement("select * from contest \n"
					+ "inner join game on contest.game_id=game.id\n"
					+ "where contest.winner_id is NULL");
			rs = req.executeQuery();

			while (rs.next()) {
				game = new Game(rs.getString("title"),rs.getInt("min_players"),rs.getInt("max_players"));
				contest = new Contest(rs.getInt(1),game,Instant.ofEpochMilli(rs.getDate("start_date").getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), winner);
				contests.add(contest);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contests;
	}
	
	public Contest getContestById(int contest_id){
		Contest contest= null;
		Game game = null;
		
		try {
			req = connect.prepareStatement("SELECT * FROM contest inner join game on contest.game_id=game.id where contest.id=?");
			req.setInt(1, contest_id);
			rs = req.executeQuery();

			if (rs.next()) {
				game = new Game(rs.getString("title"),rs.getInt("min_players"),rs.getInt("max_players"));
				contest = new Contest(rs.getInt(1),game);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contest;
	}
	
	public boolean deletePlayerFromContestById(int contest_id,int player_id) {
		try {
			req = connect.prepareStatement("delete from player_contest where contest_id= ? and player_id = ?");
			req.setInt(1, contest_id);
			req.setInt(2, player_id);
			
			req.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addPlayerToContestById(int contest_id,int player_id) {
		try {
			req = connect.prepareStatement("insert into player_contest (player_id, contest_id) values (?,?)");
			req.setInt(1, player_id);
			req.setInt(2, contest_id);
			req.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addWinnerById(int id,int player_id) {
		try {
			req = connect.prepareStatement("update contest set winner_id=? where id=?");
			req.setInt(1, player_id);
			req.setInt(2, id);
			req.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
