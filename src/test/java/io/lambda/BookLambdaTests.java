package io.lambda;

import io.aws.lambda.runtime.Lambda;
import io.aws.lambda.runtime.convert.Converter;
import io.aws.lambda.runtime.handler.EventHandler;
import io.aws.lambda.runtime.handler.impl.GatewayEventHandler;
import io.aws.lambda.runtime.model.AwsRequestContext;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author GoodforGod
 * @since 27.10.2020
 */
class BookLambdaTests extends Assertions {

    @Test
    void handleSuccess() {
        try (final ApplicationContext context = ApplicationContext.run()) {
            final Converter converter = context.getBean(Converter.class);
            final Lambda lambda = context.getBean(Lambda.class);
            final EventHandler handler = new GatewayEventHandler(lambda, converter);
            final String payload = "{\"httpMethod\":\"GET\",\"queryStringParameters\":{\"from\":\"one\",\"to\":\"ten\"},\"isBase64Encoded\":false,\"body\":\"{\\\"name\\\":\\\"bob\\\",\\\"id\\\":\\\"123\\\"}\"}";

            final AwsRequestContext requestContext = new AwsRequestContext(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            final String response = handler.handle(payload, requestContext);
            assertTrue(response.contains("bob"));
        }
    }
}
