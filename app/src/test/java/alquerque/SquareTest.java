package alquerque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import alquerque.utils.Displayable;

class SquareTest {
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

                assertEquals(Displayable.WHITE_BACKGROUND + Displayable.SPACE + Displayable.RESET,
                                nonPlayable.toString());

                assertEquals(Displayable.BROWN_BACKGROUND + Displayable.YELLOW
                                + Displayable.FILLED_CIRCLE + Displayable.RESET,
                                whitePawn.toString());

                assertEquals(Displayable.BROWN_BACKGROUND + Displayable.BROWN
                                + Displayable.FILLED_CIRCLE + Displayable.RESET,
                                blackPawn.toString());

                assertEquals(Displayable.BROWN_BACKGROUND + Displayable.YELLOW2
                                + Displayable.FILLED_ROUNDED_D + Displayable.RESET,
                                whiteQueen.toString());

                assertEquals(Displayable.BROWN_BACKGROUND + Displayable.BROWN
                                + Displayable.FILLED_ROUNDED_D + Displayable.RESET,
                                blackQueen.toString());

        }
}
