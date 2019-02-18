package builder;

import model.Building;
import model.City;

import java.util.List;
import java.util.Map;

public class CalcBuildingRatingStep implements BuildStep {
    private City city;
    private Map<String, Integer> calculatedStates;
    private String currentState;
    private Building nextBuilding;

    public CalcBuildingRatingStep(
        CityBuilder cityBuilder,
        Building nextBuilding,
        String currentState,
        int currentRate) {
        this.city = cityBuilder.getCity();
        this.calculatedStates = CityBuilder.calculatedStates;
        this.currentState = currentState;
        this.nextBuilding = nextBuilding;
    }

    @Override
    public void makeStep() {
        Integer rate = calculatedStates.get(currentState
            + nextBuilding.getNumber()
            + " "
            + nextBuilding.getXTopLeft()
            + " "
            + nextBuilding.getYTopLeft());

        if (rate == null) {
        }
    }

    private int calculateRate() {
        if (city.canPlace(nextBuilding)) {
            //TODO create stack of placed util houses
            //Calculate rating
        }
    }

}
