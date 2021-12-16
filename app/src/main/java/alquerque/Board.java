package alquerque;

import lombok.Getter;

public class Board {
    @Getter
    private Player player1;
    @Getter
    private Player player2;
    @Getter
    private Square[][] squares;
}

