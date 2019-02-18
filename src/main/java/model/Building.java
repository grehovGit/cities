package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Building {
    private int number;
    private int xTopLeft, yTopLeft;
    private int width, height, util_type, capacity;
    private int[][] cells;
    private char type;

    public static int [][] getCells(List<String> buildRows, int width) {
        int [][] newCells = new int[width][buildRows.size()];
        for (int row = 0; row < buildRows.size(); ++row) {
            String buildRow = buildRows.get(row);
            for (int index = 0; index < width; ++index) {
                newCells[index][row] = buildRow.charAt(index) == '#'
                    ? 1 : 0;
            };
        }
        return newCells;
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

    public int getCellValue(int x, int y) {
        return this.cells[x][y];
    }

    @Override
    public String toString() {
        return "Building{" +
            "xTopLeft=" + xTopLeft +
            ", yTopLeft=" + yTopLeft +
            ", width=" + width +
            ", height=" + height +
            ", util_type=" + util_type +
            ", capacity=" + capacity +
            ", cells=" + printCells() +
            ", type=" + type +
            '}';
    }

    private String printCells() {
        StringBuilder lines = new StringBuilder();
        lines.append(System.lineSeparator());
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                lines.append(cells[j][i]);
            }
            lines.append(System.lineSeparator());
        }
        return lines.toString();
    }
}
