package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Building {
    private int x, y;
    private int length, width;
    private int[][] cells;
    private Type type;

    enum Type {
        RESIDENT, UTILIT
    }
}
