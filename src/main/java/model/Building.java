package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Building {
    private int xTopLeft, yTopLeft;
    private int width, height;
    private int[][] cells;
    private Type type;

    enum Type {
        RESIDENT, UTILIT
    }

    public static int [][] getCells(List<String> buildRows, int width) {
        int [][] newCells = new int[width][buildRows.size()];
        for (int row = 0; row < buildRows.size(); ++row) {
            String buildRow = buildRows.get(row);
            for (int index = 0; index < width; ++index) {
                newCells[index][row] = buildRow.charAt(index) == '#'
                    ? 1 : 0;
            };
        }
    }

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
