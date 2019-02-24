package buildstep;

import builder.CityBuilder;
import model.Building;
import model.City;

import java.util.Set;
import java.util.TreeSet;

public class VisualizeResultStep implements BuildStep {
    City city;
    private Set<Building> maxState = new TreeSet<>();

    public VisualizeResultStep(CityBuilder cityBuilder) {
        this.city = new City(
            cityBuilder.getCity().getHeight(),
            cityBuilder.getCity().getWidth(),
            new int[cityBuilder.getCity().getWidth()][cityBuilder.getCity().getHeight()]
            );
        this.maxState = cityBuilder.getMaxState();
    }

    @Override
    public void makeStep() {
        maxState.forEach(building -> city.placeBuildingWithNumber(building));
        System.out.println("Visualization");
        System.out.println(city);
    }
}
