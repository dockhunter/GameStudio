package sk.tsystems.gamestudio.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;

/*
 * CREATE TABLE scroe (username VARCHAR(64) NOT NULL, game VARCHAR(64) NOT NULL, value INT NOT NULL)
 */

public class ScoreServicesJDBC implements ScoreService {

	private static final String URL = "jdbc:postgresql://localhost/gamestudio";
	private static final String LOGIN = "postgres";
	private static final String PASSWORD = "jahodka";

	private static final String INSERT = "INSERT INTO  highscore (username, game, value) VALUES (?, ?, ?)";
	private static final String SELECTFORMINESWEEPER = "SELECT username, game, value FROM highscore WHERE game = ? ORDER BY value desc limit 10";
	private static final String SELECTFORPUZZLEGAME = "SELECT username, game, value FROM highscore WHERE game = ? ORDER BY value asc limit 10";

	
	@Override
	public void addScore(Score score) {
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(INSERT)) {
			ps.setString(1, score.getUsername());
			ps.setString(2, score.getGame());
			ps.setInt(3, score.getValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new GameStudioException(e);
		}
	}

	@Override
	public List<Score> getTopScoreMinesweeper(String game) {
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(SELECTFORMINESWEEPER)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				List<Score> scores = new ArrayList<>();
				while (rs.next()) {
					Score score = new Score(rs.getString(1), rs.getString(2), rs.getInt(3));
					scores.add(score);
				}
				return scores;
			}
		} catch (SQLException e) {
			throw new GameStudioException(e);
		}
	}
	
	@Override
	public List<Score> getTopScorePuzzleGame(String game) {
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(SELECTFORPUZZLEGAME)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				List<Score> scores = new ArrayList<>();
				while (rs.next()) {
					Score score = new Score(rs.getString(1), rs.getString(2), rs.getInt(3));
					scores.add(score);
				}
				return scores;
			}
		} catch (SQLException e) {
			throw new GameStudioException(e);
		}
	}

	@Override
	public List<Score> getTopScore(String game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> getComment(String comment) {
		// TODO Auto-generated method stub
		return null;
	}


}
