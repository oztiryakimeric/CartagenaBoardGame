#Report

This is a report of the project.  In the UMLs section an overview of the design is presented. The rest of the document 
discusses design decisions and some other relevant information.

##UML Diagrams

This section presents the UML designs. To keep things simple it only shows the more important methods and fields.


![Uml Diagram of model part](docs/modeldiagram.png)

##Design Decisions

This section discusses some of the key design descisions.

First of all, we decided to use MVC architectural pattern to divide our view and logic part of our application to increase
the code quality. This also provides flexibility to extend our program to future needs and easier debugging features. Besides
of that, we implement decorator pattern to our Game class in order to extend our game with new requirements. For example, for 
multiplayer features, we have to implement just one MultiplayerGame class to extend our game with multiplayer features. Besides
of these, using command pattern makes our code cleaner. It's a powerful tool when use it with sockets. Lastly, adapter pattern
saved our lives when we connect our model part to view part such as displaying symbol list.

##Known Bugs

Due to our lack of experience on the pararell programming, multiplayer part of our project has some bugs that we faced late
development. For example, if client losts the connection between server, server crashes when its broadcast method called. 
If the port which server uses is not empty, what I mean is that is used by another program, again it crashes. Also, if client
try to connect a server, while there is not any server created, it crashes.