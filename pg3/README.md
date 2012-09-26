# HW #3 for Operating System

by [Yuchen Ying](yegle@uga.edu) and [Yue Yin](yinyue@uga.edu).

## Program Introduction & Directory Structure

In HW #2, we wrote two client-server applications using JAVA sockets and RMI.
This time we changed them by adding threads.

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
 2. In one Terminal, regarded as server
		(a).run `java Server` to set up the Multithread Server
		(b).run `java ServerSingle` to set up the Singlethread Server
		(c).run `java ServerPool` to ser up the Server with thread pool
 3. In the other Terminal, regarded as client 
		(a).run `java Client` to set up a single Client
		(b).run `java ClientRunnable` to set up a multithread Client(10 clients)
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
 2. In one Terminal, run `rmiregistry &` to start the registry and create the remote object
 3. Also in this Terminal, regarded as Server
		(a).Run `java ServerImpl` to set up the Server
		(b).Run `java ServerImpl simple` to set up the singlethread Server
 3. In the other Terminal, regarded as Client
		(a).Run `java Client` to set up the Client
		(b).Run `java ClientRunnable` to set up the multithread Client(10 clients)
 4. The Client program will ask you enter the input of String
	Type any format of String and end with enter
 5. The Client will get the response from the Server with information of Counts of Character and Digit
	Then the Client will exit automatically

## Document

The javadoc was included in the source code. To generate the document, go to `src/socket` or `src/rmi` directory then run `make doc`. The document will be generated at `$PWD/doc`.
