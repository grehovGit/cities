package builder;

import model.Building;
import model.City;

public class RemoveBuildingStep implements BuildStep {
    City city;
    Building building;

    public RemoveBuildingStep(Building building, CityBuilder cityBuilder) {
        this.city = cityBuilder.getCity();
        this.building = building;
    }
    @Override
    public void makeStep() { city.removeBuilding(building); }
}
