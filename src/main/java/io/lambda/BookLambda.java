package io.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import io.aws.lambda.runtime.Lambda;
import io.micronaut.core.annotation.Introspected;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.UUID;

@Introspected
@Singleton
public class BookLambda implements Lambda<Book, User> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Book handle(User user, @NotNull Context context) {
        logger.info("Processing User with name: {}", user.getName());

        return new Book(UUID.randomUUID().toString(), user.getName());
    }
}
