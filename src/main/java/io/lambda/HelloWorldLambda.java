package io.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.micronaut.core.annotation.Introspected;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.UUID;

@Introspected
@Singleton
public class HelloWorldLambda implements RequestHandler<User, Response> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Response handleRequest(User user, @NotNull Context context) {
        logger.info("Processing User with name: {}", user.getName());

        return new Response(UUID.randomUUID().toString(), "Hello - " + user.getName());
    }
}
