package io;

import model.Building;
import model.City;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {

    public static City getMainEntity(String[] mainEntityPlan) {
        City city = new City(
            Integer.parseInt(mainEntityPlan[City.CITY_HEIGHT_INDEX]),
            Integer.parseInt(mainEntityPlan[City.CITY_WIDTH_INDEX]),
            new int[Integer.parseInt(mainEntityPlan[City.CITY_WIDTH_INDEX])][Integer.parseInt(mainEntityPlan[City.CITY_HEIGHT_INDEX])]);
        System.out.println(city);
        return city;
    }

    public static  List<Building> getEntities(LinkedList<String> project, List<Building> buildings) {
        int buildingNumber = 0;
        while (project.size() > 0) {
            String[] buildingPlan = project.poll().split(" ");
            List<String> buildingCells = null;
            buildingCells = project.subList(0, Integer.parseInt(buildingPlan[Building.BUILDING_HEIGHT_INDEX]));
            Building building = new Building(
                buildingNumber++,
                0,
                0,
                Integer.parseInt(buildingPlan[Building.BUILDING_WIDTH_INDEX]),
                Integer.parseInt(buildingPlan[Building.BUILDING_HEIGHT_INDEX]),
                Integer.parseInt(buildingPlan[Building.BUILDING_CAPACTY_OR_UTIL_TYPE_INDEX]),
                Integer.parseInt(buildingPlan[Building.BUILDING_CAPACTY_OR_UTIL_TYPE_INDEX]),
                Building.getCells(buildingCells, Integer.parseInt(buildingPlan[Building.BUILDING_WIDTH_INDEX])),
                buildingPlan[Building.BUILDING_TYPE_INDEX].charAt(0));
            buildingCells.clear();
            System.out.println(building);
            buildings.add(building);
        }
        return buildings;
    }

    public static List<String> exportResult(Set<Building> maxState) {
        LinkedList<String> result = maxState.stream()
            .map(building ->
                "" + building.getNumber()
                + " "
                + building.getXTopLeft()
                + " "
                + building.getYTopLeft())
            .collect(Collectors.toCollection(() -> new LinkedList<>()));
        result.push(String.valueOf(maxState.size()));
        return result;
    }
}
