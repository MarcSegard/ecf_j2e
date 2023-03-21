package model;

import java.time.LocalDate;

public class Contest {
	private int id;
	private Game game;
	private LocalDate start_date;
	private Player winner;
	private int nbre_players;

	public Contest() {

	}
	
	public Contest(int id, LocalDate start_date){
		this.id = id;
		this.start_date = start_date;
	}
	
	public Contest(int id, Game game){
		this.id = id;
		this.game = game;
	}
		
	public Contest( Game game, LocalDate start_date, Player winner) {
		this.game = game;
		this.start_date = start_date;
		this.winner = winner;
	}
	
	public Contest( int id,Game game, LocalDate start_date, Player winner, int nbre_players) {
		this.id = id;
		this.game = game;
		this.start_date = start_date;
		this.winner = winner;
		this.nbre_players = nbre_players;
	}

	public Contest(int id, Game game, LocalDate start_date, Player winner) {
		this.id = id;
		this.game = game;
		this.start_date = start_date;
		this.winner = winner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public int getNbre_players() {
		return nbre_players;
	}

	public void setNbre_players(int nbre_players) {
		this.nbre_players = nbre_players;
	}
}
