package org.jbehave.core.junit;

import static java.util.Arrays.asList;

import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.Embeddable;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryPathResolver;
import org.junit.Test;

/**
 * <p>
 * JUnit-runnable entry-point to run a single story specified by a {@link Embeddable} class.
 * </p>
 */
public abstract class JUnitStory extends ConfigurableEmbedder {
    
	@Test
    public void run() throws Throwable {        
        Embedder embedder = configuredEmbedder();
        StoryPathResolver pathResolver = embedder.configuration().storyPathResolver();
        String storyPath = pathResolver.resolve(this.getClass());
        embedder.runStoriesAsPaths(asList(storyPath));
    }

 
}
