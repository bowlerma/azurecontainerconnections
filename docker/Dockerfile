FROM ubuntu:16.04

RUN apt-get update && apt-get install -y openssh-server
RUN mkdir /var/run/sshd

RUN echo 'root:Docker!' | chpasswd

RUN apt-get update && apt-get -y install apache2

ENV JAVA_HOME=/usr/lib/jvm/zulu-8-amd64

# Zulu installation
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0x219BD9C9 && \
    echo "deb http://repos.azulsystems.com/ubuntu stable main" >> /etc/apt/sources.list.d/zulu.list && \
    apt-get -qq update && \
    apt-get -qqy install zulu-8=8.23.0.3 && \
    rm -rf /var/lib/apt/lists/*

COPY sshd_config /etc/ssh/

COPY startup.sh /startup.sh
RUN chmod +x /startup.sh

COPY azureconntest-1.0-SNAPSHOT.jar /tmp/
COPY azureconntest-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp/
COPY ports.conf /etc/apache2/ports.conf
COPY 000-default.conf /etc/apache2/sites-available/000-default.conf

ENTRYPOINT ["/startup.sh"]
