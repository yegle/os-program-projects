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
            c.connect("127.0.0.1", 4328);
            c.send("test");
            System.out.println(c.read());
			
		} catch(Exception e){
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
