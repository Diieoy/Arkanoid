package home.arkanoid.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {

    private int id;
    private String nick_name;
    private String password;
    private String email;
    private Set<Match> matches;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nick_name")
    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(obj.getClass() != Player.class){
            return false;
        }

        Player player = (Player) obj;

        if(this.id != player.id){
            return false;
        }

        return true;
    }

    @OneToMany
    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }
}
