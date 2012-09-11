import java.net.*;
import java.io.*;

public class Server
{
    protected ServerSocket sock;

	public static void main(String[] args){
		try{
            Server s = new Server();
            s.start(4328);

            while(true){
                ClientConnection connection = s.getConnection();
                String msg = connection.getMessage();

                connection.reply(s.process(msg));
            }
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
	}

    protected void start(int port) throws Exception {
        this.sock = new ServerSocket(port);
    }

    protected ClientConnection getConnection() throws Exception {
        Socket clientSocket = this.sock.accept();
        return new ClientConnection(clientSocket);
    }

    protected String process(String msg){
        return "we get it";
    }

}

class ClientConnection{
    protected BufferedReader rcvFrom;
    protected PrintWriter sendTo;

    public ClientConnection(Socket s) throws Exception {
        this.rcvFrom = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.sendTo = new PrintWriter(s.getOutputStream(), true);
    }

    protected String getMessage() throws Exception {
        System.err.println("We received something!");
        return this.rcvFrom.readLine();
    }
    protected void reply(String response){
        this.sendTo.println(response);
    }
}
