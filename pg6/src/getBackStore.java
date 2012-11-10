import java.io.RandomAccessFile;
import java.io.*;

public class getBackStore{


	public static int getFrameNum(int pagenum){
		int framenum=0;
		File fileName;
		RandomAccessFile disk = null;
		try{
			fileName = new File("BACKING_STORE");
		    disk = new RandomAccessFile(fileName, "r");

			disk.seek(pagenum);

			for (int i = 0; i < 4; i++) {
				framenum = framenum<<4;			
				framenum += disk.read();
				System.out.println(framenum);
			}

			disk.close();			
		}
		catch (IOException e) {
			System.err.println ("Unable to start the disk");
			System.exit(1);
		}			
	
		return framenum;
	}

	public static void main(String args[]){
		int i;
		i=getFrameNum(100);
		System.out.println(i);

		i=getFrameNum(255);
		System.out.println(i);
	}
}
