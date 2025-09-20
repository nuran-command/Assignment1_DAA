package com.carrental;   // or com.assignment if that's your package

import java.util.*;

public class ClosestPair {
    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double distance(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }

    public static double closestPair(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closest(points, 0, points.length - 1);
    }

    private static double closest(Point[] points, int left, int right) {
        if (right - left <= 3) { // brute force
            double min = Double.MAX_VALUE;
            for (int i = left; i <= right; i++)
                for (int j = i + 1; j <= right; j++)
                    min = Math.min(min, distance(points[i], points[j]));
            Arrays.sort(points, left, right + 1, Comparator.comparingDouble(p -> p.y));
            return min;
        }

        int mid = (left + right) / 2;
        double midX = points[mid].x;
        double d = Math.min(closest(points, left, mid), closest(points, mid + 1, right));

        // merge by y
        Point[] temp = Arrays.copyOfRange(points, left, right + 1);
        Arrays.sort(temp, Comparator.comparingDouble(p -> p.y));

        // strip
        List<Point> strip = new ArrayList<>();
        for (Point p : temp)
            if (Math.abs(p.x - midX) < d) strip.add(p);

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }
}