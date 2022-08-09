#!/usr/bin/env bash

echo "build Started ...."

docker build -f Dockerfile -t docker.io/gandigit/ilender-load-dev-2:0.0.1 .

docker push docker.io/gandigit/ilender-load-dev-2:0.0.1

echo "build completed ...."