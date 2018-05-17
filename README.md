# Courses Code Challenge 

Small program that show you what courses can be taken depending of prerequisites

# How to run it

- On the project folder in the terminal run: mvn compile
- Then after it is compiled: mvn exec:java -Dexec.mainClass="app.StartApp" -Dexec.args="prerequisites.csv courses.csv" 

Also can be run directly from eclipse using the StartApp.class and adding the location of the files when requested by the program
The program includes a testing package with some small tests


