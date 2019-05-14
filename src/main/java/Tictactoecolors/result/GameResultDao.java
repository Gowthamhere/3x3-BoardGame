package Tictactoecolors.result;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import java.util.List;

/**
 * DAO class for GameResult class which extends GenericJpaDao Class
 */
public class GameResultDao extends GenericJpaDao<GameResult> {

    public GameResultDao(){
        super(GameResult.class);
    }

    /**
     * Finds the top number of player who solved the game with least number of moves
     * @param n number of players to return
     * @return player who solved the game with least number of moves
     */
    @Transactional
    public List<GameResult> findBest(int n){
        return entityManager.createQuery("SELECT r FROM GameResult r WHERE r.solved = true ORDER BY r.moves ASC", GameResult.class)
                .setMaxResults(n)
                .getResultList();
    }
}
