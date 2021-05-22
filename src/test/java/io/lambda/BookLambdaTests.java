package io.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import io.aws.lambda.runtime.Lambda;
import io.aws.lambda.runtime.LambdaContext;
import io.aws.lambda.runtime.convert.Converter;
import io.aws.lambda.runtime.handler.EventHandler;
import io.aws.lambda.runtime.handler.impl.APIGatewayV2EventHandler;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

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
            final EventHandler handler = new APIGatewayV2EventHandler(lambda, converter);
            final String payload = "{\"httpMethod\":\"GET\",\"queryStringParameters\":{\"from\":\"one\",\"to\":\"ten\"},\"isBase64Encoded\":false,\"body\":\"{\\\"name\\\":\\\"bob\\\",\\\"id\\\":\\\"123\\\"}\"}";

            final Context requestContext = LambdaContext.ofHeaders(Collections.emptyMap());
            final String response = handler.handle(payload, requestContext);
            assertTrue(response.contains("bob"));
        }
    }
}
