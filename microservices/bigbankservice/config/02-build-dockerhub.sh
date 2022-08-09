#!/usr/bin/env bash

echo "docker build Started ...."

export SERVICE_NAME=ilender-bigbank

../../../deployment/build-dockerhub-common.sh

echo "docker build completed ...."

