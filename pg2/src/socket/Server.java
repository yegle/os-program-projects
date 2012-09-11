import java.net.*;
import java.io.*;

public class Server
{
	public static void main(String[] args){
		try{
			ServerSocket sock = new ServerSocket(6013);

			while(true){
				Socket client = sock.accept();
				System.out.println("Hello");

				BufferedReader in=new BufferedReader(new 
					InputStreamReader(client.getInputStream()));
                		PrintWriter out = new PrintWriter(client.getOutputStream(),true);
				
				//pout.println(new java.util.Date().toString());
				out.println("Server:hello");
				int i=5;
				while(i>0){
					String line=in.readLine();
					System.out.println("reveived message is:"+line);
					out.println("received");
					out.flush();
					//if(line.equals("end"))
					//	break;
					i--;
				}
				

				//InputStream in = client.getInputStream();
				//BufferedReader bin = new
				//BufferedReader(new InputStreamReader(in));

                                
				//String line=null;
                                //while((line=bin.readLine())!=null)
                                //        System.out.println(line);
				client.close();
			}
		}
		catch(IOException ioe){
			System.err.println(ioe);
		}
	}
}
