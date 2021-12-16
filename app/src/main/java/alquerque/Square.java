package alquerque;

import lombok.Getter;
import lombok.Setter;

public class Square {
    @Getter
    private boolean isPlayable;
    @Getter
    @Setter
    private boolean isOccupied;
    @Getter
    @Setter
    private boolean isWhite;
    @Getter
    @Setter
    private boolean isQueen;

    public Square(boolean isPlayable, boolean isOccupied, boolean isWhite) {
        this.isPlayable = isPlayable;
        this.isOccupied = isOccupied;
        this.isWhite = isWhite;
        this.isQueen = false;
    }
}
