public class User {
    public String Username ;
    public String Password ;
    public  String NickName ;
    public String Email ;
    public String PasswordRecoveryQuestion ;

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPasswordRecoveryQuestion(String passwordRecoveryQuestion) {
        PasswordRecoveryQuestion = passwordRecoveryQuestion;
    }

    public void setPasswordRecoveryQuestionNumber(String passwordRecoveryQuestionNumber) {
        PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
    }

    public void setUnsuccessfulLogInAttempts(int unsuccessfulLogInAttempts) {
        UnsuccessfulLogInAttempts = unsuccessfulLogInAttempts;
    }

    public String PasswordRecoveryQuestionNumber ;
    public int UnsuccessfulLogInAttempts ;



    public User(String username, String password, String nickName, String email, String passwordRecoveryQuestion, String passwordRecoveryQuestionNumber, int unsuccessfulLogInAttempts) {
        Username = username;
        Password = password;
        NickName = nickName;
        Email = email;
        PasswordRecoveryQuestion = passwordRecoveryQuestion;
        PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
        UnsuccessfulLogInAttempts = unsuccessfulLogInAttempts;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getNickName() {
        return NickName;
    }

    public String getEmail() {
        return Email;
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
