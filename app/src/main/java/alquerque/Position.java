package alquerque;

import lombok.ToString;

public record Position(int x, int y) {
    public String toString() {
        return Character.toString('A' + x) + y;
    }
}
