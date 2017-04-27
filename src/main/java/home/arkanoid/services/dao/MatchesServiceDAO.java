package home.arkanoid.services.dao;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import home.arkanoid.services.MatchesService;

import java.util.*;

public class MatchesServiceDAO implements MatchesService{

    @Inject
    private AbstractDAO<Player> playersDAO;

    @Inject
    private AbstractDAO<Match> matchesDAO;


    @Override
    public int publish(int playerId, Date match_time, int score) {

        Player player = playersDAO.findByID(playerId);

        if(player == null){
            return 0;
        } else{
            Match match = new Match();
            match.setMatch_time(match_time);
            match.setScore(score);

            matchesDAO.insert(match);

            return match.getId();
        }
    }

    @Override
    public List<Integer> bestNumMatches(int playerId, int number) {

        Player player = playersDAO.findByID(playerId);
        List<Match> allMatches = player.getMatches();

        Collections.sort(allMatches, (Match o1, Match o2) -> Integer.compare(o2.getScore(), o1.getScore()));

        //List<Integer> myMatch - добавляем сюда первые N матчей из allMatches
        List<Integer> myMatch = new ArrayList<>();

        /*Если нужно отсортировать список (List) или множество (Set), используйте структуру TreeSet для сортировки.
        // TreeSet

        Set<ObjectName> sortedSet = new TreeSet<ObjectName>(new Comparator<ObjectName>() {
            public int compare(ObjectName o1, ObjectName o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        sortedSet.addAll(unsortedSet);*/
        return myMatch;
    }

    @Override
    public Match getInfo(int id) {
        return matchesDAO.findByID(id);
    }
}
