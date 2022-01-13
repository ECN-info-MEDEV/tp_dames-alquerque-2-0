package alquerque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        end.equals(fr) && inRange(fr) && !squareAt(fr).isOccupied()
                || end.equals(fl) && inRange(fl) && !squareAt(fl).isOccupied()
                // Jump: if we are jumping to a square which is occupied, and that the square we
                // are jumping
                // over has an enemy's piece.
                || end.equals(fr2) && inRange(fr2) && squareAt(fr).isOccupied() && squareAt(fr).isWhite() != isWhite
                        && !squareAt(fr2).isOccupied()
                || end.equals(fl2) && inRange(fl2) && squareAt(fl).isOccupied() && squareAt(fl).isWhite() != isWhite
                        && !squareAt(fl2).isOccupied()
                || end.equals(br2) && inRange(br2) && squareAt(br).isOccupied() && squareAt(br).isWhite() != isWhite
                        && !squareAt(br2).isOccupied()
                || end.equals(bl2) && inRange(bl2) && squareAt(bl).isOccupied() && squareAt(bl).isWhite() != isWhite
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
            if (inRange(pos) && theCase.isOccupied() && this.currentPlayer.isWhite() != theCase.isWhite()) {
                possiblePos.add(new Move(start, pos));
            }
        }
        return possiblePos;
    }

    public boolean moveExist() {
        var isWhite = currentPlayer.isWhite();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Only check the pieces that we have.
                if (squareAt(i, j).isOccupied() && squareAt(i, j).isWhite() == isWhite
                        && moveExist(new Position(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveExist(Position start) {
        var x = start.x();
        var y = start.y();
        // "forward" is in the -x direction for white, and the +x direction for black.
        var isWhite = currentPlayer.isWhite();
        var dx = isWhite ? -1 : 1;

        // forward-right and forward-left squares.
        var fr = new Position(x + dx, y + 1);
        var fl = new Position(x + dx, y - 1);

        // Indirect diagonal squares.
        var fr2 = new Position(x + 2 * dx, y + 2);
        var fl2 = new Position(x + 2 * dx, y - 2);
        var br2 = new Position(x - 2 * dx, y + 2);
        var bl2 = new Position(x - 2 * dx, y - 2);

        return List.of(fr, fl, fr2, fl2, br2, bl2).stream().map(end -> new Move(start, end))
                .anyMatch(this::isPossibleUnmoved);
    }

    private boolean inRange(Position pos) {
        if (pos.x() >= 0 && pos.x() < 10 && pos.y() >= 0 && pos.y() < 10) {
            return true;
        }
        return false;
    }

    public boolean Turn() {
        this.display();
        if (this.moveExist()) {
            System.out.println("À toi de jouer joueur " + (currentPlayer.isWhite() ? "blanc" : "noir") + " !");
            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            try {
                Optional<Position> start = Optional.of(new Position(0, 0));
                Optional<Position> end = Optional.of(new Position(10, 10));
                while (!move(new Move(start.get(), end.get()), true)) {
                    start = Optional.empty();
                    while (start.isEmpty()) {
                        System.out.print("Rentrez la position de départ : ");
                        String startString = obj.readLine();
                        start = syntaxCorrect(startString);
                    }
                    end = Optional.empty();
                    System.out.println("");
                    while (end.isEmpty()) {
                        System.out.print("Rentrez la position d’arrivée : ");
                        String endString = obj.readLine();
                        end = syntaxCorrect(endString);
                    }
                    System.out.println("");
                }

                // potentiel 2ème tour du même joueur

                start = end;
                List<Move> endList = isPossibleMoved(start.get());
                if (!endList.isEmpty()) {
                    System.out.println("Voici les mouvements encore possibles :");
                    for (int i = 0; i < endList.size(); i++) {
                        System.out.println(endList.get(i).end().toString() + " - " + i);
                    }
                    System.out.println("Ne rien faire - " + endList.size());
                    String choixString = obj.readLine();
                    while (!isNumeric(choixString) || Integer.parseInt(choixString) < 0
                            || Integer.parseInt(choixString) > endList.size()) {
                        System.out.print("Choix non valide, veuillez redonner votre choix :");
                        choixString = obj.readLine();
                        System.out.println("");
                    }
                    int choixInt = Integer.parseInt(choixString);
                    if (choixInt < endList.size()) {
                        move(endList.get(choixInt), false);
                    }

                }

                // changer le joueur dont c’est le tour
                if (this.currentPlayer == this.player1) {
                    this.currentPlayer = this.player2;
                } else {
                    this.currentPlayer = this.player1;
                }

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        } else {
            String winner;
            if (this.currentPlayer.isWhite()) {
                winner = "Noir";
            } else {
                winner = "Blanc";
            }
            System.out.println("Fin du jeu : " + winner + " a gagner !");
        }
        return false;
    }

    private Optional<Position> syntaxCorrect(String pos) {
        List<String> listLettres = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        pos.toUpperCase();
        if (pos.length() == 2 && listLettres.contains(pos.substring(0)) &&
                this.isNumeric(pos.substring(1)) && Integer.parseInt(pos.substring(1)) < 10
                && Integer.parseInt(pos.substring(1)) >= 0) {
            return Optional
                    .of(new Position(listLettres.indexOf(pos.substring(0, 1)), Integer.parseInt(pos.substring(1))));
        }
        System.out.println("Mauvaise syntaxe");
        return Optional.empty();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Move a piece.
     * 
     * @return If this move was successful.
     */
    public boolean move(Move m, boolean firstMove) {
        if (firstMove ? !isPossibleUnmoved(m) : !isPossibleMoved(m.start()).isEmpty()) {
            return false;
        }
        var start = m.start();
        var end = m.end();
        squares[end.x()][end.y()] = squares[start.x()][start.y()];
        squares[start.x()][start.y()] = new Square(true, false, false);
        /* TODO: Eat the piece in between if we jumped. */
        return true;
    }
}
