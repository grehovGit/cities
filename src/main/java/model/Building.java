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
}
