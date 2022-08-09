#!/usr/bin/env bash

echo ""
echo "Started ...."

# export RUN_MODE=inferencing
# export RUN_MODE=training

P_HOST=http://ilender-frontweb-ilender-ns.itzroks-aaaa.cloud

locust -f locustfile.py --headless -u 10 -r 10 -t 10m -L ERROR -H $P_HOST

# locust -f locustfile.py --headless -u 1000 -r 10 -t 4m -H http://1.1.1.1:30500 -L WARNING

####
#### -t : Stop after the specified amount of time. eg: (300s, 20m, 3h, 1h30m, etc.).
#### -u : Number of concurrent Locust users
#### -r : The rate per second in which users are spawned
#### -H : Host to load test

echo "Run completed ...."