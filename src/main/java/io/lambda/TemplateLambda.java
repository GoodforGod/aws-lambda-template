package io.lambda;

import io.aws.lambda.runtime.Lambda;
import io.micronaut.core.annotation.TypeHint;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;
import java.util.UUID;

@TypeHint(
        value = { Book.class, User.class },
        accessType = { TypeHint.AccessType.ALL_DECLARED_CONSTRUCTORS,
                TypeHint.AccessType.ALL_DECLARED_METHODS,
                TypeHint.AccessType.ALL_DECLARED_FIELDS,
                TypeHint.AccessType.ALL_PUBLIC_CONSTRUCTORS,
                TypeHint.AccessType.ALL_PUBLIC_METHODS })
@Singleton
public class TemplateLambda implements Lambda<Book, User> {

    @Override
    public Book handle(@NotNull User user) {
        return new Book(UUID.randomUUID().toString(), user.getName());
    }
}
