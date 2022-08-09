#!/usr/bin/env bash

echo "docker build common Started ...."

#### Params

echo "IMAGE_SUFFIX 1....: $IMAGE_SUFFIX"


if [ -z "$IMAGE_SUFFIX" ] 
then
    IMAGE_SUFFIX=oss2:0.0.1
fi
if [ -z "$REGISTRY_USER" ] 
then
    REGISTRY_USER=gandigit
fi


echo "IMAGE_SUFFIX 2....: $IMAGE_SUFFIX"


#  SERVICE_NAME - should be set by the calling .sh file
DOCKER_IMAGE_FULL_NAME=${REGISTRY_USER}/${SERVICE_NAME}-${IMAGE_SUFFIX}
echo "DOCKER_IMAGE_FULL_NAME ....: $DOCKER_IMAGE_FULL_NAME"

#### Copying supported files from cop to config under microservices folder
echo "copying supported files ...."
ls -l
cp ../target/*.jar app.jar
cp -r ../../../deployment/build/support-files .
ls -l

#### Docker build
echo "docker build ...."
docker build . --no-cache  -f Dockerfile -t $DOCKER_IMAGE_FULL_NAME 

#### Docker push
echo "docker push ...."
docker push $DOCKER_IMAGE_FULL_NAME

#### Deleting supported files config under microservices folder
echo "removing supported files ...."
rm app.jar
rm -dr support-files
ls -l

echo "docker build common completed ...."
