public class Cards {
    String Name ;



    String Card_DefenceOrAttack ;
    String Duration ;
    String PlayerDamage ;
    String UpgradeLevel ;
    String UpgradeCost ;
    String Level ;

    public Cards(String name, String card_DefenceOrAttack, String duration, String playerDamage, String upgradeLevel, String upgradeCost , String level ) {
        Name = name;
        Card_DefenceOrAttack = card_DefenceOrAttack;
        Duration = duration;
        PlayerDamage = playerDamage;
        UpgradeLevel = upgradeLevel;
        UpgradeCost = upgradeCost;
        Level = level ;
    }


    public String getName() {
        return Name;
    }
    public void setLevel(String level) {
        Level = level;
    }
    public String getCard_DefenceOrAttack() {
        return Card_DefenceOrAttack;
    }

    public String getDuration() {
        return Duration;
    }

    public String getPlayerDamage() {
        return PlayerDamage;
    }

    public String getUpgradeLevel() {
        return UpgradeLevel;
    }

    public String getUpgradeCost() {
        return UpgradeCost;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCard_DefenceOrAttack(String card_DefenceOrAttack) {
        Card_DefenceOrAttack = card_DefenceOrAttack;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setPlayerDamage(String playerDamage) {
        PlayerDamage = playerDamage;
    }

    public void setUpgradeLevel(String upgradeLevel) {
        UpgradeLevel = upgradeLevel;
    }

    public void setUpgradeCost(String upgradeCost) {
        UpgradeCost = upgradeCost;
    }

    public String getLevel() {
        return Level;
    }
}
