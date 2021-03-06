package buildstep;

import builder.CityBuilder;
import model.Building;
import model.City;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class RecursiveCalcRatingEagerStep implements BuildStep {
    CityBuilder cityBuilder;
    private City city;
    private List<Building> buildings;
    private Map<String, Integer> calculatedStates;
    private TreeSet<Building> placedBuildings = new TreeSet<>();

    public RecursiveCalcRatingEagerStep(CityBuilder cityBuilder) {
        this.city = cityBuilder.getCity();
        this.buildings = CityBuilder.buildings;
        this.calculatedStates = CityBuilder.calculatedStates;
        this.cityBuilder = cityBuilder;
    }

    @Override
    public void makeStep() {
        rate(this.buildings, 0);
    }

    private void rate(List<Building> buildings, int currentRate) {
        Building building = buildings.get(0);
        List<Building> others = buildings.subList(1, buildings.size());
        placedBuildings.add(building);

        while(inCity(building)) {
            CalcBuildingRatingStep calcBuildingRatingStep = new CalcBuildingRatingStep(
                cityBuilder,
                building,
                placedBuildings,
                currentRate);
            calcBuildingRatingStep.makeStep();
            int newRate = calcBuildingRatingStep.getCurrentRateAfter();

            if (newRate >= currentRate && others.size() > 0) {
                PlaceBuildingStep placeBuildingStep = new PlaceBuildingStep(building, this.cityBuilder);
                placeBuildingStep.makeStep();

                rate(others, currentRate);

                RemoveBuildingStep removeBuildingStep = new RemoveBuildingStep(building, this.cityBuilder);
                removeBuildingStep.makeStep();
            }
            moveBuilding(building);
        }
        building.resetPosition();
        placedBuildings.remove(building);
    }

    private boolean inCity(Building building){
        return building.getXTopLeft() + building.getWidth() <= this.city.getWidth() &&
            building.getYTopLeft() + building.getHeight() <= this.city.getHeight();
    }

    private boolean isInEndOfRow(Building building){
        return building.getXTopLeft() + building.getWidth() == this.city.getWidth();
    }

    private void moveToNextRow(Building building) {
        building.setXTopLeft(0);
        building.setYTopLeft(building.getYTopLeft() + 1);
    }

    private void moveNext(Building building) {
        building.setXTopLeft(building.getXTopLeft() + 1);
    }

    private void moveBuilding(Building building) {
        if (isInEndOfRow(building)) {
            moveToNextRow(building);
        } else {
            moveNext(building);
        }
    }
}
