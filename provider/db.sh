#!/bin/bash

docker cp init.sql mdb:/
docker exec -it mdb bash -c "mysql -u root -pmaster < /init.sql"
