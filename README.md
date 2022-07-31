# Pre-requisites
 * Install docker
 * If running on linux uncomment the few lines in the compose file that are for MAC support.
 * Not tested on Windows as I don't have a windows box handy but should also work fine.

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
 * containers talk to each other using container name within the overlay network, default ports are forwarded to the host that is running docker.
 * credentials for grafana are hardcoded in docker-compose as admin / Password123, move this to a secrets vault / environment variables in the real world.
 * some pre-defined dashboards added to grafana, go to left menu, dashboards, browse to see the list.  Java micro-meter for app stats, docker host for host stats from node-exporter, docker containers for per container stats from cadvisor (cadvisor doesn't play nice on apple silicon mac but should be good on linux edit docker-compose.yml accordingly, it's commented)
 * cAdvisor pulls docker container stats but has known issues on osx, should work on linux but not tested
 * should split build and run for java serverapp so build is run by a pipline and output retrieved by container on start to run
 * alertmanager needs a proper hook endpoint for slack to issue alerts and need to take time to set proper alert thresholds and filtering, this can be done in alert.rules in prometheus config
 * docker / docker-compose will run the containers on any system that supports docker (windows / mac / linux). There is no need to explicitly create a Vm with vagrant in order to this as docker containers can be treated as ephemeral as all persisted data is mapped to a host volume defined in the docker-compose.yml or Dockerfile.
 * Containers provide application packaging, reliable and consistent deployment and isolation versus simply deploying all applications onto a VM.
 * For local dev just use docker or create a VM one off with virtualbox etc if you really want. For production use terraform or similar to create a consistent and managed infrastructure either on-prem or in cloud and deploy a pool of resources (manager / worker nodes) that can run your containers.  either vanilla docker or kubernetes if you have apps of a size that require orchestration.