package oo.hide;

import java.util.Arrays;

public class PointSet {

    private Point[] points;
    private int size;

    public PointSet(int capacity) {
        points = new Point[capacity];
        size = 0;
    }

    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if (point == null) {
            if (contains(null)) {
                return;
            }
        } else if (contains(point)) {
            return;
        }

        if (size == points.length) {
            points = Arrays.copyOf(points, points.length * 2);
        }

        points[size++] = point;
    }

    public int size() {
        return size;
    }

    public boolean contains(Point point) {
        if (point == null) {
            for (int i = 0; i < size; i++) {
                if (points[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (point.equals(points[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (points[i] != null) {
                result.append("(").append(points[i].getX()).append(", ").append(points[i].getY()).append(")");
            } else {
                result.append("null");
            }
            if (i < size - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PointSet pointSet = (PointSet) o;

        if (size != pointSet.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!pointSet.contains(points[i])) {
                return false;
            }
        }
        return true;
    }

    public PointSet subtract(PointSet other) {
        PointSet result = new PointSet(points.length);
        for (int i = 0; i < size; i++) {
            if (!other.contains(points[i])) {
                result.add(points[i]);
            }
        }
        return result;
    }

    public PointSet intersect(PointSet other) {
        PointSet result = new PointSet(Math.min(points.length, other.points.length));
        for (int i = 0; i < size; i++) {
            if (other.contains(points[i])) {
                result.add(points[i]);
            }
        }
        return result;
    }

    public void remove(Point point) {
        if (point == null) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (points[i].equals(point)) {
                for (int j = i; j < size - 1; j++) {
                    points[j] = points[j + 1];
                }
                points[size - 1] = null;
                size--;
                return;
            }
        }
    }
}
