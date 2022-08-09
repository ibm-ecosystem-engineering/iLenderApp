#!/usr/bin/env bash

echo "docker build Started ...."

export SERVICE_NAME=ilender-greatbank

../../../deployment/build-dockerhub-common.sh

echo "docker build completed ...."

