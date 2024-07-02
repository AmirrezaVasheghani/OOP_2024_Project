import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private Recoveryq recoveryq;
    private ArrayList<Card> cards;
    private int level;
    private int xp;
    private int coins;
    private String Username ;
    private String Password ;
    private   String NickName ;
    private String Email ;
    private String PasswordRecoveryQuestion ;
    private String PasswordRecoveryQuestionNumber ;
    private profileController profileController;

    public User(String username, String password, String nickname, String email) {
        this.Username = username;
        this.Password = password;
        this.NickName = nickname;
        this.Email = email;
        this.profileController = new profileController(this);
    }


    public User(String username, String password, String nickName, String email, String passwordRecoveryQuestion, String passwordRecoveryQuestionNumber) {
        this.Username = username;
        this.Password = password;
        this.NickName = nickName;
        this.Email = email;
        this.PasswordRecoveryQuestion = passwordRecoveryQuestion;
        this.PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
        this.profileController = new profileController(this);
        }

    public profileController getProfileController() {
        return profileController;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}




