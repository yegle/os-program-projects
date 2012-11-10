import java.io.*;
import java.util.*;

public class AddressTranslator {

	public static void main(String[] args){
		//String inputFile = args[0];
		String inputFile = "InputFile.txt";

		try{
			BufferedReader inFile = new BufferedReader(new FileReader(inputFile));

			int addr;
			while((inFile.readLine())!= null){
				addr = Integer.valueOf(inFile.readLine());
                // 2^16 = 4^8 = 16^4
                // mask the high 16bit
                addr = addr & 0xff00;
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
