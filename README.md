# docker-prometheus-java
 test out prometheus / grafana / java in docker

# Create the overlay network
 docker network create test-docker-net

# Pull images and bring up the containers (-d runs detached from console)
 docker-compose up -d

# Check container status
 docker ps

# scripts to get a shell or logs from a container in ./scripts
 ./scripts/getshell.sh <containername>
 ./scripts/getlogs.sh <containername>

# Stop all containers
 docker-compose stop

# Serverapp container copies in source on build, if source changes rebuild the container to rebuild the app.
 docker-compose stop serverapp
 docker-compose build serverapp
 docker-compose up -d serverapp

 # notes
 * cAdvisor pulls docker container stats but has known issues on osx, should work on linux but not tested
 * should split build and run for java serverapp so build is run by a pipline and output retrieved by container on start to run
 * alertmanager needs a proper hook endpoint for slack to issue alerts and need to take time to set proper alert thresholds and filtering, this can be done in alert.rules in prometheus config
