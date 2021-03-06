package org.jbehave.examples.trader.stories;

import java.util.List;

import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.examples.trader.TraderStory;
import org.jbehave.examples.trader.service.TradingService;
import org.jbehave.examples.trader.steps.FailingBeforeAfterScenarioSteps;
import org.jbehave.examples.trader.steps.TraderSteps;

public class FailingBeforeScenario extends TraderStory {
    
    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new TraderSteps(new TradingService()), 
                new FailingBeforeAfterScenarioSteps()).createCandidateSteps();
    }
        
}
