package alquerque;

import alquerque.utils.Displayable;
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
        this.squares = new Square[10][10];
        init();
    }

    /**
     * Init the board with pawns at their initial positions (init squares)
     */
    public void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                squares[i][j] = new Square((i + j) % 2 == 1, i != 4 && i != 5, i > 5);
            }
        }
    }

    public Square squareAt(int x, int y) {
        return this.squares[x][y];
    }

    public Square squareAt(Position pos) {
        return this.squareAt(pos.x(), pos.y());
    }

    /**
     * Clear the console and display the board
     */
    public void display() {
        Displayable.clearConsole();

        boolean White = currentPlayer.isWhite();
        System.out.println(White ? "  0 1 2 3 4 5 6 7 8 9 " : "  9 8 7 6 5 4 3 2 1 0 ");

        for (int i = 0; i < 10; i++) {
            int xi = White ? i : 9 - i;
            System.out.print(Character.toString('A' + xi) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(squares[xi][White ? j : 9 - j]);
            }
            System.out.println("");
        }
    }
}
