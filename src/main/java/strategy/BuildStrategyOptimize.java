package strategy;

import builder.CityBuilder;
import buildstep.BuildStep;
import buildstep.RecursiveCalcRatingFullStep;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildStrategyOptimize implements BuildStrategy {
    private CityBuilder cityBuilder;

    @Override
    public void build() {
        BuildStep recursiveCalcRatingStep = new RecursiveCalcRatingFullStep(this.cityBuilder);
        recursiveCalcRatingStep.makeStep();
    }
}
