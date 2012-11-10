import java.io.*;
import java.util.*;

public class AddressTranslator {

	public static void main(String[] args){
		//String inputFile = args[0];
		String inputFile = "InputFile.txt";

		try{
            Scanner sc = new Scanner(new File(inputFile));

			int addr;
            int p_num;
            int offset;
            int phy_addr;

            while(sc.hasNextInt()){
                addr = sc.nextInt();
                System.out.println(addr);
                // 2^16 = 4^8 = 16^4
                // mask the high 16bit
                addr = addr % 65536;
                offset = addr % 256;
                p_num = addr / 256;


				System.out.println(
                    String.format("%s, %s, %s", addr, offset, p_num)
                );
            }
		} catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
	}
}
