package formatters;

public final class ColorFormats {
    // \033 means "format the next" number after [ means what to format and m is the end of the format.
    // look at following link for more info
    // -> TODO: http://www.santhoshreddymandadi.com/java/coloring-java-output-on-console.html
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String MAGENTA = "\033[35m";
    private static final String CYAN = "\033[36m";
    private static final String GREY = "\033[37m";

    private ColorFormats() { }

    public static String red(String str) {
        return RED + str + RESET;
    }

    public static String green(String str) {
        return GREEN + str + RESET;
    }

    public static String yellow(String str) {
        return YELLOW + str + RESET;
    }

    public static String blue(String str) {
        return BLUE + str + RESET;
    }

    public static String magenta(String str) {
        return MAGENTA + str + RESET;
    }

    public static String cyan(String str) {
        return CYAN + str + RESET;
    }

    public static String grey(String str) {
        return GREY + str + RESET;
    }

}
