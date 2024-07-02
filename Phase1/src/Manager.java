import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Manager {
    ArrayList <TempUser> tempUsers = new ArrayList<>() ;
    ArrayList <User> users = new ArrayList<>() ;

    private Scanner MyConsole = new Scanner(System.in);

    public void CheckIfUserAndPasswordIsValidForSignUp(Matcher matcher){
        //Empty Field

        if(matcher.group(1).isEmpty() || matcher.group(2).isEmpty() || matcher.group(3).isEmpty() || matcher.group(4).isEmpty()){
            System.out.println("The information entered is not complete! , please try again.");
            return;
        }


        //Invalid Username

        boolean HasLowerCaseLetter = false ;
        boolean HasUpperCaseLetter = false ;
        boolean HasNumber = false ;
        boolean HasUnderScore = false ;

        for(int i=0 ; i<matcher.group(1).length() ; i++){
            for (char c : matcher.group(1).toCharArray()) {
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
            System.out.println("The username is invalid! , please try again.");
            return;
        }

        //Duplicated Username
        //?



        //Weak Password

        if(matcher.group(2).length()<8){
            System.out.println("The password must have atleast 8 characters!");
            return;
        }
        boolean Has1UpperCaseLetter = false ;
        boolean Has1LowerCaseLetter = false ;
        boolean Has1NotLetterChar = false ;

        for (char c : matcher.group(2).toCharArray()) {
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



        //Invalid email
        if(!matcher.group(4).matches("(.+)@(.+).com")){
            System.out.println(matcher.group(4));
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

        TempUser tempuser = new TempUser(matcher.group(1) , matcher.group(2) , matcher.group(5) , matcher.group(4) , 1) ;
        tempUsers.add(tempuser) ;

    }

    public void SetPasswordRecoveryQuestionToTempUsers(Matcher matcher) {
        if (!matcher.group(2).equals(matcher.group(3))) {
            System.out.println("The password is not confirmed!");
        }
        else {
            for (TempUser tempUser : tempUsers) {
                if (tempUser.UserControl == 1) {
                    User user = new User(tempUser.Username, tempUser.Password, tempUser.NickName, tempUser.Email, matcher.group(2), matcher.group(1));
                    users.add(user) ;
                    System.out.println("The password recovery question is successfully saved. ");
                }
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

        }
    }

    public void CheckIfUserForSignUpWithRandomPassword(Matcher matcher){
        //Empty Field

        if(matcher.group(1).isEmpty() || matcher.group(2).isEmpty() || matcher.group(3).isEmpty()){
            System.out.println("The information entered is not complete! , please try again.");
            return;
        }


        //Invalid Username

        boolean HasLowerCaseLetter = false ;
        boolean HasUpperCaseLetter = false ;
        boolean HasNumber = false ;
        boolean HasUnderScore = false ;

        for(int i=0 ; i<matcher.group(1).length() ; i++){
            for (char c : matcher.group(1).toCharArray()) {
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
            System.out.println("The username is invalid! , please try again.");
            return;
        }



        //Duplicated Username
        //?


        //Invalid email
        if(!matcher.group(2).matches("(.+)@(.+).com")){
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
        System.out.println("Your random password: "+password);
        System.out.println("Please enter your password :");

        // Confirm the password
        String input ;
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
        TempUser tempuser = new TempUser(matcher.group(1) , String.valueOf(password) , matcher.group(3) , matcher.group(2) , 1) ;
        tempUsers.add(tempuser) ;
    }


    // profile menu
    public void showInformation(User user){
        profileController profileController = user.getProfileController();
        profileController.showInformation();
    }
    public void changePass(User user, String password){
        profileController profileController = user.getProfileController();
        profileController.setPassword(password);
    }
}
