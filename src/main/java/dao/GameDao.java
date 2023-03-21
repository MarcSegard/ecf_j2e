package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Game;


public class GameDao implements IDAO<Game>{
	
	Connection connect = Connect.getConnection();
	
	PreparedStatement req;
	ResultSet rs;
	
	@Override
	public boolean ajout(Game game) {
		
		try {
			req = connect.prepareStatement("INSERT INTO game (title,min_players,max_players)"
					+ "VALUES (?,?,?)");
			
			req.setString(1, game.getTitle());
			req.setInt(2, game.getMin_players());
			req.setInt(3, game.getMax_players());
			
			req.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("Insertion KO");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ArrayList<Game> read(){
		ArrayList<Game> games = new ArrayList<>();
		Game game;
		
		try {
			req = connect.prepareStatement("select * from game");
			rs = req.executeQuery();

			while (rs.next()) {
				game = new Game(rs.getInt("id"),rs.getString("title"),rs.getInt("min_players"),rs.getInt("max_players"));
				games.add(game);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}
	
	public Game getByName(int id){
		Game game=null;
		
		try {
			req = connect.prepareStatement("select * from game where id=?");
			req.setInt(1, id);
			rs = req.executeQuery();

			if(rs.next()) {
				game = new Game(rs.getInt("id"),rs.getString("title"),rs.getInt("min_players"),rs.getInt("max_players"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game;
	}
}
