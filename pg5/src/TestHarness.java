/**
 * A test harness for the bankers algorithm.
 *
 * Usage:
 *	java TestHarness <one or more resources>
 *
 * I.e.
 *	java TestHarness <input file> 10 5 7
 *
 * Once this is entered, the user enters "*" to output the state of the bank.
 * Or users may enter input on the command line by using the following:
 *	[RQ || RL] <customer number> <resource #1> <#2> <#3>
 * 
 * For example, customer 4 performing a request for 2 of resource 1,
 * 3 of resource 2, and 5 of resource 3 would enter
 *		RQ 4 2 3 5
 */

import java.io.*;
import java.util.*;

public class TestHarness
{
	public static void main(String[] args) throws java.io.IOException {
		if (args.length < 1) {
			System.err.println("Usage java TestHarness <input file> <R1> <R2> ...");
			System.exit(-1);
		}

		// get the name of the input file
		String inputFile = args[0];

		// now get the resources
          int numOfResources = args.length-1; 

		// the initial number of resources
		int[] initialResources= new int[numOfResources];

		// the resources involved in the transaction
		int[] resources= new int[numOfResources];
		for (int i = 0; i < numOfResources; i++)
			initialResources[i] = Integer.parseInt(args[i+1].trim());

		// create the bank
		Bank theBank = new BankImpl(initialResources);
          int[] maxDemand = new int[numOfResources];

		// read initial values for maximum array 
		String line;
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(inputFile));

			int threadNum = 0;
               int resourceNum = 0;

                   while((line = inFile.readLine()) != null){
               	StringTokenizer tokens = new StringTokenizer(line,",");
               	while (tokens.hasMoreTokens()) {
               		int amt = Integer.parseInt(tokens.nextToken().trim());
               		maxDemand[resourceNum++] = amt;
               	}

               	theBank.addCustomer(threadNum,maxDemand);
               	++threadNum;
               	resourceNum = 0;
               }
          }
		catch (FileNotFoundException fnfe) {
			throw new Error("Unable to find file " + inputFile);
		}
		catch (IOException ioe) {
			throw new Error("Error processing " + inputFile);
		}
                
          // now loop reading requests
                
          BufferedReader cl = new BufferedReader(new InputStreamReader(System.in));
          int[] requests = new int[numOfResources];
          String requestLine;

          while ( (requestLine = cl.readLine()) != null) {
			if (requestLine.equals(""))
				continue;

               if (requestLine.equals("*"))
				// output the state
               	theBank.getState();
               else {
               	// we know that we are reading N items on the command line
                    // [RQ || RL] <customer number> <resource #1> <#2> <#3>
                    StringTokenizer tokens = new StringTokenizer(requestLine);

				// get transaction type - request (RQ) or release (RL)
				String trans = tokens.nextToken().trim();

				// get the customer number making the tranaction
                    int custNum = Integer.parseInt(tokens.nextToken().trim());

				// get the resources involved in the transaction
				for (int i = 0; i < numOfResources; i++) {
                            resources[i] = Integer.parseInt(tokens.nextToken().trim());
                            System.out.println("*"+resources[i]+"*");
				}

				// now check the transaction type
				if (trans.equals("RQ")) {  // request
                        	if (theBank.requestResources(custNum,resources))
                         	System.out.println("Approved");
                        	else
                             System.out.println("Denied");
				}
				else if (trans.equals("RL")) // release
					theBank.releaseResources(custNum, resources);
				else // illegal request
					System.err.println("Must be either 'RQ' or 'RL'");
                    }
                }
        }
}
