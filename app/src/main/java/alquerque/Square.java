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

    private boolean playable;
    @Setter
    private boolean occupied;
    @Setter
    private boolean white;
    @Setter
    private boolean queen;

    public Square(boolean isPlayable, boolean isOccupied, boolean isWhite) {
        this.playable = isPlayable;
        this.occupied = isOccupied;
        this.white = isWhite;
        this.queen = false;
    }

    @Override
    public String toString() {
        return !playable ? NON_PLAYABLE
                : PLAYABLE + (queen ? (white ? WHITE_PAWN : BLACK_PAWN) : (white ? WHITE_QUEEN : BLACK_QUEEN))
                        + Displayable.RESET;
    }
}
