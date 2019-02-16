package builder;

import model.Building;
import model.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityBuilder {
    public static final int CITY_WIDTH_INDEX = 1;
    public static final int CITY_HEIGHT_INDEX = 0;
    public static final int MAX_WALKING_DISTANCE_INDEX = 2;
    public static final int BUILDINGS_NUMBER_INDEX = 3;

    private static Map<String, Integer> calculatedStates = new HashMap<>(10000);
    private static List<Building> buildings = new ArrayList(10);
    private City city;
    private int maxWalkingDistance;
    private int buildingsNmber;

    public void rememberState(String stateCode, int rate) {
        this.calculatedStates.put(stateCode, rate);
    }

    public static void main(String[] args) {
    }

    public CityBuilder(List<String> project) {
        this.city = new City(project.get(0).charAt(CITY_WIDTH_INDEX), project.get(0).charAt(CITY_HEIGHT_INDEX),
            new int[project.get(0).charAt(CITY_WIDTH_INDEX)][project.get(0).charAt(CITY_HEIGHT_INDEX)]);
        this.maxWalkingDistance = project.get(0).charAt(MAX_WALKING_DISTANCE_INDEX);
        this.buildingsNmber = project.get(0).charAt(BUILDINGS_NUMBER_INDEX);

        for (int i = 0; i < buildingsNmber; i++) {

        }
        //TODO START HERE
        project
            .stream()
            .skip(1)
            .forEach(buildingPlan -> {
                new Building();
            });
    }
}
