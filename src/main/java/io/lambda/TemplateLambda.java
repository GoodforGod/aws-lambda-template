package io.lambda;

import io.aws.lambda.runtime.Lambda;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

@Singleton
public class TemplateLambda implements Lambda<String, String> {

    @Override
    public String handle(@NotNull String request) {
        return "response to: " + request;
    }
}
