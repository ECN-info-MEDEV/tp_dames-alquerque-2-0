package alquerque;

import org.junit.jupiter.api.Test;

public class SquareTest {
    @Test
    void testToString() {
        Square nonPlayable = new Square(false, false, false);
        Square whitePawn = new Square(true, true, true);
        Square blackPawn = new Square(true, true, false);
        Square whiteQueen = new Square(true, true, true);
        whiteQueen.setQueen(true);
        Square blackQueen = new Square(true, true, false);
        blackQueen.setQueen(true);

        System.out.print(nonPlayable);
        System.out.print(whitePawn);
        System.out.print(blackPawn);
        System.out.print(whiteQueen);
        System.out.print(blackQueen);

        // Add assertions

    }
}
