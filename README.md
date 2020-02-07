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

(WRITE HOW THE FUCTIONS WORKS AND HOW THEY ARE TESTED)
- build
- listFilesForFolder
- runtests

The function runs the builded test files of the project. The test files has to have the name "test" in them to be run. Every file is executed and the output from the files is saved in a string builder, this includes both information about the test cases and information of a possible error message. For every file a process is created and run with exec.

Test the function: The function was tested with different test-files as input, these test files include for example HelloWorldTest which is a file that will use both the output stream and the error-stream due to an exception and errorTest that is a function that never returns due to an error. 

- jsonParser

Extracts email, clone url, branch, and sha key from JSON formatted string sent by GitHub webhook.

- writeToFile

Stores build information such as pusher id, commit id, and timestamp inside a JSON formatted text file. 

- getProjectFromGIT

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
 - Martin- build, listFilesForFolder, webhook, test file
 - Nicolai- sendMail, ngrok
 - Aditya- JSON parser, build history, test files
Velidation test2
