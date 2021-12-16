package alquerque;

import lombok.Getter;

public class Board {
    @Getter
    private Player player1;
    @Getter
    private Player player2;
    @Getter
    private Square[][] squares;
    @Getter
    private Player currentPlayer;

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }
}

