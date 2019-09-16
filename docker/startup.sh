#!/bin/bash

#apt-get update
#apt-get -y install apache2
#cp -p /tmp/ports.conf /etc/apache2/ports.conf
#cp -p /tmp/000-default.conf /etc/apache2/sites-available/000-default.conf
sed -i "s/PORT/$PORT/g" /etc/apache2/ports.conf
sed -i "s/PORT/$PORT/g" /etc/apache2/sites-available/000-default.conf
service apache2 start

# Configure the SSH port number.
sed -i "s/SSH_PORT/$SSH_PORT/g" /etc/ssh/sshd_config

exec /usr/sbin/sshd -D

