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
            int f_num;
            int value;
            int phy_addr;

            TLB tlb = new TLB();
            PageTable pt = new PageTable();
            PhysicalMemory pm = new PhysicalMemory();
            BackStore bs = new BackStore();

            while(sc.hasNextInt()){
                addr = sc.nextInt();
                // 2^16 = 4^8 = 16^4
                // mask the high 16bit
                addr = addr % 65536;
                offset = addr % 256;
                p_num = addr / 256;

                f_num = -1;
                f_num = tlb.get(p_num);
                if(f_num == -1){
                    // frame not in TLB
                    // try page table
                    f_num = pt.get(p_num);
                    if(f_num == -1){
                        // fraem not in page table
                        // read frame from BackStore
                        Frame f = new Frame(bs.getData(p_num));

                        // add frame to PhysicalMemory
                        f_num = pm.addFrame(f);
                        pt.add(f_num, p_num);
                    }
                }

                phy_addr = f_num * 256 + offset;
                value = pm.getValue(f_num, offset);

				System.out.println(
                    String.format("Virtual address: %s Physical address: %s Value: %s", addr, phy_addr , value)
                );
            }
		} catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
	}
}
