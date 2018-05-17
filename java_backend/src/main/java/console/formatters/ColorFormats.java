package console.formatters;

public class ColorFormats {
    // \033 means "format the next" number after [ means what to format and m is the end of the format.
    // look at following link for more info -> TODO: http://www.santhoshreddymandadi.com/java/coloring-java-output-on-console.html
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";

    public static String red(String str) {
        return  RED + str + RESET;
    }

    public static String green(String str) {
        return GREEN + str + RESET;
    }
}
