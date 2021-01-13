package io.lambda;

import io.aws.lambda.runtime.handler.impl.AwsEventHandler;
import io.aws.lambda.runtime.model.AwsRequestContext;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 27.10.2020
 */
class TemplateLambdaTests extends Assertions {

    @Test
    void handleSuccess() {
        try (final ApplicationContext context = ApplicationContext.run()) {
            final AwsEventHandler handler = context.getBean(AwsEventHandler.class);
            final AwsRequestContext requestContext = new AwsRequestContext("1", null);
            final String response = handler.handle("bob", requestContext);
            assertEquals("response to: bob", response);
        }
    }
}
