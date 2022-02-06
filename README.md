# online-TODO-list

features considered :

User Role Based JWT authentication with signup feature
User can create TODOs
Todo list is sorted by target date in descending order
User can check/uncheck todo
User can delete the todo
Login/Logout feature

To Run Jar file :
java -jar TODO-list-0.0.1-SNAPSHOT.jar
and visit localhost:8080

To build jar package :
To regenerate angular pages 
Node.js is required to run the development instance. To check node is installed on your machine, in the terminal run the command:
node -v
If Node.js is not installed on your machine, please visit the link below for installation instructions
https://nodejs.org/en/download/

Goto frontend/todo-list code directory :
To install the required dependencies, run the command:
npm install

to build,run the command :
ng build
Build files will be get generated in ../backend/TODO-list/src/main/resources/public folder (as mentioned in angular.json)

Goto backend/todo-list code directory
RUN: 'maven clean package' in terminal/cmd.


Code Explaination :
The entire coding logic is divided into two main part, frontend and backend code. Frontend is developed using Angular 8 and backend using Spring Boot 2 version. These technologies have been chosen because of their ease of development.

Front End Code Explaination :
Front end code is developed using components like login, logout, menu, home, footer, todo list and todo. Similarly, angular code is architected into multiple layer with service, constant and component layer. Additionally, routing has been considered with active routing and current authentication. Angular code is packaged using 'ng build' command in root directory and dist files are generated into public folder of backend code' resources folder

Back End Code Explaination :
Backend is organised into JwtAuthenticationRestController and TodoController process. Appropriately Model beans, service and repository classes/interfaces have been created. JPARepository interface is used to provide JPA features around the model classes.

H2 with hibernate is configured to store in-memory data. Similarly, exception handling, utility, web security classes have been configured.

Things to consider in future :
JUnit test cases - considering all testcases for each method.
angular test cases - - considering all testcases for each method.
e2e test cases - e2e test script.
Spring Test cases - e2e integration testing.
Code optimisation - using caching to store todos at client/server side.