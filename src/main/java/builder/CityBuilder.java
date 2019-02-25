package builder;

import io.InputOutput;
import io.Parser;
import lombok.Data;
import model.Building;
import model.City;
import buildstep.*;
import strategy.BuildStrategy;
import strategy.BuildStrategyAproximate;
import strategy.BuildStrategyEager;
import strategy.BuildStrategyOptimize;

import java.util.*;

@Data
public class CityBuilder {


    public static Map<String, Integer> calculatedStates = new HashMap<>(10000000);
    public static List<Building> buildings = new ArrayList(10);
    private City city;
    private int maxWalkingDistance;
    private int buildingsNmber;
    private int maxRate = Integer.MIN_VALUE;
    private String maxKey = "";
    private Set<Building> maxState = new TreeSet<>();

    public static void main(String[] args) {
        LinkedList<String> cityPlan = InputOutput.load("C://test/cityStart.txt");
        CityBuilder cityBuilder = new CityBuilder(cityPlan);
        BuildStrategy solvingStrategy = cityBuilder.getStratgey();

        solvingStrategy.build();
        BuildStep visualization = new VisualizeResultStep(cityBuilder);
        visualization.makeStep();

        InputOutput.export(Parser.exportResult(cityBuilder.getMaxState()), "C://test/cityStart_result.txt");

        System.out.println("Max State:" + cityBuilder.getMaxState());
        System.out.println("Max Rate:" + cityBuilder.getMaxRate());
        System.out.println("Max Key:" + cityBuilder.getMaxKey());
    }

    public CityBuilder(LinkedList<String> project) {
        String[] cityPlan = project.poll().split(" ");
        this.city = Parser.getMainEntity(cityPlan);

        this.maxWalkingDistance = Integer.parseInt(cityPlan[City.MAX_WALKING_DISTANCE_INDEX]);
        this.buildingsNmber = Integer.parseInt(cityPlan[City.BUILDINGS_NUMBER_INDEX]);

        buildings = Parser.getEntities(project, buildings);

        buildings.forEach(building -> city.placeBuilding(building));
        System.out.println(city);
        buildings.forEach(building -> city.removeBuilding(building));
        System.out.println(city);
    }

    private BuildStrategy getStratgey()
    {
        return
            this.getCity().getWidth()
                * this.getCity().getHeight()
                * this.getBuildingsNmber()
                < 500
                ? new BuildStrategyOptimize(this)
                :   this.getCity().getWidth()
                    * this.getCity().getHeight()
                    * this.getBuildingsNmber()
                    < 1000
                    ? new BuildStrategyAproximate(this)
                    :  new BuildStrategyEager(this);
    }
}
