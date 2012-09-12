# HW #2 for Operating System

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

## Test

## Document

The javadoc was included in the source code. To generate the document, go to `src/` directory then run `make doc`. The document will be generated at the root directory.
