Author : Thanh Truong
Email  : tcthanh@gmail.com

------------------------------------------------------------------
Requirements
-------------------------------------------------------------------
* [Java Platform (JDK) 7]
(http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

------------------------------------------------------------------
Quick start
------------------------------------------------------------------
0. mvn clean
1. mvn compile
1. mvn package
2. java -jar target/challenge-1.0.jar server configuration.yml

3. Point your browser to [http://localhost:8080/puzzle](http://localhost:8080/puzzle)
4. Upload a test data file (strictly followed the agreed format in the challenge)
5. Click 'Solve it'

------------------------------------------------------------------
Implementation
------------------------------------------------------------------
The source folder contains

ooyala
 |-- src
 |    |-- main
 |    |    |-- java
 |    |    |    |-- example
 |    |    |    |    |-- config
 |    |    |    |    |    |-- ExampleServiceConfiguration.java
 |    |    |    |    |    |-- MessagesConfiguration.java  
 |    |    |    |    |-- resource
 |    |    |    |    |    |-- PuzzleResource.java
 |    |    |    |    |    |-- SolveResource.java
 |    |    |    |    |-- solver
 |    |    |    |    |    |-- PuzzleSolver.java
 |    |    |    |    |-- ExampleService.java    		 
 |    |    +-- resources
 |    |    |    |-- pages
 |    |    |          |-- puzzle.html
 |    |    |                     
 |    +-- test
 |         +-- sample1.txt
 |         +-- sample2.txt
 |         +-- sample3.txt
 +-- pom.xml

 -----------------------------------------------------------------
Notes
------------------------------------------------------------------
- To fulfill the requirement for delivering result within 1 minute
  I implemented system clock to stop the running solver.  
 
- For the sample 3, my solver showed better "total revenue" the result
   given by the challenge
   
- There are rooms to improve of course :)