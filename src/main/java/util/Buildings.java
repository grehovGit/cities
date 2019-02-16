package util;

import model.Building;

public class Buildings {
    public static int distance(Building one, Building other) {
        int oneLength = one.getHeight();
        int oneWidth = one.getWidth();
        int otherLength = other.getHeight();
        int otherWidth = other.getWidth();

        int [][] oneCells = one.getCells();
        int [][] otherCells = other.getCells();

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < oneLength; ++i) {
            for (int j = 0; j < oneWidth; ++j) {
                for (int l = 0; l < otherLength; ++l) {
                    for (int m = 0; m < otherWidth; ++m) {
                        if (oneCells[i][j] == 1 && otherCells[l][m] == 1
                            && Math.abs(other.getXTopLeft() + l - (one.getXTopLeft() + i))
                            + Math.abs(other.getYTopLeft() + m - (one.getYTopLeft() + j)) < minDistance) {
                            minDistance = Math.abs(other.getXTopLeft() + l - (one.getXTopLeft() + i))
                                + Math.abs(other.getYTopLeft() + m - (one.getYTopLeft() + j));
                        }
                    }
                }
            }
        }
        return minDistance;
    }
}
