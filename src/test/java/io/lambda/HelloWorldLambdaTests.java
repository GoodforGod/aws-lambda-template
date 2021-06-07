package io.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import io.aws.lambda.runtime.Lambda;
import io.aws.lambda.runtime.LambdaContext;
import io.aws.lambda.runtime.convert.Converter;
import io.aws.lambda.runtime.handler.EventHandler;
import io.aws.lambda.runtime.handler.impl.BodyEventHandler;
import io.aws.lambda.runtime.handler.impl.RawEventHandler;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @author GoodforGod
 * @since 27.10.2020
 */
class HelloWorldLambdaTests extends Assertions {

    @Test
    void gatewayEventHandled() {
        try (final ApplicationContext context = ApplicationContext.run()) {
            final Converter converter = context.getBean(Converter.class);
            final Lambda lambda = context.getBean(Lambda.class);

            final EventHandler handler = new BodyEventHandler(lambda, converter);
            final String payload = "{\"httpMethod\":\"GET\",\"queryStringParameters\":{\"from\":\"one\",\"to\":\"ten\"},\"isBase64Encoded\":false,\"body\":\"{\\\"name\\\":\\\"bob\\\"}\"}";
            final Context requestContext = LambdaContext.ofHeaders(Collections.emptyMap());

            final String response = handler.handle(payload, requestContext);
            assertTrue(response.contains("Hello - bob"));
        }
    }

    @Test
    void rawEventHandled() {
        try (final ApplicationContext context = ApplicationContext.run()) {
            final Converter converter = context.getBean(Converter.class);
            final Lambda lambda = context.getBean(Lambda.class);

            final EventHandler handler = new RawEventHandler(lambda, converter);
            final String payload = "{\"name\":\"bob\"}";
            final Context requestContext = LambdaContext.ofHeaders(Collections.emptyMap());

            final String response = handler.handle(payload, requestContext);
            assertTrue(response.contains("Hello - bob"));
        }
    }
}
