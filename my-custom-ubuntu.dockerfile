# My custom Ubuntu 18.04 with tools necessary to run SinglePageFullHtml
# Build image with:  docker build my-custom-ubuntu

FROM ubuntu:18.04
MAINTAINER Mark Front, https://github.com/markfront

ENV TZ=America/Vancouver
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get update --fix-missing
RUN apt-get upgrade -y
RUN apt-get install -y software-properties-common

RUN apt-get install -y openjdk-8-jdk

RUN apt-get install -y python3
RUN apt-get install -y python3-pip

RUN apt-get install -y firefox
RUN apt-get install -y xauth
RUN apt-get install -y firefox-geckodriver

RUN apt-get install -y python3-tk python3-dev
RUN pip3 install pyautogui
RUN pip3 install selenium

RUN apt-get -y install maven
RUN apt-get -y install git

WORKDIR /root

RUN mkdir git

#RUN cd git && \
#	git clone https://github.com/markfront/SinglePageFullHtml.git && \
#	cd SinglePageFullHtml && \
#	mvn clean compile package 

RUN cd git && \
	git clone https://github.com/markfront/SPFH_RestApi && \
	cd SPFH_RestApi && \
	mvn -N io.takari:maven:wrapper && \
	./mvnw clean package

EXPOSE 8080

ENTRYPOINT cd git/SPFH_RestApi && \
			./mvnw spring-boot:run

VOLUME /tmp

