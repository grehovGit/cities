package buildstep;

import builder.CityBuilder;
import lombok.Data;
import model.Building;
import model.City;
import buildstep.BuildStep;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
public class CalcBuildingRatingStep implements BuildStep {
    private City city;
    private Map<String, Integer> calculatedStates;
    private TreeSet<Building> placedBuildings;
    private Building nextBuilding;
    private CityBuilder cityBuilder;
    private int distance;
    private int currentRateBefore;
    private int currentRateAfter;

    public CalcBuildingRatingStep(
        CityBuilder cityBuilder,
        Building nextBuilding,
        TreeSet placedBuildings,
        int currentRate) {
        this.cityBuilder = cityBuilder;
        this.city = cityBuilder.getCity();
        this.calculatedStates = CityBuilder.calculatedStates;
        this.placedBuildings = placedBuildings;
        this.nextBuilding = nextBuilding;
        this.distance = cityBuilder.getMaxWalkingDistance();
        this.currentRateBefore = currentRate;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Override
    public void makeStep() {
        String currentState = this.getNewState();
        Integer rate = calculatedStates.get(currentState);

        if (rate == null) {
            rate = currentRateBefore + calculateNextBuildingRate();
        }
        remeberState(currentState, rate);

        if (rate > this.cityBuilder.getMaxRate()) {
            this.cityBuilder.setMaxRate(rate);
            this.cityBuilder.setMaxKey(currentState);
            this.updateMaxState();
        }
        this.currentRateAfter = rate;
    }

    private void updateMaxState() {
        TreeSet<Building> maxState = new TreeSet<>();
        placedBuildings.forEach(building -> {
            maxState.add(Building.builder()
                .number(building.getNumber())
                .xTopLeft(building.getXTopLeft())
                .yTopLeft(building.getYTopLeft())
                .build());});
        this.cityBuilder.setMaxState(maxState);
    }

    private int calculateNextBuildingRate() {
        if (city.canPlace(nextBuilding)) {
            return nextBuilding.getCapacity() * (int) placedBuildings
                .stream()
                .filter(building -> building.getType() == 'U')
                .filter(building -> Building.distance(building, nextBuilding) <= distance)
                .filter(distinctByKey(Building::getUtilType))
                .count();
        }
        return -1;
    }

    private String getNewState() {
        String newState =  placedBuildings.toString()
            + nextBuilding.getNumber()
            + " "
            + nextBuilding.getXTopLeft()
            + " "
            + nextBuilding.getYTopLeft();
        return newState;
    }

    private void remeberState(String state, int rate) {
        this.calculatedStates.put(state, rate);
    }
}
