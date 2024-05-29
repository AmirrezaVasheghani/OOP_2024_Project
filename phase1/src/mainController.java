import java.util.Scanner;
import java.util.regex.Matcher;

public class mainController {

    private Scanner scanner;

    public mainController() {
        scanner = new Scanner(System.in);
    }

    public void begin (){

        String input;
        Matcher[] matchers = new Matcher[Inputs.values().length];

        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")){
            for (int i = 0 ; i < matchers.length ; i++){
                matchers[i] = Inputs.values()[i].getCommand().matcher(input);
            }
            if (matchers[0].find()){
                
            }
        }
    }
}
