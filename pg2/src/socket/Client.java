import java.net.*;
import java.io.*;

public class Client
{
    protected Socket sock = null;
    protected ObjectOutputStream out = null;
    protected ObjectInputStream in = null;

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

    protected void connect(String ip, int port) throws Exception {
        this.sock = new Socket(ip,port);
        this.out = new ObjectOutputStream(this.sock.getOutputStream());
        this.in = new ObjectInputStream(this.sock.getInputStream());
    }

    protected void send(MessageImpl msg) throws Exception {
        this.out.writeObject(msg);
    }
    
    protected MessageImpl read() throws Exception {
        return (MessageImpl) this.in.readObject();
    }
}
