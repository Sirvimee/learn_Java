package gol;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private Set<Point> aliveCells = new HashSet<>();

    public void markAlive(int x, int y) {
        aliveCells.add(new Point(x, y));
    }

    public boolean isAlive(int x, int y) {
        return aliveCells.contains(new Point(x, y));
    }

    public void toggle(int x, int y) {
        Point point = new Point(x, y);
        if (aliveCells.contains(point)) {
            aliveCells.remove(point);
        } else {
            aliveCells.add(point);
        }
    }

    public Integer getNeighbourCount(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                if (isAlive(x + dx, y + dy)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void nextFrame() {
        Set<Point> newAliveCells = new HashSet<>();
        Set<Point> candidates = new HashSet<>(aliveCells);

        for (Point p : aliveCells) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    candidates.add(new Point(p.x + dx, p.y + dy));
                }
            }
        }

        for (Point p : candidates) {
            int neighbors = getNeighbourCount(p.x, p.y);
            if (isAlive(p.x, p.y) && (neighbors == 2 || neighbors == 3)) {
                newAliveCells.add(p);
            } else if (!isAlive(p.x, p.y) && neighbors == 3) {
                newAliveCells.add(p);
            }
        }

        aliveCells = newAliveCells;
    }

    public void clear() {
        aliveCells.clear();
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (isLiving) {
            return neighborCount == 2 || neighborCount == 3;
        } else {
            return neighborCount == 3;
        }
    }
}
