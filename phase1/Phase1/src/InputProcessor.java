import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputProcessor {
    private Scanner MyConsole = new Scanner(System.in);


    // یه کلاس Main.Manager هم زدم تا یه سری تابع ها که شلوغ پلوغه بره اونور این کلاس خلوت تر شه
    Manager manager = new Manager();


    // اینجا Matcher زدم تا با ریجکس ورودی بگیریم.
    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }


    public void run() {
        String input;

        while (!(input = MyConsole.nextLine()).equals("Exit")) {

            if (input.matches("user create -u <(.*)> -p <(.*)> <(.*)> -email <(.*)> -n <(.*)>")) {
                Matcher matcher = getCommandMatcher(input, "user create -u <(.*)> -p <(.*)> <(.*)> -email <(.*)> -n <(.*)>");
                if (matcher.find()) {
                    manager.CheckIfUserAndPasswordIsValidForSignUp(matcher);
                    continue;
                }
            }
            else if (input.matches("question pick -q <(.*)> -a <(.*)> -c <(.*)>")) {
                Matcher matcher = getCommandMatcher(input, "question pick -q <(.*)> -a <(.*)> -c <(.*)>");
                if (matcher.find()) {
                    manager.SetPasswordRecoveryQuestionToTempUsers(matcher);
                    continue;
                }
            }
            else if (input.matches("user create -u <(.*)> -p random -email <(.*)> -n <(.*)>")) {
                Matcher matcher = getCommandMatcher(input, "user create -u <(.*)> -p random -email <(.*)> -n <(.*)>");
                if (matcher.find()) {
                    manager.CheckIfUserForSignUpWithRandomPassword(matcher);
                    continue;
                }
            }
            else if (input.matches("user login -u <(.*)> -p <(.*)>")) {
                Matcher matcher = getCommandMatcher(input, "user login -u <(.*)> -p <(.*)>");
                if (matcher.find()) {
                    manager.LoginCheckError(matcher);
                    continue;
                }
            }
            else if (input.matches("logout")) {
                Matcher matcher = getCommandMatcher(input, "logout");
                if (matcher.find()) {
                    manager.Logout();
                    continue;
                }
            }
            else if (input.matches("Forgot my password -u <(.*)>")) {
                Matcher matcher = getCommandMatcher(input, "Forgot my password -u <(.*)>");
                if (matcher.find()) {
                    manager.f(matcher);
                    continue;
                }
            }
        }
    }
}