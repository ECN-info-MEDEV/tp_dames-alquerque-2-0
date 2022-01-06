package alquerque;

import java.util.ArrayList;
import java.util.List;
import alquerque.utils.Displayable;
import lombok.Getter;
import lombok.var;

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

        boolean white = currentPlayer.isWhite();
        System.out.println(white ? "  0 1 2 3 4 5 6 7 8 9 " : "  9 8 7 6 5 4 3 2 1 0 ");

        for (int i = 0; i < 10; i++) {
            int xi = white ? i : 9 - i;
            System.out.print(Character.toString('A' + xi) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(squares[xi][white ? j : 9 - j]);
            }
            System.out.println("");
        }
    }

    /** Indicates if a movement is possible as a first move. */
    public boolean isPossibleUnmoved(Move movement) {
        var start = movement.start();
        var end = movement.end();
        var x = start.x();
        var y = start.y();
        // "forward" is in the -x direction for white, and the +x direction for black.
        var isWhite = currentPlayer.isWhite();
        var dx = isWhite ? -1 : 1;

        // forward-right and forward-left squares.
        var fr = new Position(x + dx, y + 1);
        var fl = new Position(x + dx, y - 1);

        // backward-right and backward-left squares.
        var br = new Position(x - dx, y + 1);
        var bl = new Position(x - dx, y - 1);

        // Indirect diagonal squares.
        var fr2 = new Position(x + 2 * dx, y + 2);
        var fl2 = new Position(x + 2 * dx, y - 2);
        var br2 = new Position(x - 2 * dx, y + 2);
        var bl2 = new Position(x - 2 * dx, y - 2);

        return
        // Direct move: if we are moving to a forward diagonal which is not occupied
        end.equals(fr) && !squareAt(fr).isOccupied() || end.equals(fl) && !squareAt(fl).isOccupied()
        // Jump: if we are moving to a jump which is occupied, and that the square we are jumping
        // has an enemy's piece.
                || end.equals(fr2) && squareAt(fr).isOccupied() && squareAt(fr).isWhite() != isWhite
                        && !squareAt(fr2).isOccupied()
                || end.equals(fl2) && squareAt(fl).isOccupied() && squareAt(fl).isWhite() != isWhite
                        && !squareAt(fl2).isOccupied()
                || end.equals(br2) && squareAt(br).isOccupied() && squareAt(br).isWhite() != isWhite
                        && !squareAt(br2).isOccupied()
                || end.equals(bl2) && squareAt(bl).isOccupied() && squareAt(bl).isWhite() != isWhite
                        && !squareAt(bl2).isOccupied();
    }

    public List<Move> isPossibleMoved(Position start) {
        List<Move> possiblePos = new ArrayList<>();
        List<Position> positions = List.of(new Position(start.x() - 1, start.y() - 1),
                new Position(start.x() - 1, start.y() + 1),
                new Position(start.x() + 1, start.y() - 1),
                new Position(start.x() + 1, start.y() + 1));
        for (Position pos : positions) {
            Square theCase = this.squareAt(pos);
            if (theCase.isOccupied() && this.currentPlayer.isWhite() != theCase.isWhite()) {
                possiblePos.add(new Move(start, pos));
            }
        }
        return possiblePos;
    }

}
