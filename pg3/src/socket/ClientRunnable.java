import java.net.*;
import java.io.*;

public class ClientRunnable implements Runnable
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
	
	protected Client c;

	protected int index;


	public void run(){
		try{
			c = new Client();
			c.connect("127.0.0.1", 4328);
			
			System.err.println("Client " + index + " started");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgstr = br.readLine();
            MessageImpl msg = new MessageImpl(msgstr);

            c.send(msg);
            MessageImpl result = c.read();

            System.out.println("Client " + index + ": Result received: CharacterCount=" + result.getCharacterCount() + ", DigitCount=" + result.getDigitCount());
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
	}

    public ClientRunnable(int index){
		this.index = index;
    }
	/**
	 * Main function 
 	 *
	 * Set up the ip and port for a new client
	 * Send request and read response from Service
 	 */


	public static void main(String[] args){
		try{
            for(int i=1; i<=10; i++){
				ClientRunnable c = new ClientRunnable(i);
				Thread t = new Thread(c);
				(new Thread(c)).start();
			}
		} catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
		}
	}

    /**
     * Set up connection configuration
     * 
     * @param ip String ip address of server side
     * @param port String port used to make connection
     */
    /*protected void connect(String ip, int port) throws Exception {
        this.sock = new Socket(ip,port);
        this.out = new ObjectOutputStream(this.sock.getOutputStream());
        this.in = new ObjectInputStream(this.sock.getInputStream());
    }*/

    /**
     * Send function
     * Write the message into object and send it
     *
     * @param msg the message to be sent
     */
    /*protected void send(MessageImpl msg) throws Exception {
        this.out.writeObject(msg);
    }*/
    
    /**
     * Read function
     * Read the message from Object
     *
     * @return the message read from the Object
     */
    /*protected MessageImpl read() throws Exception {
        return (MessageImpl) this.in.readObject();
    }*/
}
