# SearchApp
This app implements a command line driven text search engine aiming at reading all the text files in the given directory, building an in-memory representation of the files and their contents and giving a command prompt at which interactive searches can be performed. The search take the words given on the prompt and return a list of the top 10 (maximum) matching filenames in rank order, giving the rank score against each match.


## Requirements
Not using any external libraries.

## Short Description
An hexagonal architecture has been used to seperate the different layer of the application.  
Depending on the user input a State handling a specific type of Message is computed generating a specific command.  
The State conserves the previous results from former queries to avoid computing it again (up to the 20 last queries).  
In this app the Search and Quit messages have been implemented. Other types of Messages can further be added.

## Model
The input query is divided into words, the word are delimited by blank space.
The class FileData enables a representation of the content of a text file and its filename.
The search algorithm consists on calculating the ratio of the filtered query and the original query. The filter query is the original query with only the words present in the text file.

## Prerequesites
The program has been implemented using the following versions:
* sbt 1.5.3
* Java 11.0.11
* Scala 2.13.4.

## Tests
The impossibility to use external library like cats-effects has made harder the isolation of side-effects coming from the user.
The init function of the SearchApp object produce an Either validating or not a correct extraction of the text files content.
Unitary tests for the Scoring model can be found in ScoringSpec, while CommandSpec ensures that the limit of maximum results remains 10.
To run the tests, run the command ```sbt test``` from the root directory of this program (adevinta-word-searcher/).

## How to use
1. Select a directory inside the project where you would like to look for text files (the implementation does not allow to look into subdirectories)
2. From the root directory of the project, run the following command: ```sbt "run directory_path"```

Cautious! The "" are necessary in the former command.

As a running example, some text files have been placed in the directory src/main/resources/, you can run the program for this directory by running ```sbt "run src/main/resources/"```.
