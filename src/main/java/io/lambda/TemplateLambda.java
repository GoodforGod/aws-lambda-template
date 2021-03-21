package io.lambda;

import io.aws.lambda.runtime.Lambda;
import io.aws.lambda.runtime.model.AwsGatewayRequest;
import io.micronaut.core.annotation.TypeHint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;

@TypeHint({Book.class, User.class})
@Singleton
public class TemplateLambda implements Lambda<Book, User> {

    @Override
    public Book handle(@NotNull User user) {
        return new Book(UUID.randomUUID().toString(), user.getName());
    }
}
