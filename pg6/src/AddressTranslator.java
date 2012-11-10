import java.io.*;
import java.util.*;

public class AddressTranslator {

	public static void main(String[] args){
		//String inputFile = args[0];
		String inputFile = "InputFile.txt";

		try{
			BufferedReader inFile = new BufferedReader(new FileReader(inputFile));
			String line;
			int addr;
			while((line = inFile.readLine())!= null){
				addr = Integer.valueOf(line);
				addr = addr<<8;
				addr = addr>>8;
				System.out.println(addr);
			}
		}
		catch (FileNotFoundException fnfe) {
			throw new Error("Unable to find file " + inputFile);
		}
		catch (IOException ioe) {
			throw new Error("Error processing " + inputFile);
		}
	}
}
