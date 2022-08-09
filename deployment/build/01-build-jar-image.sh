#!/bin/sh

echo "process started ......................................................."

# image: "gandigit/ilender-frontweb-oss:0.0.1"
export IMAGE_SUFFIX=oss:0.0.1
export REGISTRY_USER=gandigit

# docker login -u $REGISTRY_USER

#### Building jar
echo "Building jar. Output is routed to a.txt " 
echo "you can view using : tail -f a.txt " 
sh 02-build-jar.sh  > a.txt

#### Verifying jar result
sh 03-verify-result-jar.sh

#### Building docker Image
echo "Building docker Image. Output is routed to b.txt " 
echo "you can view using : tail -f b.txt " 
sh 04-build-image.sh  > b.txt

#### Verifying build image result
sh 05-verify-result-image.sh

rm a.txt
rm b.txt

echo "process completed ......................................................."