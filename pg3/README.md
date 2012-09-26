# HW #3 for Operating System

by [Yuchen Ying](yegle@uga.edu) and [Yue Yin](yinyue@uga.edu).

## Program Introduction & Directory Structure

We wrote two client-server applications using JAVA sockets and RMI.


## System Requirement

This program was tested on [nike](ssh://nike.cs.uga.edu) with following requirement:

 * JDK 7u4
 * make 3.82

The version requirement may not be strict as the version given above. But only versions mentioned above was tested.

# For the socket

## Compile

 1. Go to `src/socket/` directory
 2. Run `make` without any argument. Both Server and Java will be compiled

## Test

 1. Open 2 Terminals, go to `src/socket/` directory for both
 2. In one Terminal, run `java Server` to set up the Server
 3. In the other Terminal, run `java Client` to set up the Client
 4. The Client program will ask you enter the input of String
	Type any format of String and end with enter
 5. The Client will get the response from the Server with information of Counts of Character and Digit
	Then the Client will exit automatically

# For the RMI

## Compile

 1. Go to `src/rmi/` directory
 2. Run `make` without any argument. Both Server and Java will be compiled

## Test

 1. Open 2 Terminals, go to `src/rmi/` directory for both
 2. In one Terminal, run `rmiregistry 1988 &` to start the registry and create the remote object
 3. Run `java ServerImpl` to set up the Server. Run `java ServerImpl single` for testing single thread
 4. In the other Terminal, run `java ClientRunnable` to run the client
 5. The Client program will read the source code of `ServerImpl.java` as a test string and run 10 thread to call server method
