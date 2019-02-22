package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
@AllArgsConstructor
public class City {
    public static final int CITY_WIDTH_INDEX = 1;
    public static final int CITY_HEIGHT_INDEX = 0;
    public static final int BUILDINGS_NUMBER_INDEX = 3;
    public static final int MAX_WALKING_DISTANCE_INDEX = 2;
    private int height, width;
    private int[][] cells;

    public void placeBuilding(Building building) {
        for (int i = 0; i < building.getWidth(); ++i) {
            for (int j = 0; j < building.getHeight(); ++j) {
                this.cells[building.getXTopLeft() + i][building.getYTopLeft() + j]
                    = building.getCellValue(i, j);
            }
        }
    }

    public void removeBuilding(Building building) {
        for (int i = 0; i < building.getWidth(); ++i) {
            for (int j = 0; j < building.getHeight(); ++j) {
                if (building.getCellValue(i, j) > 0) {
                    this.cells[building.getXTopLeft() + i][building.getYTopLeft() + j] = 0;
                }
            }
        }
    }

    public boolean canPlace(Building newBuilding) {
        int height = newBuilding.getHeight();
        int width = newBuilding.getWidth();
        int xtop = newBuilding.getXTopLeft();
        int ytop = newBuilding.getYTopLeft();
        int [][] buildingCells = newBuilding.getCells();

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (buildingCells[i][j] == 1 && cells[xtop + i][ytop + j] == 1)
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "City{" +
            "height=" + height +
            ", width=" + width +
            ", cells=" + printCells() +
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
