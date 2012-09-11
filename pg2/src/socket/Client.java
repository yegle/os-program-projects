import java.net.*;
import java.io.*;

public class Client
{
	public static void main(String[] args){
		try{
			Socket sock = new Socket("127.0.0.1",6013);
			
			InputStream in = sock.getInputStream();
			BufferedReader bin = new
				BufferedReader(new InputStreamReader(in));
			String line;
			while((line=bin.readLine())!=null)
				System.out.println(line);
	
			//sock.setSendBufferSize(100);
			PrintWriter bout = new 
				PrintWriter(sock.getOutputStream());
			BufferedReader wt=new BufferedReader(new
				InputStreamReader(System.in));
			
			while(true){
				System.out.println("do while"); //这里有问题 while没有进来
				String s=wt.readLine();
				System.out.println(s);
				bout.println(line);
				bout.flush();
				if(line.equals("end"))
					break;
				System.out.println(bin.readLine());
			}
				
			//bout.println("client:hello");
			//System.out.println("sent");
			
			sock.close();
			
		}
		catch(IOException ioe){
			System.err.println(ioe);
		}
	}
}
