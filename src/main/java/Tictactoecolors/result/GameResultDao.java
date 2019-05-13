package Tictactoecolors.result;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import java.beans.Transient;
import java.util.List;

public class GameResultDao extends GenericJpaDao<GameResult> {

    public GameResultDao(){
        super(GameResult.class);
    }

    @Transactional
    public List<GameResult> findBest(int n){
        return entityManager.createQuery("SELECT r FROM GameResult r WHERE r.solved = true ORDER BY r.timestamp DESC", GameResult.class)
                .setMaxResults(n)
                .getResultList();
    }
}
