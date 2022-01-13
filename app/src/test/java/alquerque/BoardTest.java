package alquerque;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BoardTest {
    Board boardTest = new Board(new Player(true), new Player(false));

    @Test
    void testDisplay() {
        boardTest.display();
    }

    @Test
    void testIsNumeric() {
        assertFalse(Board.isNumeric("strNum"));
        assertFalse(Board.isNumeric(""));
        assertTrue(Board.isNumeric("6"));
    }

    @Test
    void testIsPossibleMoved() {

    }

    @Test
    void testIsPossibleUnmoved() {
        Move movementFoward = new Move(new Position(6, 1), new Position(5, 0));
        assertTrue(boardTest.isPossibleUnmoved(movementFoward));
        Move movementOut = new Move(new Position(6, 9), new Position(5, 10));
        assertFalse(boardTest.isPossibleUnmoved(movementOut));
        Move movementNonsense = new Move(new Position(6, 9), new Position(4, 7));
        assertFalse(boardTest.isPossibleUnmoved(movementNonsense));
    }

    @Test
    void testMove() {

    }

    @Test
    void testMoveExist() {
        assertFalse(boardTest.moveExist(new Position(9, 0)));
        assertFalse(boardTest.moveExist(new Position(7, 2)));
        assertTrue(boardTest.moveExist(new Position(6, 1)));
        assertTrue(boardTest.moveExist(new Position(6, 9)));

    }

    @Test
    void testSquareAt() {

    }

    @Test
    void testSquareAt2() {

    }
}
