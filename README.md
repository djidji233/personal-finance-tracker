# personal-finance-tracker
Basic personal finance tracker calculating income, expense and balance for a user

# Instructions
Clone the repository

Start up as a regular Spring Boot project

This app is using Spring Boot 3 with Java 17 (you need to have it installed)

For database it is right now using in-memory H2 so you don't need any additional setup

Instructions for using the different endpoints provided can be found at http://localhost:8080/swagger-ui/index.html after start up

I would recommend looking at initial logic and code here, but not testing it through Postman - checkout and test the AUTH branch instead!

There is no Spring Security here and the logic with sending userId as request param is not that intuitive.

This was the initial stage, for which I wrote tests (that are now failing at the auth branch)
