import java.net.*;
import java.io.*;

/**
 * Serverside class
 *
 */
public class Server
{
    /**
     * internal variable holds the ServerSocket
     */
    protected ServerSocket sock;

    /**
     *
     * main method
     *
     * Create a Server instance and listen on port 4328, then handle request
     *
     */
	public static void main(String[] args){
		try{
            Server s = new Server();
            s.start(4328);

            while(true){

				Socket clientSocket = s.sock.accept();	
				InetAddress address = clientSocket.getInetAddress();
				System.err.println("Connect from [" + address.getHostAddress() + "]");

				ClientConnection connection = new ClientConnection(clientSocket);

				Thread connectiont = new Thread(connection);

				connectiont.start();
            }
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
	}

    /**
     * open the Server socket 
     *
     * @param port port number of this socket
     *
     */
    protected void start(int port) throws Exception {
        this.sock = new ServerSocket(port);
    }

}

/**
 * represents a Client's connection
 */
class ClientConnection implements Runnable{
    /**
     * internal variable holds the ObjectInputStream object
     */
    protected ObjectInputStream rcvFrom;
    /**
     * internal variable holds the ObjectOutStream object
     */
    protected ObjectOutputStream sendTo;

    /**
     * constructor of ClientConnection class
     *
     * @param s a Socket instance
     */
    public ClientConnection(Socket s) throws Exception {
        this.rcvFrom = new ObjectInputStream(s.getInputStream());
        this.sendTo = new ObjectOutputStream(s.getOutputStream());
    }

	public void run()
	{
		try{
		MessageImpl msg = (MessageImpl) this.rcvFrom.readObject();
		msg.setCounts();
		this.sendTo.writeObject(msg);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
