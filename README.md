# RES_Laboratory_SMTP

Authors : Neto Patrick & Joyet Christophe 
Date	: 20 April 2018

## Description

We developped a client application (TCP) in Java to send forged emails.  
This client app uses the Socket API to communicate with a SMTP server.

## Instuctions for setting up a mock SMTP (with Docker)

### Configurate your Docker

1. install Docker
2. go where there is the `DockerFile` and then execute this command :  
	a. docker build -t mymock . (don't forget `.`)  
	b. docker run -p 2525:2525 -p 8080:8080 mymock

2525 is for the open port for the STMP protocal and 8080 is to watch the email with http

### Use MockServer

If you don't want to send immediatly the message to your vicitms, you can  use a `MockServer`.  
It give you the opportunitate to test your sending without the email go out your local machine.


### How Use Our Application 

you need to be in the folder docker-client/src/ where there is the file RES_LaboSMTP.jar.  
To execute your program execute the following command :  
	`java -jar RES_LaboSMTP.jar ARG1 ARG2`  
where ARG1 is the number of senders and ARG2 is the number of recievers. 
