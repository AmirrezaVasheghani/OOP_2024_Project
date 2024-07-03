package Main;

import Main.profileController;

import java.util.ArrayList;

public class User {
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
    private int UnsuccessfulLogInAttempts;




    public User(String username, String password, String nickname, String email) {
        this.Username = username;
        this.Password = password;
        this.NickName = nickname;
        this.Email = email;
        this.profileController = new profileController(this);
    }

    public void setPasswordRecoveryQuestion(String passwordRecoveryQuestion) {
        PasswordRecoveryQuestion = passwordRecoveryQuestion;
    }

    public User(String username, String password, String nickName, String email, String passwordRecoveryQuestion, String passwordRecoveryQuestionNumber, int unsuccessfulLogInAttempts) {
        this.Username = username;
        this.Password = password;
        this.NickName = nickName;
        this.Email = email;
        this.PasswordRecoveryQuestion = passwordRecoveryQuestion;
        this.PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
        this.profileController = new profileController(this);
        UnsuccessfulLogInAttempts = unsuccessfulLogInAttempts;
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

    public String getPasswordRecoveryQuestion() {
        return PasswordRecoveryQuestion;
    }

    public String getPasswordRecoveryQuestionNumber() {
        return PasswordRecoveryQuestionNumber;
    }
    public int getUnsuccessfulLogInAttempts() {
        return UnsuccessfulLogInAttempts;
    }
}




