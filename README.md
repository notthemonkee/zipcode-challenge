# ZIP Code Parser Challenge

## Overview
This is a small example application that shows one approach to solve the task of taking a collection of 5-digit ZIP code ranges and producing the minimum number of ranges required to represent the same restrictions as the input.

## Building the application

This project uses Maven for dependency management along with the Maven Shade Plugin to package the application into a single jar file. 


From the root of the project, run the command:

`mvn clean package`

This will produce an artifact in the target directory named ZipCodeChallenge.jar. This jar contains all its dependencies and can be copied to another location and run from there.

## Running the command line application
To run the application from the command line, execute the jar command passing the jar file and the ZIP code range as the first argument

`java -jar ZipCodeChallenge.jar <ZIP code ranges>`

e.g. 
`java -jar ZipCodeChallenge.jar "[94133,94133] [94200,94299] [94600,94699]"`

Notice the required quotes around the ZIP code range because it includes spaces. 