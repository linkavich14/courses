# Courses Code

Small program that show you what courses can be taken depending of prerequisites, the description of the problem is the following

# Description

You are currently studying to complete a software development certification provided by an online education provider. The certification requires the completion of twelve online course units, which may or may not specify pre-requisite course units. In order to register for a new course unit, you must have already completed _all_ pre-requisite course units _before_ registering for the new course.

You decide to write a program that, given a list of courses and their pre-requisites, produces a possible order in which you may complete as many of the provided course units as possible, adhering to the pre-requisite requirements.

The following files are provided: 

- A comma-seperated file containing course titles and unique ids, "courses.csv".
- A comma-seperated file containing course pre-requisites, by id, "prerequisites.csv".


# How to run it

- On the project folder in the terminal run: mvn compile
- Then after it is compiled: mvn exec:java -Dexec.mainClass="app.StartApp" -Dexec.args="prerequisites.csv courses.csv" 

Also can be run directly from eclipse using the StartApp.class and adding the location of the files when requested by the program
The program includes a testing package with some small tests


