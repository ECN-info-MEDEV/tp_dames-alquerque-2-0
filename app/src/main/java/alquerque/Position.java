package alquerque;

import java.util.Collection;
import java.util.List;

import lombok.ToString;

public record Position(int x, int y) {
    public String toString() {
        return Character.toString('A' + x) + y;
    }

    public static int distance(Position start, Position end) {
        return Math.max(Math.abs(start.x() - end.x()), Math.abs(start.y() - end.y()));
    }
}
