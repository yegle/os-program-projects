import java.io.RandomAccessFile;
import java.io.*;

public class BackStore{


	public static int getData(int pageNum){
		int value=0;
		File fileName;
		RandomAccessFile disk = null;
		try{
			fileName = new File("BACKING_STORE");
		    disk = new RandomAccessFile(fileName, "r");

			disk.seek(pageNum);

			for (int i = 0; i < 4; i++) {
				value = value<<8;			
				value += disk.read();
				System.out.println(value);
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
		int i;
		i=getData(100);
		System.out.println(i);

		i=getData(255);
		System.out.println(i);
	}
}
