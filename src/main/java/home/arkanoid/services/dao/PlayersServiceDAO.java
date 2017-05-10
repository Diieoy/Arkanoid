package home.arkanoid.services.dao;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Player;
import home.arkanoid.services.PlayersService;
import home.arkanoid.utils.PasswordUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PlayersServiceDAO implements PlayersService{

    @Inject
    private AbstractDAO<Player> playersDAO;

    @Override
    public int register(String nickname, String email, String password) {

        Player player = new Player();
        player.setNick_name(nickname);
        player.setEmail(email);
        player.setPassword(PasswordUtils.md5Custom(password));

        playersDAO.insert(player);

        return player.getId();
    }

    @Override
    public void delete(int playerId) {
        Player player = playersDAO.findByID(playerId);
        if(player != null){
            playersDAO.delete(player);
        }

    }

    @Override
    public Player getInfo(int id) {
        return playersDAO.findByID(id);
    }

    @Override
    public List<Integer> searchPlayer(String nick, String email) {
        List<Player> players = playersDAO.getAll();

        List<Integer> ids = new ArrayList<>();

        for(Player p : players){
            Player temp = p;
            if(nick != null){
                if(!p.getNick_name().contains(nick)){
                    temp = null;
                }
            }

            if(email != null){
                if(!p.getEmail().contains(email)){
                    temp = null;
                }
            }

            if(temp != null){
                ids.add(temp.getId());
            }
        }

        return ids;
    }

    @Override
    public List<Integer> getBestPlayerMatches(int userId, int maxCount) {
        return null;
    }
}
