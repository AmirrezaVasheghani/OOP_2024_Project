import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Recoveryq recoveryq;
    private ArrayList<Card> cards;
    private int level;
    private int xp;
    private int coins;

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
