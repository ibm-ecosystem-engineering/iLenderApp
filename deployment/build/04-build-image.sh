#!/usr/bin/env bash

echo "build dockerhub all Started ...."

cd ../../../
rootPATH="$(pwd)"
echo "RootPath value is ............> $rootPATH"

cd $rootPATH/microservices/creditscoreservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/customerprofileservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/frontwebservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/loanprocessorservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/loanservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/openbankingservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/userservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/bigbankservice/config
sh 02-build-dockerhub.sh

cd $rootPATH/microservices/greatbankservice/config
sh 02-build-dockerhub.sh

echo "build dockerhub all completed ...."
