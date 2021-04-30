## AWS Lambda Template

Java 11+ AWS Lambda Template with GraalVM native compatibility and Micronaut DI support.

## Runtime

Template uses [AWS Lambda Runtime](https://github.com/GoodforGod/aws-lambda-runtime-micronaut) with GraalVM native compatibility.

## Deploy

If you are on Windows and having issues with 
```
Error: fork/exec /var/task/bootstrap: no such file or directory
```

Check bootstrap file that it have **LF line** (Unix one) separator.