##SmartVaadin

Template for a simple Vaadin template that only requires a JEE Servlet 3.0 container to run.
Java JVM >7 and Apache Maven are to be installed on dev machine.


###Workflow

To compile the entire project,

    mvn install

To run the application, 

    mvn jetty:run 

and open http://localhost:8080/ . Username = vaadin, Password = bluemix


Debugging client side code

    mvn vaadin:run-codeserver

on a separate console while the application is running. Then activate Super Dev Mode in the debug window of the application


###Deploy

To produce a deployable production mode WAR:

.change productionMode to true in the servlet class configuration (nested in the UI class)
.run

    mvn clean vaadin:compile-theme package

.test with 

    mvn jetty:run-war





