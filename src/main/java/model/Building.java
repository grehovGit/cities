package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Building implements Comparable<Building>{
    private int number;
    private int xTopLeft, yTopLeft;
    private int width, height, utilType, capacity;
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
        int oneWidth = one.getWidth();
        int oneHeight = one.getHeight();
        int otherWidth = other.getWidth();
        int otherHeight = other.getHeight();

        int [][] oneCells = one.getCells();
        int [][] otherCells = other.getCells();

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < oneWidth; ++i) {
            for (int j = 0; j < oneHeight; ++j) {
                for (int l = 0; l < otherWidth; ++l) {
                    for (int m = 0; m < otherHeight; ++m) {
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

    public void resetPosition() {
        this.setXTopLeft(0);
        this.setYTopLeft(0);
    }

    @Override
    public String toString() {
        return "Building{" +
            "xTopLeft=" + xTopLeft +
            ", yTopLeft=" + yTopLeft +
            ", width=" + width +
            ", height=" + height +
            ", util_type=" + utilType +
            ", capacity=" + capacity +
            ", cells=" + printCells() +
            ", type=" + type +
            '}';
    }

    private String printCells() {
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                lines.append(cells[j][i]);
            }
        }
        return lines.toString();
    }

    @Override
    public int compareTo(Building o) {
        return this.getNumber() - o.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Building building = (Building) o;

        if (number != building.number) return false;
        if (xTopLeft != building.xTopLeft) return false;
        return yTopLeft == building.yTopLeft;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        result = 31 * result + xTopLeft;
        result = 31 * result + yTopLeft;
        return result;
    }
}
