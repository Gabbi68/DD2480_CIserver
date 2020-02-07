# CI server DD2480 Group 16

DD2480 assignment 2 - Group 16 Aditya Budithi, Martin Gabrielsen, Nicolai Hellesnes & Stina Långström

## Introduction

Continuous integration is a development practices that handles the integration of code to a shared repository. The common practice is for the developers to integrate their code several times a day to minimize the overhead of merging. Each integration is automatically verified by the continuous integration server that does an automated build and test. The server notifies the users of the result.

## Overview

This is a CI-server which should build and test files uploaded to a git-repository. The result of the build and tests gets sent as a email notification to the users of the repository.

# Files

 - main
 - SendMail

# Run

Create a server object, use the server method setHandler with ContinuousIntegrationServer as parameter, then use the start and join methods for the server.

` Server server = new Server(8080);`

`server.setHandler(new ContinuousIntegrationServer());`

`server.start();`
        
`server.join(); `

### Main

- build

The build function take a source directory as in input, it then utilize the listfiles() function to added to a gobal variable array that it loops through and builds each file in the project. For every file a build is made and a "Build Successful" message is added in the string builder if the file was build correctly, if not a "Build failed" message is added.

Test the function: The function was tested by adding several programs to the git repository, both files that could be build and not and the result was observed for every file.

- listFilesForFolder

Takes a souce directory and finds every .java file in the given directory.

- runtests

The function runs the builded test files of the project. The test files has to have the name "test" in them to be run. Every file is executed and the output from the files is saved in a string builder, this includes both information about the test cases and information of a possible error message. For every file a process is created and run with exec.

Test the function: The function was tested with different test-files as input, these test files include for example HelloWorldTest which is a file that will use both the output stream and the error-stream due to an exception and errorTest that is a function that never returns due to an error. 
- jsonParser

The function retrieves "clone_url", "branch", "email", and "sha" from a JSON-formatted string received from a GitHub webhook.

Test the function: The function was tested with exampleJSON.txt, observations were made on how the function handled the file and if the output was as expected.
- writeToFile

The function writes build history to a local file (buildHistory.txt) in JSON format.

- getProjectFromGIT

The function is used to clone the project to a given directory and uses the input from the JSON (webhook) to selcet the clone url and branch to clone.

Test the function: The function was tested by doing several commits to different branches and observe weather the correct branch was run through the CI server for every commit.

### SendMail

- sendMail: Tested manually by tring to send a mail and verifying that it got received.

### Test

- HelloWorld
- failHelloWorld
- test
- aFile.jar
- dummyTest.jar
- ErrorTest: 
- HelloWorldTest

# Contributions

 - Stina- runtests, test files
 - Martin- build, listFilesForFolder, webhook, test file, getProjectFromGIT
 - Nicolai- sendMail, ngrok
 - Aditya- JSON parser, build history, test files
