package org.jbehave.core;

import org.jbehave.core.model.KeyWords;
import org.jbehave.core.errors.ErrorStrategy;
import org.jbehave.core.errors.ErrorStrategyInWhichWeTrustTheReporter;
import org.jbehave.core.errors.PendingErrorStrategy;
import org.jbehave.core.i18n.I18nKeyWords;
import org.jbehave.core.parser.ClasspathScenarioDefiner;
import org.jbehave.core.parser.PatternStoryParser;
import org.jbehave.core.parser.ScenarioDefiner;
import org.jbehave.core.reporters.PassSilentlyDecorator;
import org.jbehave.core.reporters.PrintStreamScenarioReporter;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.ScenarioReporter;
import org.jbehave.core.reporters.StepdocReporter;
import org.jbehave.core.steps.DefaultStepdocGenerator;
import org.jbehave.core.steps.StepCreator;
import org.jbehave.core.steps.StepdocGenerator;
import org.jbehave.core.steps.UnmatchedToPendingStepCreator;

/**
 * The default configuration used by {@link StoryRunner}. Works for most
 * situations that users are likely to encounter. The default elements
 * configured are:
 * <ul>
 *   <li>{@link StepCreator}: new UnmatchedToPendingStepCreator()</li>
 *   <li>{@link ScenarioDefiner}: new ClasspathScenarioDefiner(new PatternStoryParser(this))</li>
 *   <li>{@link ErrorStrategy}: ErrorStrategy.RETHROW</li>
 *   <li>{@link PendingErrorStrategy}: PendingErrorStrategy.PASSING</li>
 *   <li>{@link ScenarioReporter}: new PassSilentlyDecorator(new PrintStreamScenarioReporter())</li>
 *   <li>{@link KeyWords}: new I18nKeyWords()</li>
 *   <li>{@link StepdocGenerator}: new DefaultStepdocGenerator()</li>
 *   <li>{@link StepdocReporter}: new PrintStreamStepdocReporter(true)</li>
 * </ul>
 */
public class MostUsefulConfiguration implements Configuration {

	/**
	 * Provides pending steps where unmatched steps exist.
	 */
	public StepCreator forCreatingSteps() {
		return new UnmatchedToPendingStepCreator();
	}

	/**
	 * Defines stories by looking for a file named after the core and in
	 * the same package, using lower-case underscored name in place of the
	 * camel-cased name - so MyScenario.java maps to my_scenario.
	 */
	public ScenarioDefiner forDefiningScenarios() {
		return new ClasspathScenarioDefiner(new PatternStoryParser(keywords()));
	}

	/**
	 * Handles errors by rethrowing them.
	 * 
	 * <p>
	 * If there are multiple stories in a single story model, this could
	 * cause the story to stop after the first failing core.
	 * 
	 * <p>
	 * If you want different behaviour, you might want to look at the
	 * {@link ErrorStrategyInWhichWeTrustTheReporter}.
	 */
	public ErrorStrategy forHandlingErrors() {
		return ErrorStrategy.RETHROW;
	}

	/**
	 * Allows pending steps to pass, so that builds etc. will not fail.
	 * 
	 * <p>
	 * If you want to spot pending steps, you might want to look at
	 * {@link PendingStepStrategy.FAILING}, or alternatively at the
	 * PropertyBasedConfiguration which provides a mechanism for altering this
	 * behaviour in different environments.
	 */
	public PendingErrorStrategy forPendingSteps() {
		return PendingErrorStrategy.PASSING;
	}

	/**
	 * Reports failing or pending stories to System.out, while silently
	 * passing stories.
	 * 
	 * <p>
	 * If you want different behaviour, you might like to use the
	 * {@link PrintStreamScenarioReporter}, or look at the {@link PropertyBasedConfiguration}
	 * which provides a mechanism for altering this behaviour in different
	 * environments.
	 */
	public ScenarioReporter forReportingScenarios() {
		return new PassSilentlyDecorator(new PrintStreamScenarioReporter());
	}

	/**
	 * Provides the keywords in English
	 */
	public KeyWords keywords() {
		return new I18nKeyWords();
	}

	/**
	 * Generates stepdocs
	 */
	public StepdocGenerator forGeneratingStepdoc() {
		return new DefaultStepdocGenerator();
	}

	/**
	 * Reports stepdocs to {@link System.out}
	 */
	public StepdocReporter forReportingStepdoc() {
		return new PrintStreamStepdocReporter(true);
	}

}
