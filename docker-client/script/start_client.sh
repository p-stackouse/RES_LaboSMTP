#!/bin/sh

cp ~/CloudStation/Heig-VD/RES/Labos/RES_LaboSMTP/out/artifacts/RES_LaboSMTP_jar/RES_LaboSMTP.jar /Users/patrickneto/CloudStation/Heig-VD/RES/Labos/RES_LaboSMTP/docker-client/src/RES_LaboSMTP.jar
docker build -t client_smtp . --no-cache
