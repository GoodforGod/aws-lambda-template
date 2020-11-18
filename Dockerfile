FROM amazonlinux:2018.03.0.20200602.1 as graalvm
WORKDIR /home/application

ENV LANG=en_US.UTF-8

RUN yum install -y gcc gcc-c++ libc6-dev zlib1g-dev curl bash zlib zlib-devel zip

ENV GRAAL_VERSION 20.3.0
ENV JDK_VERSION java11
ENV GRAAL_FILENAME graalvm-ce-${JDK_VERSION}-linux-amd64-${GRAAL_VERSION}.tar.gz

RUN curl -4 -L https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAAL_VERSION}/${GRAAL_FILENAME} -o /tmp/${GRAAL_FILENAME}

RUN tar -zxvf /tmp/${GRAAL_FILENAME} -C /tmp \
    && mv /tmp/graalvm-ce-${JDK_VERSION}-${GRAAL_VERSION} /usr/lib/graalvm

RUN rm -rf /tmp/*
CMD ["/usr/lib/graalvm/bin/native-image"]

FROM graalvm
WORKDIR /home/application

RUN /usr/lib/graalvm/bin/gu install native-image

COPY build/libs/*all.jar build/libs/aws-lambda.jar
COPY bootstrap bootstrap

RUN /usr/lib/graalvm/bin/native-image --no-server -cp build/libs/aws-lambda.jar
RUN chmod 777 bootstrap
RUN chmod 777 aws-lambda

RUN zip -j function.zip bootstrap aws-lambda

EXPOSE 8080
ENTRYPOINT ["/home/application/aws-lambda"]