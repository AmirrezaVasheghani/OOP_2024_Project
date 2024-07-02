public class User {
    public String Username ;
    public String Password ;
    public  String NickName ;
    public String Email ;
    public String PasswordRecoveryQuestion ;
    public String PasswordRecoveryQuestionNumber ;

    public User(String username, String password, String nickName, String email, String passwordRecoveryQuestion, String passwordRecoveryQuestionNumber) {
        Username = username;
        Password = password;
        NickName = nickName;
        Email = email;
        PasswordRecoveryQuestion = passwordRecoveryQuestion;
        PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
    }
}
