package io.lambda;

import io.aws.lambda.runtime.Lambda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class BookLambda implements Lambda<Book, User> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Book handle(User user) {
        logger.info("Processing User with name: {}", user.getName());
        return new Book(UUID.randomUUID().toString(), user.getName());
    }
}
