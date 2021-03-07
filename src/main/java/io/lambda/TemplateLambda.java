package io.lambda;

import io.aws.lambda.runtime.Lambda;
import io.aws.lambda.runtime.model.AwsGatewayRequest;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class TemplateLambda implements Lambda<Map, AwsGatewayRequest> {

    @Override
    public Map handle(@NotNull AwsGatewayRequest request) {
        return Map.of("queryParams", request.getQueryStringParameters());
    }
}
