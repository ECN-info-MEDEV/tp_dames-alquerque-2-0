/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package alquerque;

public class App {

    public static void main(String[] args) {

        // create a new board
        Board board = new Board(new Player(true), new Player(false));

        while (board.Turn()) {

        }
    }

}
