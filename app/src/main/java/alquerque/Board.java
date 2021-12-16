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
        this.squares = new Square[10][10];
        init();
    }

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

    public void display() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
        for (int i = 0; i < 10; i++) {
            System.out.print(Character.toString('A' + i) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(squares[i][j]);
            }
            System.out.println("");
        }
    }
}
