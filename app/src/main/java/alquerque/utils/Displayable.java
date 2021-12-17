package alquerque.utils;

public class Displayable {

    private Displayable() {
        // Utility classes should not be instanciated
    }

    // clear and move cursor
    public static final String CLEAR = "\033c"; // use "\033[H\033[2J" to clear without reseting

    // reset color an style
    public static final String RESET = "\033[0m";

    // colors
    public static final String YELLOW = color(237, 210, 111);
    public static final String YELLOW2 = color(252, 215, 81);
    public static final String BROWN = color(61, 30, 8);
    public static final String GREEN = color(109, 212, 0);
    public static final String RED = color(235, 0, 27);
    public static final String WHITE_BACKGROUND = backgroundColor(219, 202, 125);
    public static final String BROWN_BACKGROUND = backgroundColor(176, 92, 19);

    // symbols
    public static final String FILLED_CIRCLE = "\u2b24\u200a"; // ⬤ + hair space (to fix display bug in some terminals)
    public static final String FILLED_ROUNDED_O = "\u24ff\u200a"; // ⓿ + hair space
    public static final String FILLED_ROUNDED_D = "🅓\u200a"; // 🅓 + hair space
    public static final String PAWN = "⛂ ";
    public static final String BOLD_PAWN = "\033[1m⛂ ";
    public static final String QUEEN = "⛃ ";
    public static final String BOLD_QUEEN = "\033[1m⛃ ";
    public static final String SPACE = "  ";

    // background color generator
    public static String backgroundColor(int r, int g, int b) {
        return "\033[48;2;" + r + ";" + g + ";" + b + "m";
    }

    // foreground color generator
    public static String color(int r, int g, int b) {
        return "\033[38;2;" + r + ";" + g + ";" + b + "m";
    }

    public static void clearConsole() {
        System.out.print(CLEAR);
    }

}