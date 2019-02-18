package builder;

import model.Building;
import model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecursiveCalcRatingStep implements BuildStep {
    private City city;
    private List<Building> buildings;
    private Map<String, Integer> calculatedStates;

    public RecursiveCalcRatingStep(CityBuilder cityBuilder) {
        this.city = cityBuilder.getCity();
        this.buildings = CityBuilder.buildings;
        this.calculatedStates = CityBuilder.calculatedStates;
    }

    @Override
    public void makeStep() {

    }

    private void rate(String stateCode, int index) {

    }
}
