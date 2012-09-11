import java.net.*;
import java.io.*;

public class Client
{
    protected Socket sock = null;
    protected PrintWriter out = null;
    protected BufferedReader in = null;

	public static void main(String[] args){
        Client c = new Client();
		try{
            c.connect("127.0.0.1", 6013);
            c.send("test");
            System.out.println(c.read());
            /*

			
			InputStream in = sock.getInputStream();
			BufferedReader bin = new
				BufferedReader(new InputStreamReader(in));
			String line;
			while((line=bin.readLine())!=null)
				System.out.println(line);
	
			//sock.setSendBufferSize(100);
            PrintWriter bout = new 
                PrintWriter(sock.getOutputStream());
            BufferedReader wt = new BufferedReader(new
                    InputStreamReader(System.in));
			
			while(true){
				System.out.println("do while");
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
            */
			
		}
		catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
	}

    protected void connect(String ip, int port) throws Exception {
        this.sock = new Socket(ip,port);
        this.out = new PrintWriter(this.sock.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        return;
    }

    protected void send(String msg){
        this.out.println(msg);
    }
    
    protected String read() throws Exception {
        return this.in.readLine();
    }
}
