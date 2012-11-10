import java.io.*;
import java.util.*;

public class AddressTranslator {

	public static void main(String[] args){
		//String inputFile = args[0];
		String inputFile = "InputFile.txt";

		/**
		 * variable of logical address
		 */
        int addr;

		/**
		 * variable of page number
		 */
        int p_num;

		/**
		 * variable of offset
		 */
        int offset;

		/**
		 * variable of frame number
		 */
        int f_num;

		/**
		 * variable of value stored in address
		 */
        int value;

		/**
		 * variable of physics address
		 */
        int phy_addr;

		/**
		 * variable of count of tlb miss
		 */
        int tlb_miss = 0;

		/**
		 * variable of count of page fault
		 */
        int page_fault = 0;


		try{
            Scanner sc = new Scanner(new File(inputFile));

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
                    tlb_miss++;
                    // frame not in TLB
                    // try page table
                    f_num = pt.get(p_num);
                    if(f_num == -1){
                        page_fault++;
                        // fraem not in page table
                        // read frame from BackStore
                        Frame f = new Frame(bs.getData(p_num));

                        // add frame to PhysicalMemory
                        f_num = pm.addFrame(f);
                        pt.add(p_num, f_num);
                        tlb.put(p_num, f_num);
                    }
                }

                phy_addr = f_num * 256 + offset;
                value = pm.getValue(f_num, offset);

				System.out.println(
                    String.format("Virtual address: %s Physical address: %s Value: %s", addr, phy_addr , value)
                );
            }
            System.out.println(String.format("TLB miss: %s, Page Fault: %s", tlb_miss, page_fault));
		} catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
	}
}
