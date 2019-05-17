#!/bin/sh
path=/home/jack/git/push
cd $path
git pull
mvn clean package -Dmaven.test.skip=true
cd target
#nohup java -jar push-1.0.0-GA.jar --spring.profiles.active=prod > catalina.out 2 > &1 &
java -jar push-1.0.0-GA.jar --spring.profiles.active=prod