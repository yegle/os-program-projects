# HW #1 for Operating System

by Yuchen Ying <yegle@uga.edu> and Yue Yin <yinyue@uga.edu>.

## Program Introduction & Directory Structure

This is a simple Java program demostrating different level of Java API used for copying file to another location.

This program uses [Commons CLI](http://commons.apache.org/cli/) from Apache Fundation for command line argument parsing.

## System Requrement

This program was tested on [nike](ssh://nike.cs.uga.edu) with following requirement:

 * JDK 7u4
 * gcc 4.6.3
 * make 3.82

The version requirement may not be strict as the version given above. But only versions mentioned above was tested.

## Compile

 1. Go to `src/` directory
 2. Run `make` without any argument. Both C file and Java file will be compiled

## Test

 1. Go to `src/` directory
 2. Run `make test` to test all three APIs using random generated files

To test each APIs, run `make test_#`, where # is a number. Different number has different meaning:

 1. java.io
 2. java.nio
 3. JNI

e.g. Run `make test_1` in `src/` directory will test the `java.io` API.
