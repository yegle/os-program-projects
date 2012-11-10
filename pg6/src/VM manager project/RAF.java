/**
 * RAF.java
 *
 * This program demonstrates using the RandomAccessFile api
 * 
 * A random access file permits reads/writes to specific byte
 * positions in a file. The seek() method permits moving to
 * specific potions of the file to read or write. 
 * Note this also allows a file to have "holes" by seeking to
 * position N and then writing data. Bytes (0 ... N-1) are empty
 * (i.e. are a hole.)
 *
 * Usage:
 *	java RAF <integer value>
 */

import java.io.*;

public class RAF
{
	public static void main(String[] args) throws java.io.IOException {
		if (args.length != 1) {
			System.err.println("Usage: java RAF <integer value>");
			System.exit(0);
		}

		// the file representing the simulated  disk
     	File fileName;
     	RandomAccessFile disk = null;
		int val;

		int number = Integer.parseInt(args[0]);

		try {
               fileName = new File("RAFFile");
               disk = new RandomAccessFile(fileName, "rw");

			/**
			 * now write an int to the file
			 * beginning at position 100
			 * NOTE - integer is 4 bytes
			 */

			// seek to byte position 100
			disk.seek(100);

			// write a 4-byte integer
			disk.writeInt(number);

			// again seek to byte position 100
			disk.seek(100);
			
			/**
			 * read() returns a byte, but since bytes
			 * in Java are signed, we use an integer
			 * to store its value to obtain the signed
			 * value of the byte.
			 */
			for (int i = 0; i < 4; i++) {
				val = disk.read();
				System.out.println("byte " + i + ": " + val);
			}
			
			// seek again to byte position 100
			disk.seek(100);

			// now read 4 bytes
			byte[] data = new byte[4];
			disk.read(data);
			for (int i = 0; i < 4; i++) 
				System.out.println("byte " + i + ": " + data[i]);
          }
          catch (IOException e) {
               System.err.println ("Unable to start the disk");
               System.exit(1);
          }
		finally {
			disk.close();
		}
	}
}
