import java.net.*;
import java.io.*;

/**
 * <h2>HW #2 for Operation System</h2>
 *
 * Demo of Client-Service using socket
 *
 * @author Yuchen Ying, Yue Yin
 * @version $Revision$, $Date$
 */



public class Client
{
    /**
     * A new Socket instance
     */
    protected Socket sock = null;

    /**
     * A new instance for OutputStream
     */
    protected ObjectOutputStream out = null;

    /**
     * A new instance for InputStream
     */
    protected ObjectInputStream in = null;

	/**
	 * Main function 
 	 *
	 * Set up the ip and port for a new client
	 * Send request and read response from Service
 	 */
	public static void main(String[] args){
        Client c = new Client();
		try{
            c.connect("127.0.0.1", 4328);
            System.err.println("Enter the string to be processed");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgstr = br.readLine();
            MessageImpl msg = new MessageImpl(msgstr);

            c.send(msg);
            MessageImpl result = c.read();

            System.out.println("Result received: CharacterCount=" + result.getCharacterCount() + ", DigitCount=" + result.getDigitCount());
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
	}

    /**
     * Set up connection configuration
     * 
     * @param ip,port  the ip and port used to make connection
     */
    protected void connect(String ip, int port) throws Exception {
        this.sock = new Socket(ip,port);
        this.out = new ObjectOutputStream(this.sock.getOutputStream());
        this.in = new ObjectInputStream(this.sock.getInputStream());
    }

    /**
     * Send function
     * Write the message into object and send it
     *
     * @param msg the message to be sent
     */
    protected void send(MessageImpl msg) throws Exception {
        this.out.writeObject(msg);
    }
    
    /**
     * Read function
     * Read the message from Object
     *
     * @return this.in.readObject() the message read from the Object
     */
    protected MessageImpl read() throws Exception {
        return (MessageImpl) this.in.readObject();
    }
}
