# personal-finance-tracker
Basic personal finance tracker calculating income, expense and balance for a user

# Instructions
Clone the repository

Start up as a regular Spring Boot project

This app is using Spring Boot 3 with Java 17 (you need to have it installed)

For database it is right now using in-memory H2 so you don't need any additional setup

This is still a work in progress, but Spring Security is implemented

Only /auth endpoints are available at first

Instructions for using the different endpoints provided can be found at http://localhost:8080/swagger-ui/index.html after start up

Since it is currently using H2 in-memory database you should first /register and when you receive the token, send it as a authorization bearer in all other requests
