package alquerque;

import alquerque.utils.Displayable;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Square {

    public static final String WHITE_PAWN = Displayable.WHITE + Displayable.FILLED_CIRCLE;
    public static final String WHITE_QUEEN = Displayable.GREEN + Displayable.FILLED_CIRCLE;
    public static final String BLACK_PAWN = Displayable.BROWN + Displayable.FILLED_CIRCLE;
    public static final String BLACK_QUEEN = Displayable.RED + Displayable.FILLED_CIRCLE;
    public static final String PLAYABLE = Displayable.BROWN_BACKGROUND;
    public static final String NON_PLAYABLE = Displayable.WHITE_BACKGROUND + Displayable.SPACE;

    private boolean isPlayable;
    @Setter
    private boolean isOccupied;
    @Setter
    private boolean isWhite;
    @Setter
    private boolean isQueen;

    public Square(boolean isPlayable, boolean isOccupied, boolean isWhite) {
        this.isPlayable = isPlayable;
        this.isOccupied = isOccupied;
        this.isWhite = isWhite;
        this.isQueen = false;
    }

    @Override
    public String toString() {
        return (isPlayable ? PLAYABLE : NON_PLAYABLE)
                + (isQueen ? (isWhite ? WHITE_PAWN : BLACK_PAWN) : (isWhite ? WHITE_QUEEN : BLACK_QUEEN))
                + Displayable.RESET;
    }
}
