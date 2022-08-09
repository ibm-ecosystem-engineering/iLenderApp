#!/usr/bin/env bash

echo "build all jar Started .... "

cd ../../../
rootPATH="$(pwd)"
echo "RootPath value is ............> $rootPATH"

cd $rootPATH/microservices/creditscoreservice
mvn clean package

cd $rootPATH/microservices/customerprofileservice
mvn clean package

cd $rootPATH/microservices/frontwebservice
mvn clean package

cd $rootPATH/microservices//loanprocessorservice
mvn clean package

cd $rootPATH/microservices/loanservice
mvn clean package

cd $rootPATH/microservices/openbankingservice
mvn clean package

cd $rootPATH/microservices/userservice
mvn clean package

cd $rootPATH/microservices/bigbankservice
mvn clean package

cd $rootPATH/microservices/greatbankservice
mvn clean package

echo "build all jar completed ...."
