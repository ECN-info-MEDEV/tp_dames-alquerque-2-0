package alquerque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class BoardTest {
    Board boardTest = new Board(new Player(true), new Player(false));

    @Test
    void testDisplay() {
        boardTest.display();
    }

    @Test
    void testSyntaxCorrect() {
        assertEquals(Optional.of(new Position(6, 1)), Board.syntaxCorrect("G1"));
        assertEquals(Optional.of(new Position(6, 1)), Board.syntaxCorrect("g1"));
        assertNotEquals(Optional.of(new Position(6, 1)), Board.syntaxCorrect("F1"));
        assertEquals(Optional.empty(), Board.syntaxCorrect("1G"));
    }

    @Test
    void testIsNumeric() {
        assertFalse(Board.isNumeric("strNum"));
        assertFalse(Board.isNumeric(""));
        assertTrue(Board.isNumeric("6"));
    }

    @Test
    void testIsPossibleMoved() {
        Position position4Foward = new Position(6, 1); // , new Position(5, 0));
        assertEquals(boardTest.isPossibleMoved(position4Foward), List.of());
        boardTest.squareAt(2, 3).setOccupied(false);
        boardTest.squareAt(4, 5).setOccupied(true);
        boardTest.squareAt(4, 5).setWhite(true);
        Position position4Eat = new Position(4, 5);
        Move movementEat = new Move(new Position(4, 5), new Position(2, 3));
        assertEquals(boardTest.isPossibleMoved(position4Eat), List.of(movementEat));

    }

    @Test
    void testIsPossibleUnmoved() {
        Move movementFoward = new Move(new Position(6, 1), new Position(5, 0));
        assertTrue(boardTest.isPossibleUnmoved(movementFoward));
        Move movementOut = new Move(new Position(6, 9), new Position(5, 10));
        assertFalse(boardTest.isPossibleUnmoved(movementOut));
        Move movementNonsense = new Move(new Position(6, 9), new Position(4, 7));
        assertFalse(boardTest.isPossibleUnmoved(movementNonsense));
        Move movementFromDust = new Move(new Position(5, 4), new Position(4, 3));
        assertFalse(boardTest.isPossibleUnmoved(movementFromDust));
        boardTest.squareAt(2, 3).setOccupied(false);
        boardTest.squareAt(4, 5).setOccupied(true);
        boardTest.squareAt(4, 5).setWhite(true);
        Move movementEat = new Move(new Position(4, 5), new Position(2, 3));
        assertTrue(boardTest.isPossibleUnmoved(movementEat));
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
}
