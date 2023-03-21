package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Player;

public class PlayerDao implements IDAO<Player> {
	Connection connect = Connect.getConnection();

	PreparedStatement req;
	ResultSet rs;

	@Override
	public boolean ajout(Player player) {
		try {
			req = connect.prepareStatement("INSERT INTO player (email,nickname) values (?,?)");
			req.setString(1, player.getEmail());
			req.setString(2, player.getNickname());
			
			req.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("Insertion KO");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ArrayList<Player> read(){
		ArrayList<Player> players = new ArrayList<>();
		Player player;
		
		try {
			req = connect.prepareStatement("select * from player");
			rs = req.executeQuery();

			while (rs.next()) {
				player = new Player(rs.getInt("id"),rs.getString("email"),rs.getString("nickname"));
				players.add(player);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}
	
	public Player getById(int id){
		Player player=null;
		
		try {
			req = connect.prepareStatement("select * from player where id=?");
			req.setInt(1, id);
			rs = req.executeQuery();

			if(rs.next()) {
				player = new Player(id, rs.getString("email"),rs.getString("nickname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}
}
