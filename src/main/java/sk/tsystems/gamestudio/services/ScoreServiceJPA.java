package sk.tsystems.gamestudio.services;

import java.util.List;

import javax.persistence.*;
import javax.transaction.*;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.entity.Rating;

@Component
@Transactional
public class ScoreServiceJPA implements ScoreService{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addScore(Score score) {
		entityManager.persist(score);
	}
	
	@Override
	public void addComment(Comment comment) {
		entityManager.persist(comment);		
	}
	
	@Override
	public List<Score> getTopScoreMinesweeper(String game) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Score> getTopScorePuzzleGame(String game) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Score> getTopScore(String game) {
		 return	(List<Score>) entityManager.createQuery(
				 "select s from Score s where s.game = :game order by s.value asc")
				 .setParameter("game", game).setMaxResults(10).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getComment(String game) {
		 return	(List<Comment>) entityManager.createQuery(
				 "select s from Comment s where s.game = :game order by s.ident asc")
				 .setParameter("game", game).getResultList();
	}

}
