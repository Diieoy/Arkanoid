package home.arkanoid.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {

    private int id;
    private Date match_time;
    private int score;

    private Player player;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JoinColumn(name = "player_id")
    @ManyToOne
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Column(name = "match_time")
    public Date getMatch_time() {
        return match_time;
    }

    public void setMatch_time(Date match_time) {
        this.match_time = match_time;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(obj.getClass() != Player.class){
            return false;
        }

        Match match = (Match) obj;

        if(this.id != match.id){
            return false;
        }

        return true;
    }
}
