import java.util.regex.Pattern;

public enum Inputs {
    USER_CREATE ("/^\\s*user\\s+create\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s*$"),
    ;

    private Pattern command;

    Inputs(String pattern){
        this.command = Pattern.compile(pattern);
    }

    public Pattern getCommand() {
        return command;
    }
}
