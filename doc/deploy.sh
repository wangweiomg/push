#!/bin/sh
path=/home/jack/git/push
cd $path
git pull
mvn clean package -U -Dmaven.test.skip=true
cd target
java -jar push-1.0.0-GA.jar