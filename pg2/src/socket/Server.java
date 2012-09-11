import java.net.*;
import java.io.*;
import java.net.URLEncoder;

public class Server
{
    protected ServerSocket sock;

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

    protected void start(int port) throws Exception {
        this.sock = new ServerSocket(port);
    }

    protected ClientConnection getConnection() throws Exception {
        Socket clientSocket = this.sock.accept();
        InetAddress address = clientSocket.getInetAddress();
        System.err.println("Connect from [" + address.getHostAddress() + "]");
        return new ClientConnection(clientSocket);
    }

    protected MessageImpl process(MessageImpl msg) throws Exception {
        msg.setCounts();
        return msg;
    }

}

class ClientConnection{
    protected ObjectInputStream rcvFrom;
    protected ObjectOutputStream sendTo;

    public ClientConnection(Socket s) throws Exception {
        this.rcvFrom = new ObjectInputStream(s.getInputStream());
        this.sendTo = new ObjectOutputStream(s.getOutputStream());
    }

    protected MessageImpl getMessage() throws Exception {
        return (MessageImpl) this.rcvFrom.readObject();
    }
    protected void reply(MessageImpl msg) throws Exception {
        this.sendTo.writeObject(msg);
    }
}
