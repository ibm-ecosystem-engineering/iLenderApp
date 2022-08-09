FROM python:3.8

RUN pip install locust

COPY locustfile.py .
COPY run.sh .
RUN chmod +x ./run.sh

ENV P_USERS=1000
ENV P_USER_SPAWN_RATE=10
ENV P_TIME_DURATION=4m 
ENV P_HOST=http://1.1.1.1:30500
ENV P_LOG=ERROR
ENV P_RUN_MODE=training


ENTRYPOINT ["./run.sh"]