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

    public Square squareAt(int x, int y) {
        return this.squares[x][y];
    }

    public Square squareAt(Position pos) {
        return this.squareAt(pos.x(), pos.y());
    }
}

