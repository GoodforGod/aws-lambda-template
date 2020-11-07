package io.lambda;

import io.lambda.aws.Lambda;

import javax.inject.Singleton;

@Singleton
public class TemplateLambda implements Lambda<String, String> {

    @Override
    public String handle(String request) {
        return "response for " + request;
    }
}
