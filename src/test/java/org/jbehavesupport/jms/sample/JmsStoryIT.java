package org.jbehavesupport.jms.sample;

import org.jbehavesupport.core.AbstractSpringStories;
import org.jbehavesupport.jms.TestConfig;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

@ContextConfiguration(classes = TestConfig.class)
public class JmsStoryIT extends AbstractSpringStories {

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList(
                "org/jbehavesupport/jms/sample/Jms.story"
        );
    }

}
