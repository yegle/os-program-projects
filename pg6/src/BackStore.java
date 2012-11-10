import java.io.RandomAccessFile;
import java.io.*;

public class BackStore{

	/**
	 * function of getDate
	 * 
	 * @param pageNum	page number
	 */
	public static int[] getData(int pageNum){
		byte[] value = new byte[256];
        int[] result = new int[256];
		File fileName;
		RandomAccessFile disk = null;
		try{
			fileName = new File("BACKING_STORE");
		    disk = new RandomAccessFile(fileName, "r");

            // seek in byte
			disk.seek(pageNum*256);
            disk.read(value);

			disk.close();
		}
		catch (IOException e) {
			System.err.println ("Unable to start the disk");
			System.exit(1);
		}
        for(int i=0; i<256; i++){
            result[i] = value[i];
        }
        return result;
	}

	public static void main(String args[]){
		int[] i = new int[32];
		System.arraycopy(getData(100),0,i,0,32);
		for(int j=0;j<32;j++)
			System.out.println(i[j]);

		System.arraycopy(getData(255),0,i,0,32);
		for(int j=0;j<32;j++)
			System.out.println(i[j]);
	}
}
