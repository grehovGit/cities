package builder;

import builder.BuildStep;
import model.Building;
import model.City;

public class PlaceBuildingStep implements BuildStep {
    City city;
    Building building;

    public PlaceBuildingStep(Building building, CityBuilder cityBuilder) {
        this.city = cityBuilder.getCity();
        this.building = building;
    }

    @Override
    public void makeStep() { city.placeBuilding(building); }
}
