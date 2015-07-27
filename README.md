# What is pimot?


Web-service for Raspberry PI Pi-mote Control


# Quick start

Prerequisites:

    * Java 8 or newer (installation instructions http://www.rpiblog.com/2014/03/installing-oracle-jdk-8-on-raspberry-pi.html)
    * Raspberry PI
    * Pi-mote Control (url???)

Install:

    > ssh to your-raspberry-pi
    > wget http://.../pimote.jar .
    
Run:

    > sudo java -jar pimote.jar
    
Query:

    > curl raspberry-pi-ip:8080/
    
# Basics

The sockets and its states can be accesses via HTTP using a webbrowser (eg. firefox) or commands like 'cUrl'. 
In the examples below raspberry-pi-ip is the actual ip address of your Raspberry Pi and for simplicity we'll be using
cUrl command.


# View sockets states:
 
    > curl raspberry-pi-ip:8080/
    
Output: [{"id":1,"active":false},{"id":2,"active":false},{"id":3,"active":false},{"id":4,"active":false}]

Meaning all sockets are switched off

    > curl raspberry-pi-ip:8080/1/
    
Output: [{"id":1,"active":false}]

    > curl raspberry-pi-ip:8080/1,3/
    
Output: [{"id":1,"active":false},{"id":3,"active":true}]


> curl raspberry-pi-ip:8080/1/on

> curl raspberry-pi-ip:8080/1/off

> curl raspberry-pi-ip:8080/1,3/on

> curl raspberry-pi-ip:8080/1,3/off

# Wrap up

Let's gracefully shutdown server:

    > curl -X POST raspberry-pi-ip:8080/shutdown
