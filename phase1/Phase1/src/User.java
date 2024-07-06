import java.security.PublicKey;
import java.util.ArrayList;

public class User {
    public String Username ;
    public String Password ;
    public  String NickName ;
    public String Email ;
    public String PasswordRecoveryQuestion ;
    public int Level ;
    public int HP ;
    public int XP ;
    public int Coin ;
    public String PasswordRecoveryQuestionNumber ;
    public int UnsuccessfulLogInAttempts ;

    public int NumberOfEntranceToMainMenu ;
    public int RemainingRound ;
    public String GameCharacter ;
    public int PlayedTurnNumber ;

    ArrayList<Cards> NormalCards = new ArrayList<>() ;
    ArrayList<String> Spells = new ArrayList<>() ;
    ArrayList<Cards> BattleField = new ArrayList<>() ;
    ArrayList<Cards> BattleDeckNormalCards = new ArrayList<>() ;
    ArrayList<String> BattleDeckSpells = new ArrayList<>() ;

    public ArrayList<Cards> getBattleDeckNormalCards() {
        return BattleDeckNormalCards;
    }



    public User(String username, String password, String nickName, String email, String passwordRecoveryQuestion , String passwordRecoveryQuestionNumber, int level, int HP, int XP, int coin, int unsuccessfulLogInAttempts , int numberOfEntranceToMainMenu , String gameCharacter , int remainingRound , int playedTurnNumber) {
        Username = username;
        Password = password;
        NickName = nickName;
        Email = email;
        PasswordRecoveryQuestion = passwordRecoveryQuestion;
        Level = level;
        this.HP = HP;
        this.XP = XP;
        Coin = coin;
        PasswordRecoveryQuestionNumber = passwordRecoveryQuestionNumber;
        UnsuccessfulLogInAttempts = unsuccessfulLogInAttempts;
        NumberOfEntranceToMainMenu = numberOfEntranceToMainMenu ;
        GameCharacter = gameCharacter ;
        RemainingRound = remainingRound ;
        PlayedTurnNumber = playedTurnNumber ;
    }

    public void setRemainingRound(int remainingRound) {
        RemainingRound = remainingRound;
    }

    public void setPlayedTurnNumber(int playedTurnNumber) {
        PlayedTurnNumber = playedTurnNumber;
    }

    public void setBattleField(ArrayList<Cards> battleField) {
        BattleField = battleField;
    }

    public void setBattleDeckNormalCards(ArrayList<Cards> battleDeckNormalCards) {
        BattleDeckNormalCards = battleDeckNormalCards;
    }

    public void setBattleDeckSpells(ArrayList<String> battleDeckSpells) {
        BattleDeckSpells = battleDeckSpells;
    }

    public int getPlayedTurnNumber() {
        return PlayedTurnNumber;
    }
    public void setGameCharacter(String gameCharacter) {
        GameCharacter = gameCharacter;
    }
    public int getRemainingRound() {
        return RemainingRound;
    }

    public String getGameCharacter() {
        return GameCharacter;
    }

    public ArrayList<Cards> getBattleField() {
        return BattleField;
    }

    public ArrayList<String> getBattleDeckSpells() {
        return BattleDeckSpells;
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

    public void setLevel(int level) {
        Level = level;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setCoin(int coin) {
        Coin = coin;
    }

    public void setNumberOfEntranceToMainMenu(int numberOfEntranceToMainMenu) {
        NumberOfEntranceToMainMenu = numberOfEntranceToMainMenu;
    }

    public void setNormalCards(ArrayList<Cards> normalCards) {
        NormalCards = normalCards;
    }

    public void setSpells(ArrayList<String> spells) {
        Spells = spells;
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

    public int getLevel() {
        return Level;
    }

    public int getHP() {
        return HP;
    }

    public int getXP() {
        return XP;
    }

    public int getCoin() {
        return Coin;
    }

    public ArrayList<Cards> getNormalCards() {
        return NormalCards;
    }

    public ArrayList<String> getSpells() {
        return Spells;
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

    public int getNumberOfEntranceToMainMenu() {
        return NumberOfEntranceToMainMenu;
    }
}
