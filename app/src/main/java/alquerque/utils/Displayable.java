package alquerque.utils;

public class Displayable {

    private Displayable() {
        // Utility classes should not be instanciated
    }

    // reset the color
    public static final String RESET = "\033[0m";

    // colors
    public static final String WHITE = color(237, 210, 111);
    public static final String GREEN = color(109, 212, 0);
    public static final String BROWN = color(71, 35, 5);
    public static final String RED = color(235, 0, 27);
    public static final String WHITE_BACKGROUND = backgroundColor(237, 210, 111);
    public static final String BROWN_BACKGROUND = backgroundColor(176, 92, 19);

    // symbols
    public static final String FILLED_CIRCLE = "\u2b24\u200a"; // ⬤ + hair space (to fix display bug in some terminals)
    public static final String SPACE = "  ";

    // background color generator
    public static String backgroundColor(int r, int g, int b) {
        return "\033[48;2;" + r + ";" + g + ";" + b + "m";
    }

    // foreground color generator
    public static String color(int r, int g, int b) {
        return "\033[38;2;" + r + ";" + g + ";" + b + "m";
    }

}