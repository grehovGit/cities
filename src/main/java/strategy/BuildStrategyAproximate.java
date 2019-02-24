package strategy;

import builder.CityBuilder;
import buildstep.BuildStep;
import buildstep.RecursiveCalcRatingEagerStep;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildStrategyAproximate implements BuildStrategy {
    private CityBuilder cityBuilder;

    @Override
    public void build() {
        BuildStep recursiveCalcRatingStep = new RecursiveCalcRatingEagerStep(this.cityBuilder);
        recursiveCalcRatingStep.makeStep();
    }
}
