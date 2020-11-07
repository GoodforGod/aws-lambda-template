#!/bin/bash

docker build -t aws-lambda-template .
mkdir -p build
docker run --rm --entrypoint cat light-product-aws-lambda /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000
