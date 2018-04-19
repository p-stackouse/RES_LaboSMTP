#!/bin/sh

docker build -t serversmtp . --no-cache
docker run -p 2526:2526 -p 8282:8282 serversmtp


