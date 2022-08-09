#!/usr/bin/env bash

echo ""
echo "Started ...."

export RUN_MODE=$P_RUN_MODE

echo "RUN_MODE : $RUN_MODE"

locust -f locustfile.py --headless -u $P_USERS -r $P_USER_SPAWN_RATE -t $P_TIME_DURATION -H $P_HOST -L $P_LOG 

# locust -f locustfile.py --headless -u 1000 -r 10 -t 4m -H http://1.1.1.1:30500

####
#### -t : Stop after the specified amount of time. eg: (300s, 20m, 3h, 1h30m, etc.).
#### -u : Number of concurrent Locust users
#### -r : The rate per second in which users are spawned
#### -H : Host to load test