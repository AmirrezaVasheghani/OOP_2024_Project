public class TempUser {
    public String Username ;
    public String Password ;
    public  String NickName ;
    public String Email ;
    public int UserControl ;

    public TempUser(String username, String password, String nickName, String email, int userControl) {
        Username = username;
        Password = password;
        NickName = nickName;
        Email = email;
        UserControl = userControl;
    }

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

    public void setUserControl(int userControl) {
        UserControl = userControl;
    }
}
