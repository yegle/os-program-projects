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
                ClientConnection connection = s.getConnection();

                MessageImpl msg = connection.getMessage();

                connection.reply(s.process(msg));
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

    /**
     * get ClientConnection handler
     *
     * @return new instance of ClientConnection
     */ 
    protected ClientConnection getConnection() throws Exception {
        Socket clientSocket = this.sock.accept();
        InetAddress address = clientSocket.getInetAddress();
        System.err.println("Connect from [" + address.getHostAddress() + "]");
        return new ClientConnection(clientSocket);
    }

    /**
     * process the Message instance 
     *
     * @param msg instance of MessageImpl to be processed
     * @return instance of MessageImpl that is processed
     */
    protected MessageImpl process(MessageImpl msg) throws Exception {
        msg.setCounts();
        return msg;
    }

}

/**
 * represents a Client's connection
 */
class ClientConnection{
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

    /**
     * get MessageImpl instance from this ClientConnection instance
     *
     * @return MessageImpl instance
     */
    protected MessageImpl getMessage() throws Exception {
        return (MessageImpl) this.rcvFrom.readObject();
    }

    /**
     * reply MessageImpl instance to Client
     * @param msg MessageImpl instance
     */
    protected void reply(MessageImpl msg) throws Exception {
        this.sendTo.writeObject(msg);
    }
}
