package sk.tsystems.gamestudio.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Player;

@Component
@Transactional
public class PlayerServiceJPA implements PlayerService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addPlayer(Player player) {
		entityManager.persist(player);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getPlayerName(String name) {
		return (List<Player>) entityManager
				.createQuery("select n from Player n where n.name = :name order by n.ident desc")
				.setParameter("name", name).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getPlayerPassword(String password) {
		return (List<Player>) entityManager
				.createQuery("select p from Player p where p.password = :password order by p.ident desc")
				.setParameter("password", password).getResultList();
	}
}
