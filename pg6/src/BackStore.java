import java.io.RandomAccessFile;
import java.io.*;

public class BackStore{


	public static int[] getData(int pageNum){
		int[] value= new int[32];
		File fileName;
		RandomAccessFile disk = null;
		try{
			fileName = new File("BACKING_STORE");
		    disk = new RandomAccessFile(fileName, "r");

			disk.seek(pageNum*256);

			for (int i = 0; i < 32; i++) {
				value[i] = disk.read();
			}

			disk.close();			
		}
		catch (IOException e) {
			System.err.println ("Unable to start the disk");
			System.exit(1);
		}			
	
		return value;
	}

	public static void main(String args[]){
		int[] i = new int[32];
		System.arraycopy(getData(100),0,i,0,32);
		for(int j=0;j<32;j++)
			System.out.println(i[j]);
	}
}
