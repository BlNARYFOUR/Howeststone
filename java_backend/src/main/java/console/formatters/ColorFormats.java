package console.formatters;

public class ColorFormats {
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
