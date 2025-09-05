FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu

RUN apt-get update && apt-get install -y openssh-server
RUN mkdir /var/run/sshd

RUN echo 'root:Docker!' | chpasswd

RUN apt-get update && apt-get -y install apache2

ENV JAVA_HOME=/usr/lib/jvm/msopenjdk-21-amd64

COPY sshd_config /etc/ssh/

COPY startup.sh /startup.sh
RUN chmod +x /startup.sh

COPY azureconntest-1.0-SNAPSHOT.jar /tmp/
COPY azureconntest-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp/
COPY ports.conf /etc/apache2/ports.conf
COPY 000-default.conf /etc/apache2/sites-available/000-default.conf

#ENTRYPOINT ["/startup.sh"]
ENTRYPOINT ["tail", "-f", "/dev/null"]