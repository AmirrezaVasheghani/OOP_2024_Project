package Main;

public class profileController{

    private User user;

    public profileController(User user) {
        this.user = user;
    }

    public void showInformation(){
        System.out.println("username : " + user.getUsername());
        System.out.println("email : " + user.getEmail());
        System.out.println("password : " + user.getPassword());
        System.out.println("nickname : " + user.getNickName());
    }

    public void setUserName(String userName){
        user.setUsername(userName);
    }

    public void setPassword(String password){
        user.setPassword(password);
    }

    public void setNickName(String nickName){
        user.setNickName(nickName);
    }

    public void setEmail(String email){
        user.setEmail(email);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
