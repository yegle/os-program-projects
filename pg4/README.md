# HW #4 for Operating System

by [Yuchen Ying](yegle@uga.edu) and [Yue Yin](yinyue@uga.edu).

## Program Introduction & Directory Structure

In HW #4, we wrote a multi-thread Java program to test synchronization of threads.

## System Requirement

This program was tested on [nike.cs.uga.edu](ssh://nike.cs.uga.edu) with following requirement:

 * JDK 7u4
 * make 3.82

The version requirement may not be strict as the version given above. But only versions mentioned above was tested.

## Compile

 1. Go to `src/` directory
 2. Run `make` without any argument.

## Run

 1. Compile first
 2. Run by `Java Dot NUMBER_OF_THREADS`. Replace `NUMBER_OF_THREADS` with your desired number of threads
 3. Use `time` command to monitor time cost

## Document

The javadoc was included in the source code. To generate the document, go to `src/` then run `make doc`. The document will be generated at `$PWD/doc`.


# Analysis of graph

The graph is included in the source tarball. In the graph we found sigle-thread has the least time cost. Adding thread doesn't always lower the time cost.

We guess this is cased by the time wasted in thread synchronization (the barrier code). In single-threaded mode, there's no time wasted in thread synchronization and thread creation.

There's a huge difference of time cost between threads lesser than 6 and threads more than 7. We guess this is caused by some other processes on the test machine.

# Division of works

 * Yuchen Ying

    - `Barrier.java`/`BarrierImpl.java`
    - `Dot.java`
    - `Multiplier.java`
    - This document

 * Yue Yin

    - `Adder.java`
    - javadoc in source code
    - testing result & graph generation
