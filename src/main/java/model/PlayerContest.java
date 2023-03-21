package model;

import java.util.ArrayList;

public class PlayerContest {
	private int id;
	private ArrayList<Player> players;
	private Contest contest;

	// Pour être Bean Ready
	public PlayerContest() {
	}

	public PlayerContest(int id, ArrayList<Player> players, Contest contest) {
		this.id = id;
		this.players = players;
		this.contest = contest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

}
