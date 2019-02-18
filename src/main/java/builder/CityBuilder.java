package builder;

import lombok.Data;
import model.Building;
import model.City;

import java.lang.reflect.Array;
import java.util.*;

@Data
public class CityBuilder {
    public static final int CITY_WIDTH_INDEX = 1;
    public static final int CITY_HEIGHT_INDEX = 0;
    public static final int MAX_WALKING_DISTANCE_INDEX = 2;
    public static final int BUILDINGS_NUMBER_INDEX = 3;

    public static final int BUILDING_WIDTH_INDEX = 2;
    public static final int BUILDING_HEIGHT_INDEX = 1;
    public static final int BUILDING_TYPE_INDEX = 0;
    public static final int BUILDING_CAPACTY_OR_UTIL_TYPE_INDEX = 3;


    static Map<String, Integer> calculatedStates = new HashMap<>(10000000);
    static List<Building> buildings = new ArrayList(10);
    private City city;
    private int maxWalkingDistance;
    private int buildingsNmber;
    private int maxRate = Integer.MIN_VALUE;
    private String maxKey = "";

    public static void main(String[] args) {
        LinkedList<String> cityPlan = new LinkedList<>();
        cityPlan.add("4 7 2 3");
        cityPlan.add("R 3 2 25");
        cityPlan.add(".#");
        cityPlan.add("##");
        cityPlan.add(".#");
        cityPlan.add("U 1 4 1");
        cityPlan.add("####");
        cityPlan.add("U 2 2 5");
        cityPlan.add("##");
        cityPlan.add("##");

        CityBuilder cityBuilder = new CityBuilder(cityPlan);
    }

    public void rememberState(String stateCode, int rate) {
        this.calculatedStates.put(stateCode, rate);
    }


    public CityBuilder(LinkedList<String> project) {
        String[] cityPlan = project.poll().split(" ");
        this.city = new City(Integer.parseInt(cityPlan[CITY_HEIGHT_INDEX]), Integer.parseInt(cityPlan[CITY_WIDTH_INDEX]),
            new int[Integer.parseInt(cityPlan[CITY_WIDTH_INDEX])][Integer.parseInt(cityPlan[CITY_HEIGHT_INDEX])]);
        System.out.println(this.city);

        this.maxWalkingDistance = Integer.parseInt(cityPlan[MAX_WALKING_DISTANCE_INDEX]);
        this.buildingsNmber = Integer.parseInt(cityPlan[BUILDINGS_NUMBER_INDEX]);

        int buildingNumber = 0;
        while (project.size() > 0) {
            String[] buildingPlan = project.poll().split(" ");
            List<String> buildingCells = null;
            buildingCells = project.subList(0, Integer.parseInt(buildingPlan[BUILDING_HEIGHT_INDEX]));
            Building building = new Building(
                buildingNumber++,
                0,
                0,
                Integer.parseInt(buildingPlan[BUILDING_WIDTH_INDEX]),
                Integer.parseInt(buildingPlan[BUILDING_HEIGHT_INDEX]),
                Integer.parseInt(buildingPlan[BUILDING_CAPACTY_OR_UTIL_TYPE_INDEX]),
                Integer.parseInt(buildingPlan[BUILDING_CAPACTY_OR_UTIL_TYPE_INDEX]),
                Building.getCells(buildingCells, Integer.parseInt(buildingPlan[BUILDING_WIDTH_INDEX])),
                buildingPlan[BUILDING_TYPE_INDEX].charAt(0));
            buildingCells.clear();
            System.out.println(building);
            buildings.add(building);
        }

        buildings.forEach(building -> city.placeBuilding(building));
        System.out.println(city);
        buildings.forEach(building -> city.removeBuilding(building));
        System.out.println(city);
    }
}
