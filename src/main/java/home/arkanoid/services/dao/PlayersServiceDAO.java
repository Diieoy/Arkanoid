package home.arkanoid.services.dao;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Player;
import home.arkanoid.services.PlayersService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PlayersServiceDAO implements PlayersService{

    @Inject
    private AbstractDAO<Player> playersDAO;

    @Override
    public int register(String nickname, String email, String password) {

        Player player = new Player();
        player.setNick_name(nickname);
        player.setEmail(email);
        player.setPassword(PlayersServiceDAO.md5Custom(password));

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

    private static String md5Custom(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
