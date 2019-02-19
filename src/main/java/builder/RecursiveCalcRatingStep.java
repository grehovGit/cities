package builder;

import javafx.collections.transformation.SortedList;
import model.Building;
import model.City;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class RecursiveCalcRatingStep implements BuildStep {
    CityBuilder cityBuilder;
    private City city;
    private List<Building> buildings;
    private Map<String, Integer> calculatedStates;
    private TreeSet<Building> placedBuildings = new TreeSet<>();

    public RecursiveCalcRatingStep(CityBuilder cityBuilder) {
        this.city = cityBuilder.getCity();
        this.buildings = CityBuilder.buildings;
        this.calculatedStates = CityBuilder.calculatedStates;
        this.cityBuilder = cityBuilder;
    }

    @Override
    public void makeStep() {
        Iterator<Building> it = buildings.iterator();
        while (it.hasNext()) {
            Building building = it.next();
            rate(buildings, 0);
            it.remove();
            buildings.add(0, building);
        }
    }

    private void rate(List<Building> buildings, int currentRate) {
        Building building = buildings.get(0);
        List<Building> others = buildings.subList(1, buildings.size());
        placedBuildings.add(building);

        while (!isFinishState(building)) {
            CalcBuildingRatingStep calcBuildingRatingStep = new CalcBuildingRatingStep(
                cityBuilder,
                building,
                placedBuildings,
                currentRate);
            calcBuildingRatingStep.makeStep();
            currentRate = calcBuildingRatingStep.getCurrentRateAfter();

            if (others.size() > 0) {
                //TODO: AVOID PLACING BUILDING WITHOUT CHECK
                PlaceBuildingStep placeBuildingStep = new PlaceBuildingStep(building, this.cityBuilder);
                placeBuildingStep.makeStep();

                rate(others, currentRate);

                RemoveBuildingStep removeBuildingStep = new RemoveBuildingStep(building, this.cityBuilder);
                removeBuildingStep.makeStep();
            }
            moveBuilding(building);
        }
    }

    private boolean isFinishState(Building building){
        return building.getXTopLeft() + building.getWidth() > this.city.getWidth() &&
            building.getYTopLeft() + building.getHeight() >= this.city.getHeight();
    }

    private boolean isInEndOfRow(Building building){
        return building.getXTopLeft() + building.getWidth() == this.city.getWidth();
    }

    private void moveToNextRow(Building building) {
        building.setXTopLeft(0);
        building.setYTopLeft(building.getYTopLeft() + 1);
    }

    private void moveNext(Building building) {
        building.setYTopLeft(building.getXTopLeft() + 1);
    }

    private void moveBuilding(Building building) {
        if (isFinishState(building)) {
            throw new IllegalStateException("Building is already in finish state!!");
        }

        if (isInEndOfRow(building)) {
            moveToNextRow(building);
        } else {
            moveNext(building);
        }
    }
}
