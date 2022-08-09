#!/usr/bin/env bash

echo "Run Started ...."

# export P_USERS=20
# export P_USER_SPAWN_RATE=10
# export P_TIME_DURATION=5m
# export P_TIME_DURATION=2m
# export P_TIME_DURATION=10s

# export P_USERS=20
# # export P_USERS=5
# export P_USER_SPAWN_RATE=5
# export P_TIME_DURATION=20m
# # export P_TIME_DURATION=10s

export P_USERS=1
export P_USER_SPAWN_RATE=1
export P_TIME_DURATION=5s
# export P_TIME_DURATION=10s


export P_HOST=http://ilender-frontweb-ilender-ns.itzroks-aaaaaa.cloud
export P_LOG=ERROR
export P_RUN_MODE=training

export DOCKER_IMAGE=docker.io/gandigit/ilender-load-dev-2:0.0.1

docker run -e P_USERS -e P_USER_SPAWN_RATE -e P_TIME_DURATION -e P_LOG -e P_RUN_MODE -e P_HOST $DOCKER_IMAGE 

# locust -f locustfile.py --headless -u 1000 -r 10 -t 4m -H http://1.1.1.1:30500

echo "Run completed ...."