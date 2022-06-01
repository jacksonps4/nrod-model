# NROD MODEL LIBRARY

## Purpose

This library provides a Java model for the abstractions published in the 
National Rail On Demand (NROD) APIs, aimed at speeding up development work for
those who are building applications - to save them having to implement from
scratch.


## Building

Compiling the code requires a Java SDK supporting at least Java 8 and a
Maven installation.

Building the library should be as simple as:
```
$ git checkout <url>
$ mvn install
```

## Contributions

Contributions are welcome - please fork the project and raise a pull-request 
in GitHub.


## How to use this library

The NROD API provides the ability to download a CIF schedule file, which 
contains the information needed to understand which trains are scheduled to run
at which times.


### Architecture

The library uses an in-memory model - so it trades off speed of access to data
for performance. This means you are able to run queries quickly but there is an
initial up-front time cost for parsing the file and an ongoing fixed memory 
cost for keeping the data in memory. 


### Parsing a CIF schedule



### Querying a CIF schedule

