#!/bin/bash

docker build -t micronaut-lambda .
mkdir -p build
docker run --rm --entrypoint cat micronaut-lambda /home/application/function.zip > build/function.zip

# sam local start-api -t sam.yaml -p 3000
