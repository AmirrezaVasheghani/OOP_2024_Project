import javax.sound.midi.Soundbank;
import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    ArrayList <TempUser> tempUsers = new ArrayList<>() ;
    ArrayList <User> users = new ArrayList<>() ;
    ArrayList <User> LoggedInUser = new ArrayList<>() ;
    ArrayList <Cards> SomeCardsFromPhase0 = new ArrayList<>() ;
    ArrayList <String> SomeSpellsFromPhase0 = new ArrayList<>() ;
    ArrayList <String> BuiltInSpells = new ArrayList<>() ;
    ArrayList <String> IsStartGameButtonPressed = new ArrayList<>() ;
    ArrayList <User> LoggedInOpponent = new ArrayList<>() ;
    ArrayList <String> GameModeDeterminer = new ArrayList<>() ;
    ArrayList <String> WhoseTurn = new ArrayList<>() ;
    ArrayList <String> TurnCounter = new ArrayList<>() ;
    ArrayList <String> IntegratedSpells = new ArrayList<>() ;






    //این اری لیسته ماله هندل کردنه اینه که تو کدوم منو هستیم در حال حاضر ، 1 به معنی اینه که الان تو اون منو هستیم و 0 یعنی نیستیم.
    // ترتیب منو ها بر اساس ایندکس این اری لیسته اینطوریه :
    // SignUp/LogIn Menu -> index 0

    ArrayList <String> ChangeMenuHandler = new ArrayList<>() ;
    ArrayList <Cards> CardsFromAdmin = new ArrayList<>() ;




    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    private Scanner MyConsole = new Scanner(System.in);
    public void StartSetOfMenu(){
        ChangeMenuHandler.add("1") ;
        ChangeMenuHandler.add("0") ;
    }
    public void CheckIfUserAndPasswordIsValidForSignUp(Matcher matcher) {
        if(IsStartGameButtonPressed.get(0).equals("1")){
            //Empty Field
            if (matcher.group(1).isEmpty() || matcher.group(2).isEmpty() || matcher.group(3).isEmpty() || matcher.group(4).isEmpty()) {
                System.out.println("The information entered is not complete! , please try again.");
                return;
            }


            //Invalid Username

            boolean HasLowerCaseLetter = false;
            boolean HasUpperCaseLetter = false;
            boolean HasNumber = false;
            boolean HasUnderScore = false;

            for (int i = 0; i < matcher.group(1).length(); i++) {
                for (char c : matcher.group(1).toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        HasUpperCaseLetter = true;
                    }
                    if (Character.isLowerCase(c)) {
                        HasLowerCaseLetter = true;
                    }
                    if (Character.isDigit(c)) {
                        HasNumber = true;
                    }
                    if (c == '_') {
                        HasUnderScore = true;
                    }
                }
            }

            if (!HasNumber || !HasUnderScore || !HasUpperCaseLetter || !HasLowerCaseLetter) {
                System.out.println("The username is invalid! , please try again.");
                return;
            }

            //Duplicated Username

            for (User user : users) {
                if (user.getUsername().equals(matcher.group(1))) {
                    System.out.println("This username already exist!");
                    return;
                }
            }


            //Weak Password

            if (matcher.group(2).length() < 8) {
                System.out.println("The password must have atleast 8 characters!");
                return;
            }
            boolean Has1UpperCaseLetter = false;
            boolean Has1LowerCaseLetter = false;
            boolean Has1NotLetterChar = false;

            for (char c : matcher.group(2).toCharArray()) {
                if (Character.isLowerCase(c)) {
                    Has1LowerCaseLetter = true;
                }
                if (Character.isUpperCase(c)) {
                    Has1UpperCaseLetter = true;
                }
                if (!Character.isLetter(c) && !Character.isDigit(c)) {
                    Has1NotLetterChar = true;
                }
            }

            if (!Has1LowerCaseLetter || !Has1UpperCaseLetter || !Has1NotLetterChar) {
                System.out.println("The password must have atleast 1 uppercase and 1 lowercase and 1 (non-digit,non-letter) character!");
                return;
            }


            //Invalid email
            if (!matcher.group(4).matches("(.+)@(.+).com")) {
                System.out.println("Email part or domain part must not be empty!");
                return;
            }


            //Successful Signup

            System.out.println("User created successfully. Please choose a security question :");
            System.out.println("1-What is your father’s name ?");
            System.out.println("2-What is your favourite color ?");
            System.out.println("3-What was the name of your first pet?");

            for (TempUser tempUser : tempUsers) {
                tempUser.setUserControl(0);
            }

            TempUser tempuser = new TempUser(matcher.group(1), matcher.group(2), matcher.group(5), matcher.group(4), 1);
            tempUsers.add(tempuser);

        }



        if(IsStartGameButtonPressed.get(0).equals("0")) {
            if (!ChangeMenuHandler.get(0).equals("1")) {
                System.out.println("You are not in SignUp/LogIn Menu!");
                return;
            }

            if (ChangeMenuHandler.get(0).equals("1")) {
                if (!LoggedInUser.isEmpty()) {
                    System.out.println("Another user is currently logged in and you can not sign up now!");
                    return;
                }

                //Empty Field
                if (matcher.group(1).isEmpty() || matcher.group(2).isEmpty() || matcher.group(3).isEmpty() || matcher.group(4).isEmpty()) {
                    System.out.println("The information entered is not complete! , please try again.");
                    return;
                }


                //Invalid Username

                boolean HasLowerCaseLetter = false;
                boolean HasUpperCaseLetter = false;
                boolean HasNumber = false;
                boolean HasUnderScore = false;

                for (int i = 0; i < matcher.group(1).length(); i++) {
                    for (char c : matcher.group(1).toCharArray()) {
                        if (Character.isUpperCase(c)) {
                            HasUpperCaseLetter = true;
                        }
                        if (Character.isLowerCase(c)) {
                            HasLowerCaseLetter = true;
                        }
                        if (Character.isDigit(c)) {
                            HasNumber = true;
                        }
                        if (c == '_') {
                            HasUnderScore = true;
                        }
                    }
                }

                if (!HasNumber || !HasUnderScore || !HasUpperCaseLetter || !HasLowerCaseLetter) {
                    System.out.println("The username is invalid! , please try again.");
                    return;
                }

                //Duplicated Username

                for (User user : users) {
                    if (user.getUsername().equals(matcher.group(1))) {
                        System.out.println("This username already exist!");
                        return;
                    }
                }


                //Weak Password

                if (matcher.group(2).length() < 8) {
                    System.out.println("The password must have atleast 8 characters!");
                    return;
                }
                boolean Has1UpperCaseLetter = false;
                boolean Has1LowerCaseLetter = false;
                boolean Has1NotLetterChar = false;

                for (char c : matcher.group(2).toCharArray()) {
                    if (Character.isLowerCase(c)) {
                        Has1LowerCaseLetter = true;
                    }
                    if (Character.isUpperCase(c)) {
                        Has1UpperCaseLetter = true;
                    }
                    if (!Character.isLetter(c) && !Character.isDigit(c)) {
                        Has1NotLetterChar = true;
                    }
                }

                if (!Has1LowerCaseLetter || !Has1UpperCaseLetter || !Has1NotLetterChar) {
                    System.out.println("The password must have atleast 1 uppercase and 1 lowercase and 1 (non-digit,non-letter) character!");
                    return;
                }


                //Invalid email
                if (!matcher.group(4).matches("(.+)@(.+).com")) {
                    System.out.println("Email part or domain part must not be empty!");
                    return;
                }


                //Successful Signup

                System.out.println("User created successfully. Please choose a security question :");
                System.out.println("1-What is your father’s name ?");
                System.out.println("2-What is your favourite color ?");
                System.out.println("3-What was the name of your first pet?");

                for (TempUser tempUser : tempUsers) {
                    tempUser.setUserControl(0);
                }

                TempUser tempuser = new TempUser(matcher.group(1), matcher.group(2), matcher.group(5), matcher.group(4), 1);
                tempUsers.add(tempuser);

            }
        }
    }

    public void SetPasswordRecoveryQuestionToTempUsers(Matcher matcher) {
        if (!matcher.group(2).equals(matcher.group(3))) {
            System.out.println("The password is not confirmed!");
        }
        else {
            System.out.println("Do the CAPTCHA below : ");
            //Generate Random numbers
            String digitChars = "0123456789";
            Random random = new Random();
            StringBuilder CAPTCHA = new StringBuilder();

            String[] asciiArtZero = {
                    " ##### ",
                    "#     #",
                    "#     #",
                    "#     #",
                    "#     #",
                    "#     #",
                    " ##### "
            };
            String[] asciiArtOne = {
                    "   #  ",
                    "  ##  ",
                    "   #  ",
                    "   #  ",
                    " #####"
            };

            String[] asciiArtTwo = {
                    " ##### ",
                    "#     #",
                    "      #",
                    " ##### ",
                    "#      ",
                    "#      ",
                    "#######"
            };
            String[] asciiArtThree = {
                    " ##### ",
                    "#     #",
                    "      #",
                    " ##### ",
                    "      #",
                    "#     #",
                    " ##### "
            };
            String[] asciiArtFour = {
                    "   #   ",
                    "  ##   ",
                    " # #   ",
                    "#  #   ",
                    "######",
                    "   #   ",
                    "   #   "
            };

            String[] asciiArtFive = {
                    "######",
                    "#     ",
                    "#     ",
                    "##### ",
                    "     #",
                    "     #",
                    "##### "
            };
            String[] asciiArtSix = {
                    " ##### ",
                    "#      ",
                    "#      ",
                    "###### ",
                    "#     #",
                    "#     #",
                    " ##### "
            };
            String[] asciiArtSeven = {
                    "#######",
                    "      #",
                    "     # ",
                    "    #  ",
                    "   #   ",
                    "  #    ",
                    " #     "
            };
            String[] asciiArtEight = {
                    " ##### ",
                    "#     #",
                    "#     #",
                    " ##### ",
                    "#     #",
                    "#     #",
                    " ##### "
            };
            String[] asciiArtNine = {
                    " ##### ",
                    "#     #",
                    "#     #",
                    " ######",
                    "      #",
                    "#     #",
                    " ##### "
            };

            String input ;


            for (int i = 0; i < 6; i++) {
                CAPTCHA.append(digitChars.charAt(random.nextInt(digitChars.length())));
            }
            for (int i = 0; i < String.valueOf(CAPTCHA).length(); i++) {
                if (String.valueOf(CAPTCHA).charAt(i) == '0') {
                    for (String line : asciiArtZero) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '1') {
                    for (String line : asciiArtOne) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '2') {
                    for (String line : asciiArtTwo) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '3') {
                    for (String line : asciiArtThree) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '4') {
                    for (String line : asciiArtFour) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '5') {
                    for (String line : asciiArtFive) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '6') {
                    for (String line : asciiArtSix) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '7') {
                    for (String line : asciiArtSeven) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '8') {
                    for (String line : asciiArtEight) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
                if (String.valueOf(CAPTCHA).charAt(i) == '9') {
                    for (String line : asciiArtNine) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
            }

            while (!(input = MyConsole.nextLine()).equals(String.valueOf(CAPTCHA))) {
                System.out.println("The CAPTCHA is not confirmed! , try again.");
                CAPTCHA.delete(0 , 6) ;
                for(int i=0 ; i<6 ; i++) {
                    CAPTCHA.append(digitChars.charAt(random.nextInt(digitChars.length())));
                }

                for (int i = 0; i < String.valueOf(CAPTCHA).length(); i++) {
                    if (String.valueOf(CAPTCHA).charAt(i) == '0') {
                        for (String line : asciiArtZero) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '1') {
                        for (String line : asciiArtOne) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '2') {
                        for (String line : asciiArtTwo) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '3') {
                        for (String line : asciiArtThree) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '4') {
                        for (String line : asciiArtFour) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '5') {
                        for (String line : asciiArtFive) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '6') {
                        for (String line : asciiArtSix) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '7') {
                        for (String line : asciiArtSeven) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '8') {
                        for (String line : asciiArtEight) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                    if (String.valueOf(CAPTCHA).charAt(i) == '9') {
                        for (String line : asciiArtNine) {
                            System.out.println(line);
                        }
                        System.out.println();
                    }
                }

            }
            System.out.println("The CAPTCHA is successfully confirmed!");


            for (TempUser tempUser : tempUsers) {
                if (tempUser.UserControl == 1) {
                    User user = new User(tempUser.Username, tempUser.Password, tempUser.NickName, tempUser.Email, matcher.group(2), matcher.group(1) , 1 , 100 , 0 , 100 , 0 , 0 , "." , 4 , 0);
                    users.add(user) ;

                    System.out.println("The password recovery question is successfully saved. ");
                }
            }
        }
    }

    public void CheckIfUserForSignUpWithRandomPassword(Matcher matcher) {
        if(IsStartGameButtonPressed.get(0).equals("1")){
            //Empty Field
            if (matcher.group(1).isEmpty() || matcher.group(2).isEmpty() || matcher.group(3).isEmpty()) {
                System.out.println("The information entered is not complete! , please try again.");
                return;
            }


            //Invalid Username

            boolean HasLowerCaseLetter = false;
            boolean HasUpperCaseLetter = false;
            boolean HasNumber = false;
            boolean HasUnderScore = false;

            for (int i = 0; i < matcher.group(1).length(); i++) {
                for (char c : matcher.group(1).toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        HasUpperCaseLetter = true;
                    }
                    if (Character.isLowerCase(c)) {
                        HasLowerCaseLetter = true;
                    }
                    if (Character.isDigit(c)) {
                        HasNumber = true;
                    }
                    if (c == '_') {
                        HasUnderScore = true;
                    }
                }
            }

            if (!HasNumber || !HasUnderScore || !HasUpperCaseLetter || !HasLowerCaseLetter) {
                System.out.println("The username is invalid! , please try again.");
                return;
            }


            //Duplicated Username
            for (User user : users) {
                if (user.getUsername().equals(matcher.group(1))) {
                    System.out.println("This username already exist!");
                    return;
                }
            }


            //Invalid email
            if (!matcher.group(2).matches("(.+)@(.+).com")) {
                System.out.println("Email part or domain part must not be empty!");
                return;
            }


            //Generate Random password

            String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
            String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String digitChars = "0123456789";
            String specialChars = "!@#$%^&*()";

            String allChars = lowercaseChars + uppercaseChars + digitChars + specialChars;

            Random random = new Random();
            StringBuilder password = new StringBuilder();

            // Add at least one of each type of character
            password.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
            password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
            password.append(digitChars.charAt(random.nextInt(digitChars.length())));
            password.append(specialChars.charAt(random.nextInt(specialChars.length())));

            // Add remaining characters
            for (int i = 4; i < 8; i++) { // Start from 4 because we've already added 4 characters
                password.append(allChars.charAt(random.nextInt(allChars.length())));
            }

            //Give the user his password
            System.out.println("Your random password: " + password);
            System.out.println("Please enter your password :");

            // Confirm the password
            String input;
            while (!(input = MyConsole.nextLine()).equals(String.valueOf(password))) {
                System.out.println("The password is not confirmed! , try again.");
            }

            System.out.println("Your password is successfully confirmed.");


            //Successful Signup

            System.out.println("User created successfully. Please choose a security question :");
            System.out.println("1-What is your father’s name ?");
            System.out.println("2-What is your favourite color ?");
            System.out.println("3-What was the name of your first pet?");

            for (TempUser tempUser : tempUsers) {
                tempUser.setUserControl(0);
            }
            TempUser tempuser = new TempUser(matcher.group(1), String.valueOf(password), matcher.group(3), matcher.group(2), 1);
            tempUsers.add(tempuser);
        }

        if (IsStartGameButtonPressed.get(0).equals("0")) {
            if (!ChangeMenuHandler.get(0).equals("1")) {
                System.out.println("You are not in SignUp/LogIn Menu!");
                return;
            }

            if (ChangeMenuHandler.get(0).equals("1")) {
                if (!LoggedInUser.isEmpty()) {
                    System.out.println("Another user is currently logged in and you can not sign up now!");
                    return;
                }
            }


            //Empty Field
            if (matcher.group(1).isEmpty() || matcher.group(2).isEmpty() || matcher.group(3).isEmpty()) {
                System.out.println("The information entered is not complete! , please try again.");
                return;
            }


            //Invalid Username

            boolean HasLowerCaseLetter = false;
            boolean HasUpperCaseLetter = false;
            boolean HasNumber = false;
            boolean HasUnderScore = false;

            for (int i = 0; i < matcher.group(1).length(); i++) {
                for (char c : matcher.group(1).toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        HasUpperCaseLetter = true;
                    }
                    if (Character.isLowerCase(c)) {
                        HasLowerCaseLetter = true;
                    }
                    if (Character.isDigit(c)) {
                        HasNumber = true;
                    }
                    if (c == '_') {
                        HasUnderScore = true;
                    }
                }
            }

            if (!HasNumber || !HasUnderScore || !HasUpperCaseLetter || !HasLowerCaseLetter) {
                System.out.println("The username is invalid! , please try again.");
                return;
            }


            //Duplicated Username
            for (User user : users) {
                if (user.getUsername().equals(matcher.group(1))) {
                    System.out.println("This username already exist!");
                    return;
                }
            }


            //Invalid email
            if (!matcher.group(2).matches("(.+)@(.+).com")) {
                System.out.println("Email part or domain part must not be empty!");
                return;
            }


            //Generate Random password

            String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
            String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String digitChars = "0123456789";
            String specialChars = "!@#$%^&*()";

            String allChars = lowercaseChars + uppercaseChars + digitChars + specialChars;

            Random random = new Random();
            StringBuilder password = new StringBuilder();

            // Add at least one of each type of character
            password.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
            password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
            password.append(digitChars.charAt(random.nextInt(digitChars.length())));
            password.append(specialChars.charAt(random.nextInt(specialChars.length())));

            // Add remaining characters
            for (int i = 4; i < 8; i++) { // Start from 4 because we've already added 4 characters
                password.append(allChars.charAt(random.nextInt(allChars.length())));
            }

            //Give the user his password
            System.out.println("Your random password: " + password);
            System.out.println("Please enter your password :");

            // Confirm the password
            String input;
            while (!(input = MyConsole.nextLine()).equals(String.valueOf(password))) {
                System.out.println("The password is not confirmed! , try again.");
            }

            System.out.println("Your password is successfully confirmed.");


            //Successful Signup

            System.out.println("User created successfully. Please choose a security question :");
            System.out.println("1-What is your father’s name ?");
            System.out.println("2-What is your favourite color ?");
            System.out.println("3-What was the name of your first pet?");

            for (TempUser tempUser : tempUsers) {
                tempUser.setUserControl(0);
            }
            TempUser tempuser = new TempUser(matcher.group(1), String.valueOf(password), matcher.group(3), matcher.group(2), 1);
            tempUsers.add(tempuser);
        }
    }

    public boolean CheckIfUsernameExist(String Username){
        for (User user : users) {
            if(user.getUsername().equals(Username)){
                return true ;
            }
        }
        return false ;
    }

    public boolean CheckIfPasswordIsOkay(String Username , String Password){
        for (User user : users) {
            if(user.getUsername().equals(Username)){
                if(user.getPassword().equals(Password)){
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean CheckIfTheUserHasLoggedIn(String Username){
        for (User user : LoggedInUser) {
            if(user.getUsername().equals(Username)){
                return true ;
            }
        }
        return false ;
    }
    public User FindUserByUserName(String UserName){
        boolean hasFoundTheUser = false ;
        User tempuser = null ;
        for (User user : users) {
            if (user.getUsername().equals(UserName)){
                hasFoundTheUser = true ;
                tempuser = user ;

                break;
            }
        }
        if(hasFoundTheUser){
            return tempuser ;
        }
        else {
            return null ;
        }
    }

    public void LoginCheckError(Matcher matcher) {
        if (IsStartGameButtonPressed.get(0).equals("1")) {
            if (LoggedInUser.isEmpty()) {
                if (!CheckIfUsernameExist(matcher.group(1))) {
                    System.out.println("Username doesn’t exist!");
                    return;
                }
                if (!CheckIfPasswordIsOkay(matcher.group(1), matcher.group(2))) {
                    System.out.println("Password and Username don’t match!");
                    return;
                }
            }
            System.out.println("user logged in successfully!");
            LoggedInOpponent.clear();
            LoggedInOpponent.add(FindUserByUserName(matcher.group(1)));
            //EnterMainMenu();


            if(LoggedInOpponent.get(0).getNumberOfEntranceToMainMenu()==0){
                AddCardToStarterPackForOpponent();
                int tempNumberOfEntranceToMainMenu = LoggedInOpponent.get(0).getNumberOfEntranceToMainMenu() ;
                LoggedInOpponent.get(0).setNumberOfEntranceToMainMenu(tempNumberOfEntranceToMainMenu+1);
            }

            System.out.println("You have entered in game menu , mode : " + PrintGameMode(GameModeDeterminer.get(0))) ;
            StartGameFromLogInOfSecondUserToGameMode(GameModeDeterminer.get(0));


        }


        if (IsStartGameButtonPressed.get(0).equals("0")) {
            if (CheckIfTheUserHasLoggedIn(matcher.group(1))) {
                System.out.println("The user has already logged in!");
                return;
            }
            if (!CheckIfTheUserHasLoggedIn(matcher.group(1))) {
                if (!LoggedInUser.isEmpty()) {
                    System.out.println("Another user is currently logged in and you can not log in now!");
                    return;
                }

                if (LoggedInUser.isEmpty()) {
                    if (!CheckIfUsernameExist(matcher.group(1))) {
                        System.out.println("Username doesn’t exist!");
                        return;
                    }
                    if (!CheckIfPasswordIsOkay(matcher.group(1), matcher.group(2))) {
                        System.out.println("Password and Username don’t match!");
                        return;
                    }
                }
            }

            System.out.println("user logged in successfully!");
            LoggedInUser.clear();
            LoggedInUser.add(FindUserByUserName(matcher.group(1)));
        }
    }

    public void PasswordValidationErrors(String Password){
        if(Password.length()<8){
            System.out.println("The password must have atleast 8 characters!");
            return;
        }
        boolean Has1UpperCaseLetter = false ;
        boolean Has1LowerCaseLetter = false ;
        boolean Has1NotLetterChar = false ;

        for (char c : Password.toCharArray()) {
            if(Character.isLowerCase(c)){
                Has1LowerCaseLetter=true ;
            }
            if(Character.isUpperCase(c)){
                Has1UpperCaseLetter=true ;
            }
            if(!Character.isLetter(c) && !Character.isDigit(c)){
                Has1NotLetterChar=true ;
            }
        }

        if(!Has1LowerCaseLetter || !Has1UpperCaseLetter || !Has1NotLetterChar){
            System.out.println("The password must have atleast 1 uppercase and 1 lowercase and 1 (non-digit,non-letter) character!");
            return;
        }
    }

    public boolean CheckIfPasswordIsValid(String Password){
        boolean IsDigitNumberOk = false ;
        boolean IsFormatOk = false ;
        if(Password.length()>=8){
            IsDigitNumberOk = true ;
        }

        boolean Has1UpperCaseLetter = false ;
        boolean Has1LowerCaseLetter = false ;
        boolean Has1NotLetterChar = false ;

        for (char c : Password.toCharArray()) {
            if(Character.isLowerCase(c)){
                Has1LowerCaseLetter=true ;
            }
            if(Character.isUpperCase(c)){
                Has1UpperCaseLetter=true ;
            }
            if(!Character.isLetter(c) && !Character.isDigit(c)){
                Has1NotLetterChar=true ;
            }
        }

        if(Has1LowerCaseLetter && Has1UpperCaseLetter && Has1NotLetterChar){
            IsFormatOk=true ;
        }

        if(IsFormatOk && IsDigitNumberOk){
            return true ;
        }
        return false ;
    }


    public void ForgetPasswordAndChangePasswordProcess(Matcher matcher) {
            String input;
            String input1;
            if (!CheckIfTheUserHasLoggedIn(matcher.group(1))) {
                System.out.println("The user is not logged in!");
                return;
            }
            if (CheckIfTheUserHasLoggedIn(matcher.group(1))) {

                System.out.println("What is your PasswordRecoveryQuestionNumber?");
                while (!(input = MyConsole.nextLine()).equals(FindUserByUserName(matcher.group(1)).PasswordRecoveryQuestionNumber)) {
                    System.out.println("The PasswordRecoveryQuestionNumber is wrong , try again.");
                }
                System.out.println("What is your PasswordRecoveryQuestion?");
                while (!(input = MyConsole.nextLine()).equals(FindUserByUserName(matcher.group(1)).getPasswordRecoveryQuestion())) {
                    System.out.println("The PasswordRecoveryQuestion is wrong , try again.");
                }
                System.out.println("The PasswordRecoveryQuestion is successfully confirmed.");
                System.out.println("Choose a new password for your account : ");

                input1 = MyConsole.nextLine();
                while (!CheckIfPasswordIsValid(input1)) {
                    PasswordValidationErrors(input1);
                    input1 = MyConsole.nextLine();
                }

                System.out.println("The new password is set for your account.");
                FindUserByUserName(matcher.group(1)).setPassword(input);
            }
        }

    public void Logout(){
        System.out.println("User "+LoggedInUser.get(0).getUsername()+" logged out successfully.");
        LoggedInUser.clear();
        EnterSignUpLogInMenu();
    }
    public void EnterSignUpLogInMenu(){
        ChangeMenuHandler.set(0 , "1") ;
        ChangeMenuHandler.set(1 , "0") ;
        System.out.println("You have entered in SignUp/LogIn Menu successfully.");

    }
    public void EnterMainMenu(){
        if(!CheckIfAnyOneIsLoggedIn()){
            System.out.println("No User is Logged In!");
            return;
        }

        ChangeMenuHandler.set(0 , "0") ;
        ChangeMenuHandler.set(1 , "1") ;
        System.out.println("You have entered in Main Menu successfully.");

        if(LoggedInUser.get(0).getNumberOfEntranceToMainMenu()==0){
            AddCardToStarterPack();
            int tempNumberOfEntranceToMainMenu = LoggedInUser.get(0).getNumberOfEntranceToMainMenu() ;
            LoggedInUser.get(0).setNumberOfEntranceToMainMenu(tempNumberOfEntranceToMainMenu+1);
        }
    }
    public void ShowOptionsInMainMenuAndHandleEachOfThem(){
        if(!CheckIfAnyOneIsLoggedIn()){
            return;
        }
        String input ;
        System.out.println("Choose your option(a number between 1-6) : ");
        System.out.println("1. Start Game");
        System.out.println("2. View Cards");
        System.out.println("3. Games History");
        System.out.println("4. Shop");
        System.out.println("5. Profile");
        System.out.println("6. Logout");

        while (Integer.parseInt(input = MyConsole.nextLine())>6 || Integer.parseInt(input)<1){
            System.out.println("Please enter a number between 1-6!") ;
        }


        if(Integer.parseInt(input)==1){
            StartGame() ;
        }
        else if(Integer.parseInt(input)==2){
            ViewMyCards();
        }
        else if(Integer.parseInt(input)==3){

        }
        else if(Integer.parseInt(input)==4){
            Shop() ;

        }
        else if(Integer.parseInt(input)==5){
            Profile();
        }
        else if(Integer.parseInt(input)==6){
           Logout();
        }




    }

    public void  LogInAdmin(Matcher matcher){
        String input ;
            System.out.println("Choose an option below(a number between 1-4) : ");
            System.out.println("1. AddCard");
            System.out.println("2. EditCard");
            System.out.println("3. DeleteCard");
            System.out.println("4. ViewAllPlayers");

            while (true){
                input = MyConsole.nextLine() ;
                if(Integer.parseInt(input)>4 || Integer.parseInt(input)<1){
                    System.out.println("Please enter a number between 1-4!") ;
                }
                else{
                    break;
                }
            }

            if(Integer.parseInt(input)==1){
                AddCard();
            }
            else if(Integer.parseInt(input)==2){
                EditCard();
            }
            else if(Integer.parseInt(input)==3){
                DeleteCard();
            }
            else if(Integer.parseInt(input)==4){
                ViewAllPlayers();

            }



        }

    public boolean CheckIfCardExistWithTheSameNameInAdminCards(String Name){
        boolean CardExist = false ;
        for (Cards cards : CardsFromAdmin) {
            if(cards.getName().equals(Name)){
                CardExist=true ;
                break;
            }
        }
        if(CardExist){
            return true ;
        }
        return false ;
    }

    public void AddCard(){
        String Name ;
        String Card_DefenceOrAttack ;
        String Duration ;
        String PlayerDamage ;
        String UpgradeLevel ;
        String UpgradeCost ;

        System.out.println("Enter the new card's name : ");

        while (CheckIfCardExistWithTheSameNameInAdminCards(Name = MyConsole.nextLine())){
            System.out.println("Card with the same name already exist!");
        }

        System.out.println("Enter the new card's Card_DefenceOrAttack (a number between 10-100) : ");
        while (Integer.parseInt(Card_DefenceOrAttack = MyConsole.nextLine())<10 || Integer.parseInt(Card_DefenceOrAttack)>100){
            System.out.println("the new card's Card_DefenceOrAttack must be between 10 and 100 !");
        }

        System.out.println("Enter the new card's Duration (a number between 1-5) : ");
        while (Integer.parseInt(Duration = MyConsole.nextLine())<1 || Integer.parseInt(Duration)>5){
            System.out.println("the new card's Duration must be between 1 and 5 !");
        }

        System.out.println("Enter the new card's PlayerDamage (a number between 10-50) : ");
        while (Integer.parseInt(PlayerDamage = MyConsole.nextLine())<10 || Integer.parseInt(PlayerDamage)>50){
            System.out.println("the new card's PlayerDamage must be between 10 and 50 !");
        }

        System.out.println("Enter the new card's UpgradeLevel : ");
        UpgradeLevel = MyConsole.nextLine() ;

        System.out.println("Enter the new card's UpgradeCost : ");
        UpgradeCost = MyConsole.nextLine() ;

        System.out.println("The new card information has successfully been set!");
        Cards cards = new Cards(Name , Card_DefenceOrAttack , Duration , PlayerDamage , UpgradeLevel , UpgradeCost , "1") ;
        CardsFromAdmin.add(cards) ;

    }

    public void EditCard(){
        String input ;
        System.out.println("Choose the number of desired card : ");
        for(int i=0 ; i<CardsFromAdmin.size() ; i++){
            System.out.println(i+1+". "+CardsFromAdmin.get(i).getName());
        }
        input = MyConsole.nextLine() ;
        ShowCardInfo(CardsFromAdmin.get(Integer.parseInt(input)-1));


    }

    public void ShowCardInfo(Cards card){
        int input ;
        System.out.println("Choose the number of desired part : ");

        System.out.println("1. Name : " + card.getName());
        System.out.println("2. Card_DefenceOrAttack : " + card.getCard_DefenceOrAttack());
        System.out.println("3. Duration : " + card.getDuration());
        System.out.println("4. PlayerDamage : " + card.getPlayerDamage());
        System.out.println("5. UpgradeLevel : " + card.getUpgradeLevel());
        System.out.println("6. UpgradeCost : " + card.getUpgradeCost());

        input = MyConsole.nextInt() ;
        MyConsole.nextLine() ;
        EditSpecificPartOfACard(input , card);

    }

    public void EditSpecificPartOfACard(int number , Cards card){
        String input ;
        String Name ;
        String Card_DefenceOrAttack ;
        String Duration ;
        String PlayerDamage ;
        String UpgradeLevel ;
        String UpgradeCost ;



        if(number==1){
            System.out.println("(Press back word if you want to go the previous menu) : ");
            System.out.println("Choose a new name for your card : ");

            Name = MyConsole.nextLine() ;
            if(Name.equalsIgnoreCase("back")){
                ShowCardInfo(card);
                return;
            }
            while(true){
                if(CheckIfCardExistWithTheSameNameInAdminCards(Name)) {
                    System.out.println("Card with the same name already exist!");
                    Name = MyConsole.nextLine() ;
                }

                else{
                    System.out.println("Are you sure you want to edit this card? (y/n) : ");
                    input = MyConsole.nextLine() ;
                    if(input.equalsIgnoreCase("y")){
                        System.out.println("The Card name has successfully been changed!");
                        FindCardInCardsFromAdmin(card).setName(Name);
                        break;
                    }
                    else {
                        ShowCardInfo(card);
                        break;
                    }

                }
            }


        }
        if(number==2){
            System.out.println("(Press back word if you want to go the previous menu) : ");
            System.out.println("Choose a new Card_DefenceOrAttack for your card : ");

            Card_DefenceOrAttack = MyConsole.nextLine() ;
            if(Card_DefenceOrAttack.equalsIgnoreCase("back")){
                ShowCardInfo(card);
                return;
            }
            while (Integer.parseInt(Card_DefenceOrAttack)<10 || Integer.parseInt(Card_DefenceOrAttack)>100){
                System.out.println("the new card's Card_DefenceOrAttack must be between 10 and 100 !");
                Card_DefenceOrAttack = MyConsole.nextLine() ;
            }
            System.out.println("Are you sure you want to edit this card? (y/n) : ");
            input = MyConsole.nextLine() ;
            if(input.equalsIgnoreCase("y")) {
                System.out.println("The Card Card_DefenceOrAttack has successfully been changed!");
                FindCardInCardsFromAdmin(card).setCard_DefenceOrAttack(Card_DefenceOrAttack);
                return;
            }
            else {
                ShowCardInfo(card);
                return;
            }
        }


        if(number==3) {
            System.out.println("(Press back word if you want to go the previous menu) : ");
            System.out.println("Choose a new Duration for your card : ");
            Duration = MyConsole.nextLine() ;
            if(Duration.equalsIgnoreCase("back")){
                ShowCardInfo(card);
                return;
            }
            while (Integer.parseInt(Duration) < 1 || Integer.parseInt(Duration) > 5) {
                System.out.println("the new card's Duration must be between 1 and 5 !");
                Duration = MyConsole.nextLine() ;
            }
            System.out.println("Are you sure you want to edit this card? (y/n) : ");
            input = MyConsole.nextLine() ;
            if(input.equalsIgnoreCase("y")) {
                System.out.println("The Card Duration has successfully been changed!");
                FindCardInCardsFromAdmin(card).setDuration(Duration);
                return;
            }
            else{
                ShowCardInfo(card);
                return;
            }
        }

        if(number==4) {
            System.out.println("(Press back word if you want to go the previous menu) : ");
            System.out.println("Enter the new card's PlayerDamage (a number between 10-50) : ");
            PlayerDamage = MyConsole.nextLine() ;
            if(PlayerDamage.equalsIgnoreCase("back")){
                ShowCardInfo(card);
                return;
            }
            while (Integer.parseInt(PlayerDamage) < 1 || Integer.parseInt(PlayerDamage) > 5) {
                System.out.println("the new card's PlayerDamage must be between 10 and 50 !");
                PlayerDamage = MyConsole.nextLine() ;
            }
            System.out.println("Are you sure you want to edit this card? (y/n) : ");
            input = MyConsole.nextLine() ;
            if(input.equalsIgnoreCase("y")) {
                System.out.println("The Card PlayerDamage has successfully been changed!");
                FindCardInCardsFromAdmin(card).setPlayerDamage(PlayerDamage);
                return;
            }
            else {
                ShowCardInfo(card);
                return;
            }
        }

        if(number==5) {
            System.out.println("(Press back word if you want to go the previous menu) : ");
            System.out.println("Enter the new card's UpgradeLevel : ");
            UpgradeLevel = MyConsole.nextLine() ;
            if(UpgradeLevel.equalsIgnoreCase("back")){
                ShowCardInfo(card);
                return;
            }
            System.out.println("Are you sure you want to edit this card? (y/n) : ");
            input = MyConsole.nextLine() ;
            if(input.equalsIgnoreCase("y")) {
                System.out.println("The Card UpgradeLevel has successfully been changed!");
                FindCardInCardsFromAdmin(card).setPlayerDamage(UpgradeLevel);
                return;
            }
            else{
                ShowCardInfo(card);
                return;
            }
        }

        if(number==6) {
            System.out.println("(Press back word if you want to go the previous menu) : ");
            System.out.println("Choose a new UpgradeCost for your card : ");
            UpgradeCost = MyConsole.nextLine() ;
            if(UpgradeCost.equalsIgnoreCase("back")){
                ShowCardInfo(card);
                return;
            }
            System.out.println("Are you sure you want to edit this card? (y/n) : ");
            input = MyConsole.nextLine() ;
            if(input.equalsIgnoreCase("y")) {
                System.out.println("The Card UpgradeCost has successfully been changed!");
                FindCardInCardsFromAdmin(card).setPlayerDamage(UpgradeCost);
                return;
            }
            else{
                ShowCardInfo(card);
                return;
            }
        }


    }

    public void DeleteCard(){
        String input ;
        String answer ;
        System.out.println("Choose the number of desired card : ");
        for(int i=0 ; i<CardsFromAdmin.size() ; i++){
            System.out.println(i+1+". "+CardsFromAdmin.get(i).getName());
        }
        input = MyConsole.nextLine() ;
        System.out.println("Are you sure you want to delete this card? (y/n)");
        answer = MyConsole.nextLine() ;
        if(answer.equalsIgnoreCase("y")){
            System.out.println("The card has successfully been removed!");
            CardsFromAdmin.remove(CardsFromAdmin.get(Integer.parseInt(input)-1)) ;
            return;
        }
        else{
            DeleteCard();
        }

    }

    public void ViewAllPlayers(){
        for(int i=0 ; i<users.size() ; i++){
            System.out.println(i+1+". Name :"+users.get(i).getUsername()+" Level :"+users.get(i).getLevel()+" Coin :"+users.get(i).getCoin());
        }

    }

    public Cards FindCardInCardsFromAdmin(Cards card){
        for (Cards cards : CardsFromAdmin) {
            if(cards == card){
                return cards ;
            }
        }
        return null ;
    }

    public void CreateSomeCardsFromPhase0(){
        Cards card = new Cards("Fire Bullet" , "30" , "2" , "20" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card) ;

        Cards card1 = new Cards("Wizard Of Courage" , "40" , "1" , "22" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card1) ;

        Cards card2 = new Cards("Elf" , "35" , "2" , "18" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card2) ;

        Cards card3 = new Cards("Little Bear" , "50" , "3" , "30" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card3) ;

        Cards card4 = new Cards("Siren" , "24" , "3" , "30" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card4) ;

        Cards card5 = new Cards("Shadow Owl" , "45" , "4" , "40" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card5) ;

        Cards card6 = new Cards("NightMare" , "34" , "2" , "26" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card6) ;

        Cards card7 = new Cards("Mad Cannon" , "28" , "3" , "32" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card7) ;

        Cards card8 = new Cards("Dead Hunter" , "16" , "1" , "28" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card8) ;

        Cards card9 = new Cards("Delayed Whisper" , "30" , "1" , "30" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card9) ;

        Cards card10 = new Cards("Boneless" , "36" , "2" , "40" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card10) ;

        Cards card11 = new Cards("Flamed Titan" , "22" , "1" , "24" , "1" ,"50" , "2") ;
        SomeCardsFromPhase0.add(card11) ;

        Cards card12 = new Cards("Foolish Robot" , "28" , "2" , "48" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card12) ;

        Cards card13 = new Cards("Faith Killer" , "18" , "2" , "20" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card13) ;

        Cards card14 = new Cards("Stone Drop" , "26" , "2" , "30" , "1" ,"50" , "1") ;
        SomeCardsFromPhase0.add(card14) ;
    }

    public void CreateSomeSpellsFromPhase0(){
        SomeSpellsFromPhase0.add("Magnet") ;
        SomeSpellsFromPhase0.add("Accelerator") ;
        SomeSpellsFromPhase0.add("Booster") ;
        SomeSpellsFromPhase0.add("Poison") ;
    }

    public void AddCardToStarterPack(){
        Random random = new Random();

       for(int i=0 ; i<17 ; i++){
            int randomNumber = random.nextInt(15) ;
            LoggedInUser.get(0).NormalCards.add(SomeCardsFromPhase0.get(randomNumber)) ;
       }
       for(int i=0 ; i<3 ; i++){
           int randomNumber = random.nextInt(4) ;
           LoggedInUser.get(0).Spells.add(SomeSpellsFromPhase0.get(randomNumber)) ;
       }



//        for (Cards normalCard : LoggedInUser.get(0).NormalCards) {
//            System.out.println(normalCard.getName());
//        }
//        for (String spell : LoggedInUser.get(0).Spells) {
//            System.out.println(spell);
//        }

    }

    public void AddCardToStarterPackForOpponent() {
        Random random = new Random();

        for (int i = 0; i < 17; i++) {
            int randomNumber = random.nextInt(14);
            LoggedInOpponent.get(0).NormalCards.add(SomeCardsFromPhase0.get(randomNumber));
        }
        for (int i = 0; i < 3; i++) {
            int randomNumber = random.nextInt(4);
            LoggedInOpponent.get(0).Spells.add(SomeSpellsFromPhase0.get(randomNumber));
        }
    }

    public void ViewMyCards(){
        String input ;
        System.out.println("Normal Cards : ");
        for(int i=0 ; i<LoggedInUser.get(0).NormalCards.size() ; i++){
            System.out.println(i+1+". Name :"+LoggedInUser.get(0).NormalCards.get(i).getName()+" Card_DefenceOrAttack :"+LoggedInUser.get(0).NormalCards.get(i).getCard_DefenceOrAttack()+" Duration :"+LoggedInUser.get(0).NormalCards.get(i).getDuration()+" PlayerDamage :"+LoggedInUser.get(0).NormalCards.get(i).getPlayerDamage()+" UpgradeLevel :"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" UpgradeCost :"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeCost()+" Level :"+LoggedInUser.get(0).NormalCards.get(i).getLevel());
        }

        System.out.println("Spell Cards : ");
        for(int i=0 ; i<LoggedInUser.get(0).Spells.size() ; i++){
            System.out.println(i+1+". Name :"+LoggedInUser.get(0).Spells.get(i));
        }
        input = MyConsole.nextLine() ;
        if(input.equalsIgnoreCase("back")){
            EnterMainMenu();
            ShowOptionsInMainMenuAndHandleEachOfThem();
            return;
        }
    }

    public void Shop(){
        String input ;
        String input1 ;
        int tempcoin ;
        String templevel ;
        System.out.println("Choose your option : ");
        System.out.println("1. Upgrade Card");
        System.out.println("2. Buy Card");
        input = MyConsole.nextLine() ;

        if(input.equalsIgnoreCase("1")){
            System.out.println("You have "+LoggedInUser.get(0).getCoin()+" Coin");
            System.out.println("Which Card do you want to Upgrade? (Type the name!) : ");
            ShowCardsForUpgrading() ;
            input1 = MyConsole.nextLine() ;
            if(input1.equalsIgnoreCase("back")){
                EnterMainMenu();
                ShowOptionsInMainMenuAndHandleEachOfThem() ;
                return;
            }

            if(!CheckIfUpgradingIsValidLevel(FindCardsByName(input1))){
                System.out.println("Your User Level is not sufficient!");
                input1 = MyConsole.nextLine() ;
                if(input1.equalsIgnoreCase("back")){
                    EnterMainMenu();
                    ShowOptionsInMainMenuAndHandleEachOfThem() ;
                    return;
                }
                return;
            }
            if(!CheckIfUpgradingIsValidCoin(FindCardsByName(input1))){
                System.out.println("Your Coin is not sufficient!");
                input1 = MyConsole.nextLine() ;
                if(input1.equalsIgnoreCase("back")){
                    EnterMainMenu();
                    ShowOptionsInMainMenuAndHandleEachOfThem() ;
                    return;
                }
                return;
            }
            if(CheckIfUpgradingIsValidCoin(FindCardsByName(input1))){
                for (Cards normalCard : LoggedInUser.get(0).NormalCards) {
                    if(normalCard.getName().equalsIgnoreCase(input1)){
                        tempcoin = LoggedInUser.get(0).getCoin() ;
                        templevel = normalCard.getLevel() ;
                        ChangeInformationOfTheUpgradedCard(normalCard) ;
                        LoggedInUser.get(0).setCoin(tempcoin - DetermineThePriceForBuying(normalCard));
                        normalCard.setLevel(String.valueOf(Integer.parseInt(templevel)+1));
                        System.out.println("The card has been successfully upgraded!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        break;
                    }
                }

            }




        }
        if(input.equalsIgnoreCase("2")){
            System.out.println("You have "+LoggedInUser.get(0).getCoin()+" Coin");
            System.out.println("Which Card do you want to buy? (Type the name!) : ");
            ShowUnBoughtCards();
            input1 = MyConsole.nextLine() ;
            if(input1.equalsIgnoreCase("back")){
                EnterMainMenu();
                ShowOptionsInMainMenuAndHandleEachOfThem() ;
                return;
            }

            if(IsItSpell(input1)) {
                if (CheckIfSpellIsBuiltIn(input1)) {
                    if (!CheckIfBuyingIsValidForBuiltInSpells(input1)) {
                        System.out.println("Your Coin is not sufficient!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        return;
                    }
                    if (CheckIfBuyingIsValidForBuiltInSpells(input1)) {
                        tempcoin = LoggedInUser.get(0).getCoin();
                        LoggedInUser.get(0).Spells.add(input1);
                        LoggedInUser.get(0).setCoin(tempcoin - SomeBuiltInSpellsPriceReturn(input1));
                        //BuiltInSpells.remove(input1) ;
                        System.out.println("The card has been successfully bought!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        return;
                    }
                }

                if (!CheckIfSpellIsBuiltIn(input1)) {
                    if(!CheckIfBuyingIsValidForSpellsFromPhase0(input1)){
                        System.out.println("Your Coin is not sufficient!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        return;
                    }
                    if(CheckIfBuyingIsValidForSpellsFromPhase0(input1)){
                        tempcoin = LoggedInUser.get(0).getCoin() ;
                        LoggedInUser.get(0).Spells.add(input1) ;
                        LoggedInUser.get(0).setCoin(tempcoin - SomeSpellsFromPhase0PriceReturn(input1));
                        //SomeSpellsFromPhase0.remove(input1) ;
                        System.out.println("The card has been successfully bought!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        return;
                    }
                }
            }



            if(!IsItSpell(input1)) {
                if (!CheckIfBuyingIsValid(FindTheCardForBuying(input1))) {
                    System.out.println("Your Coin is not sufficient!");
                    input1 = MyConsole.nextLine() ;
                    if(input1.equalsIgnoreCase("back")){
                        EnterMainMenu();
                        ShowOptionsInMainMenuAndHandleEachOfThem() ;
                        return;
                    }
                    return;
                }

                if (CheckIfBuyingIsValid(FindTheCardForBuying(input1))) {
                    tempcoin = LoggedInUser.get(0).getCoin();

                    if (CheckIfCardBelongsToPhase0(input1)) {
                        LoggedInUser.get(0).NormalCards.add(FindTheCardForBuying(input1));
                        LoggedInUser.get(0).setCoin(tempcoin - DetermineThePriceForBuying(FindTheCardForBuying(input1)));
                        //SomeCardsFromPhase0.remove(FindTheCardForBuying(input1));
                        System.out.println("The card has been successfully bought!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        return;
                    }
                    if (CheckIfCardBelongsToAdmin(input1)) {
                        LoggedInUser.get(0).NormalCards.add(FindTheCardForBuying(input1));
                        LoggedInUser.get(0).setCoin(tempcoin - DetermineThePriceForBuying(FindTheCardForBuying(input1)));
                        //CardsFromAdmin.remove(FindTheCardForBuying(input1));
                        System.out.println("The card has been successfully bought!");
                        input1 = MyConsole.nextLine() ;
                        if(input1.equalsIgnoreCase("back")){
                            EnterMainMenu();
                            ShowOptionsInMainMenuAndHandleEachOfThem() ;
                            return;
                        }
                        return;
                    }
                }
            }

        }
    }

    public void ShowUnBoughtCards(){
        boolean HasFoundTheSame = false ;
        boolean HasFoundTheSame1 = false ;
        boolean HasFoundTheSame2 = false ;
        boolean HasFoundTheSame3 = false ;



        int count=1 ;
        int count1 ;
        int count2 ;
        int count3 ;

        for(int i=0 ; i<SomeCardsFromPhase0.size() ; i++){
            for(int j=0 ; j<LoggedInUser.get(0).NormalCards.size() ; j++){
                if(SomeCardsFromPhase0.get(i)==LoggedInUser.get(0).NormalCards.get(j)){
                    HasFoundTheSame = true ;
                }
            }
            if(!HasFoundTheSame){
                System.out.println(count+". Name :"+SomeCardsFromPhase0.get(i).getName()+" Card_DefenceOrAttack :"+SomeCardsFromPhase0.get(i).getCard_DefenceOrAttack()+" Duration :"+SomeCardsFromPhase0.get(i).getDuration()+" PlayerDamage :"+SomeCardsFromPhase0.get(i).getPlayerDamage()+" UpgradeLevel :"+SomeCardsFromPhase0.get(i).getUpgradeLevel()+" UpgradeCost :"+DetermineThePriceForBuying(SomeCardsFromPhase0.get(i))+" Level :"+SomeCardsFromPhase0.get(i).getLevel());
                count++ ;
            }
            HasFoundTheSame=false ;
        }
        count1 = count ;

        for(int i=0 ; i<CardsFromAdmin.size() ; i++){
            for(int j=0 ; j<LoggedInUser.get(0).NormalCards.size() ; j++){
                if(CardsFromAdmin.get(i)==LoggedInUser.get(0).NormalCards.get(j)){
                    HasFoundTheSame1 = true ;
                }
            }
            if(!HasFoundTheSame1){
                System.out.println(count1+". Name :"+CardsFromAdmin.get(i).getName()+" Card_DefenceOrAttack :"+CardsFromAdmin.get(i).getCard_DefenceOrAttack()+" Duration :"+CardsFromAdmin.get(i).getDuration()+" PlayerDamage :"+CardsFromAdmin.get(i).getPlayerDamage()+" UpgradeLevel :"+CardsFromAdmin.get(i).getUpgradeLevel()+" UpgradeCost :"+DetermineThePriceForBuying(SomeCardsFromPhase0.get(i)));
                count1++ ;
            }
            HasFoundTheSame1=false ;
        }

        count2 = count1 ;
        for(int i=0 ; i<SomeSpellsFromPhase0.size() ; i++){
            for(int j=0 ; j<LoggedInUser.get(0).Spells.size() ; j++){
                if(SomeSpellsFromPhase0.get(i)==LoggedInUser.get(0).Spells.get(j)){
                    HasFoundTheSame2 = true ;
                }
            }
            if(!HasFoundTheSame2){
                System.out.println(count2+". Name :"+SomeSpellsFromPhase0.get(i)+" UpgradeCost :"+SomeSpellsFromPhase0PriceReturn(SomeSpellsFromPhase0.get(i)));
                count2++ ;
            }
            HasFoundTheSame2=false ;
        }

        count3 = count2 ;
        for(int i=0 ; i<BuiltInSpells.size() ; i++){
            for(int j=0 ; j<LoggedInUser.get(0).Spells.size() ; j++){
                if(BuiltInSpells.get(i)==LoggedInUser.get(0).Spells.get(j)){
                    HasFoundTheSame3 = true ;
                }
            }
            if(!HasFoundTheSame3){
                System.out.println(count3+". Name :"+BuiltInSpells.get(i)+" UpgradeCost :"+SomeBuiltInSpellsPriceReturn(BuiltInSpells.get(i)));
                count3++ ;
            }
            HasFoundTheSame3=false ;
        }


    }

    public void AddBuiltInSpells(){
        BuiltInSpells.add("Shield") ;
        BuiltInSpells.add("Heal") ;
        BuiltInSpells.add("Power Booster") ;
        BuiltInSpells.add("Hole Place Changer") ;
        BuiltInSpells.add("Repair") ;
        BuiltInSpells.add("Round Reducer") ;
        BuiltInSpells.add("Delete Card From Opponent Deck") ;
        BuiltInSpells.add("Weaken Opponent Card") ;
        BuiltInSpells.add("Copy") ;
        BuiltInSpells.add("Hider") ;
    }

    public int SomeSpellsFromPhase0PriceReturn(String Name){
        if(Name.equalsIgnoreCase("Magnet")){
            return 40 ;
        }
        if(Name.equalsIgnoreCase("Accelerator")){
            return 40 ;
        }
        if(Name.equalsIgnoreCase("Booster")){
            return 65 ;
        }
        if(Name.equalsIgnoreCase("Poison")){
            return 65 ;
        }
        return 0 ;
    }

    public int SomeBuiltInSpellsPriceReturn(String Name){
        if(Name.equalsIgnoreCase("Shield")){
            return 40 ;
        }
        if(Name.equalsIgnoreCase("Heal")){
            return 45 ;
        }
        if(Name.equalsIgnoreCase("Power Booster")){
            return 50 ;
        }
        if(Name.equalsIgnoreCase("Hole Place Changer")){
            return 40 ;
        }
        if(Name.equalsIgnoreCase("Repair")){
            return 60 ;
        }
        if(Name.equalsIgnoreCase("Round Reducer")){
            return 65 ;
        }
        if(Name.equalsIgnoreCase("Delete Card From Opponent Deck")){
            return 60 ;
        }
        if(Name.equalsIgnoreCase("Weaken Opponent Card")){
            return 70 ;
        }
        if(Name.equalsIgnoreCase("Copy")){
            return 55 ;
        }
        if(Name.equalsIgnoreCase("Hider")){
            return 80 ;
        }
        return 0 ;
    }

    public Cards FindTheCardForBuying(String Name){
        for (Cards cards : SomeCardsFromPhase0) {
            if(cards.getName().equalsIgnoreCase(Name)){
                return cards ;
            }
        }
        for (Cards cards : CardsFromAdmin) {
            if(cards.getName().equalsIgnoreCase(Name)){
                return cards ;
            }
        }
        return null ;
    }

    public boolean CheckIfCardBelongsToPhase0(String Name){
        for (Cards cards : SomeCardsFromPhase0) {
            if(cards.getName().equalsIgnoreCase(Name)){
                return true ;
            }
        }
        return false ;
    }
    public boolean CheckIfCardBelongsToAdmin(String Name){
        for (Cards cards : CardsFromAdmin) {
            if(cards.getName().equalsIgnoreCase(Name)){
                return true ;
            }
        }
        return false ;
    }

    public boolean CheckIfBuyingIsValid(Cards card){
        int IncreasePriceCofficient=1 ;
        for(int i=0 ; i<Integer.parseInt(card.getLevel())-1 ; i++){
            IncreasePriceCofficient*=1.25 ;
        }
        if(LoggedInUser.get(0).getCoin()<IncreasePriceCofficient*Integer.parseInt(card.getUpgradeCost())){
            return false ;
        }
        return true ;
    }

    public int DetermineThePriceForBuying(Cards card){
        double IncreasePriceCofficient=1 ;
        for(int i=0 ; i<Integer.parseInt(card.getLevel())-1 ; i++){
            IncreasePriceCofficient*=1.25 ;
        }
        return (int)(Integer.parseInt(card.getUpgradeCost()) * IncreasePriceCofficient) ;
    }
    public boolean IsItSpell(String Name){
        boolean ExistInAdminNormal = false ;
        boolean ExistInPhase0Normal = false ;

        for (Cards cards : SomeCardsFromPhase0) {
            if(cards.getName().equalsIgnoreCase(Name)){
                ExistInPhase0Normal = true ;
                break;
            }
        }
        for (Cards cards : CardsFromAdmin) {
            if(cards.getName().equalsIgnoreCase(Name)){
                ExistInAdminNormal = true ;
                break;
            }
        }

        if(ExistInAdminNormal || ExistInPhase0Normal){
            return false ;
        }
        return true ;
    }

    public boolean CheckIfBuyingIsValidForBuiltInSpells(String Name){
        if(LoggedInUser.get(0).getCoin()<SomeBuiltInSpellsPriceReturn(Name)){
            return false ;
        }
        return true ;
    }

    public boolean CheckIfSpellIsBuiltIn(String Name){
        for (String builtInSpell : BuiltInSpells) {
            if(builtInSpell.equalsIgnoreCase(Name)){
                return true ;
            }
        }
        return false ;
    }

    public boolean CheckIfBuyingIsValidForSpellsFromPhase0(String input){
        if(LoggedInUser.get(0).getCoin() < SomeSpellsFromPhase0PriceReturn(input)){
            return false ;
        }
        return true ;
    }
    public void ShowCardsForUpgrading(){
        for(int i=0 ; i<LoggedInUser.get(0).NormalCards.size() ; i++){
            if(LoggedInUser.get(0).NormalCards.get(i).getLevel().equalsIgnoreCase("1")) {
                System.out.println(i + 1 + ". Name :" + LoggedInUser.get(0).NormalCards.get(i).getName() + " /Change in playerDamage:+2 " + "/Change in Card_DefenceOrAttack:+1 " + "/Needed User level:"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" /Needed Coin:" + DetermineThePriceForBuying(LoggedInUser.get(0).NormalCards.get(i)));
            }
            if(LoggedInUser.get(0).NormalCards.get(i).getLevel().equalsIgnoreCase("2")) {
                System.out.println(i + 1 + ". Name :" + LoggedInUser.get(0).NormalCards.get(i).getName() + " /Change in playerDamage:+4 " + "/Change in Card_DefenceOrAttack:+2 " + "/Needed User level:"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" /Needed Coin:" + DetermineThePriceForBuying(LoggedInUser.get(0).NormalCards.get(i)));
            }
            if(LoggedInUser.get(0).NormalCards.get(i).getLevel().equalsIgnoreCase("3")) {
                System.out.println(i + 1 + ". Name :" + LoggedInUser.get(0).NormalCards.get(i).getName() + " /Change in playerDamage:+6 " + "/Change in Card_DefenceOrAttack:+3 " + "/Needed User level:"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" /Needed Coin:" + DetermineThePriceForBuying(LoggedInUser.get(0).NormalCards.get(i)));
            }
            if(LoggedInUser.get(0).NormalCards.get(i).getLevel().equalsIgnoreCase("4")) {
                System.out.println(i + 1 + ". Name :" + LoggedInUser.get(0).NormalCards.get(i).getName() + " /Change in playerDamage:+4 " + "/Change in Card_DefenceOrAttack:+4 " + "/Needed User level:"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" /Needed Coin:" + DetermineThePriceForBuying(LoggedInUser.get(0).NormalCards.get(i)));
            }
            if(LoggedInUser.get(0).NormalCards.get(i).getLevel().equalsIgnoreCase("5")) {
                System.out.println(i + 1 + ". Name :" + LoggedInUser.get(0).NormalCards.get(i).getName() + " /Change in playerDamage:+6 " + "/Change in Card_DefenceOrAttack:+5 " + "/Needed User level:"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" /Needed Coin:" + DetermineThePriceForBuying(LoggedInUser.get(0).NormalCards.get(i)));
            }
            if(LoggedInUser.get(0).NormalCards.get(i).getLevel().equalsIgnoreCase("6")) {
                System.out.println(i + 1 + ". Name :" + LoggedInUser.get(0).NormalCards.get(i).getName() + " /Change in playerDamage:+4 " + "/Change in Card_DefenceOrAttack:+6 " + "/Needed User level:"+LoggedInUser.get(0).NormalCards.get(i).getUpgradeLevel()+" /Needed Coin:" + DetermineThePriceForBuying(LoggedInUser.get(0).NormalCards.get(i)));
            }
        }
    }

    public boolean CheckIfUpgradingIsValidCoin(Cards card){
        if(LoggedInUser.get(0).getCoin()<DetermineThePriceForBuying(card)){
            return false ;
        }
        return true ;
    }
    public boolean CheckIfUpgradingIsValidLevel(Cards card){
        if(LoggedInUser.get(0).getLevel()<Integer.parseInt(card.getUpgradeLevel())){
            return false ;
        }
        return true ;
    }
    public Cards FindCardsByName(String Name){
        for (Cards normalCard : LoggedInUser.get(0).NormalCards) {
            if(normalCard.getName().equalsIgnoreCase(Name)){
                return normalCard ;
            }
        }
        return null ;
    }
    public void ChangeInformationOfTheUpgradedCard(Cards card){
        String tempPlayerDamage ;
        String tempCard_DefenceOrAttack ;
        if(card.getLevel().equalsIgnoreCase("1")){
            tempPlayerDamage = card.getPlayerDamage() ;
            tempCard_DefenceOrAttack = card.getCard_DefenceOrAttack() ;
            card.setPlayerDamage(String.valueOf(Integer.parseInt(tempPlayerDamage)+2));
            card.setCard_DefenceOrAttack(String.valueOf(Integer.parseInt(tempCard_DefenceOrAttack)+1));
        }
        if(card.getLevel().equalsIgnoreCase("2")){
            tempPlayerDamage = card.getPlayerDamage() ;
            tempCard_DefenceOrAttack = card.getCard_DefenceOrAttack() ;
            card.setPlayerDamage(String.valueOf(Integer.parseInt(tempPlayerDamage)+4));
            card.setCard_DefenceOrAttack(String.valueOf(Integer.parseInt(tempCard_DefenceOrAttack)+2));
        }
        if(card.getLevel().equalsIgnoreCase("3")){
            tempPlayerDamage = card.getPlayerDamage() ;
            tempCard_DefenceOrAttack = card.getCard_DefenceOrAttack() ;
            card.setPlayerDamage(String.valueOf(Integer.parseInt(tempPlayerDamage)+6));
            card.setCard_DefenceOrAttack(String.valueOf(Integer.parseInt(tempCard_DefenceOrAttack)+3));
        }
        if(card.getLevel().equalsIgnoreCase("4")){
            tempPlayerDamage = card.getPlayerDamage() ;
            tempCard_DefenceOrAttack = card.getCard_DefenceOrAttack() ;
            card.setPlayerDamage(String.valueOf(Integer.parseInt(tempPlayerDamage)+4));
            card.setCard_DefenceOrAttack(String.valueOf(Integer.parseInt(tempCard_DefenceOrAttack)+4));
        }
        if(card.getLevel().equalsIgnoreCase("5")){
            tempPlayerDamage = card.getPlayerDamage() ;
            tempCard_DefenceOrAttack = card.getCard_DefenceOrAttack() ;
            card.setPlayerDamage(String.valueOf(Integer.parseInt(tempPlayerDamage)+6));
            card.setCard_DefenceOrAttack(String.valueOf(Integer.parseInt(tempCard_DefenceOrAttack)+5));
        }
        if(card.getLevel().equalsIgnoreCase("6")){
            tempPlayerDamage = card.getPlayerDamage() ;
            tempCard_DefenceOrAttack = card.getCard_DefenceOrAttack() ;
            card.setPlayerDamage(String.valueOf(Integer.parseInt(tempPlayerDamage)+4));
            card.setCard_DefenceOrAttack(String.valueOf(Integer.parseInt(tempCard_DefenceOrAttack)+6));
        }

    }

    public void Profile(){
        System.out.println("You have entered in profile menu successfully!");
        String input ;
        input = MyConsole.nextLine() ;
        if(input.equalsIgnoreCase("back")){
            EnterMainMenu();
            ShowOptionsInMainMenuAndHandleEachOfThem();
            return;
        }
        if(input.equalsIgnoreCase("Show information")){
            System.out.println("Username: "+LoggedInUser.get(0).getUsername());
            System.out.println("Password: "+LoggedInUser.get(0).getPassword());
            System.out.println("NickName: "+LoggedInUser.get(0).getNickName());
            System.out.println("Email: "+LoggedInUser.get(0).getEmail());
            System.out.println("PasswordRecoveryQuestion: "+LoggedInUser.get(0).getPasswordRecoveryQuestion());

            input = MyConsole.nextLine() ;
            if(input.equalsIgnoreCase("back")){
                EnterMainMenu();
                ShowOptionsInMainMenuAndHandleEachOfThem();
            }
        }
        else if (input.matches("Profile change -u <(.*)>")) {
            Matcher matcher = getCommandMatcher(input, "Profile change -u <(.*)>");
            if (matcher.find()) {
                if(CheckIfUsernameExist(matcher.group(1))){
                    System.out.println("Entered username already exist!");
                    input = MyConsole.nextLine() ;
                    if(input.equalsIgnoreCase("back")){
                        EnterMainMenu();
                        ShowOptionsInMainMenuAndHandleEachOfThem();
                    }
                    return;
                }
                if(!CheckIfUserNameIsValid(matcher.group(1))){
                    System.out.println("The username is invalid! , please try again.");
                    input = MyConsole.nextLine() ;
                    if(input.equalsIgnoreCase("back")){
                        EnterMainMenu();
                        ShowOptionsInMainMenuAndHandleEachOfThem();
                    }
                    return;
                }
                LoggedInUser.get(0).setUsername(matcher.group(1));
                System.out.println("Your username has successfully been changed.");

                input = MyConsole.nextLine() ;
                if(input.equalsIgnoreCase("back")){
                    EnterMainMenu();
                    ShowOptionsInMainMenuAndHandleEachOfThem();
                }
            }
        }

        else if (input.matches("Profile change -n <(.*)>")) {
            Matcher matcher = getCommandMatcher(input, "Profile change -n <(.*)>");
            if (matcher.find()) {
                if(CheckIfNickNameExist(matcher.group(1))){
                    System.out.println("Entered nickname already exist!");
                    input = MyConsole.nextLine() ;
                    if(input.equalsIgnoreCase("back")){
                        EnterMainMenu();
                        ShowOptionsInMainMenuAndHandleEachOfThem();
                    }
                    return;
                }
                LoggedInUser.get(0).setNickName(matcher.group(1));
                System.out.println("Your nickname has successfully been changed.");

                input = MyConsole.nextLine() ;
                if(input.equalsIgnoreCase("back")){
                    EnterMainMenu();
                    ShowOptionsInMainMenuAndHandleEachOfThem();
                }
            }
        }

        else if (input.matches("profile change password -o <(.*)> -n <(.*)>")) {
            Matcher matcher = getCommandMatcher(input, "profile change password -o <(.*)> -n <(.*)>");
            if (matcher.find()) {
                if(!CheckIfPasswordIsValidForChanging(matcher.group(2))){
                    System.out.println("Please enter your new password again");
                    Profile();
                }
                if(CheckIfPasswordIsValidForChanging(matcher.group(2))){
                    if(!LoggedInUser.get(0).getPassword().equals(matcher.group(1))){
                        System.out.println("Current password is incorrect!");
                        Profile();
                        return;
                    }
                    if(matcher.group(2).equals(LoggedInUser.get(0).getPassword())){
                        System.out.println("Please enter a new password!");
                        Profile();
                        return;
                    }


                    System.out.println("Do the CAPTCHA below : ");
                    //Generate Random numbers
                    String digitChars = "0123456789";
                    Random random = new Random();
                    StringBuilder CAPTCHA = new StringBuilder();

                    String[] asciiArtZero = {
                            " ##### ",
                            "#     #",
                            "#     #",
                            "#     #",
                            "#     #",
                            "#     #",
                            " ##### "
                    };
                    String[] asciiArtOne = {
                            "   #  ",
                            "  ##  ",
                            "   #  ",
                            "   #  ",
                            " #####"
                    };

                    String[] asciiArtTwo = {
                            " ##### ",
                            "#     #",
                            "      #",
                            " ##### ",
                            "#      ",
                            "#      ",
                            "#######"
                    };
                    String[] asciiArtThree = {
                            " ##### ",
                            "#     #",
                            "      #",
                            " ##### ",
                            "      #",
                            "#     #",
                            " ##### "
                    };
                    String[] asciiArtFour = {
                            "   #   ",
                            "  ##   ",
                            " # #   ",
                            "#  #   ",
                            "######",
                            "   #   ",
                            "   #   "
                    };

                    String[] asciiArtFive = {
                            "######",
                            "#     ",
                            "#     ",
                            "##### ",
                            "     #",
                            "     #",
                            "##### "
                    };
                    String[] asciiArtSix = {
                            " ##### ",
                            "#      ",
                            "#      ",
                            "###### ",
                            "#     #",
                            "#     #",
                            " ##### "
                    };
                    String[] asciiArtSeven = {
                            "#######",
                            "      #",
                            "     # ",
                            "    #  ",
                            "   #   ",
                            "  #    ",
                            " #     "
                    };
                    String[] asciiArtEight = {
                            " ##### ",
                            "#     #",
                            "#     #",
                            " ##### ",
                            "#     #",
                            "#     #",
                            " ##### "
                    };
                    String[] asciiArtNine = {
                            " ##### ",
                            "#     #",
                            "#     #",
                            " ######",
                            "      #",
                            "#     #",
                            " ##### "
                    };

                    String input1 ;


                    for (int i = 0; i < 6; i++) {
                        CAPTCHA.append(digitChars.charAt(random.nextInt(digitChars.length())));
                    }
                    for (int i = 0; i < String.valueOf(CAPTCHA).length(); i++) {
                        if (String.valueOf(CAPTCHA).charAt(i) == '0') {
                            for (String line : asciiArtZero) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '1') {
                            for (String line : asciiArtOne) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '2') {
                            for (String line : asciiArtTwo) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '3') {
                            for (String line : asciiArtThree) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '4') {
                            for (String line : asciiArtFour) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '5') {
                            for (String line : asciiArtFive) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '6') {
                            for (String line : asciiArtSix) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '7') {
                            for (String line : asciiArtSeven) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '8') {
                            for (String line : asciiArtEight) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                        if (String.valueOf(CAPTCHA).charAt(i) == '9') {
                            for (String line : asciiArtNine) {
                                System.out.println(line);
                            }
                            System.out.println();
                        }
                    }

                    while (!(input1 = MyConsole.nextLine()).equals(String.valueOf(CAPTCHA))) {
                        System.out.println("The CAPTCHA is not confirmed! , try again.");
                        CAPTCHA.delete(0 , 6) ;
                        for(int i=0 ; i<6 ; i++) {
                            CAPTCHA.append(digitChars.charAt(random.nextInt(digitChars.length())));
                        }

                        for (int i = 0; i < String.valueOf(CAPTCHA).length(); i++) {
                            if (String.valueOf(CAPTCHA).charAt(i) == '0') {
                                for (String line : asciiArtZero) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '1') {
                                for (String line : asciiArtOne) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '2') {
                                for (String line : asciiArtTwo) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '3') {
                                for (String line : asciiArtThree) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '4') {
                                for (String line : asciiArtFour) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '5') {
                                for (String line : asciiArtFive) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '6') {
                                for (String line : asciiArtSix) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '7') {
                                for (String line : asciiArtSeven) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '8') {
                                for (String line : asciiArtEight) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                            if (String.valueOf(CAPTCHA).charAt(i) == '9') {
                                for (String line : asciiArtNine) {
                                    System.out.println(line);
                                }
                                System.out.println();
                            }
                        }

                    }

                    System.out.println("The CAPTCHA is successfully confirmed!");
                    System.out.println("The password has successfully been changed!");
                    LoggedInUser.get(0).setPassword(matcher.group(2));
                    input = MyConsole.nextLine() ;

                    if(input.equalsIgnoreCase("back")){
                        EnterMainMenu();
                        ShowOptionsInMainMenuAndHandleEachOfThem();
                    }
                }
            }
        }

        else if (input.matches("profile change -e <(.*)>")) {
            Matcher matcher = getCommandMatcher(input, "profile change -e <(.*)>");
            if (matcher.find()) {
                if(!CheckIfEmailIsOk(matcher.group(1))){
                    System.out.println("Please enter your email again.");
                    Profile();
                    return;
                }
                if(CheckIfEmailIsOk(matcher.group(1))){
                    if(LoggedInUser.get(0).getEmail().equals(matcher.group(1))){
                        System.out.println("Please enter a new email!");
                        Profile();
                        return;
                    }
                    System.out.println("Your email has been successfully changed!");
                    input = MyConsole.nextLine() ;
                    LoggedInUser.get(0).setEmail(matcher.group(1));
                    if(input.equalsIgnoreCase("back")){
                        EnterMainMenu();
                        ShowOptionsInMainMenuAndHandleEachOfThem();
                    }
                    return;
                }
            }
        }


    }

    public boolean CheckIfAnyOneIsLoggedIn(){
        if(LoggedInUser.isEmpty()){
            return false ;
        }
        return true ;
    }

    public boolean CheckIfUserNameIsValid(String UserName){
        boolean HasLowerCaseLetter = false ;
        boolean HasUpperCaseLetter = false ;
        boolean HasNumber = false ;
        boolean HasUnderScore = false ;

        for(int i=0 ; i<UserName.length() ; i++){
            for (char c : UserName.toCharArray()) {
                if(Character.isUpperCase(c)){
                    HasUpperCaseLetter=true ;
                }
                if(Character.isLowerCase(c)){
                    HasLowerCaseLetter=true ;
                }
                if(Character.isDigit(c)){
                    HasNumber=true ;
                }
                if(c=='_'){
                    HasUnderScore=true ;
                }
            }
        }

        if(!HasNumber || !HasUnderScore ||!HasUpperCaseLetter ||!HasLowerCaseLetter){
            return false;
        }
        return true ;
    }

    public boolean CheckIfNickNameExist(String NickName){
        for (User user : users) {
            if(user.getNickName().equalsIgnoreCase(NickName)){
                return true ;
            }
        }
        return false ;
    }

    public boolean CheckIfPasswordIsValidForChanging(String Password){
        boolean PassFirstCondition = false ;
        boolean PassSecondCondition = false ;

        if (Password.length() >= 8) {
            PassFirstCondition = true ;
        }

        boolean Has1UpperCaseLetter = false;
        boolean Has1LowerCaseLetter = false;
        boolean Has1NotLetterChar = false;

        for (char c : Password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                Has1LowerCaseLetter = true;
            }
            if (Character.isUpperCase(c)) {
                Has1UpperCaseLetter = true;
            }
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                Has1NotLetterChar = true;
            }
        }

        if (Has1LowerCaseLetter && Has1UpperCaseLetter && Has1NotLetterChar) {
            PassSecondCondition = true ;
        }

        if(!PassFirstCondition || !PassSecondCondition){
            return false ;
        }
        return true ;
    }
    public boolean CheckIfEmailIsOk(String Email){
        if (!Email.matches("(.+)@(.+).com")) {
            return false ;
        }
        return true ;

    }

    public void StartGame(){
        String input ;
        System.out.println("In which Mode do you want to play the game?");
        System.out.println("1. Double Game");
        System.out.println("2. Bet");
        input = MyConsole.nextLine() ;
        if(input.equalsIgnoreCase("back")){
            EnterMainMenu();
            ShowOptionsInMainMenuAndHandleEachOfThem();
            return;
        }
        if(input.equalsIgnoreCase("1")){
            GameModeDeterminer.clear();
            GameModeDeterminer.add("1") ;
            IsStartGameButtonPressed.set(0,"1") ;
            EnterMainMenuForSecondUser();
        }


    }

    public void EnterMainMenuForSecondUser(){
//        ChangeMenuHandler.set(0 , "1") ;
//        ChangeMenuHandler.set(1,"0") ;
        System.out.println("You have entered in SignUp/LogIn Menu successfully.");
    }

    public void ShowOptionsInMainMenuAndHandleEachOfThemFromLogInOfSecondUserToGame(String input){

    }

    public void StartGameFromLogInOfSecondUserToGameMode(String input){
        //String input ;
//        System.out.println("In which Mode do you want to play the game?");
//        System.out.println("1. Double Game");
//        System.out.println("2. Bet");
        //input = MyConsole.nextLine() ;
//        if(input.equalsIgnoreCase("back")){
//            EnterMainMenu();
//            ShowOptionsInMainMenuAndHandleEachOfThem();
//            return;
//        }
        if(input.equalsIgnoreCase("1")) {
            String input1;
//            IsStartGameButtonPressed.set(0,"1") ;
//            EnterMainMenuForSecondUser();
            System.out.println("Please select your characters");

            input1 = MyConsole.nextLine();
            if (input1.equalsIgnoreCase("back")) {
                users.remove(FindUserByUserName(LoggedInOpponent.get(0).getUsername()));
                LoggedInOpponent.clear();
                IsStartGameButtonPressed.set(0, "0");
                EnterMainMenu();
                ShowOptionsInMainMenuAndHandleEachOfThem();
                return;
            }

            if (input1.matches("Select character")) {
                System.out.println("Welcome user " + LoggedInUser.get(0).getUsername() + "! Please choose your game character below : ");
                System.out.println("1. Kebriya");
                System.out.println("2. Khode Khoda");
                System.out.println("3. Payambar");
                System.out.println("4. Bernard");
                input1 = MyConsole.nextLine();
                if (input1.equalsIgnoreCase("1")) {
                    LoggedInUser.get(0).setGameCharacter("Kebriya");
                }
                if (input1.equalsIgnoreCase("2")) {
                    LoggedInUser.get(0).setGameCharacter("Khode Khoda");
                }
                if (input1.equalsIgnoreCase("3")) {
                    LoggedInUser.get(0).setGameCharacter("Payambar");
                }
                if (input1.equalsIgnoreCase("4")) {
                    LoggedInUser.get(0).setGameCharacter("Bernard");
                }
                System.out.println("Your game character has been successfully created!");

                System.out.println("Welcome user " + LoggedInOpponent.get(0).getUsername() + "! Please choose your game character below : ");
                System.out.println("1. Kebriya");
                System.out.println("2. Khode Khoda");
                System.out.println("3. Payambar");
                System.out.println("4. Bernard");
                input1 = MyConsole.nextLine();
                if (input1.equalsIgnoreCase("1")) {
                    LoggedInOpponent.get(0).setGameCharacter("Kebriya");
                }
                if (input1.equalsIgnoreCase("2")) {
                    LoggedInOpponent.get(0).setGameCharacter("Khode Khoda");
                }
                if (input1.equalsIgnoreCase("3")) {
                    LoggedInOpponent.get(0).setGameCharacter("Payambar");
                }
                if (input1.equalsIgnoreCase("4")) {
                    LoggedInOpponent.get(0).setGameCharacter("Bernard");
                }
                System.out.println("Your game character has been successfully created!");
                System.out.println();
                System.out.println();
                System.out.println();

                System.out.println("You are entered in gameplay!");
                System.out.println("--------------------------------------------------------------------------------------------------------------");

                ShowGamePlay() ;

            }
        }


    }

    public String PrintGameMode(String Number){
        if(Number.equalsIgnoreCase("1")){
            return "Double Game" ;
        }
        if(Number.equalsIgnoreCase("2")){
            return "Bet" ;
        }
        return null ;
    }

    public void ShowGamePlay(){
        String input ;
        String input1 ;
        RandomDestroyCell(LoggedInUser.get(0)) ;
        RandomDestroyCell(LoggedInOpponent.get(0)) ;

        AddRandomCardsToUserBattleDeck(LoggedInUser.get(0)) ;
        AddRandomCardsToUserBattleDeck(LoggedInOpponent.get(0)) ;


//        ShowUserGameInformation(LoggedInUser.get(0)) ;
//        ShowUserGameInformation(LoggedInOpponent.get(0)) ;
//        TurnCounter.clear();
//        TurnCounter.add("2") ;
        WhichUserPlayGameRandomDeterminer();
        PlayingGameProcess();
//        while((input = MyConsole.nextLine()).matches("Select card number <(.*)> player <(.*)>")) {
//            Matcher matcher = getCommandMatcher(input, "Select card number <(.*)> player <(.*)>");
//            if (matcher.find()) {
//                ShowCardsDescriptionAfterSelecting(matcher.group(1) , matcher.group(2)) ;
//            }
//        }

//        while((input1 = MyConsole.nextLine()).matches("Placing card number (.*) in block (.*)")){
//            Matcher matcher = getCommandMatcher(input1, "Placing card number (.*) in block (.*)");
//            if (matcher.find()) {
//                PutCardsInTheBattleField(matcher.group(1) , matcher.group(2) , WhoseTurnReturn(WhoseTurn));
//                ShowUsersBattleField(WhoseTurnReturn(WhoseTurn));
//            }
//        }

//        if(input.matches("Placing card number (.*) in block (.*)")){
//            Matcher matcher = getCommandMatcher(input, "Placing card number (.*) in block (.*)");
//            if (matcher.find()) {
//                PutCardsInTheBattleField(matcher.group(1) , matcher.group(2) , WhoseTurnReturn(WhoseTurn));
//                ShowUsersBattleField(WhoseTurnReturn(WhoseTurn));
//            }
//        }







    }

    public void PlayingGameProcess(){
        int TurnCounter1 ;
        int TurnCounter2 ;
        ShowUserGameInformation(LoggedInUser.get(0)) ;
        ShowUserGameInformation(LoggedInOpponent.get(0)) ;
        System.out.println(WhoseTurnReturn(WhoseTurn).getUsername()+" With character "+WhoseTurnReturn(WhoseTurn).getGameCharacter()+" must play!");
        String input ;
        while(true){
            boolean CanPassToNextIf = true ;
            input = MyConsole.nextLine() ;
            if (input.matches("Select card number <(.*)> player <(.*)>")) {
                Matcher matcher = getCommandMatcher(input, "Select card number <(.*)> player <(.*)>");
                if (matcher.find()) {
                    ShowCardsDescriptionAfterSelecting(matcher.group(1) , matcher.group(2)) ;
                }
            }
            if(input.matches("Placing card number (.*) in block (.*)")){
                Matcher matcher = getCommandMatcher(input, "Placing card number (.*) in block (.*)");
                if (matcher.find()) {
                        if(WhoseTurn.get(0).equals("1")){
                        PutCardsInTheBattleField(matcher.group(1) , matcher.group(2) , WhoseTurnReturn(WhoseTurn));
                        CheckCorrespondingCellAndDecideWhichCardShouldVeRemovedFromBattleField(WhoseTurnReturn(WhoseTurn) , WhoseNotTurnReturn(WhoseTurn) , matcher.group(2) , matcher.group(1));
                        RemoveCardFromBattleDeckAfterPlacing(matcher.group(1) , WhoseTurnReturn(WhoseTurn));
                        ReplaceCardAfterRemoving(WhoseTurnReturn(WhoseTurn));
                        ShowUserGameInformation(LoggedInUser.get(0)) ;
                        ShowUserGameInformation(LoggedInOpponent.get(0)) ;
                        TurnCounter1 = WhoseTurnReturn(WhoseTurn).getPlayedTurnNumber()+1 ;
                        WhoseTurnReturn(WhoseTurn).setPlayedTurnNumber(TurnCounter1);
                        WhoseTurn.set(0 , "0") ;
                        WhoseTurn.set(1 , "1") ;
                        System.out.println(WhoseTurnReturn(WhoseTurn).getUsername()+" With character "+WhoseTurnReturn(WhoseTurn).getGameCharacter()+" must play!");
                        CanPassToNextIf = false ;
                    }
                    if(WhoseTurn.get(0).equals("0") && CanPassToNextIf){
                        PutCardsInTheBattleField(matcher.group(1) , matcher.group(2) , WhoseTurnReturn(WhoseTurn));
                        CheckCorrespondingCellAndDecideWhichCardShouldVeRemovedFromBattleField(WhoseTurnReturn(WhoseTurn) , WhoseNotTurnReturn(WhoseTurn) , matcher.group(2) , matcher.group(1));
                        RemoveCardFromBattleDeckAfterPlacing(matcher.group(1) , WhoseTurnReturn(WhoseTurn));
                        ReplaceCardAfterRemoving(WhoseTurnReturn(WhoseTurn));
                        ShowUserGameInformation(LoggedInUser.get(0)) ;
                        ShowUserGameInformation(LoggedInOpponent.get(0)) ;
                        TurnCounter2 = WhoseTurnReturn(WhoseTurn).getPlayedTurnNumber()+1 ;
                        WhoseTurnReturn(WhoseTurn).setPlayedTurnNumber(TurnCounter2);
                        WhoseTurn.set(0 , "1") ;
                        WhoseTurn.set(1 , "0") ;
                        System.out.println(WhoseTurnReturn(WhoseTurn).getUsername()+" With character "+WhoseTurnReturn(WhoseTurn).getGameCharacter()+" must play!");
                    }
                }
            }
        }
    }

    public void ShowUsersBattleFieldAtTheBeginning(User user){
        int count=1 ;
        for (Cards cards : user.BattleField) {
            if(cards!=null){
                System.out.print("0("+count+") ");
            }
            else {
                System.out.print("*("+count+") ");
            }
            count++ ;
        }
        System.out.println();

    }

    public void ShowUsersBattleField(User user){
        int count=1 ;
        for (Cards cards : user.BattleField) {
            if(cards!=null){
                if(cards.getName().equals("0")) {
                    System.out.print("0(" + count + ") ");
                }
                else{
                    System.out.print(cards.getName()+"(" + count +"/"+TotalDamageDivisionToDuration(cards)+"/"+cards.getCard_DefenceOrAttack()+") ");

                }
            }
            else{
                System.out.print("*("+count+") ");
            }
            count++ ;

        }
        System.out.println();
    }

    public void ShowUsersBattleDeck(User user){
        System.out.println("Damage/Healing Cards : ");

        for(int i=0 ; i<user.BattleDeckNormalCards.size() ; i++){
            System.out.println(i+1+". Name :"+user.BattleDeckNormalCards.get(i).getName()+" Duration :"+user.BattleDeckNormalCards.get(i).getDuration()+" Player Damage :"+user.BattleDeckNormalCards.get(i).getPlayerDamage()+" attack/defense point :"+user.BattleDeckNormalCards.get(i).getCard_DefenceOrAttack());
        }

        System.out.println("Spell Cards : ");
        for(int i=0 ; i<user.BattleDeckSpells.size() ; i++){
            System.out.println(5+". Name :"+user.BattleDeckSpells.get(i));
        }
        System.out.println();
    }

    public void AddRandomCardsToUserBattleDeck(User user){
        Random random = new Random();
        Random random1 = new Random();

        int randomNumber = random.nextInt(2) ;
        if(randomNumber==0){
            for(int i=0 ; i<5 ; i++) {
                int randomNumber1 = random.nextInt(17) ;
                user.BattleDeckNormalCards.add(user.NormalCards.get(randomNumber1)) ;
            }
        }
        if(randomNumber==1){
            for(int i=0 ; i<4 ; i++) {
                int randomNumber1 = random.nextInt(17) ;
                user.BattleDeckNormalCards.add(user.NormalCards.get(randomNumber1)) ;
            }
            int randomNumber1 = random.nextInt(3) ;
            user.BattleDeckSpells.add(user.Spells.get(randomNumber1)) ;
        }
    }


    public ArrayList RandomDestroyCell(User user){
        Random random = new Random();
        for(int i=0 ; i<21 ; i++){
            Cards card = new Cards("0" , "0" , "1" , "0" , "0" , "0" , "0") ;
            user.BattleField.add(card) ;
        }

        for(int i=0 ; i<1 ; i++){
            int randomNumber = random.nextInt(21) ;
            user.BattleField.set(randomNumber , null) ;
        }

        return user.BattleField ;
    }

    public int GetUserAppliedDamage(User user){
        int TotalDamage = 0;
        for (Cards cards : user.BattleField) {
            if(cards!=null && !cards.getName().equals("Empty Card")){
                TotalDamage+=Integer.parseInt(cards.getPlayerDamage()) ;
            }
        }
        return TotalDamage ;
    }

    public void ShowUserAppliedDamage(User user){
        System.out.println("Applied Damage : "+GetUserAppliedDamage(user));
    }

    public void ShowUserRemainingRound(User user){
        System.out.println("Remaining Round :"+user.getRemainingRound());
    }
    public void ShowUserHP(User user){
        System.out.println("HP :"+user.getHP());
    }
    public void ShowUserCharacter(User user){
        System.out.println("User Character :"+user.getGameCharacter());
        System.out.println();
        System.out.println();
    }
    public void ShowUserGameInformation(User user){
        ShowUsersBattleField(user);
        ShowUsersBattleDeck(user);
        ShowUserAppliedDamage(user);
        ShowUserRemainingRound(user);
        ShowUserHP(user);
        ShowUserCharacter(user);
    }

    public void WhichUserPlayGameRandomDeterminer(){
        //user create -u <Amirreza_83> -p <@Vasheqberg101325> <@Vasheqberg101325> -email <amirrezavasheghani@gmail.com> -n <Vasheqberg>System.out.println("--------------------------------------------------------------------------------------------------------------");
        Random random = new Random();
        int randomNumber = random.nextInt(2) ;
        if(randomNumber==0){
            WhoseTurn.clear();
            WhoseTurn.add("1") ;
            WhoseTurn.add("0") ;
            //System.out.println("User with username "+LoggedInUser.get(0).getUsername()+" and with character "+LoggedInUser.get(0).getGameCharacter()+" must play!");
        }
        if(randomNumber==1){
            WhoseTurn.clear();
            WhoseTurn.add("0") ;
            WhoseTurn.add("1") ;
            //System.out.println("User with username "+LoggedInOpponent.get(0).getUsername()+" and with character "+LoggedInOpponent.get(0).getGameCharacter()+" must play!");

        }
    }

    public User WhoseTurnReturn(ArrayList a){
        if(a.get(0).equals("1")){
            return LoggedInUser.get(0) ;
        }
        if(a.get(0).equals("0")){
            return LoggedInOpponent.get(0) ;
        }
        return null ;
    }
    public User WhoseNotTurnReturn(ArrayList a){
        if(a.get(0).equals("1")){
            return LoggedInOpponent.get(0) ;
        }
        if(a.get(0).equals("0")){
            return LoggedInUser.get(0) ;
        }
        return null ;
    }

    public void ShowCardsDescriptionAfterSelecting(String CardNumber , String Player){
        int i = Integer.parseInt(CardNumber) ;
        if(Player.equals("1")){
            if(i<5){
                System.out.println("Name :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getName()+" Card_DefenceOrAttack :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getCard_DefenceOrAttack()+" Duration :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getDuration()+" PlayerDamage :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getPlayerDamage()+" UpgradeLevel :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getUpgradeLevel()+" UpgradeCost :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getUpgradeCost()+" Level :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getLevel());
            }
            if(i==5){
                if(LoggedInUser.get(0).BattleDeckSpells.isEmpty()){
                    System.out.println("Name :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getName()+" Card_DefenceOrAttack :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getCard_DefenceOrAttack()+" Duration :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getDuration()+" PlayerDamage :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getPlayerDamage()+" UpgradeLevel :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getUpgradeLevel()+" UpgradeCost :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getUpgradeCost()+" Level :"+LoggedInUser.get(0).BattleDeckNormalCards.get(i-1).getLevel());

                }
                else{
                    System.out.println("Name :"+LoggedInUser.get(0).BattleDeckSpells.get(0)+" Description : "+PrintSpellCardsProperty(LoggedInUser.get(0).BattleDeckSpells.get(0)));
                }
            }

        }

        if(Player.equals("2")){
            if(i<5){
                System.out.println("Name :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getName()+" Card_DefenceOrAttack :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getCard_DefenceOrAttack()+" Duration :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getDuration()+" PlayerDamage :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getPlayerDamage()+" UpgradeLevel :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getUpgradeLevel()+" UpgradeCost :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getUpgradeCost()+" Level :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getLevel());
            }
            if(i==5){
                if(LoggedInOpponent.get(0).BattleDeckSpells.isEmpty()){
                    System.out.println("Name :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getName()+" Card_DefenceOrAttack :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getCard_DefenceOrAttack()+" Duration :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getDuration()+" PlayerDamage :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getPlayerDamage()+" UpgradeLevel :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getUpgradeLevel()+" UpgradeCost :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getUpgradeCost()+" Level :"+LoggedInOpponent.get(0).BattleDeckNormalCards.get(i-1).getLevel());

                }
                else{
                    System.out.println("Name :"+LoggedInOpponent.get(0).BattleDeckSpells.get(0)+" Description : "+PrintSpellCardsProperty(LoggedInOpponent.get(0).BattleDeckSpells.get(0)));
                }
            }

        }
    }

    public String PrintSpellCardsProperty(String Name){
        if(Name.equalsIgnoreCase("Magnet")){
            return "It occupies one cell and if it gets put under a cell in opponent field , if the card in that specific cell has duration equals to one , it pulls it toward itself battle field." ;
        }
        if(Name.equalsIgnoreCase("Accelerator")){
            return "It occupies two cell and it increases the damage of the all previous cards by four" ;
        }
        if(Name.equalsIgnoreCase("Booster")){
            return "It occupies two cell and it increases the number of the remaining rounds by one" ;
        }
        if(Name.equalsIgnoreCase("Poison")){
            return  "It occupies two cell and it decreases the number of the remaining rounds by one" ;
        }
        if(Name.equalsIgnoreCase("Shield")){
            return "If it get put against any card, it destroys its damage." ;
        }
        if(Name.equalsIgnoreCase("Heal")){
            return "If it get put against any card, it destroys its damage." ;
        }
        if(Name.equalsIgnoreCase("Power Booster")){
            return "With playing this card a random played card will be buffed" ;
        }
        if(Name.equalsIgnoreCase("Hole Place Changer")){
            return "This card can change the place of the whole for both users randomly" ;
        }
        if(Name.equalsIgnoreCase("Repair")){
            return "This card can be played on the wholes and repairs them." ;
        }
        if(Name.equalsIgnoreCase("Round Reducer")){
            return "This card will reduce the number of the remaining round of the opponent by one." ;
        }
        if(Name.equalsIgnoreCase("Delete Card From Opponent Deck")){
            return "With playing this card a card from opponent deck will be deleted randomly and at that round it comes to the other user deck" ;
        }
        if(Name.equalsIgnoreCase("Weaken Opponent Card")){
            return "Two card from opponent will be  chosen and one of them gets damage reduced and the other gets power reduced" ;
        }
        if(Name.equalsIgnoreCase("Copy")){
            return "it will copy one card from your deck card and you will be six cards in that round" ;
        }
        if(Name.equalsIgnoreCase("Hider")){
            return "it will cause that the opponent doesnt see the cards next round." ;
        }
        return null ;
    }

    public int WhoseTurnReturnAsOneAndTwo(){
        if(WhoseTurn.get(0).equals("1")){
            return 1 ;
        }
        if(WhoseTurn.get(0).equals("0")){
            return 2 ;
        }
        return 0 ;
    }

    public void ShowDamageAndAttackDefencePointInBattleField(){

    }

    public void PutCardsInTheBattleField(String CardNumber , String CellNumber , User user){
        int i = Integer.parseInt(CardNumber) ;
        int k = Integer.parseInt(CellNumber) ;
            if(i<5){
                for(int j=0 ; j<Integer.parseInt(user.BattleDeckNormalCards.get(i-1).getDuration()) ; j++){
                    user.BattleField.set(k-1+j,user.BattleDeckNormalCards.get(i-1)) ;
                }

            if(i==5){
                if(user.BattleDeckSpells.isEmpty()){
                    for(int j=0 ; j<Integer.parseInt(user.BattleDeckNormalCards.get(i-1).getDuration()) ; j++){
                        user.BattleField.set(k-1+j,user.BattleDeckNormalCards.get(i-1)) ;
                    }
                }
                else{
                }
            }

        }
    }

    public String TotalDamageDivisionToDuration(Cards card){
        int duration = Integer.parseInt(card.getDuration()) ;
        int playerDamage = Integer.parseInt(card.getPlayerDamage()) ;
        return String.valueOf(playerDamage/duration) ;
    }

    public void RemoveCardFromBattleDeckAfterPlacing(String CardNumber , User user){
        int j= Integer.parseInt(CardNumber) ;
        if(j<5){
            user.BattleDeckNormalCards.remove(j-1) ;
        }
        if(j==5){
            if(user.BattleDeckSpells.isEmpty()){
                user.BattleDeckNormalCards.remove(4) ;
            }
            else{
                user.BattleDeckSpells.remove(0) ;
            }
        }
    }
    public void ReplaceCardAfterRemoving(User user){
        Random random = new Random() ;
        int randomNumber = user.NormalCards.size() ;
        int randomNumber1 = user.Spells.size() ;

        if(!user.BattleDeckSpells.isEmpty()){
            user.BattleDeckNormalCards.add(user.NormalCards.get(randomNumber-1)) ;
        }
        else{
            user.BattleDeckSpells.add(user.Spells.get(randomNumber1-1)) ;
        }
    }

    public void IntegrateSpells(ArrayList<String> SpellsPhase0 , ArrayList<String> BuiltIn){
        IntegratedSpells.clear();
        for (String string : SpellsPhase0) {
            IntegratedSpells.add(string) ;
        }
        for (String string : BuiltIn) {
            IntegratedSpells.add(string) ;
        }
    }

    public void CheckCorrespondingCellAndDecideWhichCardShouldVeRemovedFromBattleField(User LastPlayed , User NextPlayed , String CellNumber , String CardNumber){
        int cellNumber = Integer.parseInt(CellNumber) ;
        int cardNumber = Integer.parseInt(CardNumber) ;
        for(int i=cellNumber-1 ; i<cellNumber-1+Integer.parseInt(LastPlayed.BattleDeckNormalCards.get(cardNumber-1).getDuration()) ; i++){
            if(NextPlayed.BattleField.get(i)!=null && !NextPlayed.BattleField.get(i).getName().equals("0")){
                if(Integer.parseInt(LastPlayed.BattleField.get(i).getCard_DefenceOrAttack()) > Integer.parseInt(NextPlayed.BattleField.get(i).getCard_DefenceOrAttack())){
                    NextPlayed.BattleField.set(i,null) ;
                }
            }
            if(NextPlayed.BattleField.get(i)!=null && !NextPlayed.BattleField.get(i).getName().equals("0")){
                if(Integer.parseInt(LastPlayed.BattleField.get(i).getCard_DefenceOrAttack()) < Integer.parseInt(NextPlayed.BattleField.get(i).getCard_DefenceOrAttack())){
                    LastPlayed.BattleField.set(i,null) ;
                }
            }
            if(NextPlayed.BattleField.get(i)!=null && !NextPlayed.BattleField.get(i).getName().equals("0")){
                if(Integer.parseInt(LastPlayed.BattleField.get(i).getCard_DefenceOrAttack()) == Integer.parseInt(NextPlayed.BattleField.get(i).getCard_DefenceOrAttack())){
                    LastPlayed.BattleField.set(i,null) ;
                    NextPlayed.BattleField.set(i,null) ;
                }
            }
        }

    }
}
